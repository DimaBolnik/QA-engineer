package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class SoftAssertionsTest {

    @Test
    void testSoftAssertionsJUnit5Example() {
        // 1. Открываем страницу Selenide в Github
        open("https://github.com/selenide/selenide");

        // 2. Переходим в раздел Wiki
        $("#wiki-tab").click();

        // 3. Убеждаемся, что есть страница SoftAssertions
        $(".wiki-more-pages-link button").click(); // раскрываем список, если он свернут
        $("#wiki-pages-box").shouldHave(Condition.text("SoftAssertions"));

        // 4. Открываем страницу SoftAssertions
        $("#wiki-pages-box").$(Selectors.byText("SoftAssertions")).click();

        // 5. Проверяем, что внутри есть пример кода для JUnit5
        $("#wiki-body").shouldHave(Condition.text("Using JUnit5 extend test class"));

        sleep(4000);
    }
}
