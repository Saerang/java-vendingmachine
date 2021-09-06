package app.exception;

public class MoneyNotEnoughException extends RuntimeException {

    public MoneyNotEnoughException(String itemName, int price, int money) {
        this(itemName + "을 구매하는데 돈이 부족합니다. 아이템가격: " + price + ", 가지고 있는 금액:" + money);
    }

    public MoneyNotEnoughException(String message) {
        super(message);
    }

}
