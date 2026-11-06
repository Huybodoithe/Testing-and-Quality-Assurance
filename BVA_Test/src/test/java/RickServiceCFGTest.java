import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskServiceCFGTest {

    /*
     * ============================================================
     * KIỂM THỬ ĐỒ THỊ LUỒNG ĐIỀU KHIỂN (CFG - CONTROL FLOW GRAPH)
     * ============================================================
     */
    @ParameterizedTest(name = "{0}: credit_score={1}, term={2} -> {3} ({4})")
    @DisplayName("Kiểm thử đồ thị luồng điều khiển (CFG)")
    @CsvSource({
            // TC01: 1(T); 5
            "TC01, 300, 12, High Risk, 1(T); 5",

            // TC02: 1(F); 2(T); 5
            "TC02, 850, 12, Low Risk, 1(F); 2(T); 5",

            // TC03: 1(F); 2(F); 3(T); 5
            "TC03, 600, 5, Invalid, 1(F); 2(F); 3(T); 5",

            // TC04: 1(F); 2(F); 3(F); 4(T); 5
            "TC04, 600, 72, Invalid, 1(F); 2(F); 3(F); 4(T); 5",

            // TC05: 1(F); 2(F); 3(F); 4(F); 6(T); 7
            "TC05, 500, 12, High Risk, 1(F); 2(F); 3(F); 4(F); 6(T); 7",

            // TC06: 1(F); 2(F); 3(F); 4(F); 6(F); 8(T); 9(T); 10
            "TC06, 700, 12, Low Risk, 1(F); 2(F); 3(F); 4(F); 6(F); 8(T); 9(T); 10",

            // TC07: 1(F); 2(F); 3(F); 4(F); 6(F); 8(T); 9(F); 11
            "TC07, 700, 48, Low Risk, 1(F); 2(F); 3(F); 4(F); 6(F); 8(T); 9(F); 11",

            // TC08: 1(F); 2(F); 3(F); 4(F); 6(F); 8(F); 12
            "TC08, 800, 12, Low Risk, 1(F); 2(F); 3(F); 4(F); 6(F); 8(F); 12"
    })
    void testControlFlowGraph(
            String testId,
            int creditScore,
            int term,
            String expectedOutput,
            String testPath
    ) {
        String actualOutput =
                RiskService.evaluateRisk(creditScore, term);

        assertEquals(
                expectedOutput,
                actualOutput,
                "Thất bại tại ca kiểm thử " + testId + " với đường đi " + testPath
        );
    }
}