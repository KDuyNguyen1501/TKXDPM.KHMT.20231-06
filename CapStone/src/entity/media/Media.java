package entity.media;

import entity.db.AIMSDB;
import utils.Utils;

import entity.db.AIMSDB;
import utils.Utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The general media class, for another media it can be done by inheriting this
 * class
 *
 *
 * @author nguyenlm
 */
public class Media {

    protected static boolean isSupportedPlaceRushOrder = new Random().nextBoolean();
    private static Logger LOGGER = Utils.getLogger(Media.class.getName());
    protected Statement stm;
    protected int id;
    protected String title;
    protected String category;
    protected int value; // the real price of product (eg: 450)
    protected int price; // the price which will be displayed on browser (eg: 500)
    protected int quantity;
    protected String type;
    protected String imageURL;

    public Media() throws SQLException {
        stm = AIMSDB.getConnection().createStatement();
    }

    public Media(int id, String title, String category, int price, int quantity, String type, int value,
            String imageUrl) throws SQLException {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.value = value;
        this.imageURL = imageUrl;

        // stm = AIMSDB.getConnection().createStatement();
    }

    /**
     * @return boolean
     */
    public static boolean getIsSupportedPlaceRushOrder() {
        return Media.isSupportedPlaceRushOrder;
    }

    /**
     * @return int
     * @throws SQLException
     */
    public int getQuantity() throws SQLException {
        int updated_quantity = getMediaById(id).quantity;
        this.quantity = updated_quantity;
        return updated_quantity;
    }

    public boolean addMedia() throws SQLException {
        PreparedStatement ps = AIMSDB.getConnection().prepareStatement(
                "INSERT INTO Media (type ,category, price, quantity, title, value, imageUrl) VALUES(?,?,?,?,?,?,?)");
        ps.setString(1, this.type);
        ps.setString(2, this.category);
        ps.setInt(3, this.price);
        ps.setInt(4, this.quantity);
        ps.setString(5, this.title);
        ps.setInt(6, this.value);
        ps.setString(7, this.imageURL);
        int res = ps.executeUpdate();

        if (res == 0) {
            return false;
        }

        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            this.setId(generatedKeys.getInt(1));
            return true;
        }

        return false;
    }

    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM Media ;";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {

            return new Media()
                    .setId(res.getInt("id"))
                    .setTitle(res.getString("title"))
                    .setQuantity(res.getInt("quantity"))
                    .setCategory(res.getString("category"))
                    .setMediaURL(res.getString("imageUrl"))
                    .setPrice(res.getInt("price"))
                    .setType(res.getString("type"));
        }
        return null;
    }

    public List getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery("select * from Media");
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            Media media = new Media()
                    .setId(res.getInt("id"))
                    .setTitle(res.getString("title"))
                    .setQuantity(res.getInt("quantity"))
                    .setCategory(res.getString("category"))
                    .setMediaURL(res.getString("imageUrl"))
                    .setPrice(res.getInt("price"))
                    .setValue(res.getInt("value"))
                    .setType(res.getString("type"));
            medium.add(media);
        }
        return medium;
    }

    public boolean updateMedia() throws SQLException {
        PreparedStatement ps = AIMSDB.getConnection().prepareStatement(
                "UPDATE Media SET type = ?,category = ? , price =?, " +
                        "quantity = ?, title =?, value =?, imageUrl =? WHERE id = ?");
        ps.setString(1, this.type);
        ps.setString(2, this.category);
        ps.setInt(3, this.price);
        ps.setInt(4, this.quantity);
        ps.setString(5, this.title);
        ps.setInt(6, this.value);
        ps.setString(7, this.imageURL);
        ps.setInt(8, this.id);
        return ps.executeUpdate() == 1;

    }

    public boolean deleteMedia() throws SQLException {
        PreparedStatement ps = AIMSDB.getConnection().prepareStatement(
                "DELETE FROM Media WHERE id = ?");
        ps.setInt(1, this.id);
        return ps.executeUpdate() == 1;
    }

    /**
     * @return int
     */
    // getter and setter
    public int getId() {
        return this.id;
    }

    private Media setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * @return String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * @param title
     * @return Media
     */
    public Media setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * @return String
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * @param category
     * @return Media
     */
    public Media setCategory(String category) {
        this.category = category;
        return this;
    }

    /**
     * @return int
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * @param price
     * @return Media
     */
    public Media setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public Media setMediaURL(String url) {
        this.imageURL = url;
        return this;
    }

    /**
     * @return String
     */
    public String getType() {
        return this.type;
    }

    /**
     * @param type
     * @return Media
     */
    public Media setType(String type) {
        this.type = type;
        return this;
    }

    public Media setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Media setValue(int value) {
        this.value = value;
        return this;
    }

    public int getValue() {
        return this.value;
    }


    /**
     * @return String
     */
    @Override
    public String toString() {
        return "{" +
                " id='" + id + "'" +
                ", title='" + title + "'" +
                ", category='" + category + "'" +
                ", price='" + price + "'" +
                ", quantity='" + quantity + "'" +
                ", type='" + type + "'" +
                ", imageURL='" + imageURL + "'" +
                "}";
    }

}