package app;

import domain.SmallChange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SmallChangeServiceTest {
    SmallChangeService smallChangeService;

    @BeforeEach
    void setUp() {
        smallChangeService = new SmallChangeService();
    }

    @Test
    void getSmallChange() {
        // given
        int change = 450;

        // when
        SmallChange smallChange = smallChangeService.getSmallChange(change);

        // then
        assertThat(smallChange.getTotal()).isEqualTo(change);
    }

}
