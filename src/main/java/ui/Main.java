package ui;

import domain.VendingMachine;

public class Main {
    public static void main(String[] args) {

        Console console = new Console();
        VendingMachine vendingMachine = console.initVendingMachine();

        int money = console.buyItemsFromVendingMachine(vendingMachine);

        console.returnSmallChange(vendingMachine.getSmallChange(), money);
    }
}
