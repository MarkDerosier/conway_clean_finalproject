package edu.century.finalproject;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.lang.Thread;



public class BoardGUI extends JFrame {
  //Dropdown box for how many steps to run in the simulation
  private final static Integer[] stepsToTake = { 1, 2, 3, 4, 5, 10, 20, 30 };
  private JComboBox<Integer> stepsDropDown =  new JComboBox<Integer>(stepsToTake);


  //Panels
  private JPanel rootPane   = new JPanel();
  private JPanel buttonGrid = new JPanel();

  //Button for next iteration
  private JButton nextGeneration = new JButton("Next");

  //Button array
  private JButton allButtons[][];
  
  //Board dimensions
  private int width;
  private int height;

  //mutable reference to the boards array
  private int[][] rawArray;

  public BoardGUI(Board board, String title) {

    //get dimensions from board object passed to method
    width  = board.getWidth();
    height = board.getHeight();
    allButtons = new JButton[width][height];

    //x and y of interal board accidently
    //swapped compared to button array
    rawArray = board.getBoard();


    //Window Behavior
    setSize(600,600);
    setTitle(title);
   
    //Window Panes
    setContentPane(rootPane);
    rootPane.setLayout(new FlowLayout());
    rootPane.add(buttonGrid);
    rootPane.add(nextGeneration);
    rootPane.add(stepsDropDown);

    buttonGrid.setBorder(new EmptyBorder(5, 5, 5, 5));
    buttonGrid.setLayout(new GridLayout(width, height, 0, 0));

    //fill grid with buttons
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        JButton cellButton = new JButton(i + "," + j);
        //needed for color to show up on mac supposedly
        //but I do not have a mac to test it with
        cellButton.setOpaque(true);
        allButtons[i][j] = cellButton;
        buttonGrid.add(cellButton);

        //action listener for button
        cellButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            System.out.println(cellButton.getText());

            //parse button label back into numbers
            String[] location = cellButton.getText().split(",");
            int btnWidth  = Integer.parseInt(location[0]);
            int btnHeight = Integer.parseInt(location[1]);
            System.out.println("The value of the grid is: " + rawArray[btnHeight][btnWidth] );
            int valueOfCell = rawArray[btnHeight][btnWidth];
            if (valueOfCell == 1) {
              cellButton.setBackground(Color.WHITE);
              rawArray[btnHeight][btnWidth] = 0;
            } else {
              cellButton.setBackground(Color.BLACK);
              rawArray[btnHeight][btnWidth] = 1;
            
            }
            

          }
        });
      }
    }

    //initialize colors
    updateColors();



    //action listener for next button, generates next generation of board
    nextGeneration.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //unboxed and casting since JComboBox only uses Integers
        //which cannot be used in for loops
        Integer boxed = (Integer)stepsDropDown.getSelectedItem();
        int unboxed = boxed;
        for (int i = 0; i < boxed; i++) {
          board.rules();
          updateColors();
         //try {
         //  Thread.sleep(100);
         //} catch (InterruptedException exception) {
         //  exception.printStackTrace();
         //}
        }
      }
    });


	


  }



  //iterates through every button and cell
  //and updates the button to the correct color
  private void updateColors() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int valueOfCell = rawArray[j][i];
        //should be case statement, but due to time constraints
        //there are only boards with two colors
        //and thus an if works fine
        if (valueOfCell == 1) {
          allButtons[i][j].setBackground(Color.BLACK);
        } else {
          allButtons[i][j].setBackground(Color.WHITE);
        }
      }
    }
  }




}
