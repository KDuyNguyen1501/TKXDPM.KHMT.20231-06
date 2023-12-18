package group06.subsystem.interbank;

import group06.common.exception.UnrecognizedException;
import group06.utils.API;

// Vi phạm nguyên lý SOLID:
// Lớp này vi phạm Nguyên tắc Đơn Trách Nhiệm (SRP)
// vì nó đang xử lý cả yêu cầu HTTP (API.post) và xử lý ngoại lệ (UnrecognizedException).
// Nên tách các trách nhiệm này ra thành các lớp khác nhau.

public class InterbankBoundary {

    // Cố định tuần tự do nhận output từ hàm InterbankSubsystemController.generateData.
    // Comment: Phương thức này xử lý cả yêu cầu HTTP và xử lý ngoại lệ,
    // vi phạm Nguyên tắc Đơn Trách Nhiệm (SRP).
    // Nên tách các trách nhiệm này thành các lớp khác nhau là lựa chọn tốt hơn.
	//Sequential cohesion do nhận output từ hàm InterbankSubsystemController.generateData
    String query(String url, String data) {
        String response = null;
        try {
            response = API.post(url, data);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // Comment: Xử lý ngoại lệ ở đây vi phạm Nguyên tắc Đơn Trách Nhiệm (SRP).
            // Nên tốt hơn là xử lý ngoại lệ trong một lớp riêng biệt dành riêng cho quản lý ngoại lệ.
            throw new UnrecognizedException();
        }
        return response;
    }
}