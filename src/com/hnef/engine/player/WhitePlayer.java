package com.hnef.engine.player;

import java.util.Collection;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Board;
import com.hnef.engine.board.Move;
import com.hnef.engine.pieces.King;
import com.hnef.engine.pieces.Piece;

public class WhitePlayer extends Player {

  private final King playerKing;

  public WhitePlayer(final Board board, final Collection<Move> whiteStandardLegalMoves, final Collection<Move> blackStandardLegalMoves) {
    super(board, whiteStandardLegalMoves, blackStandardLegalMoves);
    this.playerKing = establishKing();
  }

  @Override
  public Collection<Piece> getActivePieces() {
    return this.board.getWhitePieces();
  }

  private King establishKing() {
    for (final Piece piece : getActivePieces()) {
      if (piece.getPieceType().isKing()) {
        return (King) piece;
      }
    }
    throw new RuntimeException("Should not reach here!");
  }

  @Override
  public Alliance getAlliance() {
    return Alliance.WHITE;
  }

  @Override
  public Player getOpponent() {
    return this.board.blackPlayer();
  }
}
