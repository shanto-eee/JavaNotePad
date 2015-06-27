import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class FileSaver {
	
	private JFileChooser fc;
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
	public FileSaver(JavaNotePad javaNotePad) throws FileNotFoundException, UnsupportedEncodingException
	{
		
		
		fc = new JFileChooser();
		fc.setFileFilter(filter);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showSaveDialog(javaNotePad);
		
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	
        	javaNotePad.saveCheckFlag = 1;
        	
            File file = fc.getSelectedFile();
            
            
            String myFileSaveName = file.getAbsolutePath() + ".txt";
            String myFileString = javaNotePad.textArea.getText();          
            //System.out.println(myFileSaveName);
            System.out.println(myFileString);
            //-----------
            javaNotePad.setTitle(myFileSaveName);
            PrintWriter writer = new PrintWriter(myFileSaveName, "UTF-8");
            writer.print(myFileString);
            //writer.println(myFileString);
            writer.close();
            //-----------
        } 
	}

}
