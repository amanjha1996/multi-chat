/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author lucky
 */
public class chatClient extends JFrame implements Runnable{
    
   Socket socket;
   JTextArea ta;
   JButton  send,logout;
   JTextField tf;
   
   Thread thread;
   
    DataInputStream din;
    DataOutputStream dout;
    String LoginName;
    chatClient(String login)throws UnknownHostException,IOException
    {
        super(login);
        LoginName=login;
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                try {
                    dout.writeUTF(LoginName+" "+"LOGOUT");
                } catch (IOException ex) {
                    Logger.getLogger(chatClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        ta=new JTextArea(18,50);
        tf=new JTextField(50);
        
        send=new JButton("Send");
        logout=new JButton("Logout");
        
        send.addActionListener(new ActionListener()
        {
            
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                dout.writeUTF(LoginName + " "+ "DATA " + tf.getText().toString());
                tf.setText(" ");
                }
                catch(IOException e1)
                {
                    e1.printStackTrace();
                }
            }});
        
        
         logout.addActionListener(new ActionListener()
        {
            
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                dout.writeUTF(LoginName + " "+ "LOGOUT" );
             System.exit(1);
                }
                catch(IOException e1)
                {
                    e1.printStackTrace();
                }
            }});
        
        
        
        socket=new Socket("localhost",5218);
        
        din=new DataInputStream(socket.getInputStream());
        dout=new DataOutputStream(socket.getOutputStream());
        
        dout.writeUTF(LoginName);
        dout.writeUTF(LoginName+" "+"LOGIN");
        thread =new Thread(this);
        
        thread.start();
        setup();
    }
    private void setup()
    {
        setSize(600,400);
        JPanel panel=new JPanel();
        
        panel.add(new JScrollPane(ta));
        panel.add(tf);
        panel.add(send);
        panel.add(logout);
        
        add(panel);
        setVisible(true);
    }
    
    
    
    
    public void run()
    {
        while(true)
        {
            try
            {
                ta.append("\n" +din.readUTF());
                
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
   
    
    
}
