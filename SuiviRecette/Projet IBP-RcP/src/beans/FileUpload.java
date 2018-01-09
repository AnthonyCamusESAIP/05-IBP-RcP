package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
 
import org.primefaces.model.UploadedFile;


public class FileUpload {
	
	private DataManager dataManager;
	private UploadedFile uploadedFile;
	private File file;
	 
     
    public UploadedFile getUploadedFile() {
		return uploadedFile;
	}


	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	public void upload() {
        if(uploadedFile != null) {
            file = new File("lstTest.xls");
            try {
				uploadedFile.write(file.getPath());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            try {
				dataManager = new DataManager(new FileInputStream(file), "Query1");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (dataManager != null) {
				dataManager.saveData();
			}
            else {
            	System.out.println("Erreur upload");
            }
        }
    }
}