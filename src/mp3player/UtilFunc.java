/*
 * UtilFunc.java
 *
 * Created on 20. September 2005, 22:59
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package mp3player;

import java.net.URLDecoder;
import java.util.*;
import java.io.*;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 *
 * @author Sickert Alexander
 */
public class UtilFunc {
    
    private static MicroLogger log = new MicroLogger();
    /** Creates a new instance of UtilFunc */
    public UtilFunc() 
    {
      
    }
    
     public void addNodeToTasklist(String strPath)
    { 
         log.info("addNodeToTasklist: " + strPath);        
         String s = getFileList( strPath, "");
         log.info("addNodeToTasklist -2 : " + s);
         
         addToTasklist(s);
     }
     
     
     
    public String getFileList(String strPath, String listStr){
         String strRet = "";
         strRet = new String(listStr);
         log.info("getFileList - called with: " + strPath);
         
        // public String processFolder(String folderString, ConfigManager configXml){

        File dir = new File(strPath);

        String[] children = dir.list();
        if (children == null) {
            // Either dir does not exist or is not a directory
            log.info("getFileList - children is null");
        } else {
            for (int i=0; i<children.length; i++) {
                // Get filename of file or directory
                String filename = children[i];
//                log.info("getFileList - 1: " + strPath + "/" + filename);

                // if file then process file else process folder

                File childFile = new File(strPath + "/" + filename);

                if(childFile.exists()){
                    if(childFile.isDirectory()){
                        if(".".equals(filename) || "..".equals(filename)){
                            //nix
                        }else{
                            //log.info(folderString + "/" + filename);
//                            log.info("getFileList - is folder: " + strPath + "/" + filename);
                            strRet = getFileList(new String(strPath + "/" + filename), new String(strRet));
                        }
                    }

                    if(childFile.isFile()){
                        if(filename.toLowerCase().indexOf(".mp3") > 0 ){
                            strRet = new String(strRet + "\n"+ strPath + "/" + filename);
                            log.info("getFileList - is file: " + strPath + "/" + filename);
                        }
                        //processFile(folderString + "/" + filename, configXml);
                    }
                } else{
                    log.info("getFileList - not  child : " + strPath + "/" + filename);
                } 

            }
        }
        //log.info("getFileList -strRet : " + strRet );
        return strRet;
    } 
     
    public void addToTasklist(String strFile)
    {                
                int Buf;
                String FileString;
                FileString = "";
                ConfigManager config = new ConfigManager("configMp3Player.xml");
                                
                
                FileString = getFileContent(config.getValue("PathToPlayList") + "/tmp.plx");
                 
                
                log.info("addToTasklist: " + strFile);    
                // neuer content dazu
                try
                {
                    FileWriter playlist = new FileWriter(config.getValue("PathToPlayList") + "/tmp.plx");
                    BufferedWriter buf = new BufferedWriter(playlist);
                    if(FileString.length() > 0){
                        FileString = FileString.substring(0, FileString.length()-1);
                    }
                    
                    buf.write(FileString);  
                    buf.newLine();
                    buf.write(strFile);
                    buf.close();
                    playlist.close();
                }
                catch(Exception x)
                {
                    log.info( x.toString() );
                    log.info("\nFehler im file schreiben");  
                    
                }
                        
    };
    
     public void setFileContent(String strFile, String fileContent)
    {                
                int Buf;                
               
                ConfigManager config = new ConfigManager("configMp3Player.xml");
                
                
                //log.info(FileString);    
                // neuer content dazu
                try
                {
                    FileWriter playlist = new FileWriter(strFile);
                    BufferedWriter buf = new BufferedWriter(playlist);
                    fileContent = fileContent.substring(0, fileContent.length()-1);
                    buf.write(fileContent);  
                    buf.newLine();
                    buf.write(strFile);
                    buf.close();
                    playlist.close();
                }
                catch(Exception x)
                {
                    log.info( x.getMessage().toString() );
                    log.info("\nFehler im file " + strFile +" schreiben");  
                    
                }
                        
    };
    
