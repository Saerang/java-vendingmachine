package domain;

import app.exception.ItemNotFoundException;

import java.util.List;

public class VendingMachine {
    private final SmallChange smallChange;
    private final List<Item> items;

    public VendingMachine(SmallChange smallChange, List<Item> items) {
        this.smallChange = smallChange;
        this.items = items;
    }

    public SmallChange getSmallChange() {
        return this.smallChange;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public int buyItem(String itemName, int money) {
        return getItem(itemName).buyItem(money);
    }

    public Item getItem(String itemName) {
        return this.items.stream()
                .filter(item -> item.getName().equals(itemName)).findAny()
                .orElseThrow(() -> new ItemNotFoundException(itemName + " 찾을 수 없습니다."));
    }

    public boolean isBuyItem(int money) {
        return money >= this.getMinItemPrice() || items.stream().allMatch(item -> item.isBuyItem(money));
    }

    public int getMinItemPrice() {
        if (this.items.isEmpty()) {
            throw new ItemNotFoundException("Item 이 존재하지 않습니다.");
        }

        return this.items.stream().map(Item::getPrice).mapToInt(i -> i).min().getAsInt();
    }


    @Override
    public String toString() {
        return "VendingMachine{" +
                "smallChange=" + smallChange +
                ", items=" + items +
                '}';
    }
}
