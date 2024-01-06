package views.screen.admin;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.Configs;
import views.screen.BaseScreenHandler;
import views.screen.admin.manage_media.ManageMediaHandler;
import views.screen.home.HomeScreenHandler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminHomeHandler extends BaseScreenHandler {
    @FXML
    private ImageView adminIcon;
    @FXML
    private ImageView homeIcon;
    @FXML
    private Button homeBtn;

    @FXML
    private ImageView mediaIcon;
    @FXML
    private Button mediaBtn;

    @FXML
    private ImageView userIcon;
    @FXML
    private Button userBtn;

    public AdminHomeHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        setImage();
        homeBtn.setOnMouseClicked(e -> {
            HomeScreenHandler homeHandler = null;
            try {
                homeHandler = new HomeScreenHandler(this.stage, Configs.HOME_PATH);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            homeHandler.setScreenTitle("Home Screen");
            homeHandler.setImage();
            homeHandler.show();
        });

        mediaBtn.setOnMouseClicked(e -> {
            ManageMediaHandler manageMediaHandler = null;
            try {
                manageMediaHandler = new ManageMediaHandler(this.stage, Configs.MANAGE_MEDIA_PATH, this);
            } catch (IOException | SQLException ex) {
                throw new RuntimeException(ex);
            }
            manageMediaHandler.show();
        });

        userBtn.setOnMouseClicked(e -> {
            System.out.println("go to user manage");
        });

    }

    private void setImage() {
        // admin icon btn
        File adminIconFile = new File(Configs.IMAGE_PATH + "/" + "admin_icon.png");
        Image adminIconImage = new Image(adminIconFile.toURI().toString());
        adminIcon.setImage(adminIconImage);

        // home icon btn
        File homeIconFile = new File(Configs.IMAGE_PATH + "/" + "home_icon.png");
        Image homeIconImage = new Image(homeIconFile.toURI().toString());
        homeIcon.setImage(homeIconImage);

        // media icon btn
        File mediaIconFile = new File(Configs.IMAGE_PATH + "/" + "Media.png");
        Image mediaIconImage = new Image(mediaIconFile.toURI().toString());
        mediaIcon.setImage(mediaIconImage);

        // user icon btn
        File userIconFile = new File(Configs.IMAGE_PATH + "/" + "user_icon.png");
        Image userIconImage = new Image(userIconFile.toURI().toString());
        userIcon.setImage(userIconImage);
    }

}
