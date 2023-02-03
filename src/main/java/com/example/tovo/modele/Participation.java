package com.example.tovo.modele;
import java.sql.Connection;
import java.util.ArrayList;

import com.example.tovo.annotation.Attribut;
import com.example.tovo.annotation.NomTable;
import com.example.tovo.database.Connexion;
import com.example.tovo.obj.ObjectBdd;
import com.example.tovo.obj.Util;
@NomTable(nom="Participation")
public class Participation extends ObjectBdd {
    @Attribut(columnName="idparticipation",isprimarykey=true)
    int idparticipation;
    @Attribut(columnName="iduser")
    int iduser;
    @Attribut(columnName="idenchere")
    int idenchere;
    @Attribut(columnName="montant")
    double montant;

    public int getIdparticipation() {
        return this.idparticipation;
    }

    public void setIdparticipation(int idparticipation) {
        this.idparticipation = idparticipation;
    }

    public int getIduser() {
        return this.iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdenchere() {
        return this.idenchere;
    }

    public void setIdenchere(int idenchere) {
        this.idenchere = idenchere;
    }

    public double getMontant() {
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Participation(int idparticipation, int iduser, int idenchere, double montant) {
        super();
        this.idparticipation = idparticipation;
        this.iduser = iduser;
        this.idenchere = idenchere;
        this.montant = montant;
    }

    public Participation() {
        super();
    }
    public void insert()throws Exception{
        Connection conn=null;
        try{
            conn=Connexion.getConnection("jdbc:postgresql://postgresql-enchere.alwaysdata.net:5432/enchere_db","enchere_user","caeyla01");

        }catch(Exception e){

        }
        ArrayList<Participation> crit=getAll(this.idenchere);
        if(this.getMontant()<crit.get(crit.size()-1).getMontant()){
            throw new Exception("montant petit par rapport au dernier");
        }
        this.insert(conn,"postgres");
        try{
            if(conn!=null) conn.close();
        }catch(Exception e){

        }
    }
    public static ArrayList<Participation> getAll(int idEnchere)throws Exception{
        Connection conn=null;
        ArrayList<Participation> crit=new ArrayList<>();
       try{
           conn=Connexion.getConnection("jdbc:postgresql://postgresql-enchere.alwaysdata.net:5432/enchere_db","enchere_user","caeyla01");
           crit=Util.castParticipation(new Participation(-2002,-2002,idEnchere,-2002).select(conn));
           
       }catch(Exception e){
           
       }
       return crit;
   }


}
