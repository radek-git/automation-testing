package org.example;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class SearchResultPage extends AbstractPage {

    public SearchResultPage assertThatSearchResultPageIsShown() {
        assertThat(WebDriverRunner.url()).startsWith("https://github.com/search?q=");
        return this;
    }

    public RepositoryPage clickFirstResult() {
        $(Selectors.byXpath("(//ul/li/div/div/a)[1]")).click();
        return new RepositoryPage();
    }

    public UsersResultPage clickUserButton() {
        $x("//nav[contains(@class, 'd-md-block')]/a[text()='Users']").click();
        return new UsersResultPage();
    }

}
