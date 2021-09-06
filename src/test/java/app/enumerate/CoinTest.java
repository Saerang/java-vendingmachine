package app.enumerate;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CoinTest {

    @Test
    void getCoin() {
        // given
        int price = 10;

        // when
        Coin coin = Coin.getCoin(price);

        // then
        assertThat(coin).isEqualTo(Coin.TEN);
    }

    @Test
    void getCoinsLessThanOrEqualTo() {
        // given
        Coin coin = Coin.FIFTY;

        // when
        List<Integer> smallerCoins = Coin.getCoinPricesLessThanOrEqualTo(coin.getPrice());

        // then
        
        assertThat(smallerCoins).hasSize(2).contains(Coin.TEN.getPrice(), Coin.FIFTY.getPrice());
    }


}
