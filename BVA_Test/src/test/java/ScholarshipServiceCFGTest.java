import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScholarshipServiceCFGTest {

    @ParameterizedTest(name = "{0}: gpa={1}, drl={2}, stc={3} -> {4} ({5})")
    @DisplayName("Kiểm thử đồ thị luồng điều khiển (CFG)")
    @CsvSource({
            // TC01: 1(T); 7
            "TC01, -1.0, 50, 15, Invalid, 1(T); 7",

            // TC02: 1(F); 2(T); 7
            "TC02,  5.0, 50, 15, Invalid, 1(F); 2(T); 7",

            // TC03: 1(F); 2(F); 3(T); 7
            "TC03,  2.0, -1, 15, Invalid, 1(F); 2(F); 3(T); 7",

            // TC04: 1(F); 2(F); 3(F); 4(T); 7
            "TC04,  2.0, 200, 15, Invalid, 1(F); 2(F); 3(F); 4(T); 7",

            // TC05: 1(F); 2(F); 3(F); 4(F); 5(T); 7
            "TC05,  2.0, 50, 0, Invalid, 1(F); 2(F); 3(F); 4(F); 5(T); 7",

            // TC06: 1(F); 2(F); 3(F); 4(F); 5(F); 6(T); 7
            "TC06,  2.0, 50, 35, Invalid, 1(F); 2(F); 3(F); 4(F); 5(F); 6(T); 7",

            // TC07: 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(T); 9
            "TC07,  2.0, 50, 10, Khong du dieu kien, 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(T); 9",

            // TC08: 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(F); 10(T); 11(T); 12
            "TC08,  3.4, 90, 20, Gioi, 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(F); 10(T); 11(T); 12",

            // TC09: 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(F); 10(T); 11(F); 13(T); 14(F); 16
            "TC09,  3.8, 50, 20, Khong du dieu kien, 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(F); 10(T); 11(F); 13(T); 14(F); 16",

            // TC10: 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(F); 10(T); 11(F); 13(F); 16
            "TC10,  3.4, 50, 20, Khong du dieu kien, 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(F); 10(T); 11(F); 13(F); 16",

            // TC11: 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(F); 10(F); 13(F); 16
            "TC11,  2.0, 50, 20, Khong du dieu kien, 1(F); 2(F); 3(F); 4(F); 5(F); 6(F); 8(F); 10(F); 13(F); 16"
    })
    void testControlFlowGraph(
            String testId,
            double gpa,
            int drl,
            int stc,
            String expectedOutput,
            String testPath
    ) {
        String actualOutput =
                ScholarshipService.evaluateScholarship(gpa, drl, stc);

        assertEquals(
                expectedOutput,
                actualOutput,
                "Thất bại tại ca kiểm thử " + testId + " với luồng " + testPath
        );
    }
}