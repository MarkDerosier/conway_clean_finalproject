package edu.century.finalproject;

import java.util.Random;

public class Conway extends Board {
  private int valueOutOfBounds;

  public void rules() {
    int[][] neighbors = calculateNeighbors(valueOutOfBounds);

    for (int row = 0; row < this.board.length; row++) {
      for (int cell = 0; cell < this.board[row].length; cell++) {
        if (board[row][cell] == 1) {
          switch (neighbors[row][cell]) {
            case 1 :
            case 0 :
              board[row][cell] = 0;
              break;

            case 2 :
            case 3 :
              board[row][cell] = 1;
              break;

            default :
              board[row][cell] = 0;
              break;
          }
        } else {
            switch (neighbors[row][cell]) {
              case 3 :
                board[row][cell] = 1;
                break;

            }

        }
      }
    }
  }

  public Conway(int width, int height, int outOfBounds) {
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
