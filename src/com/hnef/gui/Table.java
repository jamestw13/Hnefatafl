package com.hnef.gui;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.StrokeBorder;

import com.hnef.engine.board.BoardUtils;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Table {
  private final JFrame gameFrame;
  private final BoardPanel boardPanel;

  private final static Dimension OUTER_FRAME_DIMENSION = new Dimension(600, 600);
  private final static Dimension BOARD_PANEL_DIMENSION = new Dimension(400, 530);
  private final static Dimension TILE_PANEL_DIMENSION = new Dimension(10, 10);

  public Table() {
    this.gameFrame = new JFrame("JHnef");
    this.gameFrame.setLayout(new BorderLayout());
    final JMenuBar tableMenuBar = createTableMenuBar();
    this.gameFrame.setJMenuBar(tableMenuBar);
    this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
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
      System.out.println("check");
      validate();
    }

  }
}
