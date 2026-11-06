import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusServiceCFGTest {

    /*
     * ============================================================
     * KIỂM THỬ ĐỒ THỊ LUỒNG ĐIỀU KHIỂN (CFG - CONTROL FLOW GRAPH)
     * BÀI 2: HỆ THỐNG PHÂN LOẠI KHEN THƯỞNG
     * ============================================================
     */
    @ParameterizedTest(name = "{0}: kpi={1}, td={2}, thang={3} -> {4} ({5})")
    @DisplayName("Kiểm thử đồ thị luồng điều khiển (CFG)")
    @CsvSource({
            // TC01: 4c1(T); 7
            // Test kpi < 0.0
            "TC01, -1.0, 50, 8, Invalid, 4c1(T); 7",

            // TC02: 4c1(F); 4c2(T); 7
            // Test kpi > 100.0
            "TC02, 200.0, 50, 8, Invalid, 4c1(F); 4c2(T); 7",

            // TC03: 4c1(F); 4c2(F); 4c3(T); 7
            // Test td < 0
            "TC03, 50.0, -100, 8, Invalid, 4c1(F); 4c2(F); 4c3(T); 7",

            // TC04: 4c1(F); 4c2(F); 4c3(F); 4c4(T); 7
            // Test td > 100
            "TC04, 50.0, 200, 8, Invalid, 4c1(F); 4c2(F); 4c3(F); 4c4(T); 7",

            // TC05: 4c1(F); 4c2(F); 4c3(F); 4c4(F); 4c5(T); 7
            // Test thang < 1
            "TC05, 50.0, 50, -1, Invalid, 4c1(F); 4c2(F); 4c3(F); 4c4(F); 4c5(T); 7",

            // TC06: 4c1(F); 4c2(F); 4c3(F); 4c4(F); 4c5(F); 4c6(T); 7
            // Test thang > 12
            "TC06, 50.0, 50, 20, Invalid, 4c1(F); 4c2(F); 4c3(F); 4c4(F); 4c5(F); 4c6(T); 7",

            // TC07: 4c1(F); ... 4c6(F); 10c(T); 11
            // Test thang < 6
            "TC07, 50.0, 50, 3, Khong, 4c1(F); ... 4c6(F); 10c(T); 11",

            // TC08: 4c1(F); ... 4c6(F); 10c(F); 14c1(T); 14c2(T); 15
            // Test kpi >= 90.0 và td >= 80 (Thưởng xuất sắc)
            "TC08, 95.0, 95, 9, Xuat sac, 4c1(F); ... 10c(F); 14c1(T); 14c2(T); 15",

            // TC09: 4c1(F); ... 4c6(F); 10c(F); 14c1(T); 14c2(F); 19c1(T); 20
            // Test kpi >= 90.0 (True) nhưng td < 80 (False) (Lỗi cấy || sẽ làm nó sai lệch)
            "TC09, 95.0, 40, 9, Khong, 4c1(F); ... 14c1(T); 14c2(F); 19c1(T); 20",

            // TC12: 4c1(F); ... 4c6(F); 10c(F); 14c1(F); 19c1(T); 20
            // Test kpi < 90.0 (False) nhưng >= 75.0 (True)
            "TC12, 82.5, 50, 9, Khong, 4c1(F); ... 10c(F); 14c1(F); 19c1(T); 20"
    })
    void testControlFlowGraph(
            String testId,
            double kpi,
            int td,
            int thang,
            String expectedOutput,
            String testPath
    ) {
        String actualOutput = BonusService.evaluateBonus(kpi, td, thang);

        assertEquals(
                expectedOutput,
                actualOutput,
                "Thất bại tại ca kiểm thử " + testId + " với đường đi " + testPath
        );
    }
}