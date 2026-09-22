public class RiskService {
    public static String evaluateRisk(int credit_score, int term) {
        // Kiểm tra miền giá trị hợp lệ toàn cục
        // Cấy lỗi creditScore sai miền bị Invalid
        if (credit_score <= 300 || credit_score >= 850 || term < 6 || term > 60) {
            return "Invalid";
        }

        // Điểm tín dụng thấp: 300 <= credit_score < 580
        if (credit_score < 580) {
            return "High Risk";
        }

        // Điểm tín dụng trung bình: 580 <= credit_score < 700
        // Cấy lỗi đánh nhầm 700 thành 800
        if (credit_score < 800) {
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
