package com.hnef.engine.player;

import java.util.Collection;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Board;
import com.hnef.engine.board.Move;
import com.hnef.engine.pieces.Piece;

public class BlackPlayer extends Player {
  public BlackPlayer(Board board, Collection<Move> blackStandardLegalMoves, Collection<Move> whiteStandardLegalMoves) {
    super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
  }

  @Override
  public Collection<Piece> getActivePieces() {
    return this.board.getBlackPieces();
  }

  @Override
  public Alliance getAlliance() {
    return Alliance.BLACK;
  }

  @Override
  public Player getOpponent() {
    return this.board.whitePlayer();
  }
}
