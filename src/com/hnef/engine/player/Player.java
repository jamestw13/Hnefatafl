package com.hnef.engine.player;

import java.util.Collection;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Board;
import com.hnef.engine.board.Move;
import com.hnef.engine.pieces.Piece;

public abstract class Player {

  protected final Board board;
  protected final Collection<Move> legalMoves;

  Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves) {

    this.board = board;
    this.legalMoves = legalMoves;
  }

  public abstract Collection<Piece> getActivePieces();

  public abstract Alliance getAlliance();

  public abstract Player getOpponent();

  public boolean isMoveLegal(final Move move) {
    return this.legalMoves.contains(move);
  }

  // Pawn capture
  public boolean isInCustody() {
    return false;
  }

  // King capture - Black win condition
  public boolean isInEnclosure() {
    return false;
  }

  // To be determined
  public boolean isInStalemate() {
    return false;
  }

  // King reached safe spot - White win condition
  public boolean hasEscaped() {
    return false;
  }

  public MoveTransition makeMove(final Move move) {
    return null;
  }

}
