package main.java;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FileFactory {

    private static final String PATHTOFICHES = "gosecuri/res/fichesAgents";

    private FileFactory(){}

    private static String[] readAgentsDirectory(){
        File directoryFichesAgents = new File(FileFactory.PATHTOFICHES);
        return directoryFichesAgents.list();
    }

    private static String renderAllAgentIndexHtml(){
        String res = "";
        for (Agent agent:FileFactory.getAgentListObject()) {
            res += agent.renderAgentHtmlGlobalList();
        }
        return res;
    }

    public static void generateIndex(){
        File file = new File("./gosecuri/index.html");
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(file);
            myWriter.write("<html>\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css\" integrity=\"sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn\" crossorigin=\"anonymous\">\n" +
                    "    <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                    "    <link rel=\"stylesheet\" href=\"css/accueil.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<nav class=\"navbar\">\n" +
                    "    <a href=\"index.html\"><img class=\"logo\" src=\"res/go-securi-logo-removebg-preview.png\" alt=\"\"/></a>\n" +
                    "\n" +
                    "    <!--<span class=\"navbar-brand mb-0 h1\">Go Securi</span>-->\n" +
                    "</nav>\n" +
                    "<div class=\"content pageAccueil\">\n" +
                    "    <h1>Accueil</h1>\n" +
                    "    <div class=\"listeAccueil\">\n" +
                    "        <h2>Liste des agents</h2>\n" +
                    "        <ul class=\"liste-agent\">\n" +
                                FileFactory.renderAllAgentIndexHtml() +
                    "        </ul>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "\n" +
                    "</body>\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF\" crossorigin=\"anonymous\"></script>\n" +
                    "</html>");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateAllAgentHtmlFile(){
        for (Agent agent: FileFactory.getAgentListObject()) {
            File file = new File("./gosecuri/fichesAgentsVues/"+agent.surname.toLowerCase().charAt(0)+agent.name.toLowerCase()+".html");
            FileWriter myWriter;
            try {
                myWriter = new FileWriter(file);
                myWriter.write(agent.renderAgentFicheHtml());
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    // retourne l'élément de type agent en prenant en paramètre le chemin vers la fiche agent
    private static Agent getAgentFromFilePath(String path){
        try {
            File file = new File(FileFactory.PATHTOFICHES+"/"+path+"/"+path+".txt");
            ArrayList list = new ArrayList();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String tempElt;
            boolean inTab = false;
            HashMap<String,String>map = new HashMap<String,String>();
            while ((tempElt = br.readLine()) != null){
                if(inTab == false && (!tempElt.equals(""))){
                    list.add(tempElt);
                }else if (tempElt.equals("")){
                    inTab = true;
                }else{
                    map.put(tempElt,"");
                }
            }
            return new Agent(list.get(0).toString(),list.get(1).toString(),list.get(2).toString(),map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Génère la liste de type Agent à partir des fichiers txt présents dans le répertoire
    public static ArrayList <Agent> getAgentListObject(){
        ArrayList<Agent> agents = new ArrayList<Agent>();
        for (String pathToAgentRepo: FileFactory.readAgentsDirectory()) {
            agents.add(FileFactory.getAgentFromFilePath(pathToAgentRepo));
        }
        return agents;
    }

    //Génère le fichier staff.txt grace aux répertoires présents dans le répertoire ./res/fichesAgents
    public static void generateStaffFile(){
        File file = new File("gosecuri/res/staff.txt");
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(file);
            for (String value : FileFactory.readAgentsDirectory()) {
                myWriter.write(value+"\n");
            }
        myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Récupère sous forme de Hashmap les équipements contenu dans le fichier liste.txt
    public static HashMap<String,String> getEquipementsListFromFile(){
        HashMap<String,String>tab = new HashMap<String,String>();
        try {
            File file = new File("gosecuri/res/liste.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                tab.put(line.substring(0,line.indexOf(' ')),line.substring(line.indexOf(' ')+1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab;
    }

}
