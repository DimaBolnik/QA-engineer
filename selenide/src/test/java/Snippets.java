

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// Это учебный класс-шпаргалка по Selenide
public class Snippets {

    void browser_command_examples() {
        open("https://google.com"); // открыть отдельное окно сайта Google
        open("/customer/orders");   // открыть относительный URL (baseUrl задаётся параметром)
        open("/", AuthenticationType.BASIC,
                new BasicAuthCredentials("", "user", "password")); // открыть с базовой аутентификацией

        Selenide.back(); // нажать "Назад" в браузере
        Selenide.refresh(); // обновить страницу

        Selenide.clearBrowserCookies(); // удалить cookies
        Selenide.clearBrowserLocalStorage(); // очистить localStorage
        executeJavaScript("sessionStorage.clear();"); // очистить sessionStorage (нет готовой команды в Selenide)

        Selenide.confirm(); // нажать "OK" в alert
        Selenide.dismiss(); // нажать "Cancel" в alert

        Selenide.closeWindow(); // закрыть текущую вкладку
        Selenide.closeWebDriver(); // закрыть браузер полностью

        Selenide.switchTo().frame("new"); // переключиться во фрейм
        Selenide.switchTo().defaultContent(); // вернуться из фрейма в основной DOM

        Selenide.switchTo().window("The Internet"); // переключиться на другое окно/вкладку по имени

        Cookie cookie = new Cookie("foo", "bar"); // создать cookie
        WebDriverRunner.getWebDriver().manage().addCookie(cookie); // добавить cookie в браузер
    }

    void selectors_examples() {
        $("div").click(); // найти элемент по CSS-селектору и кликнуть
        element("div").click(); // то же самое, но через element()

        $("div", 2).click(); // кликнуть на третий <div> (нумерация с 0)

        $x("//h1/div").click(); // найти элемент по XPath и кликнуть
        $(byXpath("//h1/div")).click(); // то же самое

        $(byText("full text")).click(); // найти элемент по точному тексту
        $(withText("ull tex")).click(); // найти элемент по части текста

        $(byTagAndText("div", "full text")); // найти <div> с текстом "full text"
        $(withTagAndText("div", "ull text")); // найти <div> с текстом, содержащим "ull text"

        $("").parent(); // родительский элемент
        $("").sibling(1); // соседний элемент справа
        $("").preceding(1); // соседний элемент слева
        $("").closest("div"); // ближайший родитель <div>
        $("").ancestor("div"); // то же самое, что closest
        $("div:last-child"); // последний дочерний элемент <div>

        $("div").$("h1").find(byText("abc")).click(); // найти внутри <div> → <h1> с текстом "abc" и кликнуть
        $(byAttribute("abc", "x")).click(); // найти по атрибуту
        $("[abc=x]").click(); // сокращённый вариант поиска по атрибуту

        $(byId("mytext")).click(); // поиск по id
        $("#mytext").click(); // то же самое, короткий синтаксис

        $(byClassName("red")).click(); // поиск по классу
        $(".red").click(); // короткий синтаксис
    }

    void actions_examples() {
        $("").click(); // клик
        $("").doubleClick(); // двойной клик
        $("").contextClick(); // правый клик

        $("").hover(); // наведение мышкой

        $("").setValue("text"); // ввести текст (очищает поле и вводит новое)
        $("").append("text"); // дописать текст
        $("").clear(); // очистить поле
        $("").setValue(""); // тоже очистка

        $("div").sendKeys("c"); // нажать клавишу "c" в элементе
        actions().sendKeys("c").perform(); // нажать клавишу "c" глобально
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); // тоже Ctrl + F, но на <html>

        $("").pressEnter(); // нажать Enter
        $("").pressEscape(); // нажать Esc
        $("").pressTab(); // нажать Tab

        // Сложное действие: зажать мышь и перетащить
        actions().moveToElement($("div"))
                .clickAndHold()
                .moveByOffset(300, 200)
                .release()
                .perform();

