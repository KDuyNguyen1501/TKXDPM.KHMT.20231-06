package entity.media;

import entity.db.AIMSDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CD extends Media {

    String artist;
    String recordLabel;
    String musicType;
    Date releasedDate;

    public CD() throws SQLException{

    }

    public CD(int id, String title, String category, int price, int quantity, String type, int value, String imageUrl) throws SQLException{
        super(id, title, category, price, quantity, type, value, imageUrl);
        this.releasedDate = new Date();
        this.artist = "artist";
        this.recordLabel = "recordLabel";
        this.musicType = "musicType";
    }
    public CD(int id, String title, String category, int price, int quantity, String type, int value, String imageUrl, String artist,
            String recordLabel, String musicType, Date releasedDate) throws SQLException{
        super(id, title, category, price, quantity, type, value, imageUrl);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.musicType = musicType;
        this.releasedDate = releasedDate;
    }

    public String getArtist() {
        return this.artist;
    }

    public CD setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public String getRecordLabel() {
        return this.recordLabel;
    }

    public CD setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
        return this;
    }

    public String getMusicType() {
        return this.musicType;
    }

    public CD setMusicType(String musicType) {
        this.musicType = musicType;
        return this;
    }

    public Date getReleasedDate() {
        return this.releasedDate;
    }

    public CD setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
        return this;
    }

    @Override
    public String toString() {
        return "{" + super.toString() + " artist='" + artist + "'" + ", recordLabel='" + recordLabel + "'"
                + "'" + ", musicType='" + musicType + "'" + ", releasedDate='"
                + releasedDate + "'" + "}";
    }

    @Override
    public boolean addMedia() throws SQLException {
        boolean addMediaSuccess = super.addMedia();

        if (addMediaSuccess) {
            Connection con = AIMSDB.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO CD (id ,artist ,recordLabel, musicType, releasedDate) VALUES(?,?,?,?,?)");
            ps.setInt(1, this.id);
            ps.setString(2, this.artist);
            ps.setString(3, this.recordLabel);
            ps.setString(4, this.musicType);
            ps.setDate(5, new java.sql.Date(this.releasedDate.getTime()));
            int res = ps.executeUpdate();

            if (res == 1) {
                return true;
            }
        }

        return false;
    }
    @Override
    public Media getMediaById(int id) throws SQLException {
        String sql = "SELECT * FROM "+
                     "aims.CD " +
                     "INNER JOIN aims.Media " +
                     "ON Media.id = CD.id " +
                     "where Media.id = " + id + ";";
        ResultSet res = stm.executeQuery(sql);
		if(res.next()) {
            
            // from media table
            String title = "";
            String type = res.getString("type");
            int price = res.getInt("price");
            String category = res.getString("category");
            int quantity = res.getInt("quantity");
            int value = res.getInt("value");
            String imageUrl = res.getString("imageUrl");

            // from CD table
            String artist = res.getString("artist");
            String recordLabel = res.getString("recordLabel");
            String musicType = res.getString("musicType");
            Date releasedDate = res.getDate("releasedDate");
           
            return new CD(id, title, category, price, quantity, type, value, imageUrl,
                          artist, recordLabel, musicType, releasedDate);
            
		} else {
			throw new SQLException();
		}
    }

    @Override
    public List getAllMedia() throws SQLException {
        Statement stm = AIMSDB.getConnection().createStatement();
        String sql = "SELECT Media.* FROM " +
                "Media " +
                "INNER JOIN CD " +
                "ON Media.id = CD.id;";
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
                "DELETE FROM CD WHERE id = ?");
        ps.setInt(1, this.id);
        int res = ps.executeUpdate();
        if (res == 1) {
            return super.deleteMedia();
        }

        return false;
    }
}
