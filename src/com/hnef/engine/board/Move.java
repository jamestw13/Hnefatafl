package com.hnef.engine.board;

import java.util.List;

import com.hnef.engine.board.Board.Builder;
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

  public int getDestinationCoordinate(){
    return this.destinationCoordinate;
  }

  public abstract Board execute();

  public static class NeutralMove extends Move {

    public NeutralMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
      super(board, movedPiece, destinationCoordinate);
    }

    @Override
    public Board execute() {
      // TODO Auto-generated method stub
      return null;
    }
  }

  public static class AttackMove extends Move {

    final List<Piece> attackedPieces;

    public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate,
        final List<Piece> attackedPieces) {
      super(board, movedPiece, destinationCoordinate);
      this.attackedPieces = attackedPieces;
    }

    @Override
    public Board execute() {
      final Builder builder = new Builder();

      for(final Piece piece : this.board.currentPlayer().getActivePieces()){
        if(!this.movedPiece.equals(piece)){
          builder.setPiece(piece);
        }
      }
      
      return null;
    }
  }
}
