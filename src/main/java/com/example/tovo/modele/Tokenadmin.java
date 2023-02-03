package com.example.tovo.modele;
import java.sql.Connection;
import java.sql.Timestamp;
import com.example.tovo.annotation.Attribut;
import com.example.tovo.annotation.NomTable;
import com.example.tovo.database.Connexion;
import com.example.tovo.obj.ObjectBdd;
@NomTable(nom="tokenadmin")
public class Tokenadmin extends ObjectBdd {
    @Attribut(columnName="idtokenadmin",isprimarykey=true)
    int idtokenadmin;
    @Attribut(columnName="idadmin")
    int idadmin;
    @Attribut(columnName="token")
    String token;
    @Attribut(columnName="dateExpiration")
    Timestamp dateExpiration;
    @Attribut(columnName="bearer")
    String type="Bearer";
    

    public int getIdadmin() {
        return this.idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }


    public Tokenadmin() {
        super();
    }


    public int getIdtokenadmin() {
        return this.idtokenadmin;
    }

    public void setIdtokenadmin(int idtokenadmin) {
        this.idtokenadmin = idtokenadmin;
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

    public Tokenadmin( int idtokenadmin,int idadmin, String token, Timestamp dateExpiration, String type) {
        super();
        this.idtokenadmin = idtokenadmin;
        this.idadmin=idadmin;
        this.token = token;
        this.dateExpiration = dateExpiration;
        this.type = type;
    }

    public String generateToken()throws Exception{
        GeneratorEncrypt gr=new GeneratorEncrypt();
        return gr.generateEncrypt(new Admin(this.idadmin, null,null).user().getMotdepasse());
    }
    public String refresh()throws Exception{
        GeneratorEncrypt gr=new GeneratorEncrypt();

        return gr.refreshtoken(new Admin(this.idadmin, null,null).user().getMotdepasse());
    }
    public static boolean checkIfEndSession(Tokenadmin ray){
        int timeAdd=10;
        if(ray!=null){
            Long timeMil=ray.getDateExpiration().getTime();
            Timestamp vao2=new Timestamp(timeMil+timeAdd*60*1000);
            java.util.Date date = new java.util.Date();
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
