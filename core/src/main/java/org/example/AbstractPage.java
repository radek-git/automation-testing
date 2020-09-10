package org.example;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.actions;

public abstract class AbstractPage {

    public void openInNewTab(SelenideElement selenideElement) {
        actions().keyDown(Keys.LEFT_CONTROL).click(selenideElement).keyUp(Keys.LEFT_CONTROL).build().perform();
    }

}
