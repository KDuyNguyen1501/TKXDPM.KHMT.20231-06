package views.screen.usermanagement;

import common.exception.SignupFailedException;
import controller.AccountController;
import controller.UserManagementController;
import entity.media.Media;
import entity.user.Account;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.admin.AdminHomeHandler;
import views.screen.home.HomeScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserManagementHandler extends BaseScreenHandler implements Initializable {
    ObservableList<String> roles;
    @FXML
    private ComboBox<String> comboBoxRole;

    @FXML
    private Button backBtn;

    @FXML
    private TableView<Account> tableUser;

    @FXML
    private TableColumn<Account, Integer> idColumn;

    @FXML
    private TableColumn<Account, String> nameColumn;

    @FXML
    private TableColumn<Account, String> usernameColumn;

//    @FXML
//    private TableColumn<Account,String> passwordColumn;

    @FXML
    private TableColumn<Account, String> birthDateColumn;

    @FXML
    private TableColumn<Account, String> phoneNumberColumn;

    @FXML
    private TableColumn<Account, String> roleColumn;

    @FXML
    private TextField nameField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField birthDateField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField roleField;
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;
    private ObservableList<Account> accountList;

    private AccountController accountController;

    private int idSeclect;

    public UserManagementHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roles = FXCollections.observableArrayList("All", "Admin", "User");

        comboBoxRole.setItems(roles);
        accountList = FXCollections.observableArrayList();

        File file = new File("assets/images/Logo.png");
        Image image = new Image(file.toURI().toString());

        accountController = new AccountController();

        deleteBtn.setVisible(false);
        editBtn.setVisible(false);

        try {
            accountList.addAll(UserManagementController.getAllAccounts());

            idColumn.setCellValueFactory(new PropertyValueFactory<Account, Integer>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("name"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
            birthDateColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("birthDate"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("phone"));
            roleColumn.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getRole() == 1 ? "User" : "Admin"));

            tableUser.setItems(accountList);
            tableUser.setRowFactory(tv -> {
                TableRow<Account> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    deleteBtn.setVisible(true);
                    editBtn.setVisible(true);

                    Account rowData = row.getItem();
                    idSeclect = rowData.getId();
                    nameField.setText(rowData.getName());
                    usernameField.setText(rowData.getUsername());
                    passwordField.setText(rowData.getPassword());
                    birthDateField.setText(rowData.getBirthDate());
                    phoneField.setText(rowData.getPhone());
                    roleField.setText(Integer.toString(rowData.getRole()));

                });

                return row;
            });

            addBtn.setOnMouseClicked(e -> {
                if (isEmpty(nameField) || isEmpty(usernameField) || isEmpty(passwordField) ||
                        isEmpty(birthDateField) || isEmpty(phoneField) || isEmpty(roleField)) {
                    throw new SignupFailedException("Yêu cầu đủ các trường");

                } else {
                    UserManagementController.createAcc(new Account(0,
                            nameField.getText(),
                            usernameField.getText(),
                            passwordField.getText(),
                            birthDateField.getText(),
                            phoneField.getText(),
                            Integer.valueOf(roleField.getText())));
                    try {
                        accountList.setAll(UserManagementController.getAllAccounts());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            editBtn.setOnMouseClicked(e -> {
                if (isEmpty(nameField) || isEmpty(usernameField) || isEmpty(passwordField) ||
                        isEmpty(birthDateField) || isEmpty(phoneField) || isEmpty(roleField)) {
                    throw new SignupFailedException("Yêu cầu đủ các trường");
                } else {
                    UserManagementController.updateAcc(new Account(idSeclect,
                            nameField.getText(),
                            usernameField.getText(),
                            passwordField.getText(),
                            birthDateField.getText(),
                            phoneField.getText(),
                            Integer.valueOf(roleField.getText())));
                    try {
                        accountList.setAll(UserManagementController.getAllAccounts());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            deleteBtn.setOnMouseClicked(e -> {
                UserManagementController.delAcc(idSeclect);
                try {
                    accountList.setAll(UserManagementController.getAllAccounts());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });


            backBtn.setOnMouseClicked(e -> {
                try {
                    AdminHomeHandler adminHome = new AdminHomeHandler(stage, Configs.ADMIN_HOME_PATH);
                    adminHome.setScreenTitle("Admin");
                    adminHome.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            comboBoxRole.setOnAction(e -> {
                String selectedRole = comboBoxRole.getValue();
                try {
                    switch (selectedRole) {
                        case "Admin":
                            System.out.println("Select Admin");
                            accountList.setAll(UserManagementController.getAllAccountsByRole(0));
                            break;
                        case "User":
                            System.out.println("Select User");
                            accountList.setAll(UserManagementController.getAllAccountsByRole(1));
                            break;
                        default:
                            System.out.println("Get All");
                            accountList.setAll(UserManagementController.getAllAccounts());
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AccountController getBController() {
        return accountController;
    }

    private boolean isEmpty(TextField textField) {
        return textField.getText().isEmpty();
    }
}
