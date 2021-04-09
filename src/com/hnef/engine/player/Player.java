package com.hnef.engine.player;

import java.util.Collection;

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

}
