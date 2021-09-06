package app;

import app.enumerate.Coin;
import com.woowahan.techcourse.utils.Randoms;
import domain.SmallChange;

import java.util.PriorityQueue;

public class SmallChangeService {

    public SmallChange getSmallChange(int change) {
        PriorityQueue<Coin> coins = new PriorityQueue<>((o1, o2) -> o2.getPrice() - o1.getPrice());
        SmallChange smallChange = SmallChange.create(coins);

        while (change > 0) {
            int pick = Randoms.pick(Coin.getCoinPricesLessThanOrEqualTo(change));
            smallChange.add(Coin.getCoin(pick));
            change -= pick;
        }

        return smallChange;
    }
}
