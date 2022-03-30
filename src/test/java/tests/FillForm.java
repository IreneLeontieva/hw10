package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class FillForm extends TestBase {

    @Test
    void successFillForm() {
        step("Open form ", () -> {
            open("/automation-practice-form");
            $(".main-header").shouldHave(text("Practice Form"));
        });
        step("Set first name ", () -> {
            $("#firstName").sendKeys("Irina");
        });
        step("Set last name ", () -> {
            $("#lastName").sendKeys("Leonteva");
        });
        step("Set email ", () -> {
            $("#userEmail").sendKeys("test@test.ru");
        });
        step("Set gender ", () -> {
            $(byText("Female")).click();
        });
        step("Set phone number ", () -> {
            $("#userNumber").sendKeys("8921634771");
        });
        step("Set birthday ", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOptionByValue("1994");
            $(".react-datepicker__month-select").selectOptionContainingText("December");
            $(".react-datepicker__day--013").click();
        });
        step("Set subjects ", () -> {
            $("#subjectsInput").setValue("Biology").pressEnter();
            $("#subjectsInput").setValue("Chemistry").pressEnter();
        });
        step("Set hobbies ", () -> {
            $(byText("Reading")).click();
            $(byText("Sports")).click();
        });
        step("Set file ", () -> {
            File cv = new File("src/test/resources/cv.jpg");
            $("#uploadPicture").uploadFile(cv);
        });
        step("Set address ", () -> {
            $("#currentAddress").sendKeys("Moscow");
            $("#react-select-3-input").setValue("NCR").pressEnter();
            $("#react-select-4-input").setValue("Delhi").pressEnter();
        });
        step("Submit form ", () -> {
            $("#submit").click();
        });

        step("Check form ", () -> {
            $(".modal-header").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text("Irina Leonteva"));
            $(".table-responsive").shouldHave(text("test@test.ru"));
            $(".table-responsive").shouldHave(text("Female"));
            $(".table-responsive").shouldHave(text("8921634771"));
            $(".table-responsive").shouldHave(text("13 December,1994"));
            $(".table-responsive").shouldHave(text("Biology, Chemistry"));
            $(".table-responsive").shouldHave(text("Reading, Sports"));
            $(".table-responsive").shouldHave(text("cv.jpg"));
            $(".table-responsive").shouldHave(text("Moscow"));
            $(".table-responsive").shouldHave(text("NCR Delhi"));
        });
    }

    @Test
    @Tag("smoke")
    void smokeTest(){
        step("Open form ", () -> {
            open("/automation-practice-form");
            $(".main-header").shouldHave(text("Practice Form"));
        });
        System.out.println("Browser size is + "+ System.getProperty("size")+ "Browser is "+System.getProperty("browser"));
    }
}
