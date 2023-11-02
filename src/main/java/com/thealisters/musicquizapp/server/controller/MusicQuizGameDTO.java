package com.thealisters.musicquizapp.server.controller;

public class MusicQuizGameDTO {
    private String userId;
    private String[] userSongName;
    private String[] userSongURL;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String[] getUserSongName() {
        return userSongName;
    }

    public void setUserSongName(String[] userSongName) {
        this.userSongName = userSongName;
    }

    public String[] getUserSongURL() {
        return userSongURL;
    }

    public void setUserSongURL(String[] userSongURL) {
        this.userSongURL = userSongURL;
    }
}
