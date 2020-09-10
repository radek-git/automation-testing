package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ShopServiceTest {

    @Test
    void test() {
        List<Item> items = List.of(
                new Item("item2", new BigDecimal("100.00")),
                new Item("item3", new BigDecimal("10.00")),
                new Item("item4", new BigDecimal("150.00")),
                new Item("item5", new BigDecimal("120.00"))
        );

        ShopService shopService = new ShopService();
        System.out.println(shopService.avgPriceOfItem(items));

        assertThat(shopService.avgPriceOfItem(items)).isEqualTo(new BigDecimal("95.00"));
    }
}