    public ArrayList getcontentList(String strFile)
    {
    
                String Buf;
                String FileString;
                FileString = "";
                ArrayList currFiles;                
                currFiles = new ArrayList();
                
                
                // bisherigen string holen
                try
                {
                    FileReader fileread = new FileReader(strFile);                    
                    BufferedReader buf = new BufferedReader(fileread);
                    
                    do{
                       Buf = buf.readLine();
                       currFiles.add(Buf);  
                       //log.info(Buf);  
                       
                    }while(Buf != null);
                    fileread.close();
                }
                 catch(Exception y)
                {                
                    log.info("\nFehler im file lesen");                
                }
                 
         return currFiles;
        
    }
    
    public ArrayList getFileList(String strFile)
    {    
                
                int i;
		               
                File file;  
                ArrayList currFiles;
                
                currFiles = new ArrayList();
               
                //log.info("1");
                File dir = new File(strFile);
                String[] list = dir.list();
                
                
		    
                if (list.length > 0)
                {
                
                    for (i = 0 ; i < list.length; i++)
                    {
                        file = new File(list[i]);
                        
                        if (file.isDirectory())
                        {
                          
                        }
                        else  // files in anderem window anzeigen
                        {
                            //node.add(new DefaultMutableTreeNode(list[i]));
                            currFiles.add(list[i]);
                            //log.info("add file");
                        }
                    
                    }                
                }
                else
                {
                
                }  
                
                return currFiles;
    }
    
    public ArrayList getFileListplx(String strFile)
    {    
                
                int i;
		               
                File file;  
                ArrayList currFiles;
                
                currFiles = new ArrayList();
               
                log.info(strFile);
                File dir = new File(strFile);
                String[] list = dir.list();
                String CurrentFileTypeTest;
                String CurrentFileType;
                String CurrentFileString;
                String TempFileString;
                
		    
                if (list.length > 0)
                {
                
                    for (i = 0 ; i < list.length; i++)
                    {
                        file = new File(list[i]);
                        
                        if (file.isDirectory())
                        {
                          
                        }
                        else  // files in anderem window anzeigen
                        {
                            //node.add(new DefaultMutableTreeNode(list[i]));
                                                        
                            CurrentFileTypeTest = "plx";
                            TempFileString = "tmp.plx";
                            CurrentFileString = list[i];
                            CurrentFileType = CurrentFileString.substring(CurrentFileString.length()-3, CurrentFileString.length()-0);
                            log.info("Cuurent file type: " + CurrentFileType);                            
                            if ( CurrentFileType.compareTo(CurrentFileTypeTest)  == 0 )
                            {
                                if ( CurrentFileString.compareTo(TempFileString)  < 0 )
                                {
                                    currFiles.add(list[i]);
                                }
                                //log.info("add file");
                            } 
                        }                    
                    }  
                }
                else
                {
                
                }  
                
                return currFiles;
    }
    
    public void AddPlaylistToTask(String strFile)
    {
              
         this.addToTasklist(this.getFileContent(strFile));            
    
    }
    
   
    
    
     public String getFileContent(String filePath){
        
        try{
                   FileInputStream file = new FileInputStream (filePath);
                   DataInputStream in = new DataInputStream (file);
                   byte[] b = new byte[in.available ()];
                   in.readFully (b);
                   in.close ();
                   String FileString;
                   FileString = new String (b, 0, b.length, "Cp850");
                   return FileString;
        }
        catch(  Exception x){
            log.info("\nKonnte file in funktion getFileContent nicht lesen"); 
            return null;
        }
    }
     
     
    public void clearcontentList(String strFile)
    {
        // "tmp.plx"
         try
        {
            FileWriter playlist = new FileWriter(strFile);
            BufferedWriter buf = new BufferedWriter(playlist);
            
            buf.write("");  
            buf.newLine();
            buf.close();
            playlist.close();
        }
        catch(Exception x)
        {
            log.info( x.getMessage().toString() );
            log.info("\nFehler im file 2 schreiben");                
        }
    }
    

    
    public String encodeString(String aURLFragment){
        
        String result = null;
        try {
            result = URLEncoder.encode(aURLFragment, "UTF-8");
        }catch (UnsupportedEncodingException ex){
            throw new RuntimeException("UTF-8 not supported", ex);
        }        
        return result;         
    }
    
    public String decodeString(String aURLFragment){
        log.info("decoding string: " + aURLFragment);
        String result = null;
        try {
            result = URLDecoder.decode(aURLFragment, "UTF-8");
        }catch (UnsupportedEncodingException ex){
            throw new RuntimeException("UTF-8 not supported", ex);
        }        
        return result;         
    }
    
    
}
