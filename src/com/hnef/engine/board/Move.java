package com.hnef.engine.board;

import com.hnef.engine.board.Board;
import com.hnef.engine.board.Piece;

// TODO may need to come back and extend this into subclasses
// In chess version, there is an AttackMove - but I'm considering just doing an attack check after the normal move.
public class Move {

  final Board board;
  final Piece movedPiece;
  final int destinationCoordinate;

  Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
    this.board = board;
    this.movedPiece = movedPiece;
    this.destinationCoordinate = destinationCoordinate;
  }
}
