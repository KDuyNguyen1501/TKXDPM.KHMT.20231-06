package group06.utils;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * @author nguyenlm Chứa các cấu hình cho Dự án AIMS
 */
public class Configs {

    // Hằng số API
    public static final String GET_BALANCE_URL = "https://ecopark-system-api.herokuapp.com/api/card/balance/118609_group1_2020";
    public static final String GET_VEHICLECODE_URL = "https://ecopark-system-api.herokuapp.com/api/get-vehicle-code/1rjdfasdfas";
    public static final String PROCESS_TRANSACTION_URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
    public static final String RESET_URL = "https://ecopark-system-api.herokuapp.com/api/card/reset";

    // Dữ liệu mẫu
    public static final String POST_DATA = "{"
            + " \"secretKey\": \"BUXj/7/gHHI=\" ,"
            + " \"transaction\": {"
            + " \"command\": \"pay\" ,"
            + " \"cardCode\": \"118609_group1_2020\" ,"
            + " \"owner\": \"Group 1\" ,"
            + " \"cvvCode\": \"185\" ,"
            + " \"dateExpried\": \"1125\" ,"
            + " \"transactionContent\": \"Pei debt\" ,"
            + " \"amount\": 50000 "
            + "}"
        + "}";
    public static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMTg2MDlfZ3JvdXAxXzIwMjAiLCJpYXQiOjE1OTkxMTk5NDl9.y81pBkM0pVn31YDPFwMGXXkQRKW5RaPIJ5WW5r9OW-Y";

    // Cấu hình database
    public static final String DB_NAME = "aims";
    public static final String DB_USERNAME = System.getenv("DB_USERNAME");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public static String CURRENCY = "VND";
    public static float PERCENT_VAT = 10;

    // Tài nguyên tĩnh
    public static final String IMAGE_PATH = "assets/images";
    public static final String INVOICE_SCREEN_PATH = "/views/fxml/invoice.fxml";
    public static final String INVOICE_MEDIA_SCREEN_PATH = "/views/fxml/media_invoice.fxml";
    public static final String PAYMENT_SCREEN_PATH = "/views/fxml/payment.fxml";
    public static final String RESULT_SCREEN_PATH = "/views/fxml/result.fxml";
    public static final String SPLASH_SCREEN_PATH = "/views/fxml/splash.fxml";
    public static final String CART_SCREEN_PATH = "/views/fxml/cart.fxml";
    public static final String SHIPPING_SCREEN_PATH = "/views/fxml/shipping.fxml";
    public static final String CART_MEDIA_PATH = "/views/fxml/media_cart.fxml";
    public static final String HOME_PATH  = "/views/fxml/home.fxml";
    public static final String HOME_MEDIA_PATH = "/views/fxml/media_home.fxml";
    public static final String POPUP_PATH = "/views/fxml/popup.fxml";

    // VI PHẠM NGUYÊN LÝ SOLID: Các Font được khai báo trực tiếp trong lớp Configs, có thể tạo lớp riêng để quản lý Fonts
    public static Font REGULAR_FONT = Font.font("Segoe UI", FontWeight.NORMAL, FontPosture.REGULAR, 24);

    // VI PHẠM NGUYÊN LÝ SOLID: Dữ liệu liên quan đến các tỉnh thành nên được quản lý bởi một lớp riêng thay vì nằm trong Configs
    public static String[] PROVINCES = { "Bắc Giang", "Bắc Kạn", "Cao Bằng", /* ... Danh sách các tỉnh thành ... */ };
}
