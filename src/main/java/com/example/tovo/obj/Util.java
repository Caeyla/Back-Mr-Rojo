package com.example.tovo.obj;

import java.util.*;
import java.lang.reflect.*;
import java.sql.Timestamp;

import com.example.tovo.modele.*;
import com.example.tovo.annotation.*;
import java.time.*;
import java.lang.*;

public class Util {
    public static  ArrayList<Enchere> castEnchere(Object[]ray){
        ArrayList<Enchere>ar=new ArrayList<Enchere>();
        if(ray.length>0){
            for(int i=0;i<ray.length;i++){
                if(ray[i] instanceof Enchere){
                    ar.add((Enchere)ray[i]);
                }
            }
        }
        return ar;
    }
    public static  ArrayList<Participation> castParticipation(Object[]ray){
        ArrayList<Participation>ar=new ArrayList<Participation>();
        if(ray.length>0){
            for(int i=0;i<ray.length;i++){
                if(ray[i] instanceof Participation){
                    ar.add((Participation)ray[i]);
                }
            }
        }
        return ar;
    }
    // public static  ArrayList<String> castString(Object[]ray){
    //     ArrayList<String>ar=new ArrayList<String>();
    //     if(ray.length>0){
    //         for(int i=0;i<ray.length;i++){
    //             if(ray[i] instanceof String){
    //                 ar.add((String)ray[i]);
    //             }
    //         }
    //     }
    //     return ar;
    // }
    // public static  ArrayList<Passer> castPasser(Object[]ray){
    //     ArrayList<Passer>ar=new ArrayList<Passer>();
    //     if(ray.length>0){
    //         for(int i=0;i<ray.length;i++){
    //             if(ray[i] instanceof Passer){
    //                 ar.add((Passer)ray[i]);
    //             }
    //         }
    //     }
    //     return ar;
    // }
    public static void tabAdd(ArrayList<String>ray,ArrayList<String>roa){
        for (String string : roa) {
            ray.add(string);
        }
    }
    // public static double td(Date date1,Date date2){
 
	// 	Date d3 = new Date(date1.getTime() - date2.getTime());
	// 	System.out.println("Hours : " + d3.getTime()/1000/60/60);
	// 	return d3.getTime()/1000/60/60;
	// }

//     final String text = "2011-10-02 18:48:05.123";
// ts = Timestamp.valueOf(text);

// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
// Date parsedDate = dateFormat.parse(yourString);
// Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
    public static double tt(Timestamp date1,Timestamp date2){
 
		double diff=date1.getTime() - date2.getTime();
		//System.out.println("Hours : " + d3.getTime()/1000/60/60);
		return diff/1000/60/60;
	}
    public static double diffJourDate(Date date1,Date date2){
 
		double diff=date1.getTime() - date2.getTime();
		//System.out.println("Hours : " + d3.getTime()/1000/60/60);
		return diff/1000/60/60/24;
	}

    public static Date parseLocalDateToDate(String dt){
        ZoneId defaultZoneId = ZoneId.systemDefault();
		
	//creating the instance of LocalDate using the day, month, year info
        LocalDate localDate = LocalDate.parse(dt);
        
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        return date;
    }
    // String text = "2009-10-20";
    // LocalDateTime dateTime = LocalDate.parse(text).atStartOfDay();
    // System.out.println(dateTime);
    public static Date toGod(String dt){
        ZonedDateTime dateTime  = ZonedDateTime.parse(dt+":00.000Z");
        LocalDateTime localDateTime  = dateTime.toLocalDateTime();
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        Date date = Date.from(zdt.toInstant());
		return date;
    }
    public static Timestamp toT(String dt){
        ZonedDateTime dateTime  = ZonedDateTime.parse(dt+":00.000Z");
        LocalDateTime localDateTime  = dateTime.toLocalDateTime();
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        Timestamp timestamp = Timestamp.valueOf(zdt.toLocalDateTime());
        return timestamp;
    }
    public static Object traductionParameterDynamique(Map<String,String> attributValue)throws Exception{
        Class ray=Class.forName(attributValue.get("class"));
        Object temp=ray.getDeclaredConstructor().newInstance();
        Field[]fields=ray.getDeclaredFields();
        Method[]methods=ray.getDeclaredMethods();
       for (int i=0;i<fields.length ;i++) 
       {
               for (int j=0;j<methods.length ;j++ ) 
               {
                   if (("set"+fields[i].getName()).compareToIgnoreCase(methods[j].getName())==0)
                   {
                           if(attributValue!=null){
                                       if(attributValue.get(fields[i].getName())!=null){
                                               if (fields[i].getType().getName()=="int")
                                               {
                                                   try{
                                                       int a=Integer.parseInt(attributValue.get(fields[i].getName()));
                                                       methods[j].invoke(temp,a);
                                                   }catch(Exception er){
                                                       
                                                   }              
                                               }else if (fields[i].getType().getName()=="float")
                                               {
                                                   try{
                                                       float a=Float.parseFloat(attributValue.get(fields[i].getName()));
                                                       methods[j].invoke(temp,a);
                                                   }catch(Exception er){

                                                   }              
                                               }else if (fields[i].getType().getName()=="double")
                                               {
                                                   try{
                                                       double a=Double.parseDouble(attributValue.get(fields[i].getName()));
                                                       methods[j].invoke(temp,a);
                                                   }catch(Exception er){

                                                   }              
                                               }else if (fields[i].getType().getName()=="long")
                                               {
                                                   try{
                                                       long a=Long.parseLong(attributValue.get(fields[i].getName()));
                                                       methods[j].invoke(temp,a);
                                                   }catch(Exception er){

                                                   }              
                                               }else if (fields[i].getType().getName()=="java.lang.String")
                                               {
                                                   try{
                                                       methods[j].invoke(temp,attributValue.get(fields[i].getName()));
                                                   }catch(Exception er){

                                                   }              
                                               }else if(fields[i].getType().getName()=="java.sql.Timestamp"){
                                                try{
                                                    java.sql.Timestamp a=toT(attributValue.get(fields[i].getName()));
                                                    methods[j].invoke(temp,a);
                                                }catch(Exception er){

                                                }  
                                            }
                                            else if(fields[i].getType().getName()=="java.util.Date"){
                                                try{
                                                    Date a=toGod(attributValue.get(fields[i].getName()));
                                                    methods[j].invoke(temp,a);
                                                }catch(Exception er){

                                                }  
                                            }else{
                                                           Attribut atter=fields[i].getAnnotation(Attribut.class);
                                                           if(atter.isforeignkey()==true){
                                                               Foreignkey fore=fields[i].getAnnotation(Foreignkey.class);
                                                               Object tempo=fields[i].getType().getDeclaredConstructor().newInstance();
                                                               Object obj=new Object();
                                                               Field[]foreign=tempo.getClass().getDeclaredFields();
                                                               
                                                               for(int k=0;k<foreign.length;k++){
                                                                   Attribut att=foreign[k].getAnnotation(Attribut.class);
                                                                   if(att.columnName().compareToIgnoreCase(fore.columnRef())==0){

                                                                          tempo.getClass().getMethod("set"+ObjectBdd.toUpperFirst(foreign[k].getName()),int.class).invoke(tempo,Integer.parseInt(attributValue.get(fields[i].getName())));
                                                            
                                                                   }
                                                               }

                                                               temp.getClass().getMethod("set"+ObjectBdd.toUpperFirst(fields[i].getName()),obj.getClass()).invoke(temp,tempo);
                                                           }
                                               }
                                       }
                           }
                           
                   }        
               }    
       }
       return temp;

    }
}
