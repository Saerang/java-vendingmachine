package domain;

import app.enumerate.Coin;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;

class SmallChangeTest {

    @Test
    void create() {
        // given
        PriorityQueue<Coin> coins = new PriorityQueue<>((o1, o2) -> o2.getPrice() - o1.getPrice());
        SmallChange smallChange = SmallChange.create(coins);

        // when
        // then
        assertThat(smallChange).isNotNull();
    }

    @Test
    void getTotal() {
        // given
        PriorityQueue<Coin> coins = new PriorityQueue<>((o1, o2) -> o2.getPrice() - o1.getPrice());
        SmallChange smallChange = SmallChange.create(coins);

        // when
        smallChange.add(Coin.TEN);
        smallChange.add(Coin.FIFTY);

        // then
        assertThat(smallChange.getTotal()).isEqualTo(60);
    }

    @Test
    void add_and_poll() {
        // given
        PriorityQueue<Coin> coins = new PriorityQueue<>((o1, o2) -> o2.getPrice() - o1.getPrice());
        SmallChange smallChange = SmallChange.create(coins);

        // when
        smallChange.add(Coin.TEN);
        smallChange.add(Coin.FIFTY);

        // then
        assertThat(smallChange.poll()).isEqualTo(Coin.FIFTY);
    }

    @Test
    void returnSmallChange_money_greaterThanOrEqualTo() {
        // given
        PriorityQueue<Coin> coins = new PriorityQueue<>((o1, o2) -> o2.getPrice() - o1.getPrice());
        SmallChange smallChange = SmallChange.create(coins);
        smallChange.add(Coin.TEN);
        smallChange.add(Coin.FIFTY);
        smallChange.add(Coin.HUNDRED);

        // when
        Map<Coin, Integer> returnSmallChange = smallChange.returnSmallChange(150);

        // then
        assertThat(returnSmallChange).hasSize(2);
        assertThat(returnSmallChange).containsKeys(Coin.HUNDRED, Coin.FIFTY);
        assertThat(returnSmallChange).containsValues(1);
        assertThat(returnSmallChange).doesNotContainKeys(Coin.TEN);
    }

    @Test
    void returnSmallChange_money_LessThan() {
        // given
        PriorityQueue<Coin> coins = new PriorityQueue<>((o1, o2) -> o2.getPrice() - o1.getPrice());
        SmallChange smallChange = SmallChange.create(coins);
        smallChange.add(Coin.TEN);
        smallChange.add(Coin.FIFTY);
        smallChange.add(Coin.HUNDRED);

        // when
        Map<Coin, Integer> returnSmallChange = smallChange.returnSmallChange(300);

        // then
        assertThat(returnSmallChange).hasSize(3);
        assertThat(returnSmallChange).containsKeys(Coin.HUNDRED, Coin.FIFTY, Coin.TEN);
        assertThat(returnSmallChange).containsValues(1);
    }
}
