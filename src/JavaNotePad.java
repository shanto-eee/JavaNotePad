import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;



public class JavaNotePad extends JFrame {
	
	public JTextArea textArea;
	public static int windowNum = 0;
	public int saveCheckFlag = 0;
	
	
	//private JPanel panel;
	
	private JMenuBar menuBar;
	
	private JMenu mnFile;
	private JMenu mnEdit;
	private JMenu mnFormat;
	private JMenu mnView;
	private JMenu mnHelp;
	
	private JMenuItem itemFileNew;
	private JMenuItem itemFileOpen;
	private JMenuItem itemFileSave;
	private JMenuItem itemFileSaveAs;
	private JMenuItem itemFilePageSetup;
	private JMenuItem itemFilePrint;
	private JMenuItem itemFileExit;
	
	private JMenuItem itemEditUndo;	
	private JMenuItem itemEditCut;	
	private JMenuItem itemEditCopy;
	private JMenuItem itemEditPaste;	
	private JMenuItem itemEditDelete;
	private JMenuItem itemEditFind; 	
	private JMenuItem itemEditFindNext; 
	private JMenuItem itemEditReplace; 	
	private JMenuItem itemEditGoTo;	
	private JMenuItem itemEditSelectAll;	
	private JMenuItem itemEditTimeDate; 
	
	private JMenuItem itemFormatWordWrap;
	private JMenuItem itemFormatFont;
	
	private JavaNotePad javaNotePad;
	private JScrollPane scrollPane;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private int checkLineWrap = 0;
	
	private String fontNameDefault = "Times New Roman";
	private int fontSizeDefault = 20;
	private int fontStyleDefault = 0;
	public Font myFont = new Font(fontNameDefault, fontStyleDefault, fontSizeDefault);
	
//	private Font fontX = new Font("Serif", Font.BOLD, 30);
		
	public JavaNotePad(String titleBar) {
		
		/*//-------------  window focus listeneer ---------------
		
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				System.out.printf("Window Gain Focused %s\n", titleBar);
			}
			public void windowLostFocus(WindowEvent arg0) {
				System.out.printf("Window Lost Focused %s\n", titleBar);
			}
		});
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				System.out.printf("Window Activated %s\n", titleBar);
			}
		});  */
		
	
		
		javaNotePad = this;
		
		
		this.setResizable(true);
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setVisible(true);
		this.setSize(693, 490);
		this.getContentPane().setLayout(null);
		this.setTitle(titleBar);	
		getContentPane().setLayout(null);
						
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 21, 693-14, 437-2);
		getContentPane().add(scrollPane);
		
		// ---------------- ADDING TEXT AREA -----------------
		textArea = new JTextArea();
		textArea.setFont(myFont);
		//textArea.setFont(fontX);
		scrollPane.setViewportView(textArea);
		
		// ---------------- ADDING STATUS BAR ----------------
		// panel = new JPanel();
		// panel.setBounds(0, 458, 693, 15);
		// getContentPane().add(panel);
		
