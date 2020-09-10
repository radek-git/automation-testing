package org.example;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class HomePage extends AbstractPage {

    public HomePage searchQuery(String query) {
        $(byName("q")).val(query);
        return this;
    }

    public SearchResultPage clickEnter() {
        actions().sendKeys(Keys.ENTER).build().perform();
        return new SearchResultPage();
    }
}
