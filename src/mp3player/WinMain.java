/****************************************************************/
/*                      WinMp301	                            */
/*                                                              */
/****************************************************************/


package mp3player;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
/**
 * Summary description for WinMp301
 *
 */
public class WinMain extends JFrame
{
private static MicroLogger log = new MicroLogger();	
// Variables declaration
	private JList LstTaskList;
	private JScrollPane jScrollPane1;
	private JTree TreeFolders;
	private JScrollPane jScrollPane2;
	private JList LstFiles;
	private JScrollPane jScrollPane3;
	private JList LstPlaylists;
	private JScrollPane jScrollPane4;
	private JButton BtnSelectFolder;
	private JButton BtnPlayFile;
        private JButton BtnAddToPlayList;
        private JButton BtnAddToTaskList;
        private JButton BtnPlayPlaylist;
        private JButton BtnClearTaskList;
        private JButton BtnStartTaskList;
        private JButton BtnStopPlayer;        
        private JButton BtnExitPlayer;
        private JButton BtnDetails;
        private JButton BtnAddNodeToTaskList;
	private JPanel contentPane;
        private String CurrentSingleLocation;
        private String CurrentSingleDir;
        private String CurrentSingleFile;
        private String CurrentPlaylistFile;
        private JTextArea currentFile;
	// End of variables declaration
        objPlayThread myThread;
        ObjPlayer myPlayer;
        UtilFunc myUtil;
        UtilFunc myUtil2;
        ConfigManager config;
        WinListDetail winDetails;
        private String currentNodePath;
         

/**
 * 
 */ 
	public WinMain()
	{
		super();
		initializeComponent();
		//
		// TODO: Add any constructor code after initializeComponent call
		//
                myPlayer = new ObjPlayer(this);
                myUtil = new UtilFunc();
		this.setVisible(true);
                
                             
//                winDetails.setVisible(false);
                
                
                config = new ConfigManager("configMp3Player.xml");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always regenerated
	 * by the Windows Form Designer. Otherwise, retrieving design might not work properly.
	 * Tip: If you must revise this method, please backup this GUI file for JFrameBuilder
	 * to retrieve your design properly in future, before revising this method.
	 */
	private void initializeComponent()
	{
		ArrayList arr;

		LstTaskList = new JList();
		jScrollPane1 = new JScrollPane();
		
		jScrollPane2 = new JScrollPane();
		LstFiles = new JList();
		jScrollPane3 = new JScrollPane();
		LstPlaylists = new JList();
		jScrollPane4 = new JScrollPane();
		BtnSelectFolder = new JButton();
		BtnPlayFile = new JButton();
		contentPane = (JPanel)this.getContentPane();
                
                BtnAddToPlayList= new JButton();
                BtnAddToTaskList = new JButton();
                BtnAddNodeToTaskList = new JButton();
                BtnPlayPlaylist = new JButton();
                BtnClearTaskList  = new JButton();
                BtnStartTaskList = new JButton();
                BtnStopPlayer = new JButton();
                BtnExitPlayer = new JButton();
                BtnDetails = new JButton();
                
                currentFile = new JTextArea();                
                currentFile.setLineWrap(true);
                currentFile.setWrapStyleWord(true);
                
                ConfigManager config = new ConfigManager("configMp3Player.xml");
               
                myUtil2 = new UtilFunc();
                arr =  myUtil2.getcontentList(config.getValue("PathToPlayList")+"/tmp.plx");                 
                LstTaskList.setListData(arr.toArray());
                
		LstTaskList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				LstTaskList_valueChanged(e);
			}

		});
		//
		// jScrollPane1
		//
		jScrollPane1.setViewportView(LstTaskList);
		//
		// TreeFolders
		//
                
                DefaultMutableTreeNode root = new DefaultMutableTreeNode(config.getValue("PathToMp3") + "/");
                
                
                
                
                DefaultTreeModel treeModel = new DefaultTreeModel(root);
                TreeFolders = new JTree(treeModel);
                
                
		TreeFolders.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e)
			{
				TreeFolders_valueChanged(e);
			}

		});

		jScrollPane2.setViewportView(TreeFolders);

		LstFiles.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				LstFiles_valueChanged(e);
			}

		});

		jScrollPane3.setViewportView(LstFiles);
	
                 FuncFillPlaylist();
                
		LstPlaylists.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				LstPlaylists_valueChanged(e);
			}

		});
	
		jScrollPane4.setViewportView(LstPlaylists);
	
		BtnSelectFolder.setText("Select Folder");
		BtnSelectFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnSelectFolder_actionPerformed(e);
			}

		});
	
		BtnPlayFile.setText("Play File");
		BtnPlayFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnPlayFile_actionPerformed(e);
			}

		});
                
                
        
		BtnAddToTaskList.setText("Add to Task List");
		BtnAddToTaskList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnAddToTaskList_actionPerformed(e);
			}

		});
                
                

		BtnExitPlayer.setText("Exit");
		BtnExitPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnExitPlayer_actionPerformed(e);
			}

		});
                
		//
		BtnPlayPlaylist.setText("Play Playlist");
		BtnPlayPlaylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnPlayPlaylist_actionPerformed(e);
			}

		});
                
                
		//
		BtnAddToPlayList.setText("Add to Playlist");
		BtnAddToPlayList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnAddToPlayList_actionPerformed(e);
			}

		});
                
		BtnAddNodeToTaskList.setText("Add Node to TL");
		BtnAddNodeToTaskList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnAddNodeToTaskList_actionPerformed(e);
			}

		});                
                
                
                //
		BtnClearTaskList.setText("Clear Task List");
		BtnClearTaskList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnClearTaskList_actionPerformed(e);
			}

		});
                
                BtnStartTaskList.setText("Start Task List");
		BtnStartTaskList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnStartTaskList_actionPerformed(e);
			}

		});
                
                
                BtnStopPlayer.setText("Stop Player");
		BtnStopPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnStopPlayer_actionPerformed(e);
			}

		});
                
                BtnDetails.setText("Details");
                BtnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				BtnDetails_actionPerformed(e);
			}

		});
                
		//
		// contentPane
		//
		contentPane.setLayout(null);
		addComponent(contentPane, jScrollPane1, 40,39,825,100);                
		addComponent(contentPane, jScrollPane2, 40,180,350,460);
		addComponent(contentPane, jScrollPane3, 400,180,290,260);
		addComponent(contentPane, jScrollPane4, 700,180,250,460);
		
                addComponent(contentPane, BtnSelectFolder, 40,150,350,28);
		addComponent(contentPane, BtnPlayFile, 400,150,290,28);                
                addComponent(contentPane, BtnStopPlayer, 560,5,150,25);                
                addComponent(contentPane, BtnExitPlayer, 720,5,145,25);                
                addComponent(contentPane, BtnPlayPlaylist, 700,150,150,28);
                addComponent(contentPane, BtnDetails, 850,150,100,28);
                
                addComponent(contentPane, BtnAddToTaskList, 400,450,140,28);
                addComponent(contentPane, BtnAddNodeToTaskList, 400,480,140,28);
                addComponent(contentPane, BtnAddToPlayList, 555,450,135,60);
                addComponent(contentPane, BtnClearTaskList, 40,5,250,25);
                addComponent(contentPane, BtnStartTaskList, 300,5,250,25);
                
                addComponent(contentPane, currentFile, 400,520,290,120);
                
   
		//
		// WinMp301
		//
		this.setTitle("Mp3 - by SOLENSKI");
		this.setLocation(new Point(-4, 17));
		this.setSize(new Dimension(1000, 700));
                this.setResizable(false);
	}

	/** Add Component Without a Layout Manager (Absolute Positioning) */
	private void addComponent(Container container,Component c,int x,int y,int width,int height)
	{
		c.setBounds(x,y,width,height);
		container.add(c);
	}

