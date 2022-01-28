import java.io.File;
import java.util.Arrays;

public class HTMLFormater {

    private HTMLFormater(){}
    private static final String PATHTOFICHES = "./res/fichesAgents/";


    public static void generateHTML(){
        System.out.println(HTMLFormater.existFiche(""));
    }

    private static String[] readAgentsDirectory(){
        File directoryFichesAgents = new File(HTMLFormater.PATHTOFICHES);
        return directoryFichesAgents.list();
    }

    private static boolean existFiche(String surname){
        return (new File(HTMLFormater.PATHTOFICHES+surname+"/"+surname+".txt")).exists();
    }

}
