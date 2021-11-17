package gonnadostuff;
import net.sourceforge.tess4j.*;
import java.io.*;
public class App {
   public String getImgText(String imageLocation) {
      ITesseract instance = new Tesseract();
      try 
      {
         String imgText = instance.doOCR(new File(imageLocation));
         return imgText;
      } 
      catch (TesseractException e) 
      {
         e.getMessage();
         return "Error while reading image";
      }
   }
   public static void main ( String[] args)
   {
      App app = new App();
      System.out.println(app.getImgText("Feliks\\1\\1poglavje-02.jpg"));
   }
}