package com.thealisters.musicquizapp.server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = NumberOfSongsNotFoundException.class )
    public ResponseEntity<String> handleNumberOfSongsNotProvidedException(
            NumberOfSongsNotFoundException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(value = MusicGameNotFoundException.class)
    public ResponseEntity handleMusicGameNotFoundException(
            MusicGameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(value = UserIdNotFoundException.class)
    public ResponseEntity handleUserIdNotFoundException(
            UserIdNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(value = GameScoreInsertionError.class)
    public ResponseEntity handleGameScoreInsertionError(
            GameScoreInsertionError e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
