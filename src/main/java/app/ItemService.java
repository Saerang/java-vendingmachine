package app;

import domain.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemService {

    private List<Item> items = new ArrayList<>();

    public List<Item> getItems(String inputItem) {
        String[] items = inputItem.split(";");

        for (String s : items) {
            String replaceItem = s.replace("[", "").replace("]", "");
            this.items.add(Item.started(replaceItem));
        }

        return this.items;
    }

}
