package gonnadostuff;
import java.util.ArrayList;
import java.io.*;
public class FindGerman {
    static ArrayList<String> allGerWords = new ArrayList<>();

    static void getAllGerWords(String filePath){
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("German words file does not exist");
            return;
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            while(br.ready()){
                allGerWords.add(br.readLine());
            }
        }catch(IOException e){
            System.out.println("Failed getting german words from file.");
            System.out.println(e.getMessage());
        }
    }

    //this one seems to be faster
    public static ArrayList<String> findGerman(ArrayList<String> allWords){
        System.out.println("Finding sentences with german.");
        ArrayList<String> germanSentences = new ArrayList<>();
        for(int i=0; i<allWords.size(); i++){
            //Compare to all german words
            int j = allGerWords.indexOf(allWords.get(i));
            if(j==-1) continue;
            String gerSentence = getGerSentence(i, allWords);
            //avoid duplicates
            if(germanSentences.isEmpty() || !gerSentence.equals(germanSentences.get(germanSentences.size()-1))) germanSentences.add(gerSentence);
        }
        return germanSentences;
    }

    static int skipIndex = 0;
    /* public static ArrayList<String> findGerman(ArrayList<String> allWords){
        System.out.println("Finding sentences with german.");
        ArrayList<String> germanSentences = new ArrayList<>();
        for(int i=0; i<allWords.size(); i++){
            //Compare to all german words
            for(int j=0; j<allGerWords.size(); j++){
                if(allWords.get(i).equals(allGerWords.get(j))){
                    //germanSentences.add(getGetSentence(i, allWords));
                    System.out.println(allWords.get(i));
                    //i=FindGerman.skipIndex;
                    break;
                }
            }
        }
        System.out.println("GermanSentences.size(): " + germanSentences.size());
        return germanSentences;
    } */

    public static String getGerSentence(int index, ArrayList<String> allWords){
        int startIndex=index;
        int endIndex=index;
        char[] regx = {'!', '.', '?'};
        /* while((allWords.get(startIndex).indexOf('?') < 0 && allWords.get(startIndex).indexOf('!') < 0 && allWords.get(startIndex).indexOf('.') < 0)){
            startIndex--;
        }
        startIndex++;
        while((allWords.get(endIndex).indexOf('?') < 0 && allWords.get(endIndex).indexOf('!') < 0 && allWords.get(endIndex).indexOf('.') < 0)){
            endIndex++;
        } */
        startIndex = findStartIndex(startIndex, allWords, regx);
        endIndex = findEndIndex(endIndex, allWords, regx);

        StringBuilder sentence = new StringBuilder("");
        while(startIndex <= endIndex){
            sentence.append(allWords.get(startIndex) + " ");
            startIndex++;
        }
        FindGerman.skipIndex = endIndex;
        return sentence.toString();
    }
    static int findStartIndex(int startIndex, ArrayList<String> allWords, char[] regx){
        while(true){
            for(int i=0; i<allWords.get(startIndex).length(); i++){
                for(int j=0; j<regx.length; j++){
                    if(allWords.get(startIndex).charAt(i) == regx[j]){
                        return startIndex+1;
                    }
                }
            }
            startIndex--;
        }
    }
    static int findEndIndex(int endIndex, ArrayList<String> allWords, char[] regx){
        while(true){
            for(int i=0; i<allWords.get(endIndex).length(); i++){
                for(int j=0; j<regx.length; j++){
                    if(allWords.get(endIndex).charAt(i) == regx[j]){
                        return endIndex;
                    }
                }
            }
            endIndex++;
        }
    }
}