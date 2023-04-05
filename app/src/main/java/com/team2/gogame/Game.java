package com.team2.gogame;

import java.util.ArrayList;
import java.util.Set;

public class Game {
    private int id;
    private String date;
    private String moves;
    private String board;
    private int turn;
    private int[][] game;
    private int n = 9;
    private int[][] nbList;

    public Game(int id, String date, String moves) {
        this.id = id;
        this.date = date;
        this.moves = moves;
        for(int i = 0; i < n*n; i++)
        {
            nbList[i] = neighbors(i);
        }
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

    public int compress(int[] coord) {
        return n * coord[0] + coord[1];
    }

    public int[] decompress(int compressedCoord) {
        int[] coord = new int[2];
        coord[0] = compressedCoord / 19;
        coord[1] = compressedCoord % 19;
        return coord;
    }

    public boolean onBoard(int[] coord) {
        return coord[0] % n == coord[0] && coord[1] % n == coord[1];
    }

    public int[] neighbors(int compressedCoord) {
        int[] coord = decompress(compressedCoord);
        int[][] possible_neighbors = new int[4][2];
        possible_neighbors[0][0] = coord[0] + 1;
        possible_neighbors[0][1] = coord[1];

        possible_neighbors[1][0] = coord[0] - 1;
        possible_neighbors[1][1] = coord[1];

        possible_neighbors[2][0] = coord[0];
        possible_neighbors[2][1] = coord[1] + 1;

        possible_neighbors[3][0] = coord[0];
        possible_neighbors[3][1] = coord[1] - 1;
        ArrayList<Integer> neighbors = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
            if (onBoard(possible_neighbors[i])) {
                neighbors.add(compress(possible_neighbors[i]));
            }
        }
        int[] ret = new int[neighbors.size()];
        for(int i = 0; i < ret.length; i++)
        {
            ret[i] = neighbors.get(i);
        }
        return ret;
    }

    public ArrayList<Set<Integer>> findReached(int compressedCoord) {
        int color = board.charAt(compressedCoord);
    }
}