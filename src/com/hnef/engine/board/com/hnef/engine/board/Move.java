package com.hnef.engine.board;

import com.hnef.engine.board.Board;
import com.hnef.engine.board.Piece;

public class Move {

  final Board board;
  final Piece movedPiece;
  final int destinationCoordinate;

  Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
    this.board = board;
    this.movedPiece = movedPiece;
    this.destinationCoordinate = destinationCoordinate;
  }

  public class AttackMove extends Move {

    final List<Piece> attackedPieces;

    AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPieces) {
      super(board, movedPiece, destinationCoordinate);
      this.attackedPieces = attackedPieces;
    }
  }
}
