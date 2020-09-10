package org.example;

import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class UserIssuesPage extends AbstractPage {

    public UserIssuesPage assertThatNumberOfAssigneesToUserIs(String assigneeUsername, int numberOfAssignee) {

        boolean buttonIsActive;
        int count = 0;

        do {
            List<SelenideElement> assignees = $$x("//a[contains(@class, 'avatar-user') and contains(@aria-label, '" + assigneeUsername + "')]");
            count += assignees.size();
            SelenideElement nextButton = $x("(//a[@class='next_page'])[1]");
            buttonIsActive = nextButton.exists();
            if (buttonIsActive) {
                nextButton.click();
            }
        } while (buttonIsActive);

        Assertions.assertThat(count).isEqualTo(numberOfAssignee);
        return this;
    }
}
