package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class UserPage extends AbstractPage {

    public UserPage assertThatNumberOfFollowersIs(int numberOfFollowers) {
        int followers = Integer.parseInt($x("(//div[@class='mb-3']/a/span)[1]").getText());
        assertThat(followers).isEqualTo(numberOfFollowers);
        return this;
    }

    public UserPage assertThatNumberOfFollowingIs(int numberOfFollowing) {
        int following = Integer.parseInt($x("(//div[@class='mb-3']/a/span)[2]").getText());
        assertThat(following).isEqualTo(numberOfFollowing);
        return this;
    }

    public UserPage assertThatNumberOfStarsIs(int numberOfStars) {
        int stars = Integer.parseInt($x("(//div[@class='mb-3']/a/span)[3]").getText());
        assertThat(stars).isEqualTo(numberOfStars);
        return this;
    }


    public UserPage assertWidthIs(int width) {
        int widthElement = Integer.parseInt(Objects.requireNonNull($x("//*[@class='js-calendar-graph-svg']").getAttribute("width")));
        assertThat(widthElement).isEqualTo(width);
        return this;
    }

    public UserPage assertThatNumberOfContributionsIs(int numberOfContributions) {
        List<SelenideElement> list = $$x("//*[@class='js-calendar-graph-svg']/*/*/*");
        int sum = list.stream()
                .mapToInt(e -> Integer.parseInt(Objects.requireNonNull(e.getAttribute("data-count"))))
                .sum();

        assertThat(sum).isEqualTo(numberOfContributions);
        return this;
    }


}
