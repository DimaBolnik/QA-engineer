package demoqa;

import Pages.RegistrationPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static com.codeborne.selenide.Selenide.sleep;

public class DemoQaFormPageObjectTest {

    @BeforeAll
    public static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
//        Configuration.holdBrowserOpen = true;
    }


    @Test
    void fillPracticeForm() {
        RegistrationPage registrationPage = new RegistrationPage();
        // Тестовые данные
        String firstName = "Dmitry";
        String lastName = "Ivanov";
        String email = "ivanov@gmail.com";
        String phone = "1234567890";
        LocalDate date = LocalDate.of(1990, 6, 10);
        String subject = "Maths";
        String picture = "test-image.png";
        String address = "Moscow, Russia";
        String state = "NCR";
        String city = "Delhi";

        registrationPage.openPage()
                .setFirstAndLastName(firstName, lastName)
                .setEmail(email)
                .selectGender("Male")
                .setUserNumber(phone)
                .setDateOfBirth(date)
                .setSubject(subject)
                .selectHobby()
                .uploadPicture(picture)
                .setCurrentAddress(address)
                .selectStateAndCity(state, city)
                .submitForm()
                .checkResultVisible();

        sleep(5000);
    }
}
