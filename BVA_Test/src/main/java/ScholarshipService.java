public class ScholarshipService {
    public static String evaluateScholarship(double gpa, int drl) {
        // Làm tròn lấy 1 chữ số thập phân để tránh lỗi sai số dấu phẩy động của double
        double roundedGpa = Math.round(gpa * 10.0) / 10.0;

        // 1. Kiểm tra miền giá trị hợp lệ toàn cục
        if (roundedGpa < 0.0 || roundedGpa > 4.0 || drl < 0 || drl > 100) {
            return "Invalid";
        }

        // 2. Học bổng Giỏi: GPA >= 3.2 và ĐRL >= 80
        if (roundedGpa >= 3.2 && drl >= 80) {
            return "Gioi";
        }

        // 3. Học bổng Xuất sắc: GPA >= 3.6 và ĐRL >= 90
        if (roundedGpa >= 3.6 && drl >= 90) {
            return "Xuat Sac";
        }



        // 4. Các trường hợp hợp lệ còn lại không đủ điều kiện nhận học bổng
        return "Khong du dieu kien";
    }
}