// ==================================================================================================================
// ==================================================================================================================
// ==================================================================================================================
// ==================================================================================================================
        
        
	private void LstTaskList_valueChanged(ListSelectionEvent e)
	{
		//log.info("\nLstTaskList_valueChanged(ListSelectionEvent e) called.");
		if(!e.getValueIsAdjusting())
		{
			Object o = LstTaskList.getSelectedValue();
		}
	}

	private void TreeFolders_valueChanged(TreeSelectionEvent e)
	{
            TreePath mytreepath;
            String currentpath;
            int i;

		DefaultMutableTreeNode node = (DefaultMutableTreeNode)TreeFolders.getLastSelectedPathComponent();
		if(node == null) return;
		
		Object o = node.getUserObject();
	              
                mytreepath = TreeFolders.getSelectionPath();
                
                currentpath = "";
                
                for (i = 0; i < mytreepath.getPathCount(); i++)
                {
                    if (i == 0)
                    {
                        currentpath =  mytreepath.getPathComponent(i).toString() + "/";
                        
                    }
                    else
                    {
                        currentpath = currentpath + "/" + mytreepath.getPathComponent(i).toString();
                    }
                    currentNodePath = currentpath;
                    //currentpath = currentpath + "/" + mytreepath.getPathComponent(i).toString();
                }
                
                //log.info(currentpath);
                
                CurrentSingleDir = currentpath;
                // aus string file list machen und dann diese liste anh�ngen
                
                File file;                
                File dir = new File(currentpath);   
                
                String[] list = dir.list();
                
                ArrayList currFiles;  
                ArrayList currFolders;
                String[] FolderList;
                String CurrentFileString;
                String CurrentFileType;
                String CurrentFileTypeTest;
                int folderCount;
                
                currFiles = new ArrayList();
                currFolders = new ArrayList();
                folderCount = 0;
                
                if (list.length > 0)
                {
                    log.info("------ List length ------------: " + list.length);
                    
                    for (i = 0 ; i < list.length; i++){
                        file = new File(currentpath + "/" + list[i]);
                      if (file.isDirectory()){
                          log.info("------ IN ++ List length ------------");
                          currFolders.add(list[i]);
                          folderCount += 1;
                      }                        
                   }                     
                }
                
                log.info("------ folder count ------------: " + folderCount);
                if (folderCount > 0){
                    Collections.sort(currFolders);  
                    for (i = 0 ; i < folderCount; i++){
                        node.add(new DefaultMutableTreeNode(  currFolders.get(i).toString()  ));
                    }                                  
                }
                log.info("------ nach IF 1 ------------");
                if (list.length > 0)
                {
                
                    for (i = 0 ; i < list.length; i++)
                    {
                        file = new File(currentpath + "/" + list[i]);
                        
                        if (file.isDirectory())
                        {
                            // nexte zeile schon gemacht
                            //node.add(new DefaultMutableTreeNode(list[i]));
                            
                        }
                        else  // files in anderem window anzeigen
                        {
                            //node.add(new DefaultMutableTreeNode(list[i]));
                            
                            CurrentFileString = list[i];
                            
                            CurrentFileType = CurrentFileString.substring(CurrentFileString.length()-3, CurrentFileString.length()-0);
                            
                            //log.info( CurrentFileType );
                            //currFiles.add(list[i]);
                            
                            CurrentFileTypeTest = "mp3";                                                       
                            if ( CurrentFileType.compareTo(CurrentFileTypeTest)  == 0 )
                            {
                                currFiles.add(list[i]);
                                //log.info( "---" + CurrentFileType );
                            } 
                            
                            
                        }                    
                    }
                    
                   
                    
                    Collections.sort(currFiles);                    
                    
                    
                    log.info("######################## LIST #######################");
                    
                LstFiles.setListData(currFiles.toArray());
                
                //log.info( TreeFolders.getSelectionPath().toString());
                //log.info( mytreepath.getPathCount());
                //log.info(mytreepath.getPathComponent(0).toString()  );
                //log.info(mytreepath.getPathComponent(mytreepath.getPathCount()  - 1).toString()  );
                }
                
                
                
	}

	private void LstFiles_valueChanged(ListSelectionEvent e)
	{
		//log.info("\nLstFiles_valueChanged(ListSelectionEvent e) called.");
		if(!e.getValueIsAdjusting())
		{
                    Object o = LstFiles.getSelectedValue();
                    //log.info(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
                    // TODO: Add any handling code here for the particular object being selected
                    CurrentSingleLocation = CurrentSingleDir + "/" + o.toString()	;

                    log.info("LstFiles_valueChanged: " + CurrentSingleLocation);
                
		}
	}

	private void LstPlaylists_valueChanged(ListSelectionEvent e)
	{
		//log.info("\nLstPlaylists_valueChanged(ListSelectionEvent e) called.");
		if(!e.getValueIsAdjusting())
		{
			Object o = LstPlaylists.getSelectedValue();
			//log.info(">>" + ((o==null)? "null" : o.toString()) + " is selected.");
			// TODO: Add any handling code here for the particular object being selected
			
                        ConfigManager config = new ConfigManager("configMp3Player.xml");
                    
                        
                        CurrentPlaylistFile = config.getValue("PathToPlayList") + "/" + o.toString();
		}
	}

	private void BtnSelectFolder_actionPerformed(ActionEvent e)
	{
		//log.info("\nBtnSelectFolder_actionPerformed(ActionEvent e) called.");
		// TODO: Add any handling code here
                
                ArrayList xarr;
                xarr = new ArrayList();
		xarr.add("a");
		xarr.add("s");
		LstTaskList.setListData(xarr.toArray());
                
               
                

	}

	private void BtnPlayFile_actionPerformed(ActionEvent e)
	{
		log.info("\nBtnPlayFile_actionPerformed(ActionEvent e) called.");
		// TODO: Add any handling code here                
                myPlayer.playSingle(CurrentSingleLocation);
	}
        
        
        private void BtnAddToPlayList_actionPerformed(ActionEvent e)
	{                
		log.info("\nBtnAddToPlayList_actionPerformed(ActionEvent e) called.");
                
                Date d1 = new Date();
                UtilFunc util = new UtilFunc();
                
                int Buf;
                String FileString;
                FileString = "";
                
                try
                {                   
                   FileInputStream file = new FileInputStream (CurrentPlaylistFile);
                   DataInputStream in = new DataInputStream (file);
                   byte[] b = new byte[in.available ()];
                   in.readFully (b);
                   in.close ();
                   FileString = new String (b, 0, b.length, "Cp850");
                  
                }
                 catch(Exception y)
                {                
                    log.info("\nFehler im file lesen: " + CurrentPlaylistFile);                
                }
                 
                Date d2 = new Date();
                long elapsed_time = d2.getTime() - d1.getTime();
                log.info("lesen brauchte " + elapsed_time + " milliseconds");
                                
                try
                {
                    FileWriter playlist = new FileWriter(CurrentPlaylistFile);
                    BufferedWriter buf = new BufferedWriter(playlist);
                    buf.write(FileString);  
                    buf.newLine();
                    buf.write(util.encodeString(CurrentSingleLocation));
                    buf.close();
                    playlist.close();
                }
                catch(Exception x)
                {                
                    log.info("\nFehler im file schreiben1" + CurrentSingleLocation); 
                    log.info("\nFehler im file schreiben2" + CurrentPlaylistFile);                    
                }
                
                Date d3 = new Date();
                elapsed_time = d3.getTime() - d2.getTime();
                log.info("schreiben brauchte " + elapsed_time + " milliseconds");                 
	}        
         
        private void BtnAddToTaskList_actionPerformed(ActionEvent e)
	{                     
                
            // get all files from currentNodePath
                    
                ConfigManager config = new ConfigManager("configMp3Player.xml");            
                myUtil.addToTasklist(CurrentSingleLocation); 
                
                
                ArrayList currFiles;
                currFiles = new ArrayList();
                currFiles =  myUtil.getcontentList(config.getValue("PathToPlayList") + "/tmp.plx");                 
                LstTaskList.setListData(currFiles.toArray());
	}
        
        
        private void BtnAddNodeToTaskList_actionPerformed(ActionEvent e)
	{                     
                ConfigManager config = new ConfigManager("configMp3Player.xml");            
//                myUtil.addToTasklist(CurrentSingleLocation);
                myUtil.addNodeToTasklist(currentNodePath); 
                
                ArrayList currFiles;
                currFiles = new ArrayList();
                currFiles =  myUtil.getcontentList(config.getValue("PathToPlayList") + "/tmp.plx");                 
                LstTaskList.setListData(currFiles.toArray());
	}        
        
        private void BtnPlayPlaylist_actionPerformed(ActionEvent e)
	{
                log.info("BtnPlayPlaylist_actionPerformed: " + CurrentPlaylistFile);
                myUtil.AddPlaylistToTask(CurrentPlaylistFile); 
                ArrayList currFiles;
                currFiles = new ArrayList(); 
                currFiles =  myUtil.getcontentList(config.getValue("PathToPlayList") + "/tmp.plx");                 
                LstTaskList.setListData(currFiles.toArray()); 
	}
        

	//
	// TODO: Add any method code to meet your needs in the following area
	//
        
        
        private void BtnClearTaskList_actionPerformed(ActionEvent e)
	{
                myUtil.clearcontentList(this.config.getValue("PathToPlayList") + "/tmp.plx"); 
                ArrayList currFiles;
                currFiles = new ArrayList();
                currFiles =  myUtil.getcontentList(this.config.getValue("PathToPlayList") + "/tmp.plx");                 
                LstTaskList.setListData(currFiles.toArray());
	}
        
         private void BtnStartTaskList_actionPerformed(ActionEvent e)
	{		
                myThread = new objPlayThread(this); 
	}
        
         
        private void BtnStopPlayer_actionPerformed(ActionEvent e)
	{              
                
                try
                {
                    myThread.running = "no";
                    myThread.playerutil.stop();
                    myThread.running = "no";
                    
                }
                catch (Exception i)
                {

                }   
                
                try{
                    myPlayer.stop();
                }catch(Exception x){
                    
                }
	}
        
        private void FuncFillPlaylist()
        {    
                UtilFunc UtilObj = new UtilFunc();
            
                //log.info("in playlist file list function");
                ArrayList currFiles;
                currFiles = new ArrayList();  
                
                ConfigManager config = new ConfigManager("configMp3Player.xml");
                currFiles =  UtilObj.getFileListplx(config.getValue("PathToPlayList"));
                //currFiles =  UtilObj.getFileListplx("/home/spaceman");
                Collections.sort(currFiles); 
                LstPlaylists.setListData( currFiles.toArray() );  
        }
                
                
        
     private void BtnExitPlayer_actionPerformed(ActionEvent e)
     {       
         System.exit(0);        
     }
     
     
     private void BtnDetails_actionPerformed(ActionEvent e){
         
         Object o = LstPlaylists.getSelectedValue();
         //currFiles =  myUtil.getcontentList(config.getValue("PathToPlayList") + o.toString());  
         winDetails = new WinListDetail();  
         winDetails.fillList(config.getValue("PathToPlayList") + "/" + o.toString());
         winDetails.setVisible(true);
         
     }
     
     public void currentlyPlaying(String x){
         
         currentFile.setText("Currently playing: " + x);
         
     }
        
        
      
}


























