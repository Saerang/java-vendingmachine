package domain;

import app.enumerate.Coin;

import java.util.*;

public class SmallChange {
    private final PriorityQueue<Coin> coins;
    private final Map<Coin, Integer> coinCountMap;
    private int total;

    public static SmallChange create(PriorityQueue<Coin> coins) {
        return new SmallChange(coins, new HashMap<>(), 0);
    }

    public SmallChange(PriorityQueue<Coin> coins, Map<Coin, Integer> coinCountMap, int total) {
        this.coins = coins;
        this.coinCountMap = coinCountMap;
        this.total = total;
    }

    public Collection<Coin> getCoins() {
        return coins;
    }

    public int getTotal() {
        return total;
    }

    public void add(Coin coin) {
        coins.add(coin);
        coinCountMap.put(coin, coinCountMap.getOrDefault(coin, 0) + 1);
        total += coin.getPrice();
    }

    public Coin poll() {
        Coin coin = coins.poll();
        if (coin == null) {
            throw new IllegalStateException("잔돈이 존재하지 않습니다.");
        }

        total -= coin.getPrice();
        return coin;
    }

    public Map<Coin, Integer> returnSmallChange(int money) {
        if(this.total < money) {
            return this.coinCountMap;
        }

        Map<Coin, Integer> smallChangeMap = new HashMap<>();
        Coin coin = this.poll();

        while (money >= coin.getPrice() ) {
            smallChangeMap.put(coin, smallChangeMap.getOrDefault(coin, 0) + 1);
            money -= coin.getPrice();
            coin = this.poll();
        }

        return smallChangeMap;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmallChange that = (SmallChange) o;
        return getTotal() == that.getTotal() && Objects.equals(getCoins(), that.getCoins());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCoins(), getTotal());
    }

    @Override
    public String toString() {
        return "SmallChange{" +
                "coins=" + coins +
                ", total=" + total +
                '}';
    }
}
