package views.screen.invoice;

import entity.order.OrderMedia;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.Utils;
import views.screen.FXMLScreenHandler;

import java.io.IOException;
import java.sql.SQLException;

//Khong vi pham quy tac SOLID

public class MediaInvoiceScreenHandler extends FXMLScreenHandler {

    @FXML
    private HBox hboxMedia;

    @FXML
    private VBox imageLogoVbox;

    @FXML
    private ImageView image;

    @FXML
    private VBox description;

    @FXML
    private Label title;

    @FXML
    private Label numOfProd;

    @FXML
    private Label labelOutOfStock;

    @FXML
    private Label price;

    private OrderMedia orderMedia;

    //Data Coupling
    //Functional Cohesion
    public MediaInvoiceScreenHandler(String screenPath) throws IOException {
        super(screenPath);
    }

    //Data Coupling
    //Functional Cohesion
    /**
     * @param orderMedia
     * @throws SQLException
     */
    public void setOrderMedia(OrderMedia orderMedia) throws SQLException {
        this.orderMedia = orderMedia;
        setMediaInfo();
    }

    //Control Coupling
    //Functional Cohesion
    /**
     * @throws SQLException
     */
    public void setMediaInfo() throws SQLException {
        title.setText(orderMedia.getMedia().getTitle());
        price.setText(Utils.getCurrencyFormat(orderMedia.getPrice()));
        numOfProd.setText(String.valueOf(orderMedia.getQuantity()));
        setImage(image, orderMedia.getMedia().getImageURL());
        image.setPreserveRatio(false);
        image.setFitHeight(90);
        image.setFitWidth(83);
    }

}
