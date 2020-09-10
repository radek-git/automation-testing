package org.example;

import java.math.BigDecimal;
import java.util.*;

public class Shop {

    List<User> users = new ArrayList<>();
    List<Item> items = new ArrayList<>();
    Map<Item, Integer> itemsInShop = new HashMap<>();

    ShopService shopService;

    public Shop(ShopService shopService) {
        this.shopService = shopService;
    }

    public boolean register(String login, String password, User.Type type) {
        if (shopService.isLoginTaken(users, login)) {
            System.out.println("Login zajęty");
            return false;
        }

        users.add(new User(login, password, type));
        return true;
    }

    public boolean isItemFound(List<Item> items, String itemName) {
        return items.stream().anyMatch(item -> item.name.equals(itemName));
    }

    public Item addItem(String name, BigDecimal price) {
        Item item = new Item(name, price);
        items.add(item);
        return item;
    }




    public boolean isItemInShop(Map<Item, Integer> itemsInShop, String name) {
        for (Item item : itemsInShop.keySet()) {
            return item.name.equals(name);
        }
        return false;
    }

    public Item  addItemToShop(String name, BigDecimal price, int quantity) {
        Item item = new Item(name, price);
        itemsInShop.put(item, quantity);
        return item;
    }

    public void buyItem(String itemName, int quantity) throws NoProductWithGivenNameException {
        Item item = shopService.findItemWithGivenName(itemsInShop, itemName);

        if (!shopService.isQuantityOfProductEnough(itemsInShop, item, quantity)) {
            throw new NoProductWithGivenNameException("Nie wystarczającej ilości produktu");
        }
        itemsInShop.put(item, itemsInShop.get(item) - quantity);
    }


    public int getItemQuantity(Item item) {
        return itemsInShop.get(item);
    }

}
