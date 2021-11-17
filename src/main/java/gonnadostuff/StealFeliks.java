package gonnadostuff;
import java.util.*;
import java.io.*;
import net.sourceforge.tess4j.*;

public class StealFeliks {
    public static void main(String[] args) {
        new StealFeliks();
    }
    //Control panel: ------------------------------------------------------------------------------------
    String txtFileName = "BookTxts\\CudezniFeliks.txt";
    String gerSentencesTxtFileName = "GerSentences\\NemStavki.txt";
    String gerWordsTxtPath = "wordlist-german.txt";
    int startChapter = 3;
    int endChapter = -1;
    int startPage = 1;
    int endPage = -1;
    //---------------------------------------------------------------------------------------------------
    ArrayList<String> allFeliksWords = new ArrayList<>();

    StealFeliks(){
        allFeliksWords.add(".");
        //Get all the words----------------------------------------
        GetBookWords gbw = new GetBookWords(txtFileName);
        gbw.startPage=startPage;
        gbw.endPage=endPage;
        gbw.readChapterImages(this.startChapter, this.endChapter);
        allFeliksWords.addAll(gbw.allBookWords);
        //---------------------------------------------------------
        //Print to file
        PrintToTxt.printArrayList(allFeliksWords, txtFileName);

        //readFeliksTxt(allFeliksWords);

        //Find all the german sentences
        FindGerman.getAllGerWords(gerWordsTxtPath);
        ArrayList<String> germanSentences = FindGerman.findGerman(allFeliksWords);

        //Print these sentences to txt
        PrintToTxt.printArrayList(germanSentences, gerSentencesTxtFileName);
    }
    
    void readFeliksTxt(ArrayList<String> allFeliksWords){
        String filePath = txtFileName.substring(0, txtFileName.length()-4);
        File file = new File(txtFileName);
        int fileNum = 1;
        while(file.exists()){
            fileNum++;
            file = new File(filePath+fileNum+".txt");
        }
        file = new File(filePath+--fileNum+".txt");
        try{
            BufferedReader br = new BufferedReader(new FileReader(txtFileName));
            while(br.ready()){
                String[] lineWords = br.readLine().split(" ");
                for(String x : lineWords){
                    allFeliksWords.add(x);
                }
            }
        }catch(IOException e){
            System.out.println("Failed getting german words from file.");
            System.out.println(e.getMessage());
        }
    }
}