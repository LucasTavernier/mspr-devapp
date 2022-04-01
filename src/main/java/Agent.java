package main.java;

import java.util.HashMap;
import java.util.Map;

public class Agent {

    public String name;
    public String surname;
    public String tache;
    public HashMap<String,String> equipement;
    public static HashMap<String,String> listeTousEquipement = FileFactory.getEquipementsListFromFile();

    public Agent(String name,String surname,String tache,HashMap<String,String>equipement){
        this.name = name;
        this.surname = surname;
        this.tache = tache;
        this.equipement = equipement;
        this.fillKeyWithValue();
    }

    public String renderEquipementList(){
        String result = "";
        for (Map.Entry<String,String> elt : this.equipement.entrySet()) {
            result = result.concat(String.format("<li><img class='check' src='../res/svg/check.svg' alt=''/>%s - %s</li>\n",elt.getKey(),elt.getValue()));
        }
        return result;
    }

    public String getPathNameAgent(){
        return this.surname.toLowerCase().charAt(0)+this.name.toLowerCase();
    }

    public String renderAgentFicheHtml(){
        return "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css\" integrity=\"sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn\" crossorigin=\"anonymous\">\n" +
                "<link rel=\"stylesheet\" href=\"../css/style.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<nav class=\"navbar\">\n" +
                "<a href=\"../index.html\"><img class=\"logo\" src=\"../res/go-securi-logo-removebg-preview.png\" alt=\"\"/></a>\n" +
                "</nav>\n" +
                "<div class=\"content\">\n" +
                "<div class=\"div-haut d-flex\">\n" +
                "<h1>"+this.name+" "+ this.surname +"</h1>\n" +
                "<h2>"+this.tache+"</h2>\n"+
                "<img class=\"img-identite\" src=\"../res/fichesAgents/"+this.getPathNameAgent()+"/"+this.getPathNameAgent()+".jpg\" alt=\"\"/>\n" +
                "</div>\n" +
                "<div class=\"liste\">\n" +
                "<h3>Liste du matériel</h3>\n" +
                "<ul class=\"liste-content\">\n" +
                            this.renderEquipementList()+
                "</ul>\n" +
                "</div>\n" +
                "</div>\n" +
                "</body>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF\" crossorigin=\"anonymous\"></script>\n" +
                "</html>\n";

    }

    private String toStringHashmap(){
        String res = "";
        for (Map.Entry<String,String> elt : this.equipement.entrySet()) {
            res+= "("+elt.getKey()+","+elt.getValue()+")";
        }
        return res;
    }

    public String renderAgentHtmlGlobalList(){
        return String.format("<a href=\"./fichesAgentsVues/%s.html\"><li class=\"liAccueil\"><p>%s %s</p><img class=\"fleche\" src=\"../res/svg/fleche.svg\" alt=\"\"/></li></a>\n",this.getPathNameAgent(),this.name,this.surname);
    }

    public void fillKeyWithValue(){
        for (String id : this.equipement.keySet()) {
            if(Agent.listeTousEquipement.containsKey(id)){
                this.equipement.replace(id,Agent.listeTousEquipement.get(id));
            }else{
                this.equipement.remove(id);
            }
        }
    }

    public String toString(){
        return "["+this.name+","+this.surname+","+this.tache+",["+this.toStringHashmap()+"]]";
    }

}
