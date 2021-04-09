package com.hnef.engine.board;

import com.hnef.engine.pieces.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

  protected int tileCoordinate;

  private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

  private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
    final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

    for (int i = 0; i < 121; i++) {
      emptyTileMap.put(i, new EmptyTile(i));
    }

    return Collections.unmodifiableMap(emptyTileMap);
  }

  public static Tile createTile(final int tileCoordinate, final Piece piece) {
    return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
  }

  Tile(int tileCoordinate) {
    this.tileCoordinate = tileCoordinate;
  }

  public abstract boolean isTileOccupied();

  public abstract Piece getPiece();

  public static final class EmptyTile extends Tile {

    EmptyTile(int coordinate) {
      super(coordinate);
    }

    @Override
    public boolean isTileOccupied() {
      return false;
    }

    @Override
    public Piece getPiece() {
      return null;
    }
  }

  public static final class OccupiedTile extends Tile {

    Piece pieceOnTile;

    OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
      super(tileCoordinate);
      this.pieceOnTile = pieceOnTile;
    }

    @Override
    public boolean isTileOccupied() {
      return true;
    }

    @Override
    public Piece getPiece() {
      return this.pieceOnTile;
    }

  }
}