package app;

import domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.PriorityQueue;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ItemServiceTest {
    ItemService itemService;

    @BeforeEach
    void setUp() {
        itemService = new ItemService();
    }

    @Test
    void getItems() {
        // given
        String item = "[콜라,20,1500];[사이다,10,1000]";

        // when
        List<Item> items = itemService.getItems(item);

        // then
        assertThat(items).extracting("name").contains("콜라", "사이다");
        assertThat(items).extracting("count").contains(10, 20);
        assertThat(items).extracting("price").contains(1500, 1000);
    }

}