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

    final List<Move> legalMoves = new ArrayList<>();

    for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
      int candidateDestinationCoordinate = this.piecePosition;

      while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

        if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)
            || isLastColumnExclusion(candidateDestinationCoordinate, candidateDestinationOffset)) {
          break;
        }

        candidateDestinationCoordinate += candidateDestinationOffset;

        if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

          final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
          if (!candidateDestinationTile.isTileOccupied() && !isKingOnlySpace(candidateDestinationCoordinate)) {
            if (isAttackMove(candidateDestinationCoordinate)) {
              legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, attackedPieces));
            }
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
    return PieceType.PAWN.toString();
  }

  private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
  }

  private static boolean isLastColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.LAST_COLUMN[currentPosition] && (candidateOffset == 1);
  }

  private static boolean isKingOnlySpace(final int currentPosition) {
    return BoardUtils.KING_ONLY_SPACE[currentPosition];
  }

  private final Piece[] isAttackMove(final int currentPosition) {
    final List<Piece> attackedPieces;

    for (final int candidateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
      final Tile adjacentTile = board.getTile(currentPosition + candidateOffset);

      if (adjacentTile.isTileOccupied()) {
        final Tile superAdjacentTile = board.getTile(currentPosition + (candidateOffset * 2));
        if (superAdjacentTile.isTileOccupied()) {
          if (adjacentTile.getPiece().getPieceAlliance() != this.pieceAlliance) {
            if (superAdjacentTile.getPiece().getPieceAlliance() == this.pieceAlliance) {
              attackedPieces.add(adjacentTile.getPiece());
            }

          }

        }
      }
    }
    return Collections.unmodifiableList(attackedPieces);
  }

}
