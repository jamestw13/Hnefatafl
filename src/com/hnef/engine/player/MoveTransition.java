package com.hnef.engine.player;

import com.hnef.engine.board.Board;
import com.hnef.engine.board.Move;

public class MoveTransition {
  private final Board transitionBoard;
  private final Move move;
  private final MoveStatus moveStatus;

  public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus) {
    this.transitionBoard = transitionBoard;
    this.move = move;
    this.moveStatus = moveStatus;
  }
}