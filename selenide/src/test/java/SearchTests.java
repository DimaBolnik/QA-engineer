import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {


    @Test
    void successfulSearchTest() {
        Configuration.holdBrowserOpen = true;

        open("https://qa.guru/");

        $(byText("Войти в кабинет")).click();

        $("body").shouldHave(text("Вход"));

        // Ждем, что появится новое окно, переключаемся на него
        switchTo().window(1); // индекс нового окна (0 — первое, 1 — второе)

        // Теперь работаем с элементами в новом окне
        $("[name='email']").setValue("dimabolnik@mail.ru");
        $("[name='password']").setValue("m3$$2a3Z@A").pressEnter();


//        $("#xdgetr4464_1_1").click();

        $("body").shouldHave(text("Вы авторизованы в этом аккаунте"));

        $("#xdgetr2637_1_1").click();

    }

}
