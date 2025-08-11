import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {
    @Test
    void successfulSearchTest() {
        open("https://qa.guru/");

//        $("[class='text-block-14']")
//                .shouldHave(text("Войти в кабинет"))
//                .click();
        $(byText("Войти в кабинет")).click();

        // Ждем, что появится новое окно, переключаемся на него
        switchTo().window(1); // индекс нового окна (0 — первое, 1 — второе)

        // Теперь работаем с элементами в новом окне
        $("[name='email']").setValue("dimabolnik@mail.ru");
        $("[name='password']").setValue("m3$$2a3Z@A");

        $("#xdgetr4464_1_1").click();

    }

}
