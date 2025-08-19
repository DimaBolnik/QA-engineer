import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AlfaBankTest {


    @Test
    void testAlfa() {
        open("https://www.alfabank.ru");
        $(byText("??????")).click();
        $("body").shouldHave(text("?????? ? ??????????"));
        $$(byText("????????")).find(visible).click();
        $("body").shouldHave(text("???????? ?????"));

        SelenideElement button = $("div").preceding(0);
        button.click();
    }
}
