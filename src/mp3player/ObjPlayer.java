/*
 * ObjPlayer.java
 *
 * Created on 15. September 2005, 09:18
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
import java.io.*;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;




/**
 *
 * @author Sickert Alexander
 */
public class ObjPlayer {
        
        BasicPlayer player;
        String playMethod;
        Integer playStartPosition;
        Integer playCurrentPosition;
        ConfigManager config;
        WinMain win;
        private static MicroLogger log = new MicroLogger();
        
        
    /** Creates a new instance of ObjPlayer */
    public ObjPlayer(WinMain w) {
        
        win = w;
        player = new BasicPlayer();
        config = new ConfigManager("configMp3Player.xml");
        
    }
    
    /** spiele lise in textdatei. 
     * methode = random oder linear
     * startposition = 0 oder poisition innerhalb der playlist 
     * nach jedem lied muss plalistfile neu gelesen werden wegen ver�nderungen
     */
    public void playPlaylist(String method)
    {
        /** playCurrentPosition 
         *  playStartPosition
         *  playMethod
         *  m�ssen gesetzt werden
         */
        
        // 1. stop plyer wenn er grad l�uft
        String currFileString;
        int x;
        java.util.Timer myTimer = new java.util.Timer();
        int currentCount;
        currFileString = "";
        currentCount = 1;
        boolean played = false;
//        x = 0;
        do
        {
                if( player.getStatus() != 0 )
                {
                    // n�chstes file bekommen von funktion aus util objekt
                    log.info("------- Player Status: " + player.getStatus()  + currentCount);
                    currFileString = this.getNextFile("normal", currentCount);
                     win.currentlyPlaying(currFileString);
                    //log.info("Player Status: " + player.getStatus() );
                    //log.info("Player File: " + currFileString );
                     

                    if(currFileString != null && currFileString != "")
                    {
                        // dann spiele file
                         played = true;
                         log.info("------- Player Status playing " + currentCount + ": " + currFileString );
                        this.playSingle(currFileString);
                    }
                     
                     if(currFileString != null && currFileString.length() > 3){
                         currentCount = currentCount + 1;
                     }else{
                         if(played){
                             currentCount = 1;
                         }                         
                     }
                }
                // wenn string nicht leer dann spiele file
                
//                x = x + 1;
               log.info("------- Player Status: " + player.getStatus()  + currentCount);
               try{
                   this.wait(1000);
               }catch(Exception e){
                   log.info("error waiting");
               }
                       
        }while (currFileString != null);    
    }
    
    
    public void stop(){
        try{
            player.stop();
        }catch(Exception y){
            
        }
    }
    
    /** stoppt playlist und spielt sofort einzelnes file und kehrt danach zur 
     * tmp-playlist zur�ck 
     */
    public void playSingle(String location)
    {
        // wenns schon spielt dann stoppen und neu starten
             
        
        if (player.getStatus() == 0) 
        {
                try
                {
                    player.stop();
                }
                catch (Exception e)
                {
                    log.info("Cannot stop player");
                }        
        }
        
        
        File myfile;
                
        myfile = new File(location);
                
                try
                {
                    player.open(myfile);
                    win.currentlyPlaying(location);
                    player.play();
                    log.info("player-playing:" + player.getStatus());
                }
                catch (Exception e)
                {
                    log.info("Failed playing. Reason: " + e.getMessage());
                }    
    }
    
   
  
    
    public String getNextFile(String mode, int start)
    {
        String strFileName;
        int intNext;
        int current;
        
//        intNext = start + 1;
        intNext = start;
        
        strFileName = "";
        
                String Buf;
                String FileString;
                FileString = "";
                ArrayList currFiles;                
                currFiles = new ArrayList();
                UtilFunc util = new UtilFunc();
                
                
                // bisherigen string holen
                current = 0;
                try
                {
                    
                    FileReader fileread = new FileReader(this.config.getValue("PathToPlayList") + "/tmp.plx");                    
                    BufferedReader buf = new BufferedReader(fileread);
                    
                    do{
                       Buf = buf.readLine();
                       current = current + 1;
                        if(current == intNext )
                        {
                        strFileName = Buf;
                        }
                       log.info(Buf);  
                       
                    }while(Buf != null);
                    fileread.close();
                }
                 catch(Exception y)
                {                
                    log.info("\nFehler im file lesen");                
                }
                
                strFileName = util.decodeString(strFileName);
    
        return strFileName;
    }
    
    public int getFileCount()
    {
        String strFileName;
        int intNext;
        int current;
        
        intNext = 0;
        
        strFileName = "";
        
                String Buf;
                String FileString;
                FileString = "";
                ArrayList currFiles;                
                currFiles = new ArrayList();                
                
                // bisherigen string holen
                current = 0;
                try
                {
                    FileReader fileread = new FileReader(this.config.getValue("PathToPlayList") + "/tmp.plx");                    
                   
                    //FileReader fileread = new FileReader("tmp.plx");                    
                    BufferedReader buf = new BufferedReader(fileread);
                    
                    do{
                       Buf = buf.readLine();
                       current = current + 1;                        
                       
                    }while(Buf != null);
                    fileread.close();
                }
                 catch(Exception y)
                {                
                    log.info("\nFehler im file lesen");                
                }
    
        return current;
    }
}
