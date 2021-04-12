package com.hnef.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Board;
import com.hnef.engine.board.Move;
import com.hnef.engine.pieces.Piece;

public abstract class Player {

  protected final Board board;
  protected final Collection<Move> legalMoves;

  Player(final Board board, 
         final Collection<Move> legalMoves, 
         final Collection<Move> opponentMoves) {

    this.board = board;
    this.legalMoves = legalMoves;
  }

  public Collection<Move> getLegalMoves(){
    return this.legalMoves;
  }

  private static Collection<Move> calculateAttacksOnTile(final int piecePosition, final Collection<Move> moves) {
    final List<Move> attackMoves = new ArrayList<>();
    for(final Move move : moves) {
      if(piecePosition == move.getDestinationCoordinate()) {
        attackMoves.add(move);
      }
    }
    return Collections.unmodifiableList(attackMoves);
  }

  public abstract Collection<Piece> getActivePieces();

  public abstract Alliance getAlliance();

  public abstract Player getOpponent();

  public boolean isMoveLegal(final Move move) {
    return this.legalMoves.contains(move);
  }

  // King capture - Black win condition
  public boolean isCaptured() {
    return false;
  }

  /* May not apply from chess to Hnefatafl
  // To be determined
  public boolean isInStalemate() {
    return !isCaptured() && !hasEscapeMoves();
  }

  protected boolean hasEscapeMoves() {
    return true;
  }
  */

  // King reached safe spot - White win condition
  public boolean hasEscaped() {
    return false;
  }

  public MoveTransition makeMove(final Move move) {
    if(!isMoveLegal(move)){
      return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
    }
    final Board transitionBoard = move.execute();

    /* Not needed for Hnef - no check
    final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getPlayerKing().getPiecePosition(),
    transitionBoard.getCurrentPlayer().getLegalMoves());
*/
    return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
  }

}
