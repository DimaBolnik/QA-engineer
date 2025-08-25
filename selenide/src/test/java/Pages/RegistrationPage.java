package Pages;

import com.codeborne.selenide.SelenideElement;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private final String Url = "/automation-practice-form";

    // элементы страницы
    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            monthSelect = $(".react-datepicker__month-select"),
            yearSelect = $(".react-datepicker__year-select"),
            subjectsInput = $("#subjectsInput"),
            hobbiesCheckbox1 = $("[for='hobbies-checkbox-1']"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateSelect = $("#state"),
            citySelect = $("#city"),
            submitButton = $("#submit"),
            modalContent = $(".modal-content");

    public RegistrationPage openPage() {
        open(Url);
        return this;
    }

    public RegistrationPage setFirstAndLastName(String firstName, String lastName) {
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage selectGender(String gender) {
        genderWrapper.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String number) {
        userNumberInput.setValue(number);
        return this;
    }

    public RegistrationPage setDateOfBirth(LocalDate date) {
        dateOfBirthInput.click();
        String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        // July, December, etc.

        monthSelect.selectOption(monthName);
        yearSelect.selectOption(String.valueOf(date.getYear()));
        $(".react-datepicker__day--0" + String.format("%02d", date.getDayOfMonth()) +
          ":not(.react-datepicker__day--outside-month)").click();
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage selectHobby() {
        hobbiesCheckbox1.click();
        return this;
    }

    public RegistrationPage uploadPicture(String fileName) {
        uploadPictureInput.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setCurrentAddress(String address) {
        currentAddressInput.setValue(address);
        return this;
    }

    public RegistrationPage selectStateAndCity(String state, String city) {
        stateSelect.click();
        $(byText(state)).click();
        citySelect.click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.click();
        return this;
    }

    public RegistrationPage checkResultVisible() {
        modalContent.shouldBe(visible);
        return this;
    }
}
