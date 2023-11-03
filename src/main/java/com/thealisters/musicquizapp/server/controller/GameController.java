package com.thealisters.musicquizapp.server.controller;


import com.thealisters.musicquizapp.server.dto.GameGetResponseDTO;
import com.thealisters.musicquizapp.server.exception.MusicGameNotFoundException;
import com.thealisters.musicquizapp.server.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.thealisters.musicquizapp.server.dto.GamePostRequestDTO;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("${root.context}")
public class GameController {

    @Value("${root.context}")
    private String rootContext;

    @Autowired
    GameService gameService;

    private static final int NUMBER_OF_SONGS = 10;

    @ExceptionHandler(value = MusicGameNotFoundException.class)
    public ResponseEntity handleMusicGameNotFoundException(
            MusicGameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    // TODO
    @GetMapping("/game")
    public ResponseEntity<GameGetResponseDTO> getGame()
            throws MusicGameNotFoundException{
        GameGetResponseDTO gameGetResponseDTO = gameService.getGameInputs(NUMBER_OF_SONGS);
        if (gameGetResponseDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(gameGetResponseDTO);
    }

    @PostMapping
    public ResponseEntity<GamePostRequestDTO> postGame(@RequestBody GamePostRequestDTO gamePostRequestDTO){
        gamePostRequestDTO = gameService.insertGameResult(gamePostRequestDTO);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("musicquizapp","/api/v1/musicquizapp/"+ gamePostRequestDTO.getUserId());
        return new ResponseEntity<>(gamePostRequestDTO, httpHeaders, HttpStatus.CREATED);
    }
}
