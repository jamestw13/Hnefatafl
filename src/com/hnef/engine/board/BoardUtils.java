package com.hnef.engine.board;

public class BoardUtils {

  public static final int NUM_TILES = 121;
  public static final int NUM_TILES_PER_ROW = 11;

  public static final boolean[] FIRST_COLUMN = initColumn(0);
  public static final boolean[] LAST_COLUMN = initColumn(NUM_TILES_PER_ROW - 1);

  public final static boolean[] KING_ONLY_SPACE = initKingSpaces();

  private static boolean[] initColumn(int columnNumber) {
    final boolean[] column = new boolean[121];

    do {
      column[columnNumber] = true;
      columnNumber += NUM_TILES_PER_ROW;
    } while (columnNumber < NUM_TILES);

    return column;
  }

  private static boolean[] initKingSpaces() {
    final boolean[] kingSpaces = new boolean[121];

    kingSpaces[0] = true;
    kingSpaces[NUM_TILES_PER_ROW - 1] = true;
    kingSpaces[NUM_TILES - NUM_TILES_PER_ROW - 1] = true;
    kingSpaces[NUM_TILES - 1] = true;

    return kingSpaces;
  }

  private BoardUtils() {
    throw new RuntimeException("You cannot instantiate me!");
  }

  public static boolean isValidTileCoordinate(int coordinate) {
    return coordinate >= 0 && coordinate < NUM_TILES;
  }
}
