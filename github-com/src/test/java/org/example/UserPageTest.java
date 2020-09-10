package org.example;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.codeborne.selenide.Selenide.open;

@RunWith(JUnitParamsRunner.class)
public class UserPageTest extends BasicSelenideTest {

    private HomePage homePage;

    @Before
    public void setUp() throws Exception {
        open("https://github.com/");
        homePage = new HomePage();
    }

    @Test
    public void whenUserIsFoundThenNumberOfFollowersIs0() {
        String username =  "radek-git";
        homePage.searchQuery(username).clickEnter()
                .clickUserButton()
                .clickLinkToUserPage(username)
                .assertThatNumberOfFollowersIs(0);
    }

    @Test
    public void whenUserIsFoundThenNumberOfFollowingsIs0() {
        String username =  "radek-git";
        homePage.searchQuery(username).clickEnter()
                .clickUserButton()
                .clickLinkToUserPage(username)
                .assertThatNumberOfFollowingIs(0);
    }

    @Test
    public void whenUserIsFoundThenNumberOfStarsIs2() {
        String username =  "radek-git";
        homePage.searchQuery(username).clickEnter()
                .clickUserButton()
                .clickLinkToUserPage(username)
                .assertThatNumberOfStarsIs(2);
    }

    @Test
    public void test() {
        String username =  "radek-git";
        homePage.searchQuery(username).clickEnter()
                .clickUserButton()
                .clickLinkToUserPage(username)
                .assertWidthIs(828);
    }

    @Test
    @Parameters({
            "radek-git, 37",
            "roman, 128",
            "john, 119"
    })
    public void assertNumberOfContributions(String username, int numberOfContributions) {
        homePage.searchQuery(username).clickEnter()
                .clickUserButton()
                .clickLinkToUserPage(username)
                .assertThatNumberOfContributionsIs(numberOfContributions);
    }

}
