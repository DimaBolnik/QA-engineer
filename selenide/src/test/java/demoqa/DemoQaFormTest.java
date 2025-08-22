package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaFormTest {

    @BeforeAll
    public static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
//        Configuration.holdBrowserOpen = true;
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");

    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach");
    }


    @Test
    void fillPracticeForm() {
        // Открываем страницу
        open("/automation-practice-form");

        // Вводим имя
        $("#firstName").setValue("Dmitry");
        // $("#firstName") — выбираем элемент по id "firstName"
        // .setValue("Dmitry") — вводим текст "Dmitry" в поле

        // Вводим фамилию
        $("#lastName").setValue("Ivanov");

        // Вводим email
        $("#userEmail").setValue("dmitry@example.com");

        // Выбираем пол (радио кнопка)
        $("#genterWrapper")
                .$(byText("Male")).click();
        // $("[for='gender-radio-1']") — выбираем label по атрибуту for
        // .click() — клик по элементу

        // Вводим мобильный телефон
        $("#userNumber").setValue("1234567890");

        // Дата рождения
        $("#dateOfBirthInput").click(); // открываем календарь
        $(".react-datepicker__month-select").selectOption("July"); // выбираем месяц
        $(".react-datepicker__year-select").selectOption("1990"); // выбираем год
        //  $$(".react-datepicker__day--010")
        //  .filter(not(cssClass("react-datepicker__day--outside-month")))
        //  .first()
        //  .click();
        $(".react-datepicker__day--010:not(.react-datepicker__day--outside-month)").click();
        // выбираем день

        // Выбираем предмет
        $("#subjectsInput").setValue("Maths").pressEnter();
        // pressEnter() имитирует нажатие Enter

        // Выбираем хобби
        $("[for='hobbies-checkbox-1']").click(); // клик по чекбоксу

        // Загружаем файл
        $("#uploadPicture").uploadFromClasspath("test-image.png");
        // загружаем файл из папки resources

        // Вводим адрес
        $("#currentAddress").setValue("Moscow, Russia");

        // Выбираем штат и город
        $("#state").click(); // открываем селект
        $("#react-select-3-option-0").click(); // выбираем штат
        $("#city").click();
        $("#react-select-4-option-0").click(); // выбираем город

        // Отправляем форму
        $("#submit").click();

        // Проверяем, что форма отправлена и модальное окно появилось
        $(".modal-content").shouldBe(visible);
        // shouldBe(visible) — проверяем, что элемент видимый на странице
        sleep(5000);
    }

    @Test
    void test(){
        Assertions.assertTrue(3 > 2);
    }
}
