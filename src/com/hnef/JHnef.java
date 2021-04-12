package com.hnef;

import com.hnef.engine.board.Board;
import com.hnef.gui.Table;

public class JHnef {

  public static void main(String[] args) {

    Board board = Board.createHnefataflBoard();
    System.out.println(board);

    Table table = new Table();
  }
}
