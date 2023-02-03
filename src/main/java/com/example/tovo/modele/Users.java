package com.example.tovo.modele;

import java.rmi.ConnectIOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.catalina.User;

import com.example.tovo.obj.ObjectBdd;
import com.example.tovo.obj.Util;

import com.example.tovo.annotation.Attribut;
import com.example.tovo.annotation.NomTable;
import com.example.tovo.database.Connexion;
@NomTable(nom="Users")
public class Users extends ObjectBdd {
    @Attribut(columnName = "idusers",isprimarykey = true)
    int idusers;
    @Attribut(columnName= "nom")
    String nom;
    @Attribut(columnName= "mdp")
    String mdp;

    public Users() {
        super();
    }

    public Users(int iduser, String nom, String mdp) {
        super();
        this.idusers = iduser;
        this.nom = nom;
        this.mdp = mdp;
    }

    public int getIdusers() {
        return this.idusers;
    }

    public void setIdusers(int iduser) {
        this.idusers = iduser;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMdp() {
        return this.mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    // public Moyenne M(){
    //     Moyenne ray=new Moyenne(-2002,this,-2002);
    //     Connection conn=null;
    //     Moyenne m=null;
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //         Object[]list=ray.select(conn);
    //         m=(Moyenne)list[0];
			
            
    //     }catch(Exception e){
    //         System.out.println(e.getMessage());
    //     }
    //     return m;
    // }
    // public void insert()throws Exception{
    //     Connection conn=null;
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");

    //     }catch(Exception e){

    //     }
    //     this.insert(conn,"postgres");
    //     try{
    //         if(conn!=null) conn.close();
    //     }catch(Exception e){

    //     }
    // }
    // public  Users user()throws Exception{
    //     Connection conn=null;
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
	// 		Users ray=(Users)(this.select(conn)[0]);
    //         return ray;
    //     }catch(Exception e){
           
    //         return null;
    //     }
    // }
    // public ArrayList<Critere> getAllCritere(){
    //     Critere ray=new Critere(-2002,this,null,-2002,-2002,-2002);
    //     Connection conn=null;
    //     ArrayList<Critere> crit=new ArrayList<>();
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //         Object[]list=ray.select(conn);
    //         System.out.println("tayyyyyyyyyyyyyyyyyyyyyyyyyyyybe"+list.length);
	// 		crit=Util.castCritere(list);
            
    //     }catch(Exception e){
    //         System.out.println(e.getMessage());
    //     }
    //     System.out.println("tayyyyyyyyyyyyyyyyyyyyyyyyyyyy"+crit.size());
    //     return crit;

    // }
    // public static ArrayList<Users> getAll(){
    //     Users use=new Users(-2002,null,null);
    //     Connection conn=null;
    //     ArrayList<Users> crit=new ArrayList<>();
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //         Object[]list=use.select(conn);
    //         System.out.println("tayyyyyyyyyyyyyyyyyyyyyyyyyyyybe"+list.length);
	// 		crit=Util.castUser(list);
            
    //     }catch(Exception e){
    //         System.out.println(e.getMessage());
    //     }
    //     System.out.println("tayyyyyyyyyyyyyyyyyyyyyyyyyyyy"+crit.size());
    //     return crit;

    // }
    // public HashMap<String,Critere> allC(){
    //     HashMap<String,Critere>map=new HashMap<>();
    //     ArrayList<Critere>ray=this.getAllCritere();
    //     if(ray.size()>0){
    //         for (Critere critere : ray) {
    //             System.out.println(critere.getIntitule());
    //             map.put(critere.getIntitule(), critere);
    //         }
    //     }
    //     return map;
    // }

    // public InfoUser getInfo()throws Exception{
    //     Connection conn=null;
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
	// 		InfoUser ray=(InfoUser)(new InfoUser(-2002,this,-2002,-2002,-2002,-2002,-2002,null,-2002,-2002,null).select(conn)[0]);
    //         return ray;
    //     }catch(Exception e){
    
    //         return null;
    //     }
    // }

    // public ArrayList<Users> getUserOppositeSexe()throws Exception{
    //      Connection conn=null;
    //      ArrayList<Users> crit=new ArrayList<>();
    //      ArrayList<Users> fin=new ArrayList<>();
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
	// 		crit=Util.castUser(new Users(-2002,null,null).select(conn));
            
    //     }catch(Exception e){
            
    //     }
    //     if(crit.size()>0){
    //         for (Users user : crit) {
                
    //             if(user.getInfo().getSexe()!=this.getInfo().getSexe()){
    //                 System.out.println(user.getIdusers()+" "+user.getInfo().getSexe()+" "+this.getIdusers()+" "+this.getInfo().getSexe());
    //                 fin.add(user);
    //             }
    //         }
    //     }
    //     return fin;
    // }
    // public ArrayList<Users> nyTenaIzy()throws Exception{
    //     Connection conn=null;
    //     Moyenne ray=null;
    //     ArrayList<Users> crit=new ArrayList<>();
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
	// 		ray=(Moyenne)(new Moyenne(-2002,this,-2002).select(conn)[0]);
            
    //     }catch(Exception e){
            
    //     }
    //     if(ray!=null){
    //         //System.out.println(user.moyenne(this.getAllCritere());
    //         if(this.getUserOppositeSexe().size()>0){
    //             for (Users user : this.getUserOppositeSexe()) {
    //                 //System.out.println(user.moyenne(this.getAllCritere()));
    //                 if(user.moyenne(this.getAllCritere())>=ray.getMoyenne()){
    //                     if(this.getPasser().size()>0){
    //                         boolean bo=false;
    //                         for (Passer asser : this.getPasser()) {
    //                             if(asser.getDeux()==user.getIdusers()){
    //                                 bo=true;
                                   
    //                             }
    //                         }
    //                         if(bo==false){
    //                             crit.add(user);
    //                         }
    //                     }else{
    //                                 crit.add(user);
    //                     }
                        
                        
    //                 }
    //             }
    //         }
    //     }
    //     return crit;
    // }
    // public double moyenne(ArrayList<Critere>ray)throws Exception{
    //     InfoUser inf=this.getInfo();
    //     double sommeCoeff=1;
    //     double sommeNote=0;
    //     if(ray.size()>0){
    //         sommeCoeff=0;
    //         for (Critere critere : ray) {
    //             if(critere.getIntitule().compareToIgnoreCase("finoana")==0){
    //                     // System.out.println("finoana:"+inf.getFinoana());
    //                     // System.out.println("minf:"+critere.getMin());
    //                     // System.out.println("maxf:"+critere.getMax());
    //                     if(critere.getMin()<critere.getMax()){
    //                         if(inf.getFinoana()>=critere.getMin()&&inf.getFinoana()<=critere.getMax()){
    //                             sommeNote+=20*critere.getCoeff();
    //                             sommeCoeff+=critere.getCoeff();
    //                         }else{
    //                             sommeNote+=0;
    //                             sommeCoeff+=critere.getCoeff();
    //                         }
    //                     }else{
    //                         double max=critere.getMin();
    //                         if(inf.getFinoana()==max){
    //                             sommeNote+=20*critere.getCoeff();
    //                             sommeCoeff+=critere.getCoeff();
    //                         }else{
    //                             sommeNote+=0*critere.getCoeff();
    //                             sommeCoeff+=critere.getCoeff();
    //                         }
    //                     }
    //                     // System.out.println("note"+sommeNote);
    //                     // System.out.println("coeff"+sommeCoeff);
    //             }else if(critere.getIntitule().compareToIgnoreCase("poids")==0){
    //                 // System.out.println("poids:"+inf.getPoids());
    //                     // System.out.println("minp:"+critere.getMin());
    //                     // System.out.println("maxp:"+critere.getMax());
    //                 if(critere.getMin()<critere.getMax()){
    //                     if(inf.getPoids()>=critere.getMin()&&inf.getPoids()<=critere.getMax()){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0;
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }else{
    //                     double max=critere.getMin();
    //                     if(inf.getPoids()==max){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }
    //                 // System.out.println("note"+sommeNote);
    //                 // System.out.println("coeff"+sommeCoeff);
    //             }else if(critere.getIntitule().compareToIgnoreCase("taille")==0){
    //                 // System.out.println("taille:"+inf.getPoids());
    //                 // System.out.println("mint:"+critere.getMin());
    //                 // System.out.println("maxt:"+critere.getMax());
    //                 if(critere.getMin()<critere.getMax()){
    //                     if(inf.getTaille()>=critere.getMin()&&inf.getTaille()<=critere.getMax()){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0;
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }else{
    //                     double max=critere.getMin();
                        
    //                     if(inf.getTaille()==max){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }
    //                 // System.out.println("note"+sommeNote);
    //                 // System.out.println("coeff"+sommeCoeff);
    //             }else if(critere.getIntitule().compareToIgnoreCase("couleur")==0){
    //                 // System.out.println("couleur:"+inf.getCouleur());
    //                 // System.out.println("minc:"+critere.getMin());
    //                 // System.out.println("maxc:"+critere.getMax());
    //                 if(critere.getMin()<critere.getMax()){
    //                     if(inf.getCouleur()>=critere.getMin()&&inf.getCouleur()<=critere.getMax()){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0;
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }else{
    //                     double max=critere.getMin();
    //                     if(inf.getCouleur()==max){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }
    //                 // System.out.println("note"+sommeNote);
    //                 // System.out.println("coeff"+sommeCoeff);
    //             }else if(critere.getIntitule().compareToIgnoreCase("bacc")==0){
    //                 // System.out.println("bacc:"+inf.getBacc());
    //                 // System.out.println("minp:"+critere.getMin());
    //                 // System.out.println("maxp:"+critere.getMax());
    //                 if(critere.getMin()<critere.getMax()){
    //                     if(inf.getBacc()>=critere.getMin()&&inf.getBacc()<=critere.getMax()){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0;
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }else{
    //                     double max=critere.getMin();
    //                     if(inf.getBacc()==max){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }
    //                 // System.out.println("note"+sommeNote);
    //                 // System.out.println("coeff"+sommeCoeff);
    //             }else if(critere.getIntitule().compareToIgnoreCase("salaire")==0){
    //                 // System.out.println("salaire:"+inf.getSalaire());
    //                 // System.out.println("mins:"+critere.getMin());
    //                 // System.out.println("maxs:"+critere.getMax());
    //                 if(critere.getMin()<critere.getMax()){
    //                     if(inf.getSalaire()>=critere.getMin()&&inf.getSalaire()<=critere.getMax()){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0;
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }else{
    //                     double max=critere.getMin();
    //                     if(inf.getSalaire()==max){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }
    //                 // System.out.println("note"+sommeNote);
    //                 // System.out.println("coeff"+sommeCoeff);
    //             }else if(critere.getIntitule().compareToIgnoreCase("dateNaissance")==0){
                    
    //                 double val=new Date().getYear()-inf.getDateNaissance().getYear();
    //                 // System.out.println("dnt:"+val);
    //                 // System.out.println("minp:"+critere.getMin());
    //                 // System.out.println("maxp:"+critere.getMax());
    //                 if(critere.getMin()<critere.getMax()){
    //                     if(val>=critere.getMin()&&val<=critere.getMax()){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0;
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }else{
    //                     double max=critere.getMin();
    //                     if(val==max){
    //                         sommeNote+=20*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }else{
    //                         sommeNote+=0*critere.getCoeff();
    //                         sommeCoeff+=critere.getCoeff();
    //                     }
    //                 }
    //                 System.out.println("note"+sommeNote);
    //                 System.out.println("coeff"+sommeCoeff);
    //             }
    //         }
            
    //     }
    //     System.out.println("note"+sommeNote);
    //     System.out.println("coeff"+sommeCoeff);
    //     return sommeNote/sommeCoeff;
    // }
    // public double totalNoteText()throws Exception{
    //     double total=0;
    //     Connection conn=null;
    //     ArrayList<Mot> crit=new ArrayList<>();
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
	// 		crit=Util.castMot(new Mot(-2002,null,-2002).select(conn));
            
    //     }catch(Exception e){
            

    //     }
    //     InfoUser info=this.getInfo();
    //     ArrayList<MotOcc>occurence=new ArrayList<>();
    //     for (Mot mot : crit) {
    //         String temp=mot.getIntitule();
    //         String[]ray=new String[0];
    //         boolean initial=false;
    //         int occ=0;
    //         if(temp.contains(";")){
    //             initial=true;
    //             ray=mot.getIntitule().split(";");
    //         }
    //         for (String mot2 : info.analyse()) {
    //             if(initial==true){
    //                 for (String string : ray) {
    //                     if(string.compareToIgnoreCase(mot2)==0){
    //                         occ+=1;
    //                         total+=mot.getPoint();
    //                     }
    //                 }
    //             }else{
    //                 if(mot.getIntitule().compareToIgnoreCase(mot2)==0){
    //                     occ+=1;
    //                     total+=mot.getPoint();
    //                 }
    //             }
                
    //         }
    //         System.out.println("iduser"+this.getIdusers());
    //         System.out.println(mot.getIntitule()+" "+occ);
    //         occurence.add(new MotOcc(mot.getIntitule(), occ));
    //     }
    //     return total;
    // }
    // public static ArrayList<AssocUserVie> noteByUser()throws Exception{
    //         ArrayList<Users>list=getAll();
    //         ArrayList<AssocUserVie>liste=new ArrayList<>();
    //         for (Users user : list) {
    //             liste.add(new AssocUserVie(user,user.totalNoteText()));
    //         }
    //         return liste;

    // }
    // public ArrayList<Passer> getPasser(){
    //     Passer ray=new Passer(-2002,this.getIdusers(),-2002);
    //     Connection conn=null;
    //     ArrayList<Passer> crit=new ArrayList<>();
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //         Object[]list=ray.select(conn);
    //         System.out.println("tayyyyyyyyyyyyyyyyyyyyyyyyyyyybe"+list.length);
	// 		crit=Util.castPasser(list);
            
    //     }catch(Exception e){
    //         System.out.println(e.getMessage());
    //     }
    //     System.out.println("tayyyyyyyyyyyyyyyyyyyyyyyyyyyy"+crit.size());
    //     return crit;
    // }


}
