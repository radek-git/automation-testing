package org.example;

import static com.codeborne.selenide.Selenide.$x;

public class UsersResultPage extends AbstractPage {

    public UserPage clickLinkToUserPage(String username) {
        $x("//div[@class='d-flex']/div/a/em[text()='" + username + "']").click();
        return new UserPage();
    }
}
