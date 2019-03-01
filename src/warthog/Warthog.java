package warthog;

//File: Lab 10 Exercise 2
//
//Author: <Your Name>
//
//A very simple application to examine usage of anonymous ActionListeners


import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

class NewFrame extends JFrame {

// Data.
Button quitB;              // Quit button.
Button getGradient;     // Two silly buttons.
Panel toolbar;

// Constructor.
public NewFrame( int width, int height ) {
 // Set the title and other frame parameters.
 this.setTitle( "Warthog" );
 this.setResizable( true );
 this.setSize( width, height );
 
 //Quit button 
 quitB = new Button(" Quit ");
 quitB.setFont( new Font( "Serif", Font.PLAIN | Font.BOLD, 15 ) );
 
 //Quit button action
 quitB.addActionListener( new ActionListener() {
     public void actionPerformed( ActionEvent a ) {
	  System.exit( 0 );
     }
   }
 );
 
 //get contentpane to add buttons and other graphics
 Container cPane = this.getContentPane();
 cPane.add(quitB, BorderLayout.SOUTH);
 
 //toolbar set-up
 toolbar = new Panel(); 
 toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.Y_AXIS));
 
 //Gradient button
 getGradient = new Button(" Gradient ");
 getGradient.setFont( new Font( "Serif", Font.PLAIN | Font.BOLD, 15 ) );
 
 getGradient.addActionListener( new ActionListener() {
     public void actionPerformed( ActionEvent a ) {
	  //get each individual pixel to perform calculation
    	
    	 
     }
   }
 );

 // Show the frame.
 this.setVisible( true );
}

} // End of class "NewFrame"


public class Warthog {

public static void main( String[] argv ) {
		//Initializes object of type NewFrame with dimensions of 300 by 200
		NewFrame nf = new NewFrame( 300, 200 );
		}

}
