package com.example.enchere.modele;

import com.example.enchere.obj.ObjectBdd;
import java.sql.*;
import com.example.enchere.database.*;
import com.example.enchere.annotation.Attribut;
import com.example.enchere.annotation.NomTable;

@NomTable(nom="Admin")
public class Admin extends ObjectBdd{
    @Attribut(columnName="idadmin",isprimarykey=true)
    int idadmin;
    @Attribut(columnName="identifiant")
    String identifiant;
    @Attribut(columnName="motdepasse")
    String motdepasse;

    public int getIdadmin() {
        return this.idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMotdepasse() {
        return this.motdepasse;
    }

    public void setMotdepasse(String motedepasse) {
        this.motdepasse = motedepasse;
    }

    public Admin() {
        super();
    }

    public Admin(int idadmin, String identifiant, String motdepasse) {
        super();
        this.idadmin = idadmin;
        this.identifiant = identifiant;
        this.motdepasse = motdepasse;
    }
    public  Admin user()throws Exception{
        Connection conn=null;
        try{
            conn=Connexion.getConnection("jdbc:postgresql://postgresql-enchere.alwaysdata.net:5432/enchere_db","enchere_user","caeyla01");
			Admin ray=(Admin)(this.select(conn)[0]);
            conn.close();
            return ray;
        }catch(Exception e){
           
            return null;
        }
    }

}
