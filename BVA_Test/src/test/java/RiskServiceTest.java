import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RiskServiceTest {

    @Test
    @DisplayName("TC01: credit_score dưới Min toàn cục (299, 30) -> Invalid")
    void testCase01() {
        assertEquals("Invalid", RiskService.evaluateRisk(299, 30));
    }

    @Test
    @DisplayName("TC02: credit_score tại Min toàn cục (300, 30) -> High Risk")
    void testCase02() {
        assertEquals("High Risk", RiskService.evaluateRisk(300, 30));
    }

    @Test
    @DisplayName("TC03: credit_score cận Min (301, 30) -> High Risk")
    void testCase03() {
        assertEquals("High Risk", RiskService.evaluateRisk(301, 30));
    }

    @Test
    @DisplayName("TC04: credit_score cận trên nhóm Thấp (579, 30) -> High Risk")
    void testCase04() {
        assertEquals("High Risk", RiskService.evaluateRisk(579, 30));
    }

    @Test
    @DisplayName("TC05: credit_score chớm Trung bình, kỳ hạn dài (580, 30) -> High Risk")
    void testCase05() {
        assertEquals("High Risk", RiskService.evaluateRisk(580, 30));
    }

    @Test
    @DisplayName("TC06: credit_score trên biên Trung bình (581, 30) -> High Risk")
    void testCase06() {
        assertEquals("High Risk", RiskService.evaluateRisk(581, 30));
    }

    @Test
    @DisplayName("TC07: credit_score cận trên nhóm Trung bình (699, 30) -> High Risk")
    void testCase07() {
        assertEquals("High Risk", RiskService.evaluateRisk(699, 30));
    }

    @Test
    @DisplayName("TC08: credit_score chớm nhóm Cao (700, 30) -> Low Risk")
    void testCase08() {
        assertEquals("Low Risk", RiskService.evaluateRisk(700, 30));
    }

    @Test
    @DisplayName("TC09: credit_score trên biên nhóm Cao (701, 30) -> Low Risk")
    void testCase09() {
        assertEquals("Low Risk", RiskService.evaluateRisk(701, 30));
    }

    @Test
    @DisplayName("TC10: credit_score cận Max toàn cục (849, 30) -> Low Risk")
    void testCase10() {
        assertEquals("Low Risk", RiskService.evaluateRisk(849, 30));
    }

    @Test
    @DisplayName("TC11: credit_score tại Max toàn cục (850, 30) -> Low Risk")
    void testCase11() {
        assertEquals("Low Risk", RiskService.evaluateRisk(850, 30));
    }

    @Test
    @DisplayName("TC12: credit_score vượt Max toàn cục (851, 30) -> Invalid")
    void testCase12() {
        assertEquals("Invalid", RiskService.evaluateRisk(851, 30));
    }

    @Test
    @DisplayName("TC13: term dưới Min toàn cục (600, 5) -> Invalid")
    void testCase13() {
        assertEquals("Invalid", RiskService.evaluateRisk(600, 5));
    }

    @Test
    @DisplayName("TC14: term tại Min toàn cục / Min Ngắn (600, 6) -> Low Risk")
    void testCase14() {
        assertEquals("Low Risk", RiskService.evaluateRisk(600, 6));
    }

    @Test
    @DisplayName("TC15: term cận Min Ngắn (600, 7) -> Low Risk")
    void testCase15() {
        assertEquals("Low Risk", RiskService.evaluateRisk(600, 7));
    }

    @Test
    @DisplayName("TC16: term cận Max Ngắn (600, 23) -> Low Risk")
    void testCase16() {
        assertEquals("Low Risk", RiskService.evaluateRisk(600, 23));
    }

    @Test
    @DisplayName("TC17: term tại Max Ngắn (600, 24) -> Low Risk")
    void testCase17() {
        assertEquals("Low Risk", RiskService.evaluateRisk(600, 24));
    }

    @Test
    @DisplayName("TC18: term chớm chạm kỳ hạn Dài (600, 25) -> High Risk")
    void testCase18() {
        assertEquals("High Risk", RiskService.evaluateRisk(600, 25));
    }

    @Test
    @DisplayName("TC19: term cận Max Dài / Max toàn cục (600, 59) -> High Risk")
    void testCase19() {
        assertEquals("High Risk", RiskService.evaluateRisk(600, 59));
    }

    @Test
    @DisplayName("TC20: term tại Max toàn cục (600, 60) -> High Risk")
    void testCase20() {
        assertEquals("High Risk", RiskService.evaluateRisk(600, 60));
    }

    @Test
    @DisplayName("TC21: term vượt Max toàn cục (600, 61) -> Invalid")
    void testCase21() {
        assertEquals("Invalid", RiskService.evaluateRisk(600, 61));
    }

    @Test
    @DisplayName("TC22: Giá trị danh nghĩa của cả 2 biến (600, 30) -> High Risk")
    void testCase22() {
        assertEquals("High Risk", RiskService.evaluateRisk(600, 30));
    }
}