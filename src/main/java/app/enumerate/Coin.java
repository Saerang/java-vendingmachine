package app.enumerate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Coin {
    TEN(10),
    FIFTY(50),
    HUNDRED(100),
    FIVE_HUNDRED(500);

    private final int price;

    Coin(int price) {
        this.price = price;
    }

    public static Coin getCoin(int price) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.getPrice() == price)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(price + " 를 찾을 수 없습니다."));
    }

    public static List<Integer> getCoinPricesLessThanOrEqualTo(int price) {
        List<Integer> result = Arrays.asList(TEN.price, FIFTY.price, HUNDRED.price, FIVE_HUNDRED.price);
        return result.stream().filter(i -> i <= price).collect(Collectors.toList());
    }

    public int getPrice() {
        return price;
    }
}
