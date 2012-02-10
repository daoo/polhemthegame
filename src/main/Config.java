package main;

import game.types.Binds;

public class Config {
  public final Binds player1, player2;

  public Config(Binds player1, Binds player2) {
    this.player1 = player1;
    this.player2 = player2;
  }
}
