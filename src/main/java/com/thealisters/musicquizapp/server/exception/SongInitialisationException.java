package com.thealisters.musicquizapp.server.exception;

public class SongInitialisationException extends Exception{
    private String exceptionMessage;
    public SongInitialisationException( String exceptionMessage){
        super(exceptionMessage);
    }
}
