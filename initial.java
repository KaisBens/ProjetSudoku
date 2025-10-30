import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class initial {
    private static int[][] initialBoard = new int[9][9];
    private static boolean ouvert = false;
    
    private static void initial(){
        JFrame fenetre = new JFrame();
    fenetre.setSize(500, 300); 
    fenetre.setLocation(100, 100);
    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel p = new JPanel();
    JButton button = new JButton("select file");
    p.add(button);
    JButton button2 = new JButton("open grid");
    p.add(button2);
    JButton button3 = new JButton("edit grid");
    p.add(button3);
    fenetre.add(p);
    fenetre.setVisible(true);
    JFileChooser fichier = new JFileChooser("./");
    button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent f) {
        int r = fichier.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION){
            int[][] tab = new int[9][9];
                    int k = 0;
                    try {
                        FileInputStream file = new FileInputStream(fichier.getSelectedFile());
                        DataInputStream f1 = new DataInputStream(file);
                        for(int i = 0; i<9; i++){
                            String line = "";
                            line = Integer.toString(f1.readInt());
                            int place = 0;
                            int count = 9- line.length();
                            System.out.println(line);
                            for (int j = count; j<9; j++){
                                char temp = line.charAt(place);
                                tab[j][k] = Integer.parseInt(String.valueOf(temp));
                                place +=1;
                            }
                            k += 1;
                        }
                        try{
                            file.close();
                            ouvert = true;
                        }catch(IOException e){
                            System.out.println("erreur fermeture du fichier");
                        }
                }catch(IOException e) {
                        System.out.println("erreur ouverture du fichier");
                    }
                    initialBoard = tab;
                }
                    else{
                        System.out.println("the user cancelled the operation");
                    }
            }
            
        });
            
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent g) {
                if(ouvert){
                    SudokuGUI sudokuGUI = new SudokuGUI(initialBoard, 1);
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent h) {
                if(ouvert){
                SudokuGUI sudokuGUI = new SudokuGUI(initialBoard, 2);
                }
            }
        });
    }
    public static void main(String[] args) {
        initial();
    }
}