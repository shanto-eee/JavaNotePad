import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class NotePadFont extends JFrame {
	
	private String fontNameDefault = "Times New Roman";
	private int fontSizeDefault = 20;
	private int fontStyleDefault = 0;
	public Font myFont = new Font(fontNameDefault, fontStyleDefault, fontSizeDefault);
	private Font savePrevFont;
	private Font hereFont = myFont;
	
	private JList fontList;
	private JList fontStyle;
	private JList fontSize;
	private JLabel labelFont;
	private JLabel labelFontStyle;
	private JLabel labelFontSize;
	
	private String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	private Font fullFonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
	private String fontStyleList[] = {"Regular", "Bold", "Italic", "Bold + Italic"};
	private String fontSizeLis[] = {"8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28"};
	private JTextField textFieldName;
	private JTextField textFieldStyle;
	private JTextField textFieldSize;
	private JScrollPane scrollPane1; 
	private JScrollPane scrollPane2; 
	private JScrollPane scrollPane3; 
	private JLabel sampleTextField;
	private JLabel labelSample;
	
	
	
	public NotePadFont(JavaNotePad javaNotePad) {
		
		
		
		this.setResizable(false);
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setVisible(true);
		this.setSize(377, 427);
		this.getContentPane().setLayout(null);
		this.setTitle("Font");
		
		savePrevFont = myFont;
		
		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(10, 63, 125, 115);
		getContentPane().add(scrollPane1);
				
		fontList = new JList(fonts);
		fontList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				
				if (! event.getValueIsAdjusting())
				{
					
					fontNameDefault = (String) fontList.getSelectedValue();
					myFont = new Font(fontNameDefault, fontStyleDefault, fontSizeDefault );
					hereFont = new Font(fontNameDefault, 0, 15 );
					textFieldName.setFont(hereFont);
					textFieldName.setText((String) fontList.getSelectedValue());
					hereFont = new Font(fontNameDefault, fontStyleDefault, 15 );
					textFieldStyle.setFont(hereFont);
					textFieldSize.setFont(hereFont);
					sampleTextField.setFont(myFont);
					javaNotePad.textArea.setFont(myFont);
					
					System.out.println(fontList.getSelectedValue());

				}
				
			}
		});
		scrollPane1.setViewportView(fontList);
		fontList.setVisibleRowCount(7);
		fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		labelFont = new JLabel("Font:");
		labelFont.setBounds(10, 19, 46, 14);
		getContentPane().add(labelFont);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(158, 63, 114, 115);
		getContentPane().add(scrollPane2);
		
		fontStyle = new JList(fontStyleList);
		fontStyle.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				
				if (! event.getValueIsAdjusting())
				{
					
					fontStyleDefault = fontStyle.getSelectedIndex();
					myFont = new Font(fontNameDefault, fontStyleDefault, fontSizeDefault );
					hereFont = new Font(fontNameDefault, fontStyleDefault, 15 );
					textFieldStyle.setFont(hereFont);
					textFieldStyle.setText((String) fontStyle.getSelectedValue());
					hereFont = new Font(fontNameDefault, fontStyleDefault, 15 );
					textFieldSize.setFont(hereFont);
					sampleTextField.setFont(myFont);
					javaNotePad.textArea.setFont(myFont);
					System.out.printf("\nFont STYLE : [%d] %s", fontStyleDefault, fontStyle.getSelectedValue());
				}
				
			}
		});
		scrollPane2.setViewportView(fontStyle);
		fontStyle.setVisibleRowCount(7);
		fontStyle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		labelFontStyle = new JLabel("Font style:");
		labelFontStyle.setBounds(158, 19, 76, 14);
		getContentPane().add(labelFontStyle);
		
		scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(295, 63, 66, 115);
		getContentPane().add(scrollPane3);
		
		fontSize = new JList(fontSizeLis);
		fontSize.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				
				if (! event.getValueIsAdjusting())
				{
				
					fontSizeDefault = Integer.parseInt((String) fontSize.getSelectedValue());
					myFont = new Font(fontNameDefault, fontStyleDefault, fontSizeDefault );
					hereFont = new Font(fontNameDefault, fontStyleDefault, 15 );
					sampleTextField.setFont(myFont);
					textFieldSize.setFont(hereFont);
					textFieldSize.setText((String) fontSize.getSelectedValue());
					javaNotePad.textArea.setFont(myFont);
					System.out.printf("Font SIZE: %d", fontSizeDefault);
					
				}
				
			}
		});
		scrollPane3.setViewportView(fontSize);
		
		labelFontSize = new JLabel("Size:");
		labelFontSize.setBounds(299, 19, 46, 14);
		getContentPane().add(labelFontSize);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(10, 35, 125, 26);
		textFieldName.setEditable(false);
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldStyle = new JTextField();
		textFieldStyle.setBounds(158, 35, 114, 26);
		textFieldStyle.setEditable(false);
		getContentPane().add(textFieldStyle);
		textFieldStyle.setColumns(10);
		
		textFieldSize = new JTextField();
		textFieldSize.setBounds(295, 35, 66, 26);
		textFieldSize.setEditable(false);
		getContentPane().add(textFieldSize);
		textFieldSize.setColumns(10);
		
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object
				
			}
		});
		okButton.setBounds(158, 337, 89, 32);
		getContentPane().add(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				javaNotePad.textArea.setFont(savePrevFont);
				setVisible(false); //you can't see me!
				dispose(); //Destroy the JFrame object
				
			}
		});
		cancelButton.setBounds(257, 337, 89, 32);
		getContentPane().add(cancelButton);
		
		sampleTextField = new JLabel();
		sampleTextField.setBounds(10, 224, 351, 78);
		sampleTextField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		sampleTextField.setFont(myFont);
		sampleTextField.setHorizontalTextPosition(JLabel.CENTER);
		sampleTextField.setVerticalTextPosition(JLabel.CENTER);
		sampleTextField.setText(" The quick brown FOX jumped");
		getContentPane().add(sampleTextField);
		
		labelSample = new JLabel(" Sample");
		labelSample.setBounds(10, 197, 351, 24);
		labelSample.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		labelSample.setHorizontalTextPosition(JLabel.CENTER);
		labelSample.setVerticalTextPosition(JLabel.CENTER);
		getContentPane().add(labelSample);
		
	}
}
