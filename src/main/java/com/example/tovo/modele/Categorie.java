package com.example.tovo.modele;

import com.example.tovo.annotation.Attribut;
import com.example.tovo.annotation.NomTable;
import com.example.tovo.obj.ObjectBdd;

@NomTable(nom="Admin")
public class Categorie extends ObjectBdd{
    @Attribut(columnName="idcategorie",isprimarykey=true)
    int idcategorie;
    @Attribut(columnName="nom")
    String nom;
    @Attribut(columnName="description")
    String description;


    public Categorie() {
        super();
    }

    public Categorie(int idcategorie, String nom, String description) {
        super();
        this.idcategorie = idcategorie;
        this.nom = nom;
        this.description = description;
    }

    public int getIdcategorie() {
        return this.idcategorie;
    }

    public void setIdcategorie(int idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
