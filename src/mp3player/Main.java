/*
 * Main.java
 *
 * Created on 13. September 2005, 11:18
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package mp3player;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import javazoom.jlgui.basicplayer.*;
import java.io.File;



/**
 *
 * @author Sickert Alexander
 */
public class Main {
    
    
    
    
    /** Creates a new instance of Main */
    public Main() {
      
        
       
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
	{
            WinMain mainwindow;	
            
            
            JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try
		{
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{
			System.out.println("Failed loading L&F: ");
                        
			System.out.println(ex);
		}
		
                mainwindow = new WinMain();
               // new WinListDetail();
                
                
                
                
	}
    
}
