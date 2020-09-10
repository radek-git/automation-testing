package org.example;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.BigDecimalAssert;
import org.assertj.core.data.Percentage;

import java.math.BigDecimal;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;

public class SonarPage extends AbstractPage {

    public SonarPage assertThatQualityGateIs(String qualityGateStatus) {
        String qualityGate = $(Selectors.byXpath("//div[@class='display-flex-center']/span")).getText();
        assertThat(qualityGate).isEqualTo(qualityGateStatus);
        return this;
    }

    public RepositoryPage switchBackToRepositoryTab() {
        Selenide.closeWindow();
        switchTo().window(0);
        return new RepositoryPage();
    }


    public SonarPage assertThatCoverageIs(BigDecimal expectedCoverage) {
        SelenideElement coverageElement = $(Selectors.byClassName("js-overview-main-coverage"));
        BigDecimal coverage = new BigDecimal(coverageElement.getText().replace("%", ""));

        assertThat(coverage).isCloseTo(expectedCoverage, Percentage.withPercentage(5));
        new BigDecimalAssert(coverage).isCloseTo(expectedCoverage, Percentage.withPercentage(5));
        return this;
    }
}
