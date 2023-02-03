package com.example.tovo.modele;
import java.sql.Connection;
import java.util.Date;
import java.util.ArrayList;
import com.example.tovo.annotation.Attribut;
import com.example.tovo.annotation.NomTable;
import com.example.tovo.annotation.NomTable;
import com.example.tovo.database.Connexion;
import com.example.tovo.obj.*;
@NomTable(nom="enchere")
public class Enchere extends ObjectBdd{
    @Attribut(columnName="idcompte",isprimarykey=true)
    int idenchere;
    @Attribut(columnName="iduser")
    int iduser;
    @Attribut(columnName="duree")
    double duree;
    @Attribut(columnName="prixminimale")
    double prixminimale;
    @Attribut(columnName="dateentree")
    Date dateentree=new Date();


    public Date getDateentree() {
        return this.dateentree;
    }

    public void setDateentree(Date dateentree) {
        this.dateentree = dateentree;
    }


    public int getIdenchere() {
        return this.idenchere;
    }

    public void setIdenchere(int idenchere) {
        this.idenchere = idenchere;
    }

    public int getIduser() {
        return this.iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public double getDuree() {
        return this.duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public double getPrixminimale() {
        return this.prixminimale;
    }

    public void setPrixminimale(double prixminimale) {
        this.prixminimale = prixminimale;
    }

    public Enchere() {
        super();
    }

    public Enchere(int idenchere, int iduser, double duree, double prixminimale,Date date) {
        super();
        this.idenchere = idenchere;
        this.iduser = iduser;
        this.duree = duree;
        this.prixminimale = prixminimale;
        this.dateentree=date;
    }
    public void insert()throws Exception{
        Connection conn=null;
        try{
            conn=Connexion.getConnection("jdbc:postgresql://postgresql-enchere.alwaysdata.net:5432/enchere_db","enchere_user","caeyla01");

        }catch(Exception e){

        }
        this.insert(conn,"postgres");
        try{
            if(conn!=null) conn.close();
        }catch(Exception e){

        }
    }
    public boolean isEnd(){
        if(duree*60*60*1000>=new Date().getTime()) return true;
        return false;
    }
    public static ArrayList<Enchere> getAll()throws Exception{
         Connection conn=null;
         ArrayList<Enchere> crit=new ArrayList<>();
        try{
            conn=Connexion.getConnection("jdbc:postgresql://postgresql-enchere.alwaysdata.net:5432/enchere_db","enchere_user","caeyla01");
			crit=Util.castEnchere(new Enchere(-2002,-2002,-2002,-2002,null).select(conn));
            
        }catch(Exception e){
            
        }
        return crit;
    }
    public static ArrayList<Enchere> EnchereNonfini()throws Exception{
       
        ArrayList<Enchere> crit=new ArrayList<>();
       try{
           int count=0;
           for(Enchere ench :getAll()){
                if(ench.isEnd()==false){
                    crit.add(ench);
                    count++;
                }
           }
           if(count==0){
             throw new Exception("aucun");
           }
           return crit;
           
       }catch(Exception e){
            return getAll();
       }
   }




}
