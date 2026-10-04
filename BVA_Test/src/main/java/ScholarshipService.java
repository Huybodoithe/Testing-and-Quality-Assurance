public class ScholarshipService {

    public static String evaluateScholarship(double gpa, int drl, int stc) {

        // 1. Kiểm tra miền giá trị hợp lệ
        if (gpa < 0.0 || gpa > 4.0
                || drl < 0 || drl > 100
                || stc < 1 || stc > 30) {
            return "Invalid";
        }

        // 2. Không đủ số tín chỉ để xét học bổng
        if (stc < 15) {
            return "Khong du dieu kien";
        }

        // 3. Học bổng Xuất sắc
        // GPA >= 3.6 và DRL >= 90
        if (gpa >= 3.6 && drl >= 90) {
            return "Xuat sac";
        }

        // 4. Học bổng Giỏi
        // GPA >= 3.2 và DRL >= 80
        if (gpa >= 3.2 && drl >= 80) {
            return "Gioi";
        }

        // 5. Các trường hợp hợp lệ còn lại
        return "Khong du dieu kien";
    }
}