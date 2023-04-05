package com.team2.gogame;

public class Game {
    private int id;
    private String date;
    private String moves;
    private int turn;
    private int[][] game;
    private int SIDE;

    public Game(int id, String date, String moves) {
        this.id = id;
        this.date = date;
        this.moves = moves;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getMoves() {
        return moves;
    }

    public void updateMoves(String move) {
        moves += " " + move;
    }
}
