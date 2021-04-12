package com.hnef.engine;

import com.hnef.engine.player.BlackPlayer;
import com.hnef.engine.player.Player;
import com.hnef.engine.player.WhitePlayer;

public enum Alliance {
  WHITE() {
    @Override
    public boolean isWhite() {
      return true;
    }

    @Override
    public boolean isBlack() {
      return false;
    }

    @Override
    public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
      return whitePlayer;
    }
  },

  BLACK() {
    @Override
    public boolean isWhite() {
      return false;
    }

    @Override
    public boolean isBlack() {
      return true;
    }

    @Override
    public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
      return blackPlayer;
    }
  };

  public abstract boolean isWhite();

  public abstract boolean isBlack();

public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
