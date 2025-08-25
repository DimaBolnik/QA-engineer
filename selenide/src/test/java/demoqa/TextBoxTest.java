package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
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
        String userName = "Dima";
        String mail = "DimaBolnik@gmail.com";

        $("h1.text-center").shouldHave(text("Text Box"));

        $("#userName").setValue(userName);
        $("#userEmail").setValue(mail);
        $("#currentAddress").setValue("Russia, Penza");
        $("#permanentAddress").setValue("Russia, Penza");
        $("#submit.btn-primary").click();
        $("#output").$("#name").shouldHave(text(userName));
        $("#output #email").shouldHave(text(mail));

    }
}
