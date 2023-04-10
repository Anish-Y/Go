package com.team2.gogame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

// Ported to Java from Python implementation of Go rules: https://www.moderndescartes.com/essays/implementing_go/

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
        board = "";
        for(int i = 0; i < n*n; i++) {
            board += " ";
        }
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
        char color = board.charAt(compressedCoord);
        Set<Integer> chain = new HashSet<Integer>();
        chain.add(compressedCoord);
        Set<Integer> reached = new HashSet<Integer>();
        Stack<Integer> frontier = new Stack<Integer>();
        frontier.add(compressedCoord);
        while(!frontier.isEmpty()) {
            int currCC = frontier.pop();
            chain.add(currCC);
            for(int t : nbList[currCC]) {
                if(board.charAt(t) == color && !chain.contains(t)) {
                    frontier.add(t);
                } else if(board.charAt(t) != color) {
                    reached.add(t);
                }
            }
        }
        ArrayList<Set<Integer>> ret = new ArrayList<Set<Integer>>();
        ret.add(chain);
        ret.add(reached);
        return ret;
    }

    /** Illegal Move **/

    public String editStone(char color, int compressedCoord) {
        return board.substring(0, compressedCoord) + color + board.substring(compressedCoord + 1);
    }

    public String bulkEditStones(char color, Set<Integer> positions) {
        String ret = board;
        for(int p : positions) {
            ret = board.substring(0, p) + color + board.substring(p + 1);
        }
        return ret;
    }

    public Set<Integer> capture(int compressedCoord) {
        Set<Integer> chain = findReached(compressedCoord).get(0);
        Set<Integer> reached = findReached(compressedCoord).get(1);
        boolean b = true;
        for(int t : reached) {
            if(board.charAt(t) == ' ') {
                b = false;
                break;
            }
        }
        if(b) {
            board = bulkEditStones(' ', chain);
            return chain;
        } else {
            return new HashSet<Integer>;
        }
    }

    public void playMoveIncomplete(int compressedCoord, char color) {
        if (board.charAt(compressedCoord) != ' ') {
            return;
        }
        board = editStone(color, compressedCoord);

        char oppColor = color == '0' ? '1' : '0';
        ArrayList<Integer> oppStones = new ArrayList<Integer>();
        ArrayList<Integer> myStones = new ArrayList<Integer>();
        for(int t : nbList[compressedCoord]) {
            if(board.charAt(t) == color) {
                myStones.add(t);
            } else if(board.charAt(t) == oppColor) {
                oppStones.add(t);
            }
        }

        for(int t : oppStones) {
            capture(compressedCoord);
        }
        for(int t : myStones) {
            capture(compressedCoord);
        }
    }
}