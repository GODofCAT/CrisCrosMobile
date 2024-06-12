package com.example.criscrosonline;

public class GameCore {
    public static boolean makeMove(String roomId, int position, boolean isXturn){
        Room room = LocalStorage.roomStorage.get(roomId);

        if (room.getField()[position] != '\0'){
            return false;
        }

        room.setCell(isXturn?'x':'o' , position);
        room.setIsFPturn(!isXturn);


        return true;
    }

    public void isGameFinish(String roomId){
        char[] field = LocalStorage.roomStorage.get(roomId).getField();

        //012
        //345
        //678

        if (field[0] == field[1] && field[0] == field[2]) LocalStorage.roomStorage.get(roomId).setGameFinish(true);
        if (field[3] == field[4] && field[3] == field[5]) LocalStorage.roomStorage.get(roomId).setGameFinish(true);
        if (field[6] == field[7] && field[6] == field[8]) LocalStorage.roomStorage.get(roomId).setGameFinish(true);
        if (field[0] == field[3] && field[0] == field[6]) LocalStorage.roomStorage.get(roomId).setGameFinish(true);
        if (field[1] == field[4] && field[1] == field[7]) LocalStorage.roomStorage.get(roomId).setGameFinish(true);
        if (field[2] == field[5] && field[2] == field[8]) LocalStorage.roomStorage.get(roomId).setGameFinish(true);
        if (field[0] == field[4] && field[0] == field[8]) LocalStorage.roomStorage.get(roomId).setGameFinish(true);
        if (field[2] == field[4] && field[2] == field[6]) LocalStorage.roomStorage.get(roomId).setGameFinish(true);
    }

}
