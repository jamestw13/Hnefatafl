package com.hnef.engin.board;

public class BoardUtils {

  public static final boolean[] FIRST_COLUMN = initColumn(0);
  public static final boolean[] SECOND_COLUMN = initColumn(1);
  public static final boolean[] TENTH_COLUMN = initColumn(9);
  public static final boolean[] ELEVENTH_COLUMN = initColumn(10);

  public final boolean[] KING_ONLY_SPACE = initKingSpaces();

  public static final int NUM_TILES = 121;
  public static final int NUM_TILES_PER_ROW = 11;

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
  }

  private BoardUtils() {
    throw new RuntimeException("You cannot instantiate me!");
  }

  public static boolean isValidTileCoordinate(int coordinate) {
    return coordinate >= 0 && coordinate < NUM_TILES;
  }
}
