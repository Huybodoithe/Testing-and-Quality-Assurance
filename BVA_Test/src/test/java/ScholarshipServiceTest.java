import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScholarshipServiceTest {

    /*
     * ============================================================
     * 1. KIỂM THỬ GIÁ TRỊ BIÊN - BOUNDARY VALUE ANALYSIS
     * ============================================================
     */

    @ParameterizedTest(name = "{0}: gpa={1}, drl={2}, stc={3} -> {4}")
    @DisplayName("Kiểm thử giá trị biên mạnh")
    @CsvSource({
            // ===== Biên của GPA =====
            "TC01, -0.1, 50, 17, Invalid",
            "TC02,  0.0, 50, 17, Khong du dieu kien",
            "TC03,  0.1, 50, 17, Khong du dieu kien",
            "TC04,  3.1, 50, 17, Khong du dieu kien",
            "TC05,  3.2, 50, 17, Khong du dieu kien",
            "TC06,  3.3, 50, 17, Khong du dieu kien",
            "TC07,  3.5, 50, 17, Khong du dieu kien",
            "TC08,  3.6, 50, 17, Khong du dieu kien",
            "TC09,  3.7, 50, 17, Khong du dieu kien",
            "TC10,  3.9, 50, 17, Khong du dieu kien",
            "TC11,  4.0, 50, 17, Khong du dieu kien",
            "TC12,  4.1, 50, 17, Invalid",

            // ===== Biên của DRL =====
            "TC13, 2.0, -1, 17, Invalid",
            "TC14, 2.0,  0, 17, Khong du dieu kien",
            "TC15, 2.0,  1, 17, Khong du dieu kien",
            "TC16, 2.0, 79, 17, Khong du dieu kien",
            "TC17, 2.0, 80, 17, Khong du dieu kien",
            "TC18, 2.0, 81, 17, Khong du dieu kien",
            "TC19, 2.0, 89, 17, Khong du dieu kien",
            "TC20, 2.0, 90, 17, Khong du dieu kien",
            "TC21, 2.0, 91, 17, Khong du dieu kien",
            "TC22, 2.0, 99, 17, Khong du dieu kien",
            "TC23, 2.0, 100, 17, Khong du dieu kien",
            "TC24, 2.0, 101, 17, Invalid",

            // ===== Biên của STC =====
            "TC25, 2.0, 50,  0, Invalid",
            "TC26, 2.0, 50,  1, Khong du dieu kien",
            "TC27, 2.0, 50,  2, Khong du dieu kien",
            "TC28, 2.0, 50, 14, Khong du dieu kien",
            "TC29, 2.0, 50, 15, Khong du dieu kien",
            "TC30, 2.0, 50, 16, Khong du dieu kien",
            "TC31, 2.0, 50, 29, Khong du dieu kien",
            "TC32, 2.0, 50, 30, Khong du dieu kien",
            "TC33, 2.0, 50, 31, Invalid",

            // ===== Giá trị bình thường =====
            "TC34, 2.0, 50, 17, Khong du dieu kien"
    })
    void testBoundaryValue(
            String testId,
            double gpa,
            int drl,
            int stc,
            String expectedOutput
    ) {
        String actualOutput =
                ScholarshipService.evaluateScholarship(gpa, drl, stc);

        assertEquals(
                expectedOutput,
                actualOutput,
                "Thất bại tại ca kiểm thử " + testId
        );
    }


    /*
     * ============================================================
     * 2. KIỂM THỬ PHÂN HOẠCH TƯƠNG ĐƯƠNG
     * ============================================================
     */

    @ParameterizedTest(name = "{0}: gpa={1}, drl={2}, stc={3} -> {4}")
    @DisplayName("Kiểm thử phân hoạch tương đương")
    @CsvSource({
            // D1 - Miền không hợp lệ
            "TC01, 2.0, 101, 17, Invalid",

            // D2 - Học bổng Xuất sắc
            "TC02, 3.8, 95, 17, Xuat sac",

            // D3 - Học bổng Giỏi
            "TC03, 3.4, 90, 17, Gioi",

            // D4 - Không đủ điều kiện
            "TC04, 2.0, 50, 17, Khong du dieu kien"
    })
    void testEquivalencePartition(
            String testId,
            double gpa,
            int drl,
            int stc,
            String expectedOutput
    ) {
        String actualOutput =
                ScholarshipService.evaluateScholarship(gpa, drl, stc);

        assertEquals(
                expectedOutput,
                actualOutput,
                "Thất bại tại ca kiểm thử " + testId
        );
    }


    /*
     * ============================================================
     * 3. KIỂM THỬ BẢNG QUYẾT ĐỊNH
     * ============================================================
     */

    @ParameterizedTest(name = "{0}: gpa={1}, drl={2}, stc={3} -> {4}")
    @DisplayName("Kiểm thử bảng quyết định")
    @CsvSource({
            // Quy tắc 1: GPA không hợp lệ
            "TC01, -1.0, 50, 17, Invalid",

            // Quy tắc 2: DRL không hợp lệ
            "TC02, 2.0, 101, 17, Invalid",

            // Quy tắc 3: STC không hợp lệ
            "TC03, 2.0, 50, -1, Invalid",

            // Quy tắc 4: GPA < 3.2
            "TC04, 2.0, 50, 17, Khong du dieu kien",

            // Quy tắc 5: DRL < 80
            "TC05, 2.0, 40, 17, Khong du dieu kien",

            // Quy tắc 6: STC < 15
            "TC06, 2.0, 50, 7, Khong du dieu kien",

            // Quy tắc 7: GPA thuộc [3.2, 3.6)
            "TC07, 3.4, 90, 17, Gioi",

            // Quy tắc 8: DRL thuộc [80, 90)
            "TC08, 3.8, 85, 17, Gioi",

            // Quy tắc 9: GPA >= 3.6 và DRL >= 90
            "TC09, 3.8, 95, 17, Xuat sac"
    })
    void testDecisionTable(
            String testId,
            double gpa,
            int drl,
            int stc,
            String expectedOutput
    ) {
        String actualOutput =
                ScholarshipService.evaluateScholarship(gpa, drl, stc);

        assertEquals(
                expectedOutput,
                actualOutput,
                "Thất bại tại ca kiểm thử " + testId
        );
    }
}