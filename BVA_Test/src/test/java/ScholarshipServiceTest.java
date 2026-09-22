import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScholarshipServiceTest {

    @ParameterizedTest(name = "{0}: gpa={1}, drl={2} -> {3}")
    @CsvSource({
            "TC01, -0.1, 50, Invalid",
            "TC02,  0.0, 50, Khong du dieu kien",
            "TC03,  0.1, 50, Khong du dieu kien",
            "TC04,  3.1, 50, Khong du dieu kien",
            "TC05,  3.2, 50, Khong du dieu kien",
            "TC06,  3.3, 50, Khong du dieu kien",
            "TC07,  3.5, 50, Khong du dieu kien",
            "TC08,  3.6, 50, Khong du dieu kien",
            "TC09,  3.7, 50, Khong du dieu kien",
            "TC10,  3.9, 50, Khong du dieu kien",
            "TC11,  4.0, 50, Khong du dieu kien",
            "TC12,  4.1, 50, Invalid",
            "TC13,  2.0, -1, Invalid",
            "TC14,  2.0,  0, Khong du dieu kien",
            "TC15,  2.0,  1, Khong du dieu kien",
            "TC16,  2.0, 79, Khong du dieu kien",
            "TC17,  2.0, 80, Khong du dieu kien",
            "TC18,  2.0, 81, Khong du dieu kien",
            "TC19,  2.0, 89, Khong du dieu kien",
            "TC20,  2.0, 90, Khong du dieu kien",
            "TC21,  2.0, 91, Khong du dieu kien",
            "TC22,  2.0, 99, Khong du dieu kien",
            "TC23,  2.0, 100, Khong du dieu kien",
            "TC24,  2.0, 101, Invalid",
            "TC25,  2.0, 50, Khong du dieu kien"
    })
    @DisplayName("Kiểm thử giá trị biên mạnh xét học bổng")
    void testEvaluateScholarship(String testId, double gpa, int drl, String expectedOutput) {
        String actualOutput = ScholarshipService.evaluateScholarship(gpa, drl);
        assertEquals(expectedOutput, actualOutput, "Thất bại tại ca kiểm thử " + testId);
    }
}