package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;

import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoriesPage extends AbstractPage {

    public UserRepositoriesPage assertThatNumberOfProjectsInLanguageIs(String language, int numberOfProjects) {
        int count = 0;
        boolean nextButtonExists;

        do {
            System.out.println("cos");
            List<SelenideElement> list = Selenide.$$x("//span[@itemprop='programmingLanguage' and contains(text(), '" + language + "')]");
            count += list.size();

            SelenideElement button = $x("//a[contains(text(), 'Next')]");
            nextButtonExists = button.exists();
            if (nextButtonExists) {
                button.click();
            }

        } while (nextButtonExists);

        assertThat(count).isEqualTo(numberOfProjects);
        return this;
    }

}
