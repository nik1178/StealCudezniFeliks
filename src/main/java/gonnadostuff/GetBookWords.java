package gonnadostuff;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import net.sourceforge.tess4j.*;
public class GetBookWords{
    ArrayList<String> allBookWords = new ArrayList<>();
    String txtFileName;
    int firstPageNum = 1;
    int startPage = 1;
    int endPage = -1;
    GetBookWords(File file){
        this.txtFileName = file.getAbsolutePath();
    }
    GetBookWords(String txtFileName){
        this.txtFileName = txtFileName;
    }

    void readChapterImages(int currentChapter){
        readChapterImages(currentChapter, -1);
    }
    void readChapterImages(int currentChapter, int endChapter){
        //if we reached desired chapter return
        if(currentChapter==endChapter+1) return;
        //if chapter doesn't exist return----------------------------------------------------------------
        File chapterDir = new File("Feliks\\"+currentChapter);
        if(!chapterDir.exists()){
            System.out.println("Chapter " + currentChapter + " doesn't exist.");
            return;
        }
        //----------------------------------------------------------------------------------------------

        String pageText = readPage(currentChapter, this.startPage, this.endPage);
        allBookWords.addAll(Arrays.asList(pageText.split(" ")));
        readChapterImages(currentChapter+1, endChapter);
    }

    //Returns text of all images in chapter
    String readPage(int chapter, int startPage){
        return readPage(chapter, startPage, -1);
    }
    String readPage(int chapter, int currentPage, int endPage){
        //if reached desired page return----------------------------------------------------------------
        if(currentPage==endPage+1) return "";
        //if page doesn't exist return------------------------------------------------------------------
        String pageLocation = String.format("Feliks\\%s\\%spoglavje-", chapter, chapter);
        if(currentPage<10)pageLocation +="0"+currentPage+".jpg";
        else pageLocation += currentPage+".jpg";

        File pageImage = new File(pageLocation);
        if(!pageImage.exists()){
            if(currentPage>99){
                System.out.println("Page " + currentPage + " doesn't exist.");
                return "";
            }
            return readPage(chapter, currentPage+1, endPage);
        }
        //----------------------------------------------------------------------------------------------

        System.out.println("Reading page " + currentPage);
        String pageText = "Stran: " + firstPageNum++ + " -------------------------------------------------\n" + getImgText(pageLocation) + readPage(chapter, currentPage+1, endPage) + " ";
        return pageText;
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