package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

public class ShopService {

    public boolean isLoginTaken(List<User> users, String login) {
        return users.stream().anyMatch(user -> user.login.equals(login));
    }

    public BigDecimal avgPriceOfItem(List<Item> items) {
        BigDecimal sum = new BigDecimal(0);
        for (Item item : items) {
            sum = sum.add(item.price);
        }
        return sum.divide(new BigDecimal(items.size()), 2, RoundingMode.DOWN);
    }

    public Item findItemWithGivenName(Map<Item, Integer> itemsInShop, String name) throws NoProductWithGivenNameException {
        return itemsInShop.keySet().stream()
                .filter(i -> i.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new NoProductWithGivenNameException("Brak produktu o podanej nazwie"));
    }

    public boolean isQuantityOfProductEnough(Map<Item, Integer> itemsInShop, Item item, int quantity) throws NoProductWithGivenNameException {
        return itemsInShop.get(item) >= quantity;
    }
}
