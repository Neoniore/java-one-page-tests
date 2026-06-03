import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationTests extends TestBase {

    @BeforeEach
    public void setUp() {
        open("/automation-practice-form.html");
        $("button[aria-label='Close']").click();
    }

    @Test
    public void positiveRegistrationFormFillingTest() {
        $("[id='firstName']").setValue("Aleks");
        $("[id='lastName']").setValue("Pechkin");
        $("[id='userEmail']").setValue("Aleks@sdfs.com");
        $("[id='genterWrapper']").$(byText("Male")).click();
        $("[id='userNumber']").setValue("8999999997");


        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1998");
        $(".react-datepicker__day--001").click();

        $("[id='subjectsInput']").setValue("E")
                .pressEnter()
                .setValue("computer science")
                .pressEnter();

        $("[id='hobbiesWrapper']").$(byText("Sports")).click();
        $("[id='hobbiesWrapper']").$(byText("Reading")).click();
        $("[id='hobbiesWrapper']").$(byText("Music")).click();

        $("[id='uploadPicture']").uploadFromClasspath("img/1.png");

        $("[id='currentAddress']").setValue("Backer street, 221b");

        $("#state").click();
        $(byText("Uttar Pradesh")).click();

        $("#city").click();
        $(byText("Agra")).click();


        $("[id='submit']").click();


        $x("//tr[td[text()='Student Name']]/td[2]")
                .shouldBe(text("Aleks Pechkin"));

        $x("//tr[td[text()='Student Email']]/td[2]")
                .shouldBe(text("Aleks@sdfs.com"));

        $x("//tr[td[text()='Gender']]/td[2]")
                .shouldBe(text("Male"));

        $x("//tr[td[text()='Mobile']]/td[2]")
                .shouldBe(text("8999999997"));

        $x("//tr[td[text()='Date of Birth']]/td[2]")
                .shouldBe(text("1998-07-01"));

        $x("//tr[td[text()='Subjects']]/td[2]")
                .shouldBe(text("Chemistry, Computer Science"));

        $x("//tr[td[text()='Hobbies']]/td[2]")
                .shouldBe(text("Sports, Reading, Music"));

        $x("//tr[td[text()='Picture']]/td[2]")
                .shouldBe(text("1.png"));

        $x("//tr[td[text()='Address']]/td[2]")
                .shouldBe(text("Backer street, 221b"));

        $x("//tr[td[text()='State and City']]/td[2]")
                .shouldBe(text("Uttar Pradesh Agra"));
    }

    @Test
    public void positiveRegistrationFormRequiredFieldsFillingTest() {
        $("[id='firstName']").setValue("Иван");
        $("[id='lastName']").setValue("Петров");
        $("[id='genterWrapper']").$(byText("Other")).click();
        $("[id='userNumber']").setValue("89999999999");


        $("[id='submit']").click();


        $x("//tr[td[text()='Student Name']]/td[2]")
                .shouldBe(text("Иван Петров"));
        $x("//tr[td[text()='Gender']]/td[2]")
                .shouldBe(text("Other"));
        $x("//tr[td[text()='Mobile']]/td[2]")
                .shouldBe(text("8999999999"));
    }

    @Test
    public void shouldRejectDigitsInNameFieldsTest() {
        $("[id='firstName']").setValue("00000");
        $("[id='lastName']").setValue("99999");
        $("[id='genterWrapper']").$(byText("Other")).click();
        $("[id='userNumber']").setValue("89999999999");


        $("[id='submit']").click();


        $(".table-responsive").shouldNotBe(visible);
    }

    @Test
    public void closeTableFormAfterSubmittingFormTest() {
        $("[id='firstName']").setValue("Иван");
        $("[id='lastName']").setValue("Петров");
        $("[id='genterWrapper']").$(byText("Other")).click();
        $("[id='userNumber']").setValue("89999999999");


        $("[id='submit']").click();
        $("[id='closeModal']").click();


        $(".table-responsive").shouldNotBe(visible);
    }

    @Test
    public void shouldRejectNonImageFileUploadTest() {
        $("[id='firstName']").setValue("Алёна");
        $("[id='lastName']").setValue("Иванова");
        $("[id='genterWrapper']").$(byText("Female")).click();
        $("[id='userNumber']").setValue("89993339933");
        $("[id='uploadPicture']").uploadFromClasspath("img/textFile.txt");


        $("[id='submit']").click();


        $(".table-responsive").shouldNotBe(visible);
    }

    @Test
    public void phoneFieldShouldNotAcceptLettersTest() {
        $("[id='firstName']").setValue("Алёна");
        $("[id='lastName']").setValue("Иванова");
        $("[id='genterWrapper']").$(byText("Other")).click();
        $("[id='userNumber']").setValue("ggggggggggg");


        $("[id='submit']").click();


        $(".table-responsive").shouldNotBe(visible);
    }
}
