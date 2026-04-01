package view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TestMenu {

    public static void main(String[] args) {
      
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                JFrame frame = new JFrame("Test - Menu Principal");
                
                
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
                frame.setSize(800, 600); 
                
               
                frame.setLocationRelativeTo(null); 
                
                
                VueMenu monMenu = new VueMenu();
                
               
                frame.add(monMenu);
                
               
                frame.setVisible(true);
            }
        });
    }
}