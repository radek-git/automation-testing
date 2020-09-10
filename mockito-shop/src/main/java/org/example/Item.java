package org.example;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

public class Item {

    static AtomicInteger idGenerator = new AtomicInteger(1);

    int id;
    String name;
    BigDecimal price;

    public Item(String name, BigDecimal price) {
        this.id = idGenerator.getAndIncrement();
        this.name = name;
        this.price = price;
    }


}
