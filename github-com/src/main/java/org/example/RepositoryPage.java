package org.example;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RepositoryPage extends AbstractPage {

    public RepositoryPage assertThatNumberOfLanguagesIs(int numberOfLanguages) {
        List<SelenideElement> languages = $$(Selectors.byXpath("//div[@class='BorderGrid-cell']/ul[@class='list-style-none']/li"));
        assertThat(languages.size()).isEqualTo(numberOfLanguages);
        return this;
    }


    public RepositoryPage openQualityGateInNewTab() {
        SelenideElement qualityGateButton = $x("//img[starts-with(@data-canonical-src, 'https://sonarcloud.io')]");
        openInNewTab(qualityGateButton);
        return this;
    }

    public SonarPage switchToQualityGateTab() {
        switchTo().window(1);
        return new SonarPage();
    }


}
