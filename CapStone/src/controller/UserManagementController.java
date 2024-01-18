package controller;

import common.exception.LoginFailedException;
import entity.db.AIMSDB;
import entity.user.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserManagementController extends BaseController {
    private static Logger LOGGER = utils.Utils.getLogger(PlaceRushOrderController.class.getName());

    public static void createAcc(Account acc){
        try{

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
            String sql = "INSERT INTO User (name,username, password,birthDate,phoneNumber,role) VALUES ('"
                    + acc.getName() + "','"
                    + acc.getUsername() + "','"
                    + acc.getPassword() + "','"
                    + acc.getBirthDate() + "','"
                    + acc.getPhone() + "','"
                    + acc.getRole()
                    +"');";
            Statement stm = AIMSDB.getConnection().createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateAcc(Account acc){
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

    public static void delAcc(int id){
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
}
