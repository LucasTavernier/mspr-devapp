import java.io.File;

public class FileFactory {

    private static final String PATHTOFICHES = "./res/fichesAgents/";

    private FileFactory(){}

    private static String[] readAgentsDirectory(){
        File directoryFichesAgents = new File(FileFactory.PATHTOFICHES);
        return directoryFichesAgents.list();
    }

    private static boolean existFiche(String surname){
        return (new File(FileFactory.PATHTOFICHES+surname+"/"+surname+".txt")).exists();
    }

}
