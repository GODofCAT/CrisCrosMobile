package com.example.criscrosonline;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameService {
    public GameService() {
    }

    private JSONObject getRoomJSON(Room room){
        return new JSONObject().put("id", room.getId())
                .put("playersNum",room.getPlayersNum())
                .put("field", room.getField())
                .put("isFPturn",room.getIsFPturn())
                .put("isFinish", room.isGameFinish())
                .put("origPlayerSide", room.getOrigPlayerSide());
    }

    private JSONObject createResponse(int status, Object content, String message){
        return new JSONObject().put("status", status)
                .put("content", content)
                .put("message", message);
    }
    public JSONObject createRoom(char origPlayerSide){
        try{
            String roomId = UUID.randomUUID().toString().substring(0,8);
            Room room = new Room(roomId, origPlayerSide);
            room.setIsFPturn(origPlayerSide == 'x');
            LocalStorage.roomStorage.put(roomId,room);
            return createResponse(200,getRoomJSON(room),"ok");
        }catch (Exception e){
            return createResponse(500,null,  e.toString());
        }
    }

    private void deleteRoom(String roomId){
        LocalStorage.roomStorage.remove(roomId);
    }

    public JSONObject joinRoom(String roomId){
        if (!LocalStorage.roomStorage.containsKey(roomId)){
            return createResponse(401, null,"комната с таким id не найдена");
        }
        try{
            Room room = LocalStorage.roomStorage.get(roomId);
            if (room.getPlayersNum() == 2){
                return createResponse(401, null,"достигнут предел количества игроков");
            }
            room.setPlayersNum(2);
            return createResponse(200, getRoomJSON(room), "ok");
        }catch (Exception e){
            return createResponse(500,null,  e.toString());
        }
    }

    public JSONObject exitRoom(String roomId){
        try{
            Room room = LocalStorage.roomStorage.get(roomId);
            if (room.getPlayersNum() == 1){
                deleteRoom(roomId);
                return createResponse(200,null,"");
            }

            room.setPlayersNum(1);
            return createResponse(200,null,"");
        }catch (Exception e){
            return createResponse(500,null,  e.toString());
        }
    }

    public JSONObject getRoomById(String roomId){
        try{
            return createResponse(200,getRoomJSON(LocalStorage.roomStorage.get(roomId)), "ok");
        }catch (Exception e){
            return createResponse(500,null,  e.toString());
        }
    }

    public JSONObject makeMove(String roomId, int position){
        try{
            Room room = LocalStorage.roomStorage.get(roomId);
            boolean isXTurn = room.getIsFPturn() && (room.getOrigPlayerSide() == 'x');

            if(GameCore.makeMove(roomId, position, LocalStorage.roomStorage.get(roomId).getIsFPturn())){
                return createResponse(200,getRoomJSON(LocalStorage.roomStorage.get(roomId)), "ok");
            }
            return createResponse(400,null,"недопустимый ход");
        }catch (Exception e){
            return createResponse(500,null, e.toString());
        }
    }
}
