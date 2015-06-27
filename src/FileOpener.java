import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class FileOpener {
	
	private String hereOpenString;
	private JFileChooser fc;
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
	
	
	public FileOpener(JavaNotePad javaNotePad) throws IOException
	{
		
		//javaNotePad.saveCheckFlag = 1;
		
		fc = new JFileChooser();
		fc.setFileFilter(filter);
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = fc.showOpenDialog(javaNotePad);
		
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	
            File file = fc.getSelectedFile();
            
            //This is where a real application would open the file.
            String myFileSaveName = file.getAbsolutePath();
                      
            BufferedReader br = new BufferedReader(new FileReader(myFileSaveName));
            try 
            {
            	StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null)
                {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
                
                hereOpenString = sb.toString();
                
            }
            finally 
            {
                //br.close();
            }
            
            JavaNotePad javaNotePadOpen = new JavaNotePad(myFileSaveName);
            javaNotePadOpen.saveCheckFlag = 1;
            javaNotePadOpen.textArea.setText(hereOpenString);
       
     
        } 
	}

}
