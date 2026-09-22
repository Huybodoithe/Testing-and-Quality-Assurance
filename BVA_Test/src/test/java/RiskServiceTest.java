import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskServiceTest {

    @ParameterizedTest(name = "{0}: credit_score={1}, term={2} -> {3}")
    @CsvSource({
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
            "TC13, 575,  5, Invalid",
            "TC14, 575,  6, High Risk",
            "TC15, 575,  7, High Risk",
            "TC16, 575, 23, High Risk",
            "TC17, 575, 24, High Risk",
            "TC18, 575, 25, High Risk",
            "TC19, 575, 59, High Risk",
            "TC20, 575, 60, High Risk",
            "TC21, 575, 61, Invalid",
            "TC22, 575, 33, High Risk"
    })
    @DisplayName("Kiểm thử biên mạnh đánh giá mức độ rủi ro gói vay")
    void testEvaluateRisk(String testId, int creditScore, int term, String expectedOutput) {
        String actualOutput = RiskService.evaluateRisk(creditScore, term);
        assertEquals(expectedOutput, actualOutput, "Thất bại tại ca kiểm thử " + testId);
    }
}