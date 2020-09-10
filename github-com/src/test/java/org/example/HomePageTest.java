package org.example;

import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;

public class HomePageTest extends BasicSelenideTest {

    private HomePage homePage;


    @Before
    public void setUp() throws Exception {
        open("https://github.com/");
        homePage = new HomePage();
    }

    @Test
    public void whenSelenideIsSearchedThenSearchResultPageIsShown() {
        homePage.searchQuery("selenide").clickEnter()
                .assertThatSearchResultPageIsShown();
    }

    @Test
    public void whenSelenideRepoIsFoundThenNumberOfLanguagesIs3() {
        homePage.searchQuery("selenide").clickEnter()
                .clickFirstResult()
                .assertThatNumberOfLanguagesIs(3);
    }

    @Test
    public void whenSelenideIsFoundThenQualityGateStatusIs() {
        homePage.searchQuery("selenide").clickEnter()
                .clickFirstResult()
                .openQualityGateInNewTab()
                .switchToQualityGateTab()
                .assertThatQualityGateIs("Failed");
    }





}
