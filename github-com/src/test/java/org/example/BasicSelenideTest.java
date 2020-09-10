package org.example;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;

public class BasicSelenideTest {

    @BeforeClass
    public static void beforeClass() throws Exception {
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = true;
    }
}
