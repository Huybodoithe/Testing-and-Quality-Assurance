import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskServiceTest {

    @ParameterizedTest(name = "{0}: credit_score={1}, term={2} -> {3}")
    @CsvSource({
            "TC01, 299, 30, Invalid",
            "TC02, 300, 30, High Risk",
            "TC03, 301, 30, High Risk",
            "TC04, 579, 30, High Risk",
            "TC05, 580, 30, High Risk",
            "TC06, 581, 30, High Risk",
            "TC07, 699, 30, High Risk",
            "TC08, 700, 30, Low Risk",
            "TC09, 701, 30, Low Risk",
            "TC10, 849, 30, Low Risk",
            "TC11, 850, 30, Low Risk",
            "TC12, 851, 30, Invalid",
            "TC13, 600,  5, Invalid",
            "TC14, 600,  6, Low Risk",
            "TC15, 600,  7, Low Risk",
            "TC16, 600, 23, Low Risk",
            "TC17, 600, 24, Low Risk",
            "TC18, 600, 25, High Risk",
            "TC19, 600, 59, High Risk",
            "TC20, 600, 60, High Risk",
            "TC21, 600, 61, Invalid",
            "TC22, 600, 30, High Risk"
    })
    @DisplayName("Kiểm thử biên mạnh đánh giá mức độ rủi ro gói vay")
    void testEvaluateRisk(String testId, int creditScore, int term, String expectedOutput) {
        String actualOutput = RiskService.evaluateRisk(creditScore, term);
        assertEquals(expectedOutput, actualOutput, "Thất bại tại ca kiểm thử " + testId);
    }
}