/*
 * ConfigManager.java
 *
 * Created on 23 July 2006, 23:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mp3player;


import java.io.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author spaceman
 */
public class ConfigManager {
    
    Document document;
    String thisFile;
    private static MicroLogger log = new MicroLogger();
    
    /** Creates a new instance of ConfigManager */
    public ConfigManager(String fileName) {
        boolean status = false;
        thisFile = fileName;
        
        
        //test if file exists - if not then create it
        
        String defaultFile = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><config></config>";
        File f = new File(fileName);
        
        if(! f.exists()){
            try                
            {                    
                FileWriter xmlDoc = new FileWriter(fileName);                    
                BufferedWriter buf = new BufferedWriter(xmlDoc);                 
                buf.write(defaultFile);                      
                buf.newLine();                    
                buf.close();                    
                xmlDoc.close();                
            }                
            catch(Exception x)                
            {                    
                System.out.println( x.getMessage().toString() );                    
                System.out.println("\nError writing default file");                
            }
        }
        
        try{
        DocumentBuilderFactory factory  = DocumentBuilderFactory.newInstance();
        DocumentBuilder        builder  = factory.newDocumentBuilder();
        
        document = builder.parse( new File( fileName ) );       
        
        
        } catch( SAXParseException spe ) {
            System.out.println( "\n** Parsing error, line " + spe.getLineNumber()
                                    + ", uri "  + spe.getSystemId() );
            System.out.println( "   " + spe.getMessage() );
            Exception e = ( spe.getException() != null ) ? spe.getException() : spe;
            e.printStackTrace();
            status = false;
        } catch( SAXException sxe ) {
            Exception e = ( sxe.getException() != null ) ? sxe.getException() : sxe;
            e.printStackTrace();
            status = false;
        } catch( ParserConfigurationException pce ) {
            pce.printStackTrace();
            status = false;
        } catch( IOException ioe ) {
            ioe.printStackTrace();
            status = false;
        }
       

    }
    /* set a value - doc will not be saved */
    public boolean setValue(String parameter, String strValue){
       boolean status = false;
       
       NodeList thisNode = this.document.getElementsByTagName(parameter );
       System.out.println("--- trying to save " + parameter+ " with value:" + strValue);
       if(thisNode.getLength() != 0){
           try{
               //thisNode.item(0).setNodeValue(parameter);
               
               //check if empty node
               if(thisNode.item(0).hasChildNodes()){
                   thisNode.item(0).getFirstChild().setNodeValue(strValue);
               }else{
                   Text newTextNode  = document.createTextNode( strValue );
                   thisNode.item(0).appendChild(newTextNode);
               }
              
       
                       
               //thisNode.item(0).setNodeValue(strValue);
           }catch(Exception e){
               System.out.println("---error save xml line 95");
               System.out.println(e.toString());
                status = false;
           }
           
       }else{
           // if parameter does not exist then append it   
           try{
           Node rootNode = this.document.getDocumentElement();
           Node newChildNode = document.createElement( parameter );            
           Text newTextNode  = document.createTextNode( strValue );
           newChildNode.appendChild( newTextNode );
           rootNode.appendChild(newChildNode);
           }catch(Exception x){
               System.out.println("---error save xml line 109");
           }
           
       }  
       
       
       
       return status;
    }
    
    /* get value from the doc object */
    public String getValue(String parameter){        
       String returnValue = "";
       try{           
       
           NodeList thisNode = this.document.getElementsByTagName(parameter );
           if(thisNode.getLength() != 0){
               if(thisNode.item(0).hasChildNodes()){
                   returnValue = thisNode.item(0).getFirstChild().getNodeValue();
               }
           }else{
               System.out.println("Tag does not exist");
       }   
       }catch(Exception x){
           System.out.println("error trying to get parameter: " + parameter);
       }       
       
       return returnValue;        
    }
    
    /* save doc object to xml file */
    public boolean saveDoc(){        
        boolean status = false;
       
      try{  
        
         // ---- Use a XSLT transformer for writing the new XML file ----      
          Transformer transformer = TransformerFactory.newInstance().newTransformer();      
          DOMSource        source = new DOMSource( document );      
          FileOutputStream os     = new FileOutputStream( new File( this.thisFile ) );      
          StreamResult     result = new StreamResult( os );      
          transformer.transform( source, result );

      // ---- Error handling ----        
      } catch( TransformerConfigurationException tce ) {
        System.out.println( "\n** Transformer Factory error" );
        System.out.println( "   " + tce.getMessage() );
        Throwable e = ( tce.getException() != null ) ? tce.getException() : tce;
        e.printStackTrace();
        
      } catch( TransformerException tfe ) {
        System.out.println( "\n** Transformation error" );
        System.out.println( "   " + tfe.getMessage() );
        Throwable e = ( tfe.getException() != null ) ? tfe.getException() : tfe;
        e.printStackTrace();
        
      } catch( IOException ioe ) {
        ioe.printStackTrace();        
      }               
        return status;           
    }
    
}
