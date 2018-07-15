package edu.century.finalproject;

public abstract class Board {
  private int height;
  private int width;
  int board[][];

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    this.board = new int[height][width];
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public int[][] showBoard() {
    return this.board.clone();
  }

  public int[][] getBoard() {
    return this.board;
  }

  //sums the values of the 8 cells around a cell
  //https://en.wikipedia.org/wiki/Moore_neighborhood
  public int[][] calculateNeighbors(int out_of_bounds_value) {
  int[][] metaboard = new int[this.getHeight()][this.getWidth()];

    for (int row = 0; row < this.board.length; row++) {
      for (int cell = 0; cell < this.board[row].length; cell++) {
        //all 8 positions around a cell named
        int up      = safe2DArray(row    , cell - 1, this.board, out_of_bounds_value);
        int upRight = safe2DArray(row + 1, cell - 1, this.board, out_of_bounds_value);
        int upLeft  = safe2DArray(row - 1, cell - 1, this.board, out_of_bounds_value);

        int down      = safe2DArray(row    , cell + 1, this.board, out_of_bounds_value);
        int downRight = safe2DArray(row + 1, cell + 1, this.board, out_of_bounds_value);
        int downLeft  = safe2DArray(row - 1, cell + 1, this.board, out_of_bounds_value);

        int right = safe2DArray(row + 1, cell, this.board, out_of_bounds_value);
        int left  = safe2DArray(row - 1, cell, this.board, out_of_bounds_value);


        metaboard[row][cell] = up + upRight + upLeft
                             + down + downRight + downLeft
                             + right + left;
     }
    }
  return metaboard;
  }


  //wrapper around safeArray() for 2d arrays
  public int safe2DArray(int indexRow, int indexCell, int[][] array, int out_of_bounds_value) {
    if (indexRow >= 0 && indexRow < array.length) {
      return safeArray(indexCell, array[indexRow], out_of_bounds_value);
    } else {
      return out_of_bounds_value;
    }
  }

  //prevents out of bounds, default value given when going out of bounds
  public int safeArray(int index, int[] array, int out_of_bounds_value){
    if (index >= 0 && index < array.length) {
      return array[index];
    } else {
      return out_of_bounds_value;
    }
  }


  public abstract void rules();


}
