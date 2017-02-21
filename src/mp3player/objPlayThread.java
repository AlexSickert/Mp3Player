/*
 * objPlayThread.java
 *
 * Created on 22. September 2005, 00:40
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package mp3player;

import javazoom.jlgui.basicplayer.*;
import java.io.File;
import java.io.*;
import java.util.Random;

/**
 *
 * @author Sickert Alexander
 */
public class objPlayThread implements Runnable{
    
    private Thread runner;
    public String running;    
    BasicPlayer playerutil;
    ObjPlayer util;
    WinMain win;
    private static MicroLogger log = new MicroLogger();
    
    
    /** Creates a new instance of objPlayThread */
    public objPlayThread(WinMain w) {
        win = w;
        if (runner == null)
        {        
        runner = new Thread(this);
        runner.start();
        }
    }
    
    public void run()
    {
        
        
        playerutil = new BasicPlayer();
        util = new ObjPlayer(win);
         
          String currFileString;
        int x;
        int randomMax;
        int randomNow;
        java.util.Timer myTimer = new java.util.Timer();
        int currentCount;
        currFileString = "";
        File myFile;
        currentCount = 0;
        x = 0;
        running = "yes"; 
             
            do{
                    if( playerutil.getStatus() == -1 ||  playerutil.getStatus() == 2)
                    {
                        // n�chstes file bekommen von funktion aus util objekt
                        currentCount = currentCount + 1;
                       
                        // set selection type default to random
//                        randomMax = util.getFileCount();
//                        
//                        // random number between 1 AND randomMax
//                        Random r = new Random();
//                        randomNow = r.nextInt(randomMax-1);
//                        randomNow += 1;
                        
                        randomNow = currentCount;
                        
                                                
                        currFileString = util.getNextFile("normal", randomNow);
                        win.currentlyPlaying(currFileString);
              
                        if(currFileString != null && currFileString != "")
                        {
                            // dann spiele file                            
                            myFile = new File(currFileString);
                            try{
                            playerutil.open(myFile);
                            playerutil.play();
                            }catch(Exception t)
                            {}
                        }
                    }
                    
                                  
                 
                        try{
                          
                         
                           runner.sleep(1000);
                            
                        }catch(Exception p)
                         {
                        log.info("Timer Exception: "  + p.getMessage());
                        }
                  
               
                }while ( running == "yes");
                
               
    }
    
}