		// ----------------------------- ADD MENU BAR --------------------------------
		// --File--Edit--Format--View--Help-------------------------------------------
		// ---------------------------------------------------------------------------
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, this.getWidth(), 21);
		getContentPane().add(menuBar);
		
		// ---------------- START FILE MENU ------------------
		mnFile = new JMenu("File  ");
		menuBar.add(mnFile);
		
		itemFileNew = new JMenuItem("New");
		itemFileNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				windowNum++;
				new JavaNotePad("Untitled" + Integer.toString(windowNum) + " - Notepad");
				
			}
		});
		mnFile.add(itemFileNew);
		
		itemFileOpen = new JMenuItem("Open...");
		itemFileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new FileOpener(javaNotePad);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("xxx");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mnFile.add(itemFileOpen);
		
		itemFileSave = new JMenuItem("Save");
		itemFileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.printf("Flag = %d\n", javaNotePad.saveCheckFlag);
				
				if(javaNotePad.saveCheckFlag == 0) 
				{
					
					try 
					{
						new FileSaver(javaNotePad);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						System.out.println("xxx");
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else
				{
					
					String fileName = javaNotePad.getTitle();
					System.out.println("My previously saved filename" + fileName);
					
				}
				
				
				
			}
		});
		mnFile.add(itemFileSave);
		
		itemFileSaveAs = new JMenuItem("Save As...");
		itemFileSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					new FileSaver(javaNotePad);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("xxx");
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mnFile.add(itemFileSaveAs);
		
		separator = new JSeparator();
		mnFile.add(separator);
		
		itemFilePageSetup = new JMenuItem("Page Setup...");
		mnFile.add(itemFilePageSetup);
		
		itemFilePrint = new JMenuItem("Print");
		mnFile.add(itemFilePrint);
		
		separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		itemFileExit = new JMenuItem("Exit");
		mnFile.add(itemFileExit);
		// ---------------- END FILE MENU ------------------
		
		// ---------------- START EDIT MENU ----------------
		mnEdit = new JMenu("Edit  ");
		menuBar.add(mnEdit);
		
		itemEditUndo = new JMenuItem("Undo");
		mnEdit.add(itemEditUndo);
		
		separator_2 = new JSeparator();
		mnEdit.add(separator_2);
		
		itemEditCut = new JMenuItem("Cut");
		mnEdit.add(itemEditCut);
		
		itemEditCopy = new JMenuItem("Copy");
		mnEdit.add(itemEditCopy);
		
		itemEditPaste = new JMenuItem("Paste");
		mnEdit.add(itemEditPaste);
		
		itemEditDelete = new JMenuItem("Delete");
		mnEdit.add(itemEditDelete);
		
		separator_3 = new JSeparator();
		mnEdit.add(separator_3);
		
		itemEditFind = new JMenuItem("Find");
		mnEdit.add(itemEditFind);
		
		itemEditFindNext = new JMenuItem("Find Next");
		mnEdit.add(itemEditFindNext);
		
		itemEditReplace = new JMenuItem("Replace");
		mnEdit.add(itemEditReplace);
		
		itemEditGoTo = new JMenuItem("Go to");
		mnEdit.add(itemEditGoTo);
		
		separator_4 = new JSeparator();
		mnEdit.add(separator_4);
		
		itemEditSelectAll = new JMenuItem("Select All");
		mnEdit.add(itemEditSelectAll);
		
		itemEditTimeDate = new JMenuItem("Time/Date");
		mnEdit.add(itemEditTimeDate);
		// ----------------- END EDIT MENU -----------------
		
		
		// ---------------- START FORMAT MENU --------------
		mnFormat = new JMenu("Format  ");
		menuBar.add(mnFormat);
		
		itemFormatWordWrap = new JMenuItem("Word Wrap");
		itemFormatWordWrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(javaNotePad.checkLineWrap == 0)
				{
					javaNotePad.textArea.setLineWrap(true);
					javaNotePad.checkLineWrap = 1;
				}
				else if(javaNotePad.checkLineWrap == 1)
				{
					javaNotePad.textArea.setLineWrap(false);
					javaNotePad.checkLineWrap = 0;
				}
				
				
				
			}
		});
		mnFormat.add(itemFormatWordWrap);
		
		itemFormatFont = new JMenuItem("Font...");
		itemFormatFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new NotePadFont(javaNotePad);
				
			}
		});
		mnFormat.add(itemFormatFont);
		// ------------------ END FORMAT MENU --------------
		
		
		mnView = new JMenu("View  ");
		menuBar.add(mnView);
		
		mnHelp = new JMenu("Help  ");
		menuBar.add(mnHelp);
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				
				menuBar.setBounds(0, 0, javaNotePad.getWidth(), 21);
				//textArea.setBounds(0, 21, javaNotePad.getWidth(), javaNotePad.getHeight());
				scrollPane.setBounds(0, 21, javaNotePad.getContentPane().getWidth(), javaNotePad.getContentPane().getHeight()-21);
				//scrollPane.setBounds(0, 21, javaNotePad.getWidth()-16, javaNotePad.getHeight()-56);
				// NotePadFont NotePadFont = new NotePadFont();
				// panel.setBounds(0, 21 + javaNotePad.getHeight(), javaNotePad.getWidth(), 15);
				//repaint();
			}
		});
		
		repaint();
		
		
	}
}
