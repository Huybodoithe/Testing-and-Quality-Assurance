import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusServiceTest {

    /*
     * ============================================================
     * 1. KIỂM THỬ GIÁ TRỊ BIÊN - BOUNDARY VALUE ANALYSIS
     * ============================================================
     */

    @ParameterizedTest(name = "{0}: kpi={1}, td={2}, thang={3} -> {4}")
    @DisplayName("Kiểm thử giá trị biên mạnh")
    @CsvSource({
            // ===== Biên của KPI =====
            "TC01, -0.1, 50,  8, Invalid",
            "TC02,  0.0, 50,  8, Khong",
            "TC03,  0.1, 50,  8, Khong",
            "TC04, 74.9, 50,  8, Khong",
            "TC05, 75.0, 50,  8, Khong",
            "TC06, 75.1, 50,  8, Khong",
            "TC07, 89.9, 50,  8, Khong",
            "TC08, 90.0, 50,  8, Khong",
            "TC09, 90.1, 50,  8, Khong",
            "TC10, 99.9, 50,  8, Khong",
            "TC11, 100.0, 50,  8, Khong",
            "TC12, 100.1, 50,  8, Invalid",

            // ===== Biên của TD =====
            "TC13, 50.0, -1,  8, Invalid",
            "TC14, 50.0,  0,  8, Khong",
            "TC15, 50.0,  1,  8, Khong",
            "TC16, 50.0, 79,  8, Khong",
            "TC17, 50.0, 80,  8, Khong",
            "TC18, 50.0, 81,  8, Khong",
            "TC19, 50.0, 99,  8, Khong",
            "TC20, 50.0, 100, 8, Khong",
            "TC21, 50.0, 101, 8, Invalid",

            // ===== Biên của THANG =====
            "TC22, 50.0, 50,  0, Invalid",
            "TC23, 50.0, 50,  1, Khong",
            "TC24, 50.0, 50,  2, Khong",
            "TC25, 50.0, 50,  5, Khong",
            "TC26, 50.0, 50,  6, Khong",
            "TC27, 50.0, 50,  7, Khong",
            "TC28, 50.0, 50, 11, Khong",
            "TC29, 50.0, 50, 12, Khong",
            "TC30, 50.0, 50, 13, Invalid",

            // ===== Giá trị bình thường =====
            "TC31, 50.0, 50,  8, Khong"
    })
    void testBoundaryValue(
            String testId,
            double kpi,
            int td,
            int thang,
            String expectedOutput
    ) {
        String actualOutput = BonusService.evaluateBonus(kpi, td, thang);
        assertEquals(expectedOutput, actualOutput, "Thất bại tại ca kiểm thử " + testId);
    }


    /*
     * ============================================================
     * 2. KIỂM THỬ PHÂN HOẠCH TƯƠNG ĐƯƠNG
     * ============================================================
     */

    @ParameterizedTest(name = "{0}: kpi={1}, td={2}, thang={3} -> {4}")
    @DisplayName("Kiểm thử phân hoạch tương đương")
    @CsvSource({
            // D1 - Miền không hợp lệ (td không hợp lệ)
            "TC01, 50.0, 200, 8, Invalid",

            // D2 - Không đạt thưởng
            "TC02, 50.0,  40, 8, Khong",

            // D3 - Thưởng tiêu chuẩn
            "TC03, 82.0,  90, 8, Tieu chuan",

            // D4 - Thưởng xuất sắc
            "TC04, 95.0,  90, 8, Xuat sac"
    })
    void testEquivalencePartition(
            String testId,
            double kpi,
            int td,
            int thang,
            String expectedOutput
    ) {
        String actualOutput = BonusService.evaluateBonus(kpi, td, thang);
        assertEquals(expectedOutput, actualOutput, "Thất bại tại ca kiểm thử " + testId);
    }


    /*
     * ============================================================
     * 3. KIỂM THỬ BẢNG QUYẾT ĐỊNH
     * ============================================================
     */

    @ParameterizedTest(name = "{0}: kpi={1}, td={2}, thang={3} -> {4}")
    @DisplayName("Kiểm thử bảng quyết định")
    @CsvSource({
            // Quy tắc 1: KPI không hợp lệ
            "TC01, -1.0, 50,  8, Invalid",

            // Quy tắc 2: TD không hợp lệ
            "TC02, 50.0, 200, 8, Invalid",

            // Quy tắc 3: THANG không hợp lệ
            "TC03, 50.0, 50, -1, Invalid",

            // Quy tắc 4: KPI < 75.0
            "TC04, 37.5, 50,  8, Khong",

            // Quy tắc 5: TD < 80
            "TC05, 50.0, 40,  8, Khong",

            // Quy tắc 6: THANG < 6
            "TC06, 50.0, 50,  3, Khong",

            // Quy tắc 7: Đạt điều kiện thưởng Tieu chuan
            "TC07, 82.0, 90,  8, Tieu chuan",

            // Quy tắc 8: Đạt điều kiện thưởng Xuat sac
            "TC08, 95.0, 90,  8, Xuat sac"
    })
    void testDecisionTable(
            String testId,
            double kpi,
            int td,
            int thang,
            String expectedOutput
    ) {
        String actualOutput = BonusService.evaluateBonus(kpi, td, thang);
        assertEquals(expectedOutput, actualOutput, "Thất bại tại ca kiểm thử " + testId);
    }
}