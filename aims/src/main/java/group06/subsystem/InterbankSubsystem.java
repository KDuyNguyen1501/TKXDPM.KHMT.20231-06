package group06.subsystem;

import group06.common.exception.InternalServerErrorException;
import group06.common.exception.InvalidCardException;
import group06.common.exception.NotEnoughBalanceException;
import group06.entity.payment.CreditCard;
import group06.entity.payment.PaymentTransaction;
import group06.subsystem.interbank.InterbankSubsystemController;

/***
 * Lớp {@code InterbankSubsystem} được sử dụng để giao tiếp với Interbank để thực hiện giao dịch.
 * 
 * @author hieud
 *
 */
public class InterbankSubsystem implements InterbankInterface {

    /**
     * Đại diện cho bộ điều khiển của hệ thống con
     */
    private InterbankSubsystemController ctrl;

    /**
     * Khởi tạo một đối tượng {@code InterbankSubsystem} mới để đại diện cho hệ thống con của Interbank.
     */
    public InterbankSubsystem() {
        String str = new String();
        this.ctrl = new InterbankSubsystemController();
    }

    /**
     * @see InterbankInterface#payOrder(entity.payment.CreditCard, int,
     *      java.lang.String)
     */
    // Vi phạm SRP: Phương thức này thực hiện quá nhiều công việc, bao gồm cả gọi hàm từ controller và trả về kết quả.
    // Comment: Phương thức payOrder vi phạm nguyên lý Single Responsibility Principle (SRP) vì thực hiện quá nhiều công việc.
    // Nên xem xét chia thành các phương thức nhỏ để mỗi phương thức chỉ thực hiện một nhiệm vụ cụ thể.
    public PaymentTransaction payOrder(CreditCard card, int amount, String contents) {
        PaymentTransaction transaction = ctrl.payOrder(card, amount, contents);
        return transaction;
    }

    /**
     * @see InterbankInterface#refund(entity.payment.CreditCard, int,
     *      java.lang.String)
     */
    // Vi phạm SRP: Phương thức này thực hiện quá nhiều công việc, bao gồm cả gọi hàm từ controller và trả về kết quả.
    // Comment: Phương thức refund vi phạm nguyên lý Single Responsibility Principle (SRP) vì thực hiện quá nhiều công việc.
    // Nên xem xét chia thành các phương thức nhỏ để mỗi phương thức chỉ thực hiện một nhiệm vụ cụ thể.
    public PaymentTransaction refund(CreditCard card, int amount, String contents) {
        PaymentTransaction transaction = ctrl.refund(card, amount, contents);
        return transaction;
    }
}
