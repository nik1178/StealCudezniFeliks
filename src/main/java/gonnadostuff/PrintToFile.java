package gonnadostuff;
import java.util.ArrayList;
import java.io.*;
class PrintToTxt{
    static ArrayList<String> allWords;

    public static void printArrayList(ArrayList<String> allWords, String filePath){
        File file = new File(filePath);
        printArrayList(allWords, file);
    } 
    public static void printArrayList(ArrayList<String> allWords, File file){
        //Make a new file so you have backups or something idk
        file = getFile(file);
        if(file==null) return;

        //Actual print
        try{
            PrintWriter pw = new PrintWriter(file);
            for(String x : allWords){
                pw.print(x + " ");
            }
            pw.close();
            System.out.println("Printed ArrayList to file: " + file.getAbsolutePath());
        }catch(IOException e){
            System.out.println("Failed printing words to file; " + e.getMessage());
        }
    } 
    public static void printlnArrayList(ArrayList<String> allWords, File file){
        //Make a new file so you have backups or something idk
        file = getFile(file);
        if(file==null) return;

        //Actual print
        try{
            PrintWriter pw = new PrintWriter(file);
            for(String x : allWords){
                pw.println(x);
            }
            pw.close();
            System.out.println("Printed ArrayList to file: " + file.getAbsolutePath());
        }catch(IOException e){
            System.out.println("Failed printing words to file; " + e.getMessage());
        }
    } 
    public static File getFile(File file) {
        int fileNum = 1;
        String fileName = file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4);
        while(file.exists()){
            fileNum++;
            file = new File(fileName+fileNum+".txt");
        }
        try{
            if(!file.createNewFile()){
                System.out.println("Failed creating file " + file.getAbsolutePath());
                return null;
            }
            System.out.println("Created file: " + file.getAbsolutePath());
        }catch(IOException e){
            System.out.println("Failed creating file " + file + e.getMessage());
        }
        return file;
    }
}