   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   public class Driver extends JPanel{ //makes a frame, starts a Game
      public static void main(String[] args){
      	JFrame frame = new JFrame("BattleFight");
         frame.setSize(832, 853);   
         frame.setLocation(200, 50);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new Game());
         frame.setVisible(true);
         frame.setFocusable(false);
			frame.setBackground(Color.black);
      }
   }