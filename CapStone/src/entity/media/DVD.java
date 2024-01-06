package entity.media;

import entity.db.AIMSDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DVD extends Media {

    String discType;
    String director;
    int runtime;
    String studio;
    String subtitles;
    Date releasedDate;
    String filmType;

    public DVD() throws SQLException {

    }

    public DVD(int id, String title, String category, int price, int quantity, String type, int value, String imageUrl)
            throws SQLException {
        super(id, title, category, price, quantity, type, value, imageUrl);
        this.releasedDate = new Date();
        this.discType = "discType";
        this.director = "director";
        this.runtime = 100;
        this.studio = "studio";
        this.subtitles = "subtitles";
        this.filmType = "filmType";

    }

    public DVD(int id, String title, String category, int price, int quantity, String type, int value, String imageUrl,
            String discType,
            String director, int runtime, String studio, String subtitles, Date releasedDate, String filmType)
            throws SQLException {
        super(id, title, category, price, quantity, type, value, imageUrl);
        this.discType = discType;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.subtitles = subtitles;
        this.releasedDate = releasedDate;
        this.filmType = filmType;
    }

    /**
     * @return String
     */
    public String getDiscType() {
        return this.discType;
    }

    /**
     * @param discType
     * @return DVD
     */
    public DVD setDiscType(String discType) {
        this.discType = discType;
        return this;
    }

    /**
     * @return String
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * @param director
     * @return DVD
     */
    public DVD setDirector(String director) {
        this.director = director;
        return this;
    }

    /**
     * @return int
     */
    public int getRuntime() {
        return this.runtime;
    }

    /**
     * @param runtime
     * @return DVD
     */
    public DVD setRuntime(int runtime) {
        this.runtime = runtime;
        return this;
    }

    /**
     * @return String
     */
    public String getStudio() {
        return this.studio;
    }

    /**
     * @param studio
     * @return DVD
     */
    public DVD setStudio(String studio) {
        this.studio = studio;
        return this;
    }

    /**
     * @return String
     */
    public String getSubtitles() {
        return this.subtitles;
    }

    /**
     * @param subtitles
     * @return DVD
     */
    public DVD setSubtitles(String subtitles) {
        this.subtitles = subtitles;
        return this;
    }

    /**
     * @return Date
     */
    public Date getReleasedDate() {
        return this.releasedDate;
    }

    /**
     * @param releasedDate
     * @return DVD
     */
    public DVD setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

    /**
     * @return String
     */
    public String getFilmType() {
        return this.filmType;
    }

    /**
     * @param filmType
     * @return DVD
     */
    public DVD setFilmType(String filmType) {
        this.filmType = filmType;
        return this;
    }

    /**
     * @return String
     */
    @Override
    public String toString() {
        return "{" + super.toString() + " discType='" + discType + "'" + ", director='" + director + "'" + ", runtime='"
                + runtime + "'" + ", studio='" + studio + "'" + ", subtitles='" + subtitles + "'" + ", releasedDate='"
                + releasedDate + "'" + ", filmType='" + filmType + "'" + "}";
    }


    @Override
    public boolean addMedia() throws SQLException {
        boolean addMediaSuccess = super.addMedia();

        if (addMediaSuccess) {
            Connection con = AIMSDB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO DVD (id ,discType ,director, runtime, studio, subtitle, releasedDate,filmType) VALUES(?,?,?,?,?,?,?,?)");
            ps.setInt(1, this.id);
            ps.setString(2, this.discType);
            ps.setString(3, this.director);
            ps.setInt(4, this.runtime);
            ps.setString(5, this.studio);
            ps.setString(6, this.subtitles);
            ps.setDate(7, new java.sql.Date(this.releasedDate.getTime()));
            ps.setString(8, this.filmType);
            int res = ps.executeUpdate();

            if (res == 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param id
     * @return Media
     * @throws SQLException
     */
    @Override
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM " +
                "aims.DVD " +
                "INNER JOIN aims.Media " +
                "ON Media.id = DVD.id " +
                "where Media.id = " + id + ";";
        ResultSet res = stm.executeQuery(sql);
        if (res.next()) {

            // from media table
            String title = "";
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");
            int value = res.getInt("value");
            String imageUrl = res.getString("imageUrl");

            // from DVD table
            String discType = res.getString("discType");
            String director = res.getString("director");
            int runtime = res.getInt("runtime");
            String studio = res.getString("studio");
            String subtitles = res.getString("subtitle");
            Date releasedDate = res.getDate("releasedDate");
            String filmType = res.getString("filmType");

            return new DVD(id, title, category, price, quantity, type, value, imageUrl, discType, director, runtime,
                    studio, subtitles, releasedDate, filmType);

        } else {
            throw new SQLException();
        }
    }

    /**
     * @return List
     */
    @Override
    public List getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        String sql = "SELECT Media.* FROM " +
                "Media " +
                "INNER JOIN DVD " +
                "ON Media.id = DVD.id;";
        ResultSet res = stm.executeQuery(sql);
        ArrayList medium = new ArrayList<>();
        while (res.next()) {
            Media media = new Media(res.getInt("id"), res.getString("title"),
                    res.getString("category"), res.getInt("price"),
                    res.getInt("quantity"), res.getString("type"), res.getInt("value"), res.getString("imageUrl"));
            medium.add(media);
        }
        return medium;
    }

    @Override
    public boolean deleteMedia() throws SQLException {
        PreparedStatement ps = AIMSDB.getConnection().prepareStatement(
                "DELETE FROM DVD WHERE id = ?");
        ps.setInt(1, this.id);
        int res = ps.executeUpdate();
        if (res == 1) {
            return super.deleteMedia();
        }

        return false;
    }
}
