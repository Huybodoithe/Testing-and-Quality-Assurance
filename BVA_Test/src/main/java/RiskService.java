public class RiskService {
    public static String evaluateRisk(int creditScore, int term) {
        // Kiểm tra miền giá trị hợp lệ toàn cục
        if (creditScore < 300 || creditScore > 850 || term < 6 || term > 60) {
            return "Invalid";
        }

        // Điểm tín dụng thấp: 300 <= credit_score < 580
        if (creditScore < 580) {
            return "High Risk";
        }

        // Điểm tín dụng trung bình: 580 <= credit_score < 700
        if (creditScore < 700) {
            if (term <= 24) { // 6 <= term <= 24: kỳ hạn ngắn
                return "Low Risk";
            } else {          // 25 <= term <= 60: kỳ hạn dài
                return "High Risk";
            }
        }

        // Điểm tín dụng cao: 700 <= credit_score <= 850
        return "Low Risk";
    }
}
