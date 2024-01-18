package controller;

import common.exception.LoginFailedException;
import entity.db.AIMSDB;
import entity.user.Account;
import views.screen.popup.PopupScreen;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManagementController extends BaseController {
    private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

    public static void createAcc(Account acc) {
        try {

            String sql = "SELECT * FROM " +
                    "User " +
                    "where username = '" + acc.getUsername() + "';";
            Statement stm = AIMSDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                try {
                    PopupScreen.error("Tài khoản đã tồn tại!");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String sql = "INSERT INTO User (name,username, password,birthDate,phoneNumber,role) VALUES ('"
                    + acc.getName() + "','"
                    + acc.getUsername() + "','"
                    + acc.getPassword() + "','"
                    + acc.getBirthDate() + "','"
                    + acc.getPhone() + "','"
                    + acc.getRole()
                    + "');";
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateAcc(Account acc) {
        try {

            String sql = "SELECT * FROM " +
                    "User " +
                    "where username = '" + acc.getUsername() + "';";
            Statement stm = AIMSDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery(sql);
            if (res.next()) {
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            String sql = "UPDATE User SET name = '"
                    + acc.getName() + "', username = '"
                    + acc.getUsername() + "', password = '"
                    + acc.getPassword() + "', birthDate = '"
                    + acc.getBirthDate() + "', phoneNumber = '"
                    + acc.getPhone() + "', role = '"
                    + acc.getRole()
                    + "' WHERE id = " + acc.getId() + ";";

            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delAcc(int id) {
        try {
            String sql = "DELETE FROM User WHERE id = " + id + ";";
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Account> getAllAccounts() throws SQLException {
        List<Account> accountList = new ArrayList<>();
        System.out.println("///////");

        String sql = "SELECT * FROM User;";
        try (Statement stm = AIMSDB.getConnection().createStatement();
             ResultSet res = stm.executeQuery(sql)) {

            while (res.next()) {
                Account account = new Account(res.getInt("id"), res.getString("name"), res.getString("username"),
                        res.getString("password"), res.getString("birthdate"), res.getString("phoneNumber"), res.getInt("role"));
                accountList.add(account);
            }
        }

        if (accountList.isEmpty()) {
            throw new LoginFailedException("Không tồn tại tài khoản");
        }
        System.out.println("///////");

        return accountList;
    }

    public static List<Account> getAllAccountsByRole(int role) throws SQLException {
        List<Account> accountList = new ArrayList<>();
        System.out.println("///////");

        String sql = "SELECT * FROM User WHERE role = " + role + ";";
        try (Statement stm = AIMSDB.getConnection().createStatement();
             ResultSet res = stm.executeQuery(sql)) {

            while (res.next()) {
                Account account = new Account(res.getInt("id"), res.getString("name"), res.getString("username"),
                        res.getString("password"), res.getString("birthdate"), res.getString("phoneNumber"), res.getInt("role"));
                accountList.add(account);
            }
        }

        if (accountList.isEmpty()) {
            throw new LoginFailedException("Không tồn tại tài khoản");
        }
        System.out.println("///////");

        return accountList;
    }

    public static boolean validateLoginInformation(String password) {
        String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        } else {
            try {
                PopupScreen.error("Sai format mật khẩu!");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            throw new LoginFailedException("Sai format mật khẩu");
        }
    }
}
