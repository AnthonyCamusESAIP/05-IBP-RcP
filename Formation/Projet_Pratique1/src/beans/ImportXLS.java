package beans;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;



public class ImportXLS {

        
    
    private static HSSFWorkbook wb;



	public ImportXLS(){
        
    }

	
    
	
    public static JTable CreerJTableAvecExcel(File file) throws FileNotFoundException, IOException{
       
    	
        JTable table = new JTable();
        
       
        InputStream inp = new FileInputStream(file);
        wb = new HSSFWorkbook(new POIFSFileSystem(inp));
        
      
        HSSFSheet sheet = wb.getSheetAt(0);
        
        
        int Nrow = sheet.getLastRowNum();
        System.out.println("nr "+Nrow);
        
        
        
        int st = NombreMaxColonne(sheet);
        System.out.println(st);
		
        
        Object[][] o = new Object[Nrow][st];
		
        
        String[] titre = new String[st];
        for(int i = 0;i<st;i++){
            titre[i] = "#"+i;
        }
		
        
        for(int i = 0; i<Nrow;i++){
            HSSFRow row = sheet.getRow(i);
            
            
            if(row!=null){
                for(int j = 0;j<st;j++){
					
                    HSSFCell cell = row.getCell((short)j);
                    Object value = ContenuCellule(cell);
                    
                    o[i][j] = value;
                }
            }
        }
        
        inp.close();
        table.setModel(new DefaultTableModel(o, titre));
        return table;
    }
    
    
    @SuppressWarnings("deprecation")
	private static Object ContenuCellule(HSSFCell cell){
        Object value = null ;
        
        if(cell == null){
            value = "";
        }
        
        else if(cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN){
            value = cell.getBooleanCellValue();
        }
        else if(cell.getCellType() == HSSFCell.CELL_TYPE_ERROR){
            value = cell.getErrorCellValue();
        }
        else if(cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA){
            value = cell.getCellFormula();
        }
        else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            value = cell.getNumericCellValue();
        }
        else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
            value = cell.getStringCellValue();
        }
        return value;
                
    }
    
	
    private static int NombreMaxColonne(HSSFSheet sheet){
        
       int r = sheet.getLastRowNum();
        int max = 0;
        int s = 0;
       while(s<r){
           if(sheet.getRow(s) != null){
               int c = sheet.getRow(s).getLastCellNum();
               if(c>max){
                   max = c;
               }
           }
           s++;
           
       }
        return max+1;
    }
    
    

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
					
                    File cible = new File("//Users/yassinfraihi/Documents/workspace/Projet_Pratique1/src/beans/yassin.xlsx");
                    
                    JTable tableur = ImportXLS.CreerJTableAvecExcel(cible);
                    
                    
                    tableur.setFont(new Font(Font.SERIF,Font.PLAIN,10));
                    
                    JFrame cadre = new JFrame();
                    cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    cadre.setVisible(true);
                    cadre.setSize(800, 200);
                    JScrollPane panel = new JScrollPane();
                    panel.setViewportView(tableur);
                    cadre.add(panel);
                    
					
					
                    //MessageFormat header = new MessageFormat("Page {0,number,integer}");
                    //tableur.print(JTable.PrintMode.FIT_WIDTH);               

                    
                    
                }catch (FileNotFoundException ex) {
                    Logger.getLogger(ImportXLS.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ImportXLS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
