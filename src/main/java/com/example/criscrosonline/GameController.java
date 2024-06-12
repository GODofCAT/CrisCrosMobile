package com.example.criscrosonline;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/game")
public class GameController {
    private final GameService gameService = new GameService();

    @GetMapping(value = "/create-room/{origPlayerSide}")
    public ResponseEntity<String> createRoom(@PathVariable char origPlayerSide){
        return ResponseEntity.status(200).body(gameService.createRoom(origPlayerSide).toString());
    }

    @GetMapping(value = "/get-room-by-id/{roomId}")
    public ResponseEntity<String> getRoomById(@PathVariable String roomId){
        return ResponseEntity.status(200).body(gameService.getRoomById(roomId).toString());
    }

    @GetMapping(value = "/join/{roomId}")
    public ResponseEntity<String> joinRoom(@PathVariable String roomId){
        return ResponseEntity.status(200).body(gameService.joinRoom(roomId).toString());
    }

    @GetMapping(value = "/exit/{roomId}")
    public ResponseEntity<String> exitRoom(@PathVariable String roomId){
        return ResponseEntity.status(200).body(gameService.exitRoom(roomId).toString());
    }

    @GetMapping(value = "/move")
    public ResponseEntity<String> makeMove(@RequestParam String roomId, @RequestParam int position){
        return ResponseEntity.status(200).body(gameService.makeMove(roomId,position).toString());
    }
}
