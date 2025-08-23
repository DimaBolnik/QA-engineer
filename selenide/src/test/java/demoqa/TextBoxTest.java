package demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTest {


    @BeforeAll
    public static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
//        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void testTextBox() {
        open("/text-box");

        $("h1.text-center").shouldHave(text("Text Box"));

        $("#userName").setValue("Dima");
        $("#userEmail").setValue("DimaBolnik@gmail.com");
        $("#currentAddress").setValue("Russia, Penza");
        $("#permanentAddress").setValue("Russia, Penza");
        $("#submit.btn-primary").click();
        $("#output").shouldBe(visible);

    }
}
