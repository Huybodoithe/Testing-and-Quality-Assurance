public class ScholarshipService {
    public static String evaluateScholarship(double gpa, int drl, int stc) {
        if (gpa < 0.0 || gpa > 4.0
                || drl < 0 || drl > 100
                || stc < 1 || stc > 30) {
            return "Invalid";
        }

        if (stc < 15) {
            return "Khong du dieu kien";
        }

        // Cấy lỗi xét học bổng Giỏi (gpa >= 3.2 && drl >= 80) trước
        // Nên học bổng xuất sắc không bao giờ được xét
        if (gpa >= 3.2 && drl >= 80) {
            return "Gioi";
        }

        if (gpa >= 3.6 && drl >= 90) {
            return "Xuat sac";
        }

        return "Khong du dieu kien";
    }
}
