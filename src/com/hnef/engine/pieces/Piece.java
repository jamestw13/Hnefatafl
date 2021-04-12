package com.hnef.engine.pieces;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Move;
import com.hnef.engine.board.Board;

import java.util.Collection;

public abstract class Piece {

  protected final PieceType pieceType;
  protected final int piecePosition;
  protected final Alliance pieceAlliance;
  protected final boolean isFirstMove;
  private final int cachedHashCode;

  Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
    this.pieceType = pieceType;
    this.pieceAlliance = pieceAlliance;
    this.piecePosition = piecePosition;
    this.isFirstMove = false;
    this.cachedHashCode = computeHashCode();
  }

  @Override
  public int hashCode() {
    return cachedHashCode;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }
    if (!(other instanceof Piece)) {
      return false;
    }
    final Piece otherPiece = (Piece) other;
    return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType()
        && pieceAlliance == otherPiece.getPieceAlliance() && isFirstMove == otherPiece.isFirstMove();
  }

  private int computeHashCode() {
    int result = pieceType.hashCode();
    result = 31 * result + pieceAlliance.hashCode();
    result = 31 * result + piecePosition;
    result = 31 * result + (isFirstMove ? 1 : 0);
    return result;
  }

  public int getPiecePosition() {
    return this.piecePosition;
  }

  public Alliance getPieceAlliance() {
    return this.pieceAlliance;
  }

  public boolean isFirstMove() {
    return this.isFirstMove;
  }

  public PieceType getPieceType() {
    return this.pieceType;
  }

  public abstract Collection<Move> calculateLegalMoves(final Board board);

  public abstract Piece movePiece(Move move);

  public enum PieceType {
    PAWN("P") {
      @Override
      public boolean isKing() {
        return false;
      }
    },
    KING("K") {
      @Override
      public boolean isKing() {
        return true;
      }
    };

    private String pieceName;

    PieceType(final String pieceName) {
      this.pieceName = pieceName;
    }

    @Override
    public String toString() {
      return this.pieceName;
    }

    public abstract boolean isKing();

  }

}
