package domain;

import app.exception.ItemOutOfStockException;
import app.exception.MoneyNotEnoughException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ItemTest {

    @Test
    void create_staticItem() {
        // given
        // when
        Item item = Item.started("콜라,10,1000");

        // then
        assertThat(item.getName()).isEqualTo("콜라");
        assertThat(item.getCount()).isEqualTo(10);
        assertThat(item.getPrice()).isEqualTo(1000);
    }

    @Test
    void buyItem() {
        // given
        Item item = Item.started("콜라,10,1000");

        // when
        int balance = item.buyItem(1500);

        // then
        assertThat(balance).isEqualTo(500);
    }
    
    @Test 
    void buyItem_out_of_stock() {
        // given
        Item item = Item.started("콜라,0,1000");

        // when
        // then
        assertThatThrownBy(() -> item.buyItem(1500))
                .isInstanceOf(ItemOutOfStockException.class);
    }

    @Test
    void buyItem_not_enough_money() {
        // given
        Item item = Item.started("콜라,10,1000");

        // when
        // then
        assertThatThrownBy(() -> item.buyItem(900))
                .isInstanceOf(MoneyNotEnoughException.class);
    }

    @Test
    void isBuyItem() {
        // given
        Item item = Item.started("콜라,10,1000");

        // when
        boolean isBuyItem = item.isBuyItem(1000);

        // then
        assertThat(isBuyItem).isEqualTo(true);
    }

    @Test
    void isBuyItem_out_of_stock() {
        // given
        Item item = Item.started("콜라,0,1000");

        // when
        boolean isBuyItem = item.isBuyItem(1000);

        // then
        assertThat(isBuyItem).isEqualTo(false);
    }

    @Test
    void isBuyItem_not_enough_money() {
        // given
        Item item = Item.started("콜라,10,1000");

        // when
        boolean isBuyItem = item.isBuyItem(900);

        // then
        assertThat(isBuyItem).isEqualTo(false);
    }
}
