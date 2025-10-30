import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SudokuGUI extends JFrame {
    private static final int SIZE = 9;
    private JTextField[][] grid;
    private JButton solveButton;
    private JButton fillButton;
    private JButton savebutton;

    public SudokuGUI(int[][] initialBoard, int option) {
        
        setTitle("Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE + 1, SIZE));

        grid = new JTextField[SIZE][SIZE];

        if(option == 1){
            for (int j = 0; j < SIZE; j++) {
                for (int i = 0; i < SIZE; i++) {
                    grid[i][j] = new JTextField(1);
                    grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                    grid[i][j].setFont(new Font("Arial", Font.BOLD, 20));

                    // Remplir les cases pré-remplies avec les nombres initiaux
                    if (initialBoard[i][j] != 0) {
                        grid[i][j].setText(Integer.toString(initialBoard[i][j]));
                        grid[i][j].setEditable(false);
                    }

                    add(grid[i][j]);
                }
            }

            solveButton = new JButton("Solve");
            solveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    checkSolution(initialBoard);
                }
            });
            add(solveButton);

            fillButton = new JButton("Fill");
            fillButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fillGrid(initialBoard);
                }
            });
            add(fillButton);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        } 
        
        else{ 
            for (int j = 0; j < SIZE; j++) {
                for (int i = 0; i < SIZE; i++) {
                    grid[i][j] = new JTextField(1);
                    grid[i][j].setHorizontalAlignment(JTextField.CENTER);
                    grid[i][j].setFont(new Font("Arial", Font.BOLD, 20));

                    // Remplir les cases pré-remplies avec les nombres initiaux
                    if (initialBoard[i][j] != 0) {
                        grid[i][j].setText(Integer.toString(initialBoard[i][j]));
                    }

                    add(grid[i][j]);
                }
            }
            savebutton = new JButton("save");
            savebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent f) {
                    int[][] board = new int[SIZE][SIZE];
                    for (int i = 0; i < SIZE; i++) {
                        for (int j = 0; j < SIZE; j++) {
                            String text = grid[i][j].getText();
                            if (!text.isEmpty()) {
                                board[i][j] = Integer.parseInt(text);
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }

                    boolean error = false;
                    // check toute les position de la grille
                    for(int i = 0; i < 9; i++){
                        for(int j = 0; j < 9; j++){
                            // Check si la position est vide
                            if(board[i][j] != 0){
                                // Check si la valeur brise une regle
                                if (!resolveurGrille.isValid2(board, i, j, board[i][j])) {
                                    // si erreur, stop la boucle est renvoie une erreur
                                    error = true;
                                    break;
                                }
                            }
                        }
                        // sort de la 2eme boucle si erreur
                        if(error){
                            break;
                        }
                    }
                     // If no error is found, save the Sudoku board to a file
                     if(!error) {
                        saveGrid saver = new saveGrid();
                        saver.saveGrid(grid);
                    }
                }
            });
            
            add(savebutton);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    private void checkSolution(int[][] initialBoard) {
        int[][] board = new int[SIZE][SIZE];
        int[][] solution = resolveurGrille.resoudreGrille(initialBoard);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String text = grid[i][j].getText();
                if (!text.isEmpty()) {
                    board[i][j] = Integer.parseInt(text);
                } else {
                    board[i][j] = 0;
                }
            }
        }

        boolean correct = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != solution[i][j]) {
                    if (board[i][j] != 0) {
                        correct = false;
                        break;
                    }
                }
            }
        }

        if (correct) {
                JOptionPane.showMessageDialog(this, "Bravo ! La solution est correcte.");
            } else{
                JOptionPane.showMessageDialog(this, "Désolé, la solution est incorrecte.");
            }
    }

    private void fillGrid(int[][] initialBoard) {
        int[][] solution = resolveurGrille.resoudreGrille(initialBoard);

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j].getText().isEmpty()) {
                    grid[i][j].setText(Integer.toString(solution[i][j]));
                }
            }
        }
    }
}
