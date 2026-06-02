import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class LoginTests {

//    @BeforeEach
//    void setup() {
//        closeWebDriver();
//    }

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = Paths.get("src/test/resources/pages").toUri().toString();
    }

    @Test
    void successfulAuthorizationTest() {
        open("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
        closeWebDriver();
    }

    @Test
    void wrongPasswordAuthorizationTest() {
        open("/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("WRONG PASSWORD");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
        closeWebDriver();
    }

}
