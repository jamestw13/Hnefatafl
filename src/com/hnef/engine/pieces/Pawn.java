package com.hnef.engine.pieces;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Board;
import com.hnef.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Pawn extends Piece {

  private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -1, -11, 11, 1 };

  Pawn(final int piecePosition, final Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(Board board) {

    // TODO add forbidden, king-only coordinates

    /* TODO CHECK COLUMN EXCLUSIONS!!!!!!!!! video #7 ~15 minutes in */

    final List<Move> legalMoves = new ArrayList<>();

    for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
      int candidateDestinationCoordinate = this.piecePosition;

      while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
        candidateDestinationCoordinate += candidateDestinationOffset;

        if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

          final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
          if (!candidateDestinationTile.isTileOccupied()) {
            legalMoves.add(new Move(board, this, candidateDestinationCoordinate));
          }
        }
        break;
      }

    }
    return Collections.unmodifiableList(legalMoves);
  }

}
