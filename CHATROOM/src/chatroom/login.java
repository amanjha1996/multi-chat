/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author lucky
 */
public class login {
public static void main(String args[])
{
    JFrame login=new JFrame("Login");
    JPanel panel=new JPanel();
    final JTextField loginName=new JTextField(20);
    JButton enter=new JButton("login");
    
    panel.add(loginName);
    panel.add(enter);
    login.setSize(300,100);
    login.add(panel);
    login.setVisible(true);
    login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    enter.addActionListener(new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {  
                chatClient client=new chatClient(loginName.getText());
                login.setVisible(false);
                login.dispose();
            } catch (IOException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    });
    loginName.addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent ke) {
             //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if(ke.getKeyCode()==KeyEvent.VK_ENTER)
            {
                try {
                    
                    chatClient client=new chatClient(loginName.getText());
                    login.setVisible(false);
                    login.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }

        @Override
        public void keyReleased(KeyEvent ke) {
             //To change body of generated methods, choose Tools | Templates.
        }
    } );
    {
        
}
    
}}
