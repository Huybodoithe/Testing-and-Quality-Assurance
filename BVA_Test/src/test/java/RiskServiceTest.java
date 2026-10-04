import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskServiceTest {

    /*
     * ============================================================
     * 1. KIỂM THỬ GIÁ TRỊ BIÊN - BOUNDARY VALUE ANALYSIS
     * ============================================================
     */

    @ParameterizedTest(name = "{0}: credit_score={1}, term={2} -> {3}")
    @DisplayName("Kiểm thử giá trị biên mạnh")
    @CsvSource({
            // ===== Biên credit_score =====
            "TC01, 299, 33, Invalid",
            "TC02, 300, 33, High Risk",
            "TC03, 301, 33, High Risk",

            "TC04, 579, 33, High Risk",
            "TC05, 580, 33, High Risk",
            "TC06, 581, 33, High Risk",

            "TC07, 699, 33, High Risk",
            "TC08, 700, 33, Low Risk",
            "TC09, 701, 33, Low Risk",

            "TC10, 849, 33, Low Risk",
            "TC11, 850, 33, Low Risk",
            "TC12, 851, 33, Invalid",

            // ===== Biên term =====
            "TC13, 575,  5, Invalid",
            "TC14, 575,  6, High Risk",
            "TC15, 575,  7, High Risk",

            "TC16, 575, 23, High Risk",
            "TC17, 575, 24, High Risk",
            "TC18, 575, 25, High Risk",

            "TC19, 575, 59, High Risk",
            "TC20, 575, 60, High Risk",
            "TC21, 575, 61, Invalid",

            // ===== Giá trị bình thường =====
            "TC22, 575, 33, High Risk"
    })
    void testBoundaryValue(
            String testId,
            int creditScore,
            int term,
            String expectedOutput
    ) {
        String actualOutput =
                RiskService.evaluateRisk(creditScore, term);

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

    @ParameterizedTest(name = "{0}: credit_score={1}, term={2} -> {3}")
    @DisplayName("Kiểm thử phân hoạch tương đương")
    @CsvSource({
            // D1 - Invalid
            "TC01, 200, 33, Invalid",

            // D2 - High Risk
            "TC02, 500, 33, High Risk",

            // D3 - Low Risk
            "TC03, 775, 33, Low Risk"
    })
    void testEquivalencePartition(
            String testId,
            int creditScore,
            int term,
            String expectedOutput
    ) {
        String actualOutput =
                RiskService.evaluateRisk(creditScore, term);

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

    @ParameterizedTest(name = "{0}: credit_score={1}, term={2} -> {3}")
    @DisplayName("Kiểm thử bảng quyết định")
    @CsvSource({
            // Quy tắc 1:
            // credit_score không hợp lệ
            "TC01, 200, 33, Invalid",

            // Quy tắc 2:
            // term không hợp lệ
            "TC02, 575, 0, Invalid",

            // Quy tắc 3:
            // credit_score thấp
            "TC03, 500, 33, High Risk",

            // Quy tắc 4:
            // credit_score trung bình + term ngắn
            "TC04, 640, 15, Low Risk",

            // Quy tắc 5:
            // credit_score trung bình + term dài
            "TC05, 640, 42, High Risk",

            // Quy tắc 6:
            // credit_score cao
            "TC06, 775, 33, Low Risk"
    })
    void testDecisionTable(
            String testId,
            int creditScore,
            int term,
            String expectedOutput
    ) {
        String actualOutput =
                RiskService.evaluateRisk(creditScore, term);

        assertEquals(
                expectedOutput,
                actualOutput,
                "Thất bại tại ca kiểm thử " + testId
        );
    }
}