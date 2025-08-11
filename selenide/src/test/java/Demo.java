import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Demo {
    @Test
    void firstTest() {

        LocalDate localDate = LocalDate.parse("22.10.2020", DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        assertEquals(DayOfWeek.THURSDAY, localDate.getDayOfWeek());


    }

}

