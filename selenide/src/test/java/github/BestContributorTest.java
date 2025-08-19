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
        // ��������� ������� ���� �������� (null = ������ �� ���������)
        Configuration.browserSize = null;
    }

    @Test
    public void testBestContributor() {
        // ��������� �������� ����������� Selenide �� GitHub
        open("https://github.com/selenide/selenide");

        // ������� ���� � �������� "Contributors"
        var contributorsBlock = $(".BorderGrid")
                .$(Selectors.byText("Contributors"))
                .ancestor(".BorderGrid-row");

        // ����� ������� ��������� �� ������
        var firstContributor = contributorsBlock.$$("ul li").first();

        // ������� ������ �� ������ ���������
        firstContributor.hover();

        // ���������, ��� ��������� ����������� ���� � ������ ���������
        $(".Popover").shouldHave(Condition.text("Andrei Solntsev"));

        sleep(5000); // ����� ��� ���������� ��������
    }
}
