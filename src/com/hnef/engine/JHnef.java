package com.hnef.engine;

import com.hnef.engine.board.Board;

public class JHnef {

  public static void main(String[] args) {

    Board board = Board.createHnefataflBoard();
    System.out.println(board);
  }
}