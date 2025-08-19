package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.*;

public class BestContributorTest {

    @BeforeAll
    static void setup() {
        // Настройка размера окна браузера (null = размер по умолчанию)
        Configuration.browserSize = null;
    }

    @Test
    public void testBestContributor() {
        // Открываем страницу репозитория Selenide на GitHub
        open("https://github.com/selenide/selenide");

        // Находим блок с вкладкой "Contributors"
        var contributorsBlock = $(".BorderGrid")
                .$(Selectors.byText("Contributors"))
                .ancestor(".BorderGrid-row");

        // Берем первого участника из списка
        var firstContributor = contributorsBlock.$$("ul li").first();

        // Наводим курсор на аватар участника
        firstContributor.hover();

        // Проверяем, что появилось всплывающее окно с именем участника
        $(".Popover").shouldHave(Condition.text("Andrei Solntsev"));

        sleep(5000); // пауза для визуальной проверки
    }
}
