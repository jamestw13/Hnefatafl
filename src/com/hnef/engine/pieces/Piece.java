package com.hnef.engine.pieces;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Move;
import com.hnef.engine.board.Board;

import java.util.List;

public abstract class Piece {

  protected final int piecePosition;
  protected final Alliance pieceAlliance;

  Piece(final int piecePosition, final Alliance pieceAlliance) {
    this.pieceAlliance = pieceAlliance;
    this.piecePosition = piecePosition;
  }

  public abstract List<Move> calculateLegalMoves(final Board board);

}
