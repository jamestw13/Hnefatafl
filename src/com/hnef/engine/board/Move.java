package com.hnef.engine.board;

import java.util.List;

import com.hnef.engine.pieces.Piece;

public abstract class Move {

  final Board board;
  final Piece movedPiece;
  final int destinationCoordinate;

  private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
    this.board = board;
    this.movedPiece = movedPiece;
    this.destinationCoordinate = destinationCoordinate;
  }

  public static class NeutralMove extends Move {

    public NeutralMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
      super(board, movedPiece, destinationCoordinate);
    }
  }

  public static class AttackMove extends Move {

    final List<Piece> attackedPieces;

    public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate,
        final List<Piece> attackedPieces) {
      super(board, movedPiece, destinationCoordinate);
      this.attackedPieces = attackedPieces;
    }
  }
}
