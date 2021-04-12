package com.hnef.engine.pieces;

import com.hnef.engine.Alliance;
import com.hnef.engine.board.Board;
import com.hnef.engine.board.BoardUtils;
import com.hnef.engine.board.Move;
import com.hnef.engine.board.Move.NeutralMove;
import com.hnef.engine.board.Move.AttackMove;
import com.hnef.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

  private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -1, -11, 11, 1 };

  public Pawn(final int piecePosition, final Alliance pieceAlliance) {
    super(PieceType.PAWN, piecePosition, pieceAlliance);
  }

  @Override
  public Collection<Move> calculateLegalMoves(final Board board) {

    final List<Move> legalMoves = new ArrayList<>();

    for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
      int candidateDestinationCoordinate = this.piecePosition;

      while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

        if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)
            || isLastColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
          break;
        }

        candidateDestinationCoordinate += candidateCoordinateOffset;

        if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

          final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
          if (!candidateDestinationTile.isTileOccupied() && !isKingOnlySpace(candidateDestinationCoordinate)) {
            final List<Piece> attackedPieces = isAttackMove(candidateDestinationCoordinate, board);
            if (attackedPieces.isEmpty()) {
              legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinate, attackedPieces));
            }
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
    return PieceType.PAWN.toString();
  }

  private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1);
  }

  private static boolean isLastColumnExclusion(final int currentPosition, final int candidateOffset) {
    return BoardUtils.LAST_COLUMN[currentPosition] && (candidateOffset == 1);
  }

  public Pawn movePiece(final Move move) {
    return new Pawn(move.getDestinationCoordinate(), move.getMovedPiece().getPieceAlliance());
  }

  private static boolean isKingOnlySpace(final int currentPosition) {
    return BoardUtils.KING_ONLY_SPACE[currentPosition];
  }

  private final List<Piece> isAttackMove(final int currentPosition, final Board board) {
    final List<Piece> attackedPieces = new ArrayList<>();

    for (final int candidateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {

      if (!BoardUtils.isValidTileCoordinate(currentPosition + candidateOffset)
          || !BoardUtils.isValidTileCoordinate(currentPosition + (candidateOffset * 2))) {
        break;
      }
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
