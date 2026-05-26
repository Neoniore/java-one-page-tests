import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginTests {

//    @BeforeEach
//    void setup() {
//        closeWebDriver();
//    }

    @Test
    void successfulAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
//        $("[id=login-input]").setValue("user1");
//        $("#login-input").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
        closeWebDriver();
    }

    @Test
    void wrongPasswordAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("WRONG PASSWORD");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
        closeWebDriver();
    }

}
