package views.screen.usermanagement;

import common.exception.SignupFailedException;
import controller.AccountController;
import entity.user.Account;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.home.HomeScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserManagementHandler extends BaseScreenHandler implements Initializable {

    @FXML
    private ListView<Account> itemListView;
    @FXML
    private  Button backButton;


    private ObservableList<Account> accountList;
    private AccountController accountController;

    public UserManagementHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountList = FXCollections.observableArrayList();
        File file = new File("assets/images/Logo.png");
        Image image = new Image(file.toURI().toString());

        accountController = new AccountController();

        try {
            List<Account> accounts = getAllAccounts();
            accountList.addAll(accounts);

            itemListView.setItems(accountList);

            itemListView.setCellFactory(param -> new ListCell<Account>() {
                @Override
                protected void updateItem(Account item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.toString());
                    }
                }
            });

            backButton.setOnMouseClicked(e->{
                HomeScreenHandler homeHandler = null;
                try {
                    homeHandler = new HomeScreenHandler(stage, Configs.HOME_PATH);
                    homeHandler.setScreenTitle("Home Screen");
                    homeHandler.setImage();
                    homeHandler.show();
                } catch (IOException ex) {
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


    private List<Account> getAllAccounts() throws SQLException {
        return Account.getAllAccounts();
    }
}
