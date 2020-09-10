package org.example;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.codeborne.selenide.Selenide.open;

@RunWith(JUnitParamsRunner.class)
public class UserRepositoriesPageTest extends BasicSelenideTest {



    @Test
    @Parameters({
            "roman, Haskell, 10",
            "radek-git, Java, 27",
            "john, Ruby, 53"
    })
    public void assertThatNumberOfRepositories(String username, String language, int numberOfProjects) {
        open("https://github.com/" + username + "?tab=repositories");
        new UserRepositoriesPage().assertThatNumberOfProjectsInLanguageIs(language, numberOfProjects);
    }
}
