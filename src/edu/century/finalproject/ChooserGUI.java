package edu.century.finalproject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ChooserGUI extends JFrame {

  //JComboBox for board types
  private String[] boardTypes = { "Conways Game Of life", "Conways Game of life: 1 varient", "Cave generator", "Cave generator: 0 varient", "Day and Night", "Day and Night: 1 varient"};
  JComboBox<String> chooseBoard = new JComboBox<String>(boardTypes);

  //Button to create board 
  JButton createBoard = new JButton("Create Board!");

  //Panels
  private JPanel rootPane = new JPanel();

  //Label
  private JLabel lbChooseBoard = new JLabel("Board Type");

  //instructions
  private JTextArea help = new JTextArea("This is a cellular automata simulator, It simulates common types of automata, with variation. The next button will procead the specified number of generations forward. Any cell can be clicked on to toggle it on or off. The varients deal with the default value for the outof bounds cells, showing how simple changes can drastically alter the system");

  public ChooserGUI() {
    //Window Behavior
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Window Panes
    setContentPane(rootPane);
    rootPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    rootPane.setLayout(new FlowLayout());
    rootPane.add(lbChooseBoard);
    rootPane.add(chooseBoard);
    rootPane.add(createBoard);
    rootPane.add(help);

    //text field settings
    help.setEditable(false);


    //Action Listen, spawns the board
    createBoard.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String windowTitle = chooseBoard.getItemAt(chooseBoard.getSelectedIndex());
        Board gameBoard;
        switch (chooseBoard.getSelectedIndex()) {
          case 0 :
            gameBoard = new Conway(70,55, 0);
            break;
          case 1 :
            gameBoard = new Conway(70,55,1);
            break;
          case 2 :
            gameBoard = new Cave(70,55,1);
            break;
          case 3 :
            gameBoard = new Cave(70,55,0);
            break;
          case 4 :
            gameBoard = new DayAndNight(70,55,0);
            break;
          case 5 :
            gameBoard = new DayAndNight(70,55,1);
            break;

          default :
            gameBoard = new Conway(70,55, 0);
            break;
        }
        BoardGUI GUI = new BoardGUI(gameBoard, windowTitle);
        GUI.setVisible(true);


      }
    });
    
  }
}
