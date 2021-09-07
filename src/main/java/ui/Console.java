package ui;

import app.ItemService;
import app.SmallChangeService;
import app.enumerate.Coin;
import domain.Item;
import domain.SmallChange;
import domain.VendingMachine;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Console {

    public VendingMachine initVendingMachine() {
        SmallChangeService smallChangeService = new SmallChangeService();
        ItemService itemService = new ItemService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        // 450
        int change = scanner.nextInt();
        System.out.println();

        SmallChange smallChange = smallChangeService.getSmallChangeWithRandomCoins(change);

        System.out.println("상품명과 수량, 금액을 입력해 주세요.");
        scanner.nextLine();
        // [콜라,20,1500];[사이다,10,1000]
        String inputItem = scanner.nextLine();
        System.out.println();

        List<Item> items = itemService.getItems(inputItem);

        return new VendingMachine(smallChange, items);
    }

    public int buyItemsFromVendingMachine(VendingMachine vendingMachine) {
        System.out.println("투입 금액을 입력해 주세요.");
        Scanner scanner = new Scanner(System.in);
        int inputMoney = scanner.nextInt();
        scanner.nextLine();
        System.out.println();

        while (vendingMachine.isBuyItem(inputMoney)) {
            System.out.println("남은 금액: " + inputMoney + "원");
            System.out.println("구매할 상품명을 입력해 주세요.");
            String buyItem = scanner.nextLine();
            System.out.println();

            inputMoney = vendingMachine.buyItem(buyItem, inputMoney);
        }

        return inputMoney;
    }

    public void returnSmallChange(SmallChange smallChange, int money) {
        Map<Coin, Integer> returnSmallChange = smallChange.returnSmallChange(money);

        System.out.println("남은 금액: " + (money - this.returnTotalMoney(returnSmallChange)) + "원");
        System.out.println("잔돈");

        for (Coin coin : returnSmallChange.keySet()) {
            System.out.println(coin.getPrice() + "원 - " + returnSmallChange.get(coin) + "개");
        }
    }

    protected int returnTotalMoney(Map<Coin, Integer> returnSmallChange) {
        return returnSmallChange.entrySet().stream()
                .mapToInt(coinMap -> coinMap.getKey().getPrice() * coinMap.getValue())
                .sum();
    }

}
