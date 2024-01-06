package test;
import com.example.aims.exception.SignupFailedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.example.aims.entity.account.Account;
public class AccountTest {

private Account account= new Account("Test Name", "testUsername", "TestPassword123!", "12/05/2002", "0123456789");;    

    
    // Đặt mật khẩu đúng định dạng
    @Test
    public void test1() {
       
       Account account1 = new Account("Test Name", "testUsername", "TestPassword1!", "12/05/2002", "0123456789");
        boolean isValid = account.validateLoginInformation();

        assertTrue(isValid);
    }
   // Đặt mật khẩu không đúng định dạng
    @Test
    public void test2() {
     
        account = new Account("Test Name", "testUsername", "123456789123145678", "12/05/2002", "0123456789");
        try {
			 account.validateSignUpInformation();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Sai format mật khẩu");
		    }
     
    }
  // Đặt thông tin user đúng
    @Test
    public void test3() {
        account = new Account("Test Name", "testUsername", "Nam1234!", "12/05/2002", "0123456789");
       
        boolean isValid = account.validateSignUpInformation();

        assertTrue(isValid);
    }
// Kiểm tra username sai
    @Test
    public void test4() {
      String message = "";
        account = new Account("Test Name", "Nam", "Nam0123!", "12/05/2002", "0123456789");
        try {
			 account.validateSignUpInformation();
       
		    } catch (Exception e) {
		    message = e.getMessage();
		      
		    }
        assertEquals(message, "Tài khoản phải có độ dài từ 8-20 kí tự");
    }
// Kiểm tra username sai
    @Test
    public void test41() {
      String message = "";
        account = new Account("Test Name", "Nam1111111111111111111111111111111111111111111111111111111111111111111111", "Nam0123!", "12/05/2002", "0123456789");
        try {
			 account.validateSignUpInformation();
       
		    } catch (Exception e) {
		    message = e.getMessage();
		      
		    }
        assertEquals(message, "Tài khoản phải có độ dài từ 8-20 kí tự");
    }
// Kiểm tra tên sai
    @Test
    public void test6() {
    account = new Account("Tes11111111111111111111111111111111111111111111111", "testUsername", "123456789", "12/05/2002", "0123456789");
        try {
			 account.validateSignUpInformation();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Tên không được vượt quá 30 kí tự");
		    }
       
    }
// Kiểm tra số điện thoại sai
 @Test
    public void test7() {
    account = new Account("Test Name", "testUsername", "123456789", "12/05/2002", "01789");
        try {
			 account.validateSignUpInformation();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Số điện thoại không hợp lệ");
		    }
       
    }
    // Kiểm tra số điện thoại sai
 @Test
    public void test8() {
    account = new Account("Test Name", "testUsername", "123456789", "12/05/2002", "01789999999999999999");
        try {
			 account.validateSignUpInformation();
		    } catch (Exception e) {
		     
		      assertEquals(e.getMessage(), "Số điện thoại không hợp lệ");
		    }
       
    }
}
