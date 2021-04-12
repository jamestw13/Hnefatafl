package com.hnef.engine.board;

import java.util.Collection;
import java.util.List;

import com.hnef.engine.board.Board.Builder;
import com.hnef.engine.pieces.Piece;

public abstract class Move {

  final Board board;
  final Piece movedPiece;
  final int destinationCoordinate;

  public static final Move NULL_MOVE = new NullMove();

  private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
    this.board = board;
    this.movedPiece = movedPiece;
    this.destinationCoordinate = destinationCoordinate;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;

    result = prime * result + this.destinationCoordinate;
    result = prime * result + this.movedPiece.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Move)) {
      return false;
    }

    final Move otherMove = (Move) other;
    return getDestinationCoordinate() == otherMove.getDestinationCoordinate()
        && getMovedPiece().equals(otherMove.getMovedPiece());
  }

  public int getCurrentCoordinate() {
    return this.getMovedPiece().getPiecePosition();
  }

  public int getDestinationCoordinate() {
    return this.destinationCoordinate;
  }

  public Piece getMovedPiece() {
    return this.movedPiece;
  }

  public boolean isAttack() {
    return false;
  }

  public Collection<Piece> getAttackedPieces() {
    return null;
  }

  public Board execute() {
    final Builder builder = new Builder();

    for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
      if (!this.movedPiece.equals(piece)) {
        builder.setPiece(piece);
      }
    }

    for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
      builder.setPiece(piece);
    }
    builder.setPiece(this.movedPiece.movePiece(this));
    builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
    return builder.build();
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

    @Override
    public int hashCode() {
      return this.attackedPieces.hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
      if (this == other) {
        return true;
      }
      if (!(other instanceof AttackMove)) {
        return false;
      }
      final AttackMove otherAttackMove = (AttackMove) other;
      return super.equals(otherAttackMove) && getAttackedPieces().equals(otherAttackMove.getAttackedPieces());
    }

    @Override
    public Board execute() {
      return null;
    }

    @Override
    public boolean isAttack() {
      return true;
    }

    @Override
    public Collection<Piece> getAttackedPieces() {
      return this.attackedPieces;
    }
  }

  public static class NullMove extends Move {

    public NullMove() {
      super(null, null, -1);
    }

    @Override
    public Board execute() {
      throw new RuntimeException("Cannot execute the null move!");
    }

  }

  public static class MoveFactory {
    private MoveFactory() {
      throw new RuntimeException("Not instantiable!");

    }

    public static Move createMove(final Board board, final int currentCoordinate, final int destinationCoordinate) {
      for (final Move move : board.AllLegalMoves()) {
        if (move.getCurrentCoordinate() == currentCoordinate
            && move.getDestinationCoordinate() == destinationCoordinate) {
          return move;
        }
      }
      return NULL_MOVE;
    }
  }
}
