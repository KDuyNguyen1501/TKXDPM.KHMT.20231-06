package views.screen.admin.manage_media;

import controller.ManageMediaController;
import entity.media.Book;
import entity.media.CD;
import entity.media.DVD;
import entity.media.Media;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import views.screen.BaseScreenHandler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ManageMediaHandler extends BaseScreenHandler {
    @FXML
    private Button backBtn;

    String mediaTypes[] =
            {"All", "Book", "CD", "DVD"};

    // Create a combo box
    @FXML
    private ComboBox comboBoxMediaType;

    // table Media
    @FXML
    private TableView<Media> tableMedia;

    @FXML
    private TableColumn<Media, Integer> idColumn;

    @FXML
    private TableColumn<Media, String> titleColumn;

    @FXML
    private TableColumn<Media, String> categoryColumn;

    @FXML
    private TableColumn<Media, Integer> priceColumn;

    @FXML
    private TableColumn<Media, Integer> quantityColumn;

    @FXML
    private TableColumn<Media, String> typeColumn;

    @FXML
    private TableColumn<Media, Integer> valueColumn;

    @FXML
    private TableColumn<Media, String> imageUrlColumn;

    //    form
    @FXML
    private TextField idTextField;

    @FXML
    private ComboBox mediaTypeField;

    @FXML
    private TextField titleTextFile;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private TextField valueTextField;

    @FXML
    private TextField quantityTextFiled;

    @FXML
    private TextField imageTextField;

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;


    private List medias;
    private BaseScreenHandler prevScreen;

    public ManageMediaHandler(Stage stage, String screenPath, BaseScreenHandler prevScreen) throws IOException, SQLException {
        super(stage, screenPath);
        setBController(new ManageMediaController());
        this.prevScreen = prevScreen;
        setMediaByType("All");
        initFXML();
        setHandler();
    }

    private void initFXML() {
        comboBoxMediaType.setItems(FXCollections.observableArrayList(mediaTypes));
        comboBoxMediaType.getSelectionModel().selectFirst();

        idColumn.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Media, Integer>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<Media, Integer>("quantity"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("type"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Media, Integer>("value"));
        imageUrlColumn.setCellValueFactory(new PropertyValueFactory<Media, String>("imageURL"));
        setItem();

        mediaTypeField.setItems(FXCollections.observableArrayList(mediaTypes));
        mediaTypeField.getSelectionModel().selectFirst();

    }

    private void setHandler() {
        backBtn.setOnMouseClicked(e -> {
            prevScreen.show();
        });

        comboBoxMediaType.setOnAction(e -> {
            String value = comboBoxMediaType.getValue().toString();
            try {
                setMediaByType(value);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        tableMedia.setRowFactory(tv -> {
            TableRow<Media> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Media rowData = row.getItem();
                idTextField.setText(Integer.toString(rowData.getId()));
                titleTextFile.setText(rowData.getTitle());
                categoryTextField.setText(rowData.getCategory());
                priceTextField.setText(Integer.toString(rowData.getPrice()));
                valueTextField.setText(Integer.toString(rowData.getValue()));
                imageTextField.setText(rowData.getImageURL());
                mediaTypeField.setValue(getTypeByString(rowData.getType()));
                try {
                    quantityTextFiled.setText(Integer.toString(rowData.getQuantity()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            });

            return row;
        });

        addBtn.setOnMouseClicked(e -> {
            try {
                handleAddMedia();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        editBtn.setOnMouseClicked(e -> {
            try {
                handleEditMedia();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        deleteBtn.setOnMouseClicked(e -> {
            try {
                handleDeleteMedia();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void setMediaByType(String type) throws SQLException {
        ManageMediaController controller = (ManageMediaController) getBController();

        switch (type) {
            case "Book": {
                System.out.println("select book");
                this.medias = controller.getAllMedia(new Book());
                System.out.println(this.medias);

                break;
            }
            case "CD": {
                System.out.println("select cd");
                this.medias = controller.getAllMedia(new CD());
                System.out.println(this.medias);
                break;
            }
            case "DVD": {
                System.out.println("select dvd");
                this.medias = controller.getAllMedia(new DVD());
                System.out.println(this.medias);
                break;
            }
            default: {
                System.out.println("get all");
                this.medias = controller.getAllMedia(new Media());
                System.out.println(this.medias);

            }
        }

        setItem();

    }

    private void setItem() {
        tableMedia.setItems(FXCollections.observableArrayList(medias));
    }

    private void handleAddMedia() throws SQLException {
        boolean result = false;
        String type = mediaTypeField.getValue().toString();

        switch (type) {
            case "Book": {
                Book newMedia = new Book(Integer.valueOf(idTextField.getText()), titleTextFile.getText(), categoryTextField.getText(),
                        Integer.valueOf(priceTextField.getText()), Integer.valueOf(quantityTextFiled.getText()),
                        mediaTypeField.getValue().toString(), Integer.valueOf(valueTextField.getText()), imageTextField.getText());
                result = newMedia.addMedia();
                break;
            }

            case "CD": {
                CD newMedia = new CD(Integer.valueOf(idTextField.getText()), titleTextFile.getText(), categoryTextField.getText(),
                        Integer.valueOf(priceTextField.getText()), Integer.valueOf(quantityTextFiled.getText()),
                        mediaTypeField.getValue().toString(), Integer.valueOf(valueTextField.getText()), imageTextField.getText());
                result = newMedia.addMedia();
                break;
            }

            case "DVD": {
                DVD newMedia = new DVD(Integer.valueOf(idTextField.getText()), titleTextFile.getText(), categoryTextField.getText(),
                        Integer.valueOf(priceTextField.getText()), Integer.valueOf(quantityTextFiled.getText()),
                        mediaTypeField.getValue().toString(), Integer.valueOf(valueTextField.getText()), imageTextField.getText());
                result = newMedia.addMedia();
                break;
            }
        }

        if (result) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Add successfully");
            a.show();

            String value = comboBoxMediaType.getValue().toString();
            try {
                setMediaByType(value);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Add fail");
            a.show();
        }

    }

    private void handleEditMedia() throws SQLException {
        if (idTextField.getText() != "") {
            Media editMedia = new Media(Integer.valueOf(idTextField.getText()), titleTextFile.getText(), categoryTextField.getText(),
                    Integer.valueOf(priceTextField.getText()), Integer.valueOf(quantityTextFiled.getText()),
                    mediaTypeField.getValue().toString(), Integer.valueOf(valueTextField.getText()), imageTextField.getText());
            boolean result = editMedia.updateMedia();
            if (result) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Update successfully");
                a.show();

                String value = comboBoxMediaType.getValue().toString();
                try {
                    setMediaByType(value);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Update fail");
                a.show();
            }
        }
    }

    private void handleDeleteMedia() throws SQLException {
        if (idTextField.getText() != "") {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm delete " + mediaTypeField.getValue().toString(), ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                confirmDeleteMedia();
            }
        }

    }

    private void confirmDeleteMedia() throws SQLException {
        boolean result = false;
        String type = mediaTypeField.getValue().toString();

        switch (type) {
            case "Book": {
                Book media = new Book(Integer.valueOf(idTextField.getText()), titleTextFile.getText(), categoryTextField.getText(),
                        Integer.valueOf(priceTextField.getText()), Integer.valueOf(quantityTextFiled.getText()),
                        mediaTypeField.getValue().toString(), Integer.valueOf(valueTextField.getText()), imageTextField.getText());
                result = media.deleteMedia();
                break;
            }

            case "CD": {
                CD media = new CD(Integer.valueOf(idTextField.getText()), titleTextFile.getText(), categoryTextField.getText(),
                        Integer.valueOf(priceTextField.getText()), Integer.valueOf(quantityTextFiled.getText()),
                        mediaTypeField.getValue().toString(), Integer.valueOf(valueTextField.getText()), imageTextField.getText());
                result = media.deleteMedia();
                break;
            }

            case "DVD": {
                DVD media = new DVD(Integer.valueOf(idTextField.getText()), titleTextFile.getText(), categoryTextField.getText(),
                        Integer.valueOf(priceTextField.getText()), Integer.valueOf(quantityTextFiled.getText()),
                        mediaTypeField.getValue().toString(), Integer.valueOf(valueTextField.getText()), imageTextField.getText());
                result = media.deleteMedia();
                break;
            }
        }


        if (result) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Delete successfully");
            a.show();

            String value = comboBoxMediaType.getValue().toString();
            try {
                setMediaByType(value);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Delete fail");
            a.show();
        }
    }

    private String getTypeByString(String type) {
        type = type.toLowerCase();
        return switch (type) {
            case "book" -> "Book";
            case "cd" -> "CD";
            case "dvd" -> "DVD";
            default -> "All";
        };
    }

}

