package controller;

import entity.media.Media;

import java.sql.SQLException;
import java.util.List;

public class ManageMediaController extends  BaseController{
    public List getAllMedia(Media media) throws SQLException {
        return media.getAllMedia();
    }

    public void addMedia(Media media) throws SQLException {
        media.addMedia();
    }
    public void updateMedia(Media media) throws SQLException {
        media.updateMedia();
    }

    public void deleteMedia(Media media) throws SQLException {
        media.deleteMedia();
    }

}
