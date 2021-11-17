package gonnadostuff;
import net.sourceforge.tess4j.*;
import java.io.*;
public class StealFelix {
    public static void main(String[] args) {
        new StealFelix();
    }
    String txtFileName = "poglavje1.txt";
    StealFelix(){
        writeToFile();
    }
    void writeToFile() {
        int chapter=1;
        int page=1;

        File writeFile = new File(txtFileName);
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(writeFile));
            while(true){

                String readFileName = String.format("Feliks\\%s\\%spoglavje-",chapter, chapter);
                if(page<10){
                    readFileName +="0"+page+".jpg";
                }else if(page<100){
                    readFileName +=page+".jpg";
                } else System.out.println("Page num too big: " + page);
    
                File readFile = new File(readFileName);
    
                if(!readFile.exists()){
                    System.out.println("File not found: " + readFileName);
                    break;
                }
    
                String pageText = getImgText(readFileName);
                pw.println(pageText);
                System.out.println("Page: " + page + " done.");
                page++;
            }
            pw.close();
        }catch (IOException e){
            System.out.println("Failed creating printwriter: " + e.getMessage());
        }

    }
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
}
