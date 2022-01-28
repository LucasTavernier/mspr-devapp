import java.io.File;
import java.util.HashMap;
import java.util.Set;


public class HTMLFormater {

    private HTMLFormater(){}

    public static void generateHTMLFile(){
        System.out.println(HTMLFormater.element("Abcd","efgh","Rien",new HashMap<String,String>()));
    }

    private static String element(String name, String surname, String tache, HashMap<String,String>equipement){
        return String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>",name,surname,tache);
    }


}
