import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class saveGrid {
    public void saveGrid(JTextField[][] grid) {
        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("choose save directory");
            int userSelection = fileChooser.showSaveDialog(null);

            if(userSelection == JFileChooser.APPROVE_OPTION){
                FileOutputStream file = new FileOutputStream(fileChooser.getSelectedFile());
                DataOutputStream fichier = new DataOutputStream(file);
                JTextField[][] listeTxt = grid;
                for(int line = 0; line <9; line ++){
                    StringBuilder sb = new StringBuilder();
                    for(int column = 0; column <9; column ++){
                        String Texte = listeTxt[column][line].getText();
                        if(Texte.isEmpty()){
                            sb.append("0");
                        } else{
                            sb.append(Texte);
                        }
                    }
                    String numChaine = sb.toString();
                    int n = Integer.parseInt(numChaine);
                    fichier.writeInt(n);
                }
                fichier.close();
                JOptionPane.showMessageDialog(null, "Successfully saved");
            }
        } catch(IOException | NumberFormatException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving");
        }
    }
}