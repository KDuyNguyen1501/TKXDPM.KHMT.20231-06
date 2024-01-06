package entity.media;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.db.AIMSDB;

public class Book extends Media {

    String author;
    String coverType;
    String publisher;
    Date publishDate;
    int numOfPages;
    String language;
    String bookCategory;

    public Book() throws SQLException {

    }

    public Book(int id, String title, String category, int price, int quantity, String type,int value, String imageUrl) throws SQLException {
        super(id, title, category, price, quantity, type, value, imageUrl);
        this.publishDate = new Date();
        this.author = "author";
        this.coverType = "coverType";
        this.publisher = "publisher";
        this.numOfPages = 100;
        this.language = "language";
        this.bookCategory = "bookCategory";
    }

    public Book(int id, String title, String category, int price, int quantity, String type,int value, String imageUrl, String author,
                String coverType, String publisher, Date publishDate, int numOfPages, String language,
                String bookCategory) throws SQLException {
        super(id, title, category, price, quantity, type, value, imageUrl);
        this.author = author;
        this.coverType = coverType;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.numOfPages = numOfPages;
        this.language = language;
        this.bookCategory = bookCategory;
    }

    // getter and setter
    public int getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getCoverType() {
        return this.coverType;
    }

    public Book setCoverType(String coverType) {
        this.coverType = coverType;
        return this;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Date getPublishDate() {
        return this.publishDate;
    }

    public Book setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public int getNumOfPages() {
        return this.numOfPages;
    }

    public Book setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
        return this;
    }

    public String getLanguage() {
        return this.language;
    }

    public Book setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getBookCategory() {
        return this.bookCategory;
    }

    public Book setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
        return this;
    }

    @Override
    public boolean addMedia() throws SQLException {
        boolean addMediaSuccess = super.addMedia();

        if (addMediaSuccess) {
            Connection con = AIMSDB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Book (id ,author ,coverType, publisher, publishDate, numOfPages, language, bookCategory) VALUES(?,?,?,?,?,?,?,?)");
            ps.setInt(1, this.id);
            ps.setString(2, this.author);
            ps.setString(3, this.coverType);
            ps.setString(4, this.publisher);
            ps.setDate(5, new java.sql.Date(this.publishDate.getTime()));
            ps.setInt(6, this.numOfPages);
            ps.setString(7, this.language);
            ps.setString(8, this.bookCategory);
            int res = ps.executeUpdate();

            if (res == 1) {
                return true;
            }
        }

        return false;
    }
    @Override
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM " +
                "aims.Book " +
                "INNER JOIN aims.Media " +
                "ON Media.id = Book.id " +
                "where Media.id = " + id + ";";
        Statement stm = AIMSDB.getConnection().createStatement();
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {

            // from Media table
            String title = "";
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");
            int value = res.getInt("value");
            String imageUrl = res.getString("imageUrl");

            // from Book table
            String author = res.getString("author");
            String coverType = res.getString("coverType");
            String publisher = res.getString("publisher");
            Date publishDate = res.getDate("publishDate");
            int numOfPages = res.getInt("numOfPages");
            String language = res.getString("language");
            String bookCategory = res.getString("bookCategory");

            return new Book(id, title, category, price, quantity, type,value, imageUrl,
                    author, coverType, publisher, publishDate, numOfPages, language, bookCategory);

        } else {
            throw new SQLException();
        }
    }

    @Override
    public List getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        String sql = "SELECT Media.* FROM " +
                "Media " +
                "INNER JOIN Book " +
                "ON Media.id = Book.id;";
        ResultSet res = stm.executeQuery(sql);
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            Media media = new Media(res.getInt("id"), res.getString("title"),
                    res.getString("category"), res.getInt("price"),
                    res.getInt("quantity"), res.getString("type"),res.getInt("value"),res.getString("imageUrl"));
            medium.add(media);
        }
        return medium;
    }

    @Override
    public boolean deleteMedia() throws SQLException {
        PreparedStatement ps = AIMSDB.getConnection().prepareStatement(
                "DELETE FROM Book WHERE id = ?");
        ps.setInt(1, this.id);
        int res = ps.executeUpdate();
        if (res == 1) {
            return super.deleteMedia();
        }
        return false;
    }

    @Override
    public String toString() {
        return "{" +
                super.toString() +
                " author='" + author + "'" +
                ", coverType='" + coverType + "'" +
                ", publisher='" + publisher + "'" +
                ", publishDate='" + publishDate + "'" +
                ", numOfPages='" + numOfPages + "'" +
                ", language='" + language + "'" +
                ", bookCategory='" + bookCategory + "'" +
                "}";
    }
}
