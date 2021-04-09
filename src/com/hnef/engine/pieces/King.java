package com.hnef.engine.pieces;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Board;
import com.hnef.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class King extends Piece {

  private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -1, -11, 11, 1 };

  King(final int piecePosition, final Alliance pieceAlliance) {
    super(piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(Board board) {

    // TODO add forbidden, king-only coordinates

    final List<Move> legalMoves = new ArrayList<>();

    for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
      int candidateDestinationCoordinate = this.piecePosition;

      while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

        if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)
            || isEleventhColumnExclusion(candidateDestinationCoordinate, candidateDestinationOffset)) {
          break;
        }

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

  @Override
  public String toString() {
    return PieceType.KING.toString();
  }

  private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
  }

  private static boolean isEleventhColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.ELEVENTH_COLUMN[currentPosition] && (candidateOffset == 1);
  }

}
