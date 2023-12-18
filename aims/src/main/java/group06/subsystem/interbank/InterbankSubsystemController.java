package group06.subsystem.interbank;

import java.util.Map;

import group06.common.exception.InternalServerErrorException;
import group06.common.exception.InvalidCardException;
import group06.common.exception.InvalidTransactionAmountException;
import group06.common.exception.InvalidVersionException;
import group06.common.exception.NotEnoughBalanceException;
import group06.common.exception.NotEnoughTransactionInfoException;
import group06.common.exception.SuspiciousTransactionException;
import group06.common.exception.UnrecognizedException;
import group06.entity.payment.CreditCard;
import group06.entity.payment.PaymentTransaction;
import group06.utils.Configs;
import group06.utils.MyMap;
import group06.utils.Utils;

public class InterbankSubsystemController {

    private static final String PUBLIC_KEY = "AQzdE8O/fR8=";
    private static final String SECRET_KEY = "BUXj/7/gHHI=";
    private static final String PAY_COMMAND = "pay";
    private static final String VERSION = "1.0.0";

    private static InterbankBoundary interbankBoundary = new InterbankBoundary();

    // Cohesion theo mô hình logic
    // Comment: Hàm refund có cohesion theo mô hình logic, chỉ thực hiện một nhiệm vụ cụ thể.
    public PaymentTransaction refund(CreditCard card, int amount, String contents) {
        return null;
    }

    // Cohesion tuần tự vì là output của hàm interbankBoundary.query
    // Comment: Hàm generateData có cohesion tuần tự vì chỉ thực hiện một công việc cụ thể, là tạo dữ liệu từ Map thành chuỗi JSON.
    private String generateData(Map<String, Object> data) {
        return ((MyMap) data).toJSON();
    }

    // Vi phạm SRP: Hàm này thực hiện nhiều công việc, bao gồm cả tạo giao dịch, gửi yêu cầu và xử lý kết quả.
    // Comment: Hàm payOrder vi phạm nguyên lý Single Responsibility Principle (SRP) vì thực hiện quá nhiều công việc.
    public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
        Map<String, Object> transaction = new MyMap();

        try {
            transaction.putAll(MyMap.toMyMap(card));
        } catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            // Comment: Xử lý ngoại lệ InvalidCardException khi có lỗi với dữ liệu thẻ.
            throw new InvalidCardException();
        }
        transaction.put("command", PAY_COMMAND);
        transaction.put("transactionContent", contents);
        transaction.put("amount", amount);
        transaction.put("createdAt", Utils.getToday());

        Map<String, Object> requestMap = new MyMap();
        requestMap.put("version", VERSION);
        requestMap.put("transaction", transaction);

        String responseText = interbankBoundary.query(Configs.PROCESS_TRANSACTION_URL, generateData(requestMap));
        MyMap response = null;
        try {
            response = MyMap.toMyMap(responseText, 0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            // Comment: Xử lý ngoại lệ UnrecognizedException khi không thể chuyển đổi chuỗi thành MyMap.
            throw new UnrecognizedException();
        }

        return makePaymentTransaction(response);
    }

    // Vi phạm SRP: Hàm này thực hiện nhiều công việc, bao gồm cả tạo PaymentTransaction và xử lý lỗi.
    // Comment: Hàm makePaymentTransaction vi phạm nguyên lý Single Responsibility Principle (SRP) vì thực hiện quá nhiều công việc.
    private PaymentTransaction makePaymentTransaction(MyMap response) {
        if (response == null)
            return null;
        MyMap transaction = (MyMap) response.get("transaction");
        CreditCard card = new CreditCard((String) transaction.get("cardCode"), (String) transaction.get("owner"),
                Integer.parseInt((String) transaction.get("cvvCode")), (String) transaction.get("dateExpired"));
        PaymentTransaction trans = new PaymentTransaction((String) response.get("errorCode"), card,
                (String) transaction.get("transactionId"), (String) transaction.get("transactionContent"),
                Integer.parseInt((String) transaction.get("amount")), (String) transaction.get("createdAt"));

        switch (trans.getErrorCode()) {
            case "00":
                break;
            case "01":
                // Comment: Xử lý ngoại lệ InvalidCardException khi thẻ không hợp lệ.
                throw new InvalidCardException();
            case "02":
                // Comment: Xử lý ngoại lệ NotEnoughBalanceException khi không đủ số dư trong tài khoản.
                throw new NotEnoughBalanceException();
            case "03":
                // Comment: Xử lý ngoại lệ InternalServerErrorException khi có lỗi từ hệ thống nội bộ.
                throw new InternalServerErrorException();
            case "04":
                // Comment: Xử lý ngoại lệ SuspiciousTransactionException khi giao dịch nghi ngờ.
                throw new SuspiciousTransactionException();
            case "05":
                // Comment: Xử lý ngoại lệ NotEnoughTransactionInfoException khi thiếu thông tin giao dịch.
                throw new NotEnoughTransactionInfoException();
            case "06":
                // Comment: Xử lý ngoại lệ InvalidVersionException khi phiên bản không hợp lệ.
                throw new InvalidVersionException();
            case "07":
                // Comment: Xử lý ngoại lệ InvalidTransactionAmountException khi số tiền giao dịch không hợp lệ.
                throw new InvalidTransactionAmountException();
            default:
                // Comment: Xử lý ngoại lệ UnrecognizedException khi mã lỗi không được nhận diện.
                throw new UnrecognizedException();
        }

        return trans;
    }
}
