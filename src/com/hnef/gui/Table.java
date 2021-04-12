package com.hnef.gui;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.hnef.engine.board.Board;
import com.hnef.engine.board.BoardUtils;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Table {
  private final JFrame gameFrame;
  private final BoardPanel boardPanel;
  private final Board hnefBoard;

  private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
  private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 530);
  private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);
  private static String defaultPieceImagesPath = "art/";

  public Table() {
    this.gameFrame = new JFrame("JHnef");
    this.gameFrame.setLayout(new BorderLayout());
    final JMenuBar tableMenuBar = createTableMenuBar();
    this.gameFrame.setJMenuBar(tableMenuBar);
    this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
    this.hnefBoard = Board.createHnefataflBoard();
    this.boardPanel = new BoardPanel();
    this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
    this.gameFrame.setVisible(true);
  }

  private JMenuBar createTableMenuBar() {
    final JMenuBar tableMenuBar = new JMenuBar();
    tableMenuBar.add(createFileMenu());
    return tableMenuBar;
  }

  private JMenu createFileMenu() {
    final JMenu fileMenu = new JMenu("File");
    final JMenuItem openPGN = new JMenuItem("Load PGN File");
    openPGN.addActionListener((e) -> {
      System.out.println("open up that pgn file");
    });
    fileMenu.add(openPGN);

    final JMenuItem exitMenuItem = new JMenuItem("Exit");
    exitMenuItem.addActionListener((e) -> {
      System.out.println("exiting");
      System.exit(0);
    });
    fileMenu.add(exitMenuItem);
    return fileMenu;

  }

  private class BoardPanel extends JPanel {
    final List<TilePanel> boardTiles;

    BoardPanel() {
      super(new GridLayout(11, 11));
      this.boardTiles = new ArrayList<>();
      for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
        final TilePanel tilePanel = new TilePanel(this, i);
        this.boardTiles.add(tilePanel);
        add(tilePanel);
      }
      setPreferredSize(BOARD_PANEL_DIMENSION);
      validate();
    }
  }

  private class TilePanel extends JPanel {
    private final int tileId;

    TilePanel(final BoardPanel boardPanel, final int tileId) {
      super(new GridBagLayout());
      this.tileId = tileId;
      Border border = BorderFactory.createLineBorder(Color.BLACK);
      setPreferredSize(TILE_PANEL_DIMENSION);
      setBackground(Color.decode("#593E1A"));
      setBorder(border);
      assignTilePieceIcon(hnefBoard);
      validate();
    }

    private void assignTilePieceIcon(final Board board) {
      this.removeAll();
      if (board.getTile(this.tileId).isTileOccupied()) {
        try {
          final BufferedImage image = ImageIO.read(new File(defaultPieceImagesPath
              + board.getTile(this.tileId).getPiece().getPieceAlliance().toString().substring(0, 1)
              + board.getTile(this.tileId).getPiece().toString() + ".gif"));
          add(new JLabel(new ImageIcon(image)));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }
}
