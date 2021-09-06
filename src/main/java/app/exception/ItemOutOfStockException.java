package app.exception;

public class ItemOutOfStockException extends RuntimeException{
    public ItemOutOfStockException(String itemName) {
        super(itemName + " 품절되었습니다.");
    }
}
