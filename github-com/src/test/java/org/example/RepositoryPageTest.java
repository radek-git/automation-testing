package org.example;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static com.codeborne.selenide.Selenide.open;

@RunWith(JUnitParamsRunner.class)
public class RepositoryPageTest extends BasicSelenideTest {

    @Test
    @Parameters({
            "https://github.com/Defvyb/speedometer, Passed",
            "https://github.com/three-rocketeers/licensorama, Passed",
            "https://github.com/federico-peyrani/autotrader, Passed",
            "https://github.com/selenide/selenide, Failed"})
    public void test(String testedURL, String expectedQualityGateStatus) {
        open(testedURL);
        new RepositoryPage().openQualityGateInNewTab().switchToQualityGateTab()
                .assertThatQualityGateIs(expectedQualityGateStatus)
                .switchBackToRepositoryTab();
    }

    @Test
    @Parameters({
            "https://github.com/federico-peyrani/autotrader, Passed, 83.0"})
    public void test2(String testedURL, String expectedQualityGateStatus, String codeCoverage) {
        open(testedURL);
        new RepositoryPage().openQualityGateInNewTab().switchToQualityGateTab()
                .assertThatQualityGateIs(expectedQualityGateStatus)
                .assertThatCoverageIs(new BigDecimal(codeCoverage))
                .switchBackToRepositoryTab();
    }
}
