/*
 * MicroLogger.java
 *
 * Created on June 21, 2008, 7:54 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mp3player;

/**
 *
 * @author spaceman
 */
public class MicroLogger {
    
    /** Creates a new instance of MicroLogger */
    public MicroLogger() {
        
    }
    
    public void info(String x){
        System.out.println(x);
    }
    
   public void info(String x, Object o){
        System.out.println(x);
    }    
    
    public void error(String x){
        System.out.println(x);
    }
    
    public void error(String x, Object o){
        System.out.println(x);
    }
    
    public void debug(String x){
        System.out.println(x);
    }
    
    public void debug(String x, Object o){
        System.out.println(x);
    }    
    
}
