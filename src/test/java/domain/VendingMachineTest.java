package domain;

import app.enumerate.Coin;
import app.exception.ItemNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class VendingMachineTest {

    @Test
    void getItem() {
        // given
        Item item = Item.started("콜라,10,1000");
        SmallChange smallChange = getSmallChange();
        VendingMachine vendingMachine = new VendingMachine(smallChange, Collections.singletonList(item));

        // when
        Item findItem1 = vendingMachine.getItem("콜라");

        // then
        assertThat(item).isEqualTo(findItem1);
    }



    @Test
    void getItem_item_not_found() {
        // given
        Item item = Item.started("콜라,10,1000");

        SmallChange smallChange = getSmallChange();

        VendingMachine vendingMachine = new VendingMachine(smallChange, Collections.singletonList(item));

        // when
        // then
        assertThatThrownBy(() -> vendingMachine.getItem("환타")).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void isBuyItem() {
        // given
        Item item1 = Item.started("콜라,10,1000");
        Item item2 = Item.started("사이다,10,1500");
        SmallChange smallChange = getSmallChange();
        VendingMachine vendingMachine = new VendingMachine(smallChange, Arrays.asList(item1, item2));

        // when
        boolean isBuyItem = vendingMachine.isBuyItem(1000);

        // then
        assertThat(isBuyItem).isEqualTo(true);
    }

    @Test
    void isBuyItem_money_not_enough() {
        // given
        Item item1 = Item.started("콜라,10,1000");
        Item item2 = Item.started("사이다,10,1500");
        SmallChange smallChange = getSmallChange();
        VendingMachine vendingMachine = new VendingMachine(smallChange, Arrays.asList(item1, item2));

        // when
        boolean isBuyItem = vendingMachine.isBuyItem(500);

        // then
        assertThat(isBuyItem).isEqualTo(false);
    }

    @Test
    void getMinItemPrice() {
        // given
        Item item1 = Item.started("콜라,10,1000");
        Item item2 = Item.started("사이다,10,1500");
        SmallChange smallChange = getSmallChange();
        VendingMachine vendingMachine = new VendingMachine(smallChange, Arrays.asList(item1, item2));

        // when
        int minItemPrice = vendingMachine.getMinItemPrice();

        // then
        assertThat(minItemPrice).isEqualTo(1000);
    }

    @Test
    void getMinItemPrice_item_not_found() {
        // given
        SmallChange smallChange = getSmallChange();
        VendingMachine vendingMachine = new VendingMachine(smallChange, Collections.emptyList());

        // when
        // then
        assertThatThrownBy(vendingMachine::getMinItemPrice).isInstanceOf(ItemNotFoundException.class);
    }

    private SmallChange getSmallChange() {
        PriorityQueue<Coin> coins = new PriorityQueue<>((o1, o2) -> o2.getPrice() - o1.getPrice());
        SmallChange smallChange = SmallChange.create(coins);
        smallChange.add(Coin.TEN);
        smallChange.add(Coin.FIFTY);
        return smallChange;
    }
}
