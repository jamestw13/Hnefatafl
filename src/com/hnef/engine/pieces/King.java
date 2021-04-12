package com.hnef.engine.pieces;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Board;
import com.hnef.engine.board.BoardUtils;
import com.hnef.engine.board.Move;
import com.hnef.engine.board.Move.NeutralMove;
import com.hnef.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class King extends Piece {

  private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -1, -11, 11, 1 };

  public King(final int piecePosition, final Alliance pieceAlliance) {
    super(PieceType.KING, piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(Board board) {

    final List<Move> legalMoves = new ArrayList<>();

    for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
      int candidateDestinationCoordinate = this.piecePosition;

      while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

        if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)
            || isEleventhColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
          break;
        }

        candidateDestinationCoordinate += candidateCoordinateOffset;

        if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

          final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
          if (!candidateDestinationTile.isTileOccupied()) {
            legalMoves.add(new NeutralMove(board, this, candidateDestinationCoordinate));
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
    return BoardUtils.LAST_COLUMN[currentPosition] && (candidateOffset == 1);
  }

  @Override
  public King movePiece(final Move move) {
    return new King(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
  }

}
