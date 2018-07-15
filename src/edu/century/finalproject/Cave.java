package edu.century.finalproject;

import java.util.Random;

public class Cave extends Board {
  private int valueOutOfBounds;

  public void rules() {
    int[][] neighbors = calculateNeighbors(valueOutOfBounds);

    for (int row = 0; row < this.board.length; row++) {
      for (int cell = 0; cell < this.board[row].length; cell++) {
        if (board[row][cell] == 1) {
          if (neighbors[row][cell] >= 4) {
            this.board[row][cell] = 1;
          } else {
            this.board[row][cell] = 0;
          }

        } else {
          if (neighbors[row][cell] >= 5) {
            this.board[row][cell] = 1;
          } else {
            this.board[row][cell] = 0;
          }
        }
      }
    }
  }

  public Cave(int width, int height, int outOfBounds) {
    super(width, height);
    valueOutOfBounds = outOfBounds;
    init(this.board);
  }

  private void init(int[][] board) {
    Random rnd = new Random();
    for (int row = 0; row < board.length; row++) {
      for (int cell = 0; cell < board[row].length; cell++) {
        board[row][cell] = rnd.nextInt(2);
      }
    }
  }

}
