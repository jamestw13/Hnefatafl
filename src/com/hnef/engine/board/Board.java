package com.hnef.engine.board;

import com.hnef.engine.Alliance;
import com.hnef.engine.pieces.King;
import com.hnef.engine.pieces.Pawn;
import com.hnef.engine.pieces.Piece;
import com.hnef.engine.player.BlackPlayer;
import com.hnef.engine.player.Player;
import com.hnef.engine.player.WhitePlayer;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

  private final List<Tile> gameBoard;
  private final Collection<Piece> whitePieces;
  private final Collection<Piece> blackPieces;

  private final WhitePlayer whitePlayer;
  private final BlackPlayer blackPlayer;
  private final Player currentPlayer;

  private Board(final Builder builder) {
    this.gameBoard = createGameBoard(builder);
    this.whitePieces = calculateActivePieces(this.gameBoard, Alliance.WHITE);
    this.blackPieces = calculateActivePieces(this.gameBoard, Alliance.BLACK);

    final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
    final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);

    this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
    this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
    this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer, this.blackPlayer);
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
      final String tileText = this.gameBoard.get(i).toString();
      builder.append(String.format("%3s", tileText));
      if ((i + 1) % BoardUtils.NUM_TILES_PER_ROW == 0) {
        builder.append("\n");
      }
    }
    return builder.toString();
  }

  public Player whitePlayer() {
    return this.whitePlayer;
  }

  public Player blackPlayer() {
    return this.blackPlayer;
  }

  public Player currentPlayer() {
    return this.currentPlayer;
  }
  public Collection<Piece> getBlackPieces() {
    return this.blackPieces;
  }

  public Collection<Piece> getWhitePieces() {
    return this.whitePieces;
  }

  private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
    final List<Move> legalMoves = new ArrayList<>();

    for (final Piece piece : pieces) {
      legalMoves.addAll(piece.calculateLegalMoves(this));
    }
    return Collections.unmodifiableList(legalMoves);
  }

  private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
    final List<Piece> activePieces = new ArrayList<>();

    for (final Tile tile : gameBoard) {
      if (tile.isTileOccupied()) {
        final Piece piece = tile.getPiece();
        if (piece.getPieceAlliance() == alliance) {
          activePieces.add(piece);
        }
      }
    }
    return Collections.unmodifiableList(activePieces);
  }

  public Tile getTile(final int tileCoordinate) {
    return gameBoard.get(tileCoordinate);
  }

  private static List<Tile> createGameBoard(final Builder builder) {
    final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
    for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
      tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
    }
    return Collections.unmodifiableList(Arrays.asList(tiles));
  }

  public static Board createHnefataflBoard() {
    final Builder builder = new Builder();
    // Black Layout
    builder.setPiece(new Pawn(3, Alliance.BLACK));
    builder.setPiece(new Pawn(4, Alliance.BLACK));
    builder.setPiece(new Pawn(5, Alliance.BLACK));
    builder.setPiece(new Pawn(6, Alliance.BLACK));
    builder.setPiece(new Pawn(7, Alliance.BLACK));
    builder.setPiece(new Pawn(16, Alliance.BLACK));
    builder.setPiece(new Pawn(33, Alliance.BLACK));
    builder.setPiece(new Pawn(43, Alliance.BLACK));
    builder.setPiece(new Pawn(44, Alliance.BLACK));
    builder.setPiece(new Pawn(54, Alliance.BLACK));
    builder.setPiece(new Pawn(55, Alliance.BLACK));
    builder.setPiece(new Pawn(56, Alliance.BLACK));
    builder.setPiece(new Pawn(64, Alliance.BLACK));
    builder.setPiece(new Pawn(65, Alliance.BLACK));
    builder.setPiece(new Pawn(66, Alliance.BLACK));
    builder.setPiece(new Pawn(76, Alliance.BLACK));
    builder.setPiece(new Pawn(77, Alliance.BLACK));
    builder.setPiece(new Pawn(87, Alliance.BLACK));
    builder.setPiece(new Pawn(104, Alliance.BLACK));
    builder.setPiece(new Pawn(113, Alliance.BLACK));
    builder.setPiece(new Pawn(114, Alliance.BLACK));
    builder.setPiece(new Pawn(115, Alliance.BLACK));
    builder.setPiece(new Pawn(116, Alliance.BLACK));
    builder.setPiece(new Pawn(117, Alliance.BLACK));
    // White Layout
    builder.setPiece(new Pawn(38, Alliance.WHITE));
    builder.setPiece(new Pawn(48, Alliance.WHITE));
    builder.setPiece(new Pawn(49, Alliance.WHITE));
    builder.setPiece(new Pawn(50, Alliance.WHITE));
    builder.setPiece(new Pawn(58, Alliance.WHITE));
    builder.setPiece(new Pawn(59, Alliance.WHITE));
    builder.setPiece(new King(60, Alliance.WHITE));
    builder.setPiece(new Pawn(61, Alliance.WHITE));
    builder.setPiece(new Pawn(62, Alliance.WHITE));
    builder.setPiece(new Pawn(70, Alliance.WHITE));
    builder.setPiece(new Pawn(71, Alliance.WHITE));
    builder.setPiece(new Pawn(72, Alliance.WHITE));
    builder.setPiece(new Pawn(82, Alliance.WHITE));
    // White to Move
    builder.setMoveMaker(Alliance.WHITE);

    return builder.build();
  }

  public static class Builder {

    Map<Integer, Piece> boardConfig;
    Alliance nextMoveMaker;

    public Builder() {
      this.boardConfig = new HashMap<>();
    }

    public Builder setPiece(final Piece piece) {
      this.boardConfig.put(piece.getPiecePosition(), piece);
      return this;
    }

    public Builder setMoveMaker(final Alliance alliance) {
      this.nextMoveMaker = nextMoveMaker;
      return this;
    }

    public Board build() {
      return new Board(this);
    }
  }

}
