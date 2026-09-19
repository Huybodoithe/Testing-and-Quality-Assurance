import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScholarshipServiceTest {

    @ParameterizedTest(name = "{0}: gpa={1}, drl={2} -> {3}")
    @CsvSource({
            "TC01, -0.1, 95, Invalid",
            "TC02,  0.0, 95, Khong du dieu kien",
            "TC03,  0.1, 95, Khong du dieu kien",
            "TC04,  3.1, 95, Khong du dieu kien",
            "TC05,  3.2, 95, Gioi",
            "TC06,  3.3, 95, Gioi",
            "TC07,  3.5, 95, Gioi",
            "TC08,  3.6, 95, Xuat Sac",
            "TC09,  3.7, 95, Xuat Sac",
            "TC10,  3.9, 95, Xuat Sac",
            "TC11,  4.0, 95, Xuat Sac",
            "TC12,  4.1, 95, Invalid",
            "TC13,  3.4, -1, Invalid",
            "TC14,  3.4,  0, Khong du dieu kien",
            "TC15,  3.4,  1, Khong du dieu kien",
            "TC16,  3.4, 79, Khong du dieu kien",
            "TC17,  3.4, 80, Gioi",
            "TC18,  3.4, 81, Gioi",
            "TC19,  3.4, 89, Gioi",
            "TC20,  3.4, 90, Gioi",
            "TC21,  3.4, 91, Gioi",
            "TC22,  3.4, 99, Gioi",
            "TC23,  3.4, 100, Gioi",
            "TC24,  3.4, 101, Invalid",
            "TC25,  3.4, 95, Gioi"
    })
    @DisplayName("Kiểm thử giá trị biên mạnh xét học bổng")
    void testEvaluateScholarship(String testId, double gpa, int drl, String expectedOutput) {
        String actualOutput = ScholarshipService.evaluateScholarship(gpa, drl);
        assertEquals(expectedOutput, actualOutput, "Thất bại tại ca kiểm thử " + testId);
    }
}