        $("").selectOption("dropdown_option"); // выбрать из выпадающего списка
        $("").selectRadio("radio_options"); // выбрать радио-кнопку
    }

    void assertions_examples() {
        $("").shouldBe(visible); // должен быть виден
        $("").shouldNotBe(visible); // не должен быть виден
        $("").shouldHave(text("abc")); // должен содержать текст "abc"
        $("").shouldNotHave(text("abc")); // не должен содержать текст "abc"
        $("").should(appear); // должен появиться
        $("").shouldNot(appear); // не должен появиться

        $("").shouldBe(visible, Duration.ofSeconds(30)); // ожидание до 30 сек
    }

    void conditions_examples() {
        $("").shouldBe(visible); // проверка видимости
        $("").shouldBe(hidden); // проверка невидимости

        $("").shouldHave(text("abc")); // содержит текст
        $("").shouldHave(exactText("abc")); // точное совпадение текста
        $("").shouldHave(textCaseSensitive("abc")); // чувствительный к регистру
        $("").shouldHave(exactTextCaseSensitive("abc")); // точное совпадение + чувствительный к регистру
        $("").should(matchText("[0-9]abc$")); // совпадение по regex

        $("").shouldHave(cssClass("red")); // класс = red
        $("").shouldHave(cssValue("font-size", "12")); // CSS-стиль

        $("").shouldHave(value("25")); // значение поля = 25
        $("").shouldHave(exactValue("25")); // точное значение
        $("").shouldBe(empty); // пустое поле

        $("").shouldHave(attribute("disabled")); // есть атрибут disabled
        $("").shouldHave(attribute("name", "example")); // атрибут name=example
        $("").shouldHave(attributeMatching("name", "[0-9]abc$")); // проверка атрибута по regex

        $("").shouldBe(checked); // чекбокс выбран
        $("").should(exist); // элемент есть в DOM (не факт, что виден)

        $("").shouldBe(disabled); // выключен
        $("").shouldBe(enabled); // включен
    }

    void collections_examples() {
        $$("div"); // найти все div

        $$x("//div"); // найти все div по XPath

        $$("div").filterBy(text("123")).shouldHave(size(1)); // отфильтровать коллекцию по тексту
        $$("div").excludeWith(text("123")).shouldHave(size(1)); // исключить элементы с текстом

        $$("div").first().click(); // первый элемент
        elements("div").first().click(); // то же самое
        $$("div").last().click(); // последний
        $$("div").get(1).click(); // второй (нумерация с 0)
        $("div", 1).click(); // тоже второй
        $$("div").findBy(text("123")).click(); // найти первый div с текстом "123"

        $$("").shouldHave(size(0)); // коллекция пустая
        $$("").shouldBe(CollectionCondition.empty); // то же самое

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); // тексты в коллекции
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma")); // точное совпадение

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa")); // порядок не важен
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa")); // порядок не важен + чувствительность к регистру

        $$("").shouldHave(itemWithText("Gamma")); // хотя бы один элемент с текстом Gamma

        $$("").shouldHave(sizeGreaterThan(0)); // больше 0 элементов
        $$("").shouldHave(sizeGreaterThanOrEqual(1)); // ≥ 1 элемент
        $$("").shouldHave(sizeLessThan(3)); // < 3 элементов
        $$("").shouldHave(sizeLessThanOrEqual(2)); // ≤ 2 элементов
    }

    void file_operation_examples() throws FileNotFoundException {
        File file1 = $("a.fileLink").download(); // скачать файл по ссылке <a href>
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // скачать через div (альтернатива)

        File file = new File("src/test/resources/readme.txt"); // файл на диске
        $("#file-upload").uploadFile(file); // загрузить файл с диска
        $("#file-upload").uploadFromClasspath("readme.txt"); // загрузить файл из ресурсов
        $("uploadButton").click(); // клик по кнопке "Загрузить"
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')"); // вызвать alert через JS
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12); // вызвать alert с параметрами
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7); // выполнить JS и вернуть результат
    }
}
