package domain;

import app.exception.ItemOutOfStockException;
import app.exception.MoneyNotEnoughException;
import common.Constant;

import java.util.Objects;

public class Item {

    private final String name;
    private final int price;
    private int count;

    public static Item started(String strItem) {
        String[] items = strItem.split(",");

        return new Item(items[0], Integer.parseInt(items[1]), Integer.parseInt(items[2]));
    }

    private Item(String name, int count, int price) {
        validate(price);

        this.name = name;
        this.count = count;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getPrice() {
        return price;
    }

    private void validate(int price) {
        if(price < Constant.ITEM_MIN_PRICE) {
            throw new IllegalArgumentException("상품의 최소 금액은 " + Constant.ITEM_MIN_PRICE + "원 입니다.");
        }

        if(price % Constant.ITEM_PRICE_UNIT != 0) {
            throw new IllegalArgumentException("상품의 금액은 " + Constant.ITEM_PRICE_UNIT + "원 단위입니다.");
        }
    }

    public int buyItem(int money) {
        if (count == 0) {
            throw new ItemOutOfStockException(this.name);
        }

        if (money < price) {
            throw new MoneyNotEnoughException(this.name, this.price, money);
        }

        this.count--;

        return money - this.price;
    }

    public boolean isBuyItem(int money) {
        return count > 0 && money >= price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getCount() == item.getCount() && getPrice() == item.getPrice() && Objects.equals(getName(), item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCount(), getPrice());
    }

    @Override
    public String toString() {
        return "domain.Item{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
