package org.example;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.codeborne.selenide.Selenide.open;

@RunWith(JUnitParamsRunner.class)
public class UserIssuesPageTest extends BasicSelenideTest {


    @Test
    @Parameters({
            "google, googletest, mattcalabrese-google, 4",
            "chef-cookbooks, aws, marian, 4"
    })
    public void assertThatNumberOfAssigneesForUserIs(String username, String repositoryName, String assigneeUsername, int numberOfAssignees) {
        open("https://github.com/" + username + "/" + repositoryName + "/issues");
        new UserIssuesPage().assertThatNumberOfAssigneesToUserIs(assigneeUsername, numberOfAssignees);
    }
}
