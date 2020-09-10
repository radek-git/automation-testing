package org.example;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShopTest {

    private Shop shop;
    private ShopService shopService;

    @BeforeEach
    void setUp() {
        shopService = new ShopService();
        shop = new Shop(shopService);
    }

    @Test
    public void whenLoginIsNotTakenThenUserIsRegistered() {
        boolean isRegistered = shop.register("user1", "pass1", User.Type.ADMIN);
        assertThat(isRegistered).isTrue();
        assertThat(shop.users.stream().anyMatch(user -> user.login.equals("user1"))).isTrue();
    }

    @Test
    public void whenLoginIsTakenThenUserIsNotRegistered() {
        shop.register("user1", "pass1", User.Type.ADMIN);
        assertThat(shop.users.stream().anyMatch(user -> user.login.equals("user1"))).isTrue();

        boolean isRegistered = shop.register("user1", "pass1", User.Type.ADMIN);
        assertThat(isRegistered).isFalse();
        assertThat(shop.users.stream().filter(user -> user.login.equals("user1")).count()).isEqualTo(1);
    }

    @Test
    public void test1() {
        ShopService service = Mockito.mock(ShopService.class);
        Shop shop1 = new Shop(service);

        Mockito.when(service.isLoginTaken(ArgumentMatchers.anyList(), ArgumentMatchers.anyString())).thenReturn(false);
        boolean isRegistered = shop1.register("user2", "pass2", User.Type.EMPLOYEE);
        assertThat(isRegistered).isTrue();
    }

    @Test
    public void test2() {
        ShopService service = Mockito.mock(ShopService.class);
        Shop shop1 = new Shop(service);

        Mockito.when(service.isLoginTaken(ArgumentMatchers.anyList(), ArgumentMatchers.eq("user2"))).thenReturn(false, true); //za 1-szym razem zwróci false, za 2-gim razem true
        boolean isRegistered = shop1.register("user2", "pass2", User.Type.EMPLOYEE);
        assertThat(isRegistered).isTrue();

        boolean isRegistered2 = shop1.register("user2", "pass2", User.Type.EMPLOYEE);
        assertThat(isRegistered2).isFalse();
    }

    @Test
    @Order(1)
    public void whenUserAddsItemThenItemIsAdded() {
        Item item = shop.addItem("item1", new BigDecimal("52.20"));
        assertThat(item.id).isEqualTo(1);
        assertThat(shop.items.get(0)).isEqualTo(item);
    }


    @Test
    @Order(2)
    public void when3ItemsAreAddedThenEachOfThemHasId() {
        Item item = shop.addItem("item1", new BigDecimal("52.20"));
        Item item2 = shop.addItem("item2", new BigDecimal("42.20"));
        Item item3 = shop.addItem("item3", new BigDecimal("32.20"));

        assertThat(item.id).isEqualTo(2);
        assertThat(item2.id).isEqualTo(3);
        assertThat(item3.id).isEqualTo(4);
    }

    @Test
    public void addItemToShop() {
        Item item = shop.addItemToShop("item1", new BigDecimal("52.20"), 4);
        Item item2 = shop.addItemToShop("item2", new BigDecimal("42.20"), 8);
        Item item3 = shop.addItemToShop("item3", new BigDecimal("32.20"), 12);

        System.out.println(shop.itemsInShop.get(item));

        for (Item i : shop.itemsInShop.keySet()) {
            System.out.println(i.name);
        }

        System.out.println(shop.itemsInShop.get(item));
        assertThat(shop.itemsInShop.get(item)).isEqualTo(2);
    }

    @Test
    public void buyMoreItemsThanInShop() throws NoProductWithGivenNameException {
        Item item = shop.addItemToShop("item1", new BigDecimal("52.20"), 10);

        Assertions.assertThrows(NoProductWithGivenNameException.class, () -> {
            shop.buyItem("item1", 11);
        });
    }

    @Test
    public void buyProductWithNameThatNotExist() {
        Item item = shop.addItemToShop("item1", new BigDecimal("52.20"), 10);

        Assertions.assertThrows(NoProductWithGivenNameException.class, () -> {
            shop.buyItem("item5", 2);
        });
    }

    @Test
    public void buyItemThatExistsAndQuantityIsEnoughInShop() throws NoProductWithGivenNameException {
        Item item = shop.addItemToShop("item1", new BigDecimal("52.20"), 10);

        shop.buyItem("item1", 3);
    }


    @Test
    public void buyMoreItemsThanInShopMock() throws NoProductWithGivenNameException {
        ShopService service = Mockito.mock(ShopService.class);
        Shop shop2 = new Shop(shopService);

        Item item = shop2.addItemToShop("item1", new BigDecimal("25.00"), 6);

        Mockito.when(service.findItemWithGivenName(ArgumentMatchers.anyMap(), ArgumentMatchers.eq("item1"))).thenReturn(item);
        Mockito.when(service.isQuantityOfProductEnough(ArgumentMatchers.anyMap(), ArgumentMatchers.eq(item), ArgumentMatchers.eq(50))).thenReturn(false);

        Assertions.assertThrows(NoProductWithGivenNameException.class, () -> {
            shop2.buyItem("item1", 50);
        });
    }

    @Test
    public void buyItemThatExistsInShopMock() throws NoProductWithGivenNameException {
        ShopService service = Mockito.mock(ShopService.class);
        Shop shop2 = new Shop(shopService);

        Item item = shop2.addItemToShop("item1", new BigDecimal("25.00"), 6);

        Mockito.when(service.findItemWithGivenName(ArgumentMatchers.anyMap(), ArgumentMatchers.eq("item1"))).thenReturn(item);
        Mockito.when(service.isQuantityOfProductEnough(ArgumentMatchers.anyMap(), ArgumentMatchers.eq(item), ArgumentMatchers.eq(4))).thenReturn(true);

        shop2.buyItem("item1", 4);
        assertThat(shop2.getItemQuantity(item)).isEqualTo(2);
    }


    @Test
    public void buyItemThatNotExistsInShopMock() throws NoProductWithGivenNameException {
        ShopService service = Mockito.mock(ShopService.class);
        Shop shop2 = new Shop(shopService);

        Item item = shop2.addItemToShop("item1", new BigDecimal("25.00"), 6);

        Mockito.when(service.findItemWithGivenName(ArgumentMatchers.anyMap(), ArgumentMatchers.eq("item2"))).thenThrow(NoProductWithGivenNameException.class);

        Assertions.assertThrows(NoProductWithGivenNameException.class, () -> {
            shop2.buyItem("item2", 1);
        });
    }

}



