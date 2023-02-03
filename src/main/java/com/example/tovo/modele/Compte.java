package com.example.tovo.modele;
import java.sql.Connection;

import com.example.tovo.annotation.Attribut;
import com.example.tovo.annotation.NomTable;
import com.example.tovo.database.Connexion;
import com.example.tovo.obj.ObjectBdd;
@NomTable(nom="compte")
public class Compte extends ObjectBdd {
    @Attribut(columnName="idcompte",isprimarykey=true)
    int idcompte;
    @Attribut(columnName="iduser")
    int iduser;
    @Attribut(columnName="montant")
    double montant;

    public Compte(int idcompte, int iduser, double montant) {
        super();
        this.idcompte = idcompte;
        this.iduser = iduser;
        this.montant = montant;
    }

    public Compte() {
        super();
    }

    public int getIdcompte() {
        return this.idcompte;
    }

    public void setIdcompte(int idcompte) {
        this.idcompte = idcompte;
    }

    public int getIduser() {
        return this.iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public double getMontant() {
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
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
    public void update()throws Exception{
        Connection conn=null;
        try{
            conn=Connexion.getConnection("jdbc:postgresql://postgresql-enchere.alwaysdata.net:5432/enchere_db","enchere_user","caeyla01");

        }catch(Exception e){

        }
        this.update(conn);
        try{
            if(conn!=null) conn.close();
        }catch(Exception e){

        }
    }
    public  Compte user()throws Exception{
        Connection conn=null;
        try{
            conn=Connexion.getConnection("jdbc:postgresql://postgresql-enchere.alwaysdata.net:5432/enchere_db","enchere_user","caeyla01");
			Compte ray=(Compte)(this.select(conn)[0]);
            conn.close();
            return ray;
        }catch(Exception e){
           
            return null;
        }
    }


    


}
