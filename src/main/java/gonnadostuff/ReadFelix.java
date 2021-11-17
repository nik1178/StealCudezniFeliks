package gonnadostuff;
import java.io.*;
public class ReadFelix {
    public static void main(String[] args) {
        new ReadFelix();
    }
    ReadFelix(){
        try{
            //Get all german words
            File germanFile = new File("wordlist-german.txt");
            BufferedReader germanbr = new BufferedReader(new FileReader(germanFile));
            StringBuilder germansb = new StringBuilder("");
            while(germanbr.ready()){
                germansb.append(germanbr.readLine() + " ");
            }
            String[] germanWords = germansb.toString().split(" ");

            File feliksFile = new File("poglavje1.txt");
            BufferedReader br = new BufferedReader(new FileReader(feliksFile));
            StringBuilder sb = new StringBuilder("");
            while(br.ready()){
                sb.append(br.readLine() + " ");
            }
            String[] splitFeliks = sb.toString().split(" ");
            for(int i=0; i<splitFeliks.length; i++){
                if(splitFeliks[i].length()<2) continue;
                for(String germanWord : germanWords){
                    if(splitFeliks[i].equals(germanWord)){
                        System.out.println(splitFeliks[i]);
                        break;
                    }
                }
            }
        }catch(Exception e){}
    }
}
