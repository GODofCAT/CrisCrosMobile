package com.example.criscrosonline;

public class Room {
    private String id;
    private int playersNum;
    private char[] field;
    private boolean isXturn;

    private char origPlayerSide;

    private boolean isGameFinish;

    public Room(String id, char originPlayerSide) {
        this.id = id;
        playersNum = 1;
        field = new char[9];
        isXturn = true;
        isGameFinish = false;
        this.origPlayerSide = originPlayerSide;
    }

    public String getId() {
        return id;
    }

    public int getPlayersNum() {
        return playersNum;
    }

    public char[] getField() {
        return field;
    }

    public boolean getIsFPturn() {
        return isXturn;
    }

    public void setPlayersNum(int playersNum) {
        this.playersNum = playersNum;
    }

    public void setCell(char cell, int position) {
        field[position] = cell;
    }

    public void setIsFPturn(boolean FPturn) {
        isXturn = FPturn;
    }

    public boolean isGameFinish() {
        return isGameFinish;
    }

    public void setGameFinish(boolean gameFinish) {
        isGameFinish = gameFinish;
    }

    public char getOrigPlayerSide() {
        return origPlayerSide;
    }
}
