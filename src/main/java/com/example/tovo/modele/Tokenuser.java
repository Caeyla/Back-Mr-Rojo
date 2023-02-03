package com.example.tovo.modele;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.sql.Connection;

import com.example.tovo.annotation.Attribut;
import com.example.tovo.annotation.NomTable;
import com.example.tovo.database.Connexion;
import com.example.tovo.obj.ObjectBdd;
@NomTable(nom="tokenuser")
public class Tokenuser extends ObjectBdd {
    /**
     *
     */
    @Attribut(columnName="idtokenuser",isprimarykey=true)
    int idtokenuser;
    @Attribut(columnName="iduser")
    int iduser;
    @Attribut(columnName="token")
    String token;
    @Attribut(columnName="dateExpiration")
    Timestamp dateExpiration;
    @Attribut(columnName="bearer")
    String type="Bearer";
    

    public int getIduser() {
        return this.iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }


    public Tokenuser() {
        super();
    }


    public int getIdtokenuser() {
        return this.idtokenuser;
    }

    public void setIdtokenuser(int idtokenuser) {
        this.idtokenuser = idtokenuser;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getDateExpiration() {
        return this.dateExpiration;
    }

    public void setDateExpiration(Timestamp dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public Tokenuser( int idtokenuser,int iduser, String token, Timestamp dateExpiration, String type) {
        super();
        this.idtokenuser = idtokenuser;
        this.iduser=iduser;
        this.token = token;
        this.dateExpiration = dateExpiration;
        this.type = type;
    }

    public String generateToken()throws Exception{
        GeneratorEncrypt gr=new GeneratorEncrypt();
        return gr.generateEncrypt(new User(this.iduser, null,null, null,null).user().getMotdepasse());
    }
    public String refresh()throws Exception{
        GeneratorEncrypt gr=new GeneratorEncrypt();

        return gr.refreshtoken(new User(this.iduser, null,null, null,null).user().getMotdepasse());
    }
    public static boolean checkIfEndSession(Tokenuser ray){
        int timeAdd=10;
        if(ray!=null){
            Long timeMil=ray.getDateExpiration().getTime();
            Timestamp vao2=new Timestamp(timeMil+timeAdd*60*1000);
            Date date = new Date();
            Timestamp present = new Timestamp(date.getTime());
            if(present.after(vao2)){
                return true;
            }else{
                return false;
            }
        }
          
        return true;
    }
    


}
