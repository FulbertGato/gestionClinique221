/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gatoj
 */
public class FileGenerator {
    
    public static void generator(String codePatient,String date, String resultats){
        try {
            Path path = Paths.get("F:/projet java/gestionClinique221/src/resultats/"+codePatient);
            Files.createDirectories(path);
            try {
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream("F:/projet java/gestionClinique221/src/resultats/"+codePatient+"/"+codePatient+date+".pdf"));
                document.open();
                document.add(new Paragraph(resultats));
                document.close();
            } catch (FileNotFoundException | DocumentException ex) {
                Logger.getLogger(FileGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("Miou verifions");
            
        } catch (IOException ex) {
            Logger.getLogger(FileGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
