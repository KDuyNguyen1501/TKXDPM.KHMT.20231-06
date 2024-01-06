package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import entity.user.Account;

public class AccountTest {

  private Account account = new Account("TESTNAME", "testUsername", "IllusionofTime23!", "27/11/2002", "0869116188");;

  // Đặt mật khẩu đúng định dạng
  @Test
  public void checkPass() {
    boolean isValid = account.validateLoginInformation();
    assertTrue(isValid);
  }

  // Đặt mật khẩu không đúng định dạng
  @Test
  public void checkPass2() {

    account = new Account("TESTNAME", "testUsername", "123456789123145678", "27/11/2002", "0869116188");
    try {
      account.validateSignUpInformation();
    } catch (Exception e) {

      assertEquals(e.getMessage(), "Sai format mật khẩu");
    }

  }

  // Đặt thông tin user đúng
  @Test
  public void setUserInfo() {
    account = new Account("TESTNAME", "testUsername", "NTD1234!", "27/11/2002", "0869116188");
    boolean isValid;
    try {
      isValid = account.validateSignUpInformation();
    } catch (SQLException e) {
      isValid = false;
    }
    assertTrue(isValid);
  }

  // Kiểm tra username sai
  @Test
  public void setUserInfo2() {
    String message = "";
    account = new Account("TESTNAME", "NTD", "NTD0123!", "27/11/2002", "0869116188");
    try {
      account.validateSignUpInformation();

    } catch (Exception e) {
      message = e.getMessage();

    }
    assertEquals(message, "Tài khoản phải có độ dài từ 8-20 kí tự");
  }

  // Kiểm tra username sai
  @Test
  public void setUserInfo3() {
    String message = "";
    account = new Account("TESTNAME", "NTD8t8ui48493ri4t3ủi3489ur34ru9439ry932uey7823uê1e23r23e",
        "NTD0123!", "27/11/2002", "0869116188");
    try {
      account.validateSignUpInformation();

    } catch (Exception e) {
      message = e.getMessage();

    }
    assertEquals(message, "Tài khoản phải có độ dài từ 8-20 kí tự");
  }

  // Kiểm tra tên sai
  @Test
  public void setName() {
    account = new Account("Tes8t8ui48493ri4t3ủi3489ur34ru9439ry932uey7823uê1e23r23e11", "testUsername", "123456789",
        "27/11/2002", "0869116188");
    try {
      account.validateSignUpInformation();
    } catch (Exception e) {

      assertEquals(e.getMessage(), "Tên không được vượt quá 30 kí tự");
    }

  }

  // Kiểm tra số điện thoại sai
  @Test
  public void setTel() {
    account = new Account("TESTNAME", "testUsername", "123456789", "27/11/2002", "01789");
    try {
      account.validateSignUpInformation();
    } catch (Exception e) {

      assertEquals(e.getMessage(), "Số điện thoại không hợp lệ");
    }

  }

  // Kiểm tra số điện thoại sai
  @Test
  public void setTel2() {
    account = new Account("TESTNAME", "testUsername", "123456789", "27/11/2002", "01789999999999999999");
    try {
      account.validateSignUpInformation();
    } catch (Exception e) {

      assertEquals(e.getMessage(), "Số điện thoại không hợp lệ");
    }

  }
}
