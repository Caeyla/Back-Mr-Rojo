// package com.example.tovo.obj;

// import java.sql.Connection;
// import java.util.ArrayList;

// import com.example.tovo.modele.Personne;

// public class ThreadPersoFangalana extends Thread{
//         Personne ray;
//         ArrayList<Personne>pers=new ArrayList<Personne>();

//         public Personne getRay() {
//                 return this.ray;
//         }

//         public void setRay(Personne ray) {
//                 this.ray = ray;
//         }

//         public ArrayList<Personne> getPers() {
//                 return this.pers;
//         }

//         public void setPers(ArrayList<Personne> pers) {
//                 this.pers = pers;
//         }

//         public ThreadPersoFangalana(Personne mit){
//                 ray=mit;
//         }
//         @Override
//         public void run(){
//                 try{
//                         Thread.sleep(7000);
//                         System.out.println("tay elah");
// 			Personne.checkAvantInsert(ray);
// 			pers=Personne.personneVoacheckAndListNonSend(ray);
// 		}catch(Exception e){
// 			System.out.println(e.getMessage());
// 		}
//         }
// }
