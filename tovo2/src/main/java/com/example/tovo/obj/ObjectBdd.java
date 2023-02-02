package com.example.tovo.obj;
import java.sql.*;
import java.lang.reflect.*;
import java.util.Date;
import database.*;
import annotation.*;
public class ObjectBdd{
    public ObjectBdd(){

    }
    public static String toUpperFirst(String a) {
        char[] toChar = a.toCharArray();
        char[] f = new char[1];
        f[0] = toChar[0];
        String first = new String(f);
        String other;
        char[] oth = new char[toChar.length - 1];
        for (int i = 0; i < oth.length; i++) {
            oth[i] = toChar[i + 1];
        }
        other = new String(oth);
        return first.toUpperCase() + other;
    }
    public static String toLowerFirst(String a) {
        char[] toChar = a.toCharArray();
        char[] f = new char[1];
        f[0] = toChar[0];
        String first = new String(f);
        String other;
        char[] oth = new char[toChar.length - 1];
        for (int i = 0; i < oth.length; i++) {
            oth[i] = toChar[i + 1];
        }
        other = new String(oth);
        return first.toLowerCase() + other;
    }
    public void insert(Connection c,String database)throws Exception
    {
        String sequence="";
        // String id = (String) this.getClass().getMethod("getId").invoke(this);
        Field[] fields = this.getClass().getDeclaredFields();
        Method[] method = new Method[fields.length-1];
        Object[] value = new Object[fields.length-1];
        String req = "";
        int compte=0;
        NomTable nomTable=(NomTable) this.getClass().getAnnotation(NomTable.class);
        String nom=nomTable.nom();
        sequence="nextval('id"+toLowerFirst(nom)+"')";
        
        Attribut att=null;
       int countForeignkey=0;
        for(int i=0;i<fields.length;i++) {
            att=fields[i].getAnnotation(Attribut.class);
           if(att.isprimarykey()==false) 
           {
               if(att.isforeignkey()==true)
               {
                    method[compte]=this.getClass().getMethod("get"+toUpperFirst(fields[i].getName()));
                    value[compte]=method[compte].invoke(this);
                    compte++;
                    countForeignkey++;
               }else{
                    method[compte]=this.getClass().getMethod("get"+toUpperFirst(fields[i].getName()));
                    value[compte]=method[compte].invoke(this);
                    compte++;
               }
                

           }
        }
         int[]fkey=null;
        if(countForeignkey>0){
            fkey=new int[countForeignkey];
            int count=0;
            for(int i=0;i<fields.length;i++) {
                att=fields[i].getAnnotation(Attribut.class);
                if (att.isprimarykey()==false) 
                {
                    if(att.isforeignkey()==true){
                            fkey[count]=i;
                            count++;
                    }
                }
            }
        }
        
            if(fields.length==1) {
                req = "INSERT INTO "+nom+" VALUES ("+sequence+", ";
                if (value[0]!=null) 
                {
                    //String[] string = value[0].toString().split(" ");
                    if(value[0] instanceof Number) {
                    req += value[0]+")";
                    }else if(value[0] instanceof java.sql.Timestamp){
                        int jour = ((java.sql.Timestamp) value[0]).getDate();
                        int mois = ((java.sql.Timestamp) value[0]).getMonth()+1;
                        int annee = ((java.sql.Timestamp) value[0]).getYear()+1900;
                        int hours=((java.sql.Timestamp) value[0]).getHours();
                        int minutes=((java.sql.Timestamp) value[0]).getMinutes();
                        int secondes=((java.sql.Timestamp) value[0]).getSeconds();
                        req += "TO_TIMESTAMP('"+jour+"-"+mois+"-"+annee+" "+hours+":"+minutes+":"+secondes+"','DD-MM-YYYY HH24:MI:SS'))";

                    }
                    else if(value[0] instanceof java.util.Date) {
                        int jour = ((java.util.Date) value[0]).getDate();
                        int mois = ((java.util.Date) value[0]).getMonth()+1;
                        int annee = ((java.util.Date) value[0]).getYear()+1900;
                        req += "TO_DATE('"+jour+"-"+mois+"-"+annee+"','DD-MM-YYYY'),";
                    }
                    else {
                     req += "'"+value[0]+"')";
                    }    
                }
            }
            else {
                req = "INSERT INTO "+nomTable.nom()+" VALUES ("+sequence+", ";
                for(int i=0;i<value.length;i++) {
                    if(i<value.length-1) {
                       
                               if (value[i]!=null) 
                               {
                                    String[] string = value[i].toString().split(" ");
                                     if(value[i] instanceof Number) {
                                        req += value[i]+",";
                                    }else if(value[i] instanceof java.sql.Timestamp){
                                        System.out.println("zsdfghjklm");
                                        int jour = ((java.sql.Timestamp) value[i]).getDate();
                                        int mois = ((java.sql.Timestamp) value[i]).getMonth()+1;
                                        int annee = ((java.sql.Timestamp) value[i]).getYear()+1900;
                                        int hours=((java.sql.Timestamp) value[i]).getHours();
                                        int minutes=((java.sql.Timestamp) value[i]).getMinutes();
                                        int secondes=((java.sql.Timestamp) value[i]).getSeconds();
                                        req += "TO_TIMESTAMP('"+jour+"-"+mois+"-"+annee+" "+hours+":"+minutes+":"+secondes+"','DD-MM-YYYY HH24:MI:SS'),";
                
                                    }
                                    else if(value[i] instanceof java.util.Date) {
                                        int jour = ((java.util.Date) value[i]).getDate();
                                        int mois = ((java.util.Date) value[i]).getMonth()+1;
                                        int annee = ((java.util.Date) value[i]).getYear()+1900;
                                        req += "TO_DATE('"+jour+"-"+mois+"-"+annee+"','DD-MM-YYYY'),";
                                    }
                                    else if(value[i] instanceof String){
                                        req += "'"+value[i]+"',";
                                    }
                                    else {
                                        if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                System.out.println("tay");
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     int val=-1;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(int)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                    if(val!=-1){
                                                         req += ""+val+",";
                                                    }else{
                                                        throw new Exception("manque de foreign key");
                                                    }
                                                }
                                            }
                                        }
                                    }    
                               }else{
                                req+="null,";
                               }
                        
                            
                        
                    }
                    else {
                        if (value[i]!=null) 
                        {
                                String[] string = value[i].toString().split(" ");
                                if(value[i] instanceof Number) {
                                    req += value[i]+")";
                                 
                                }else if(value[i] instanceof java.sql.Timestamp){
                                    int jour = ((java.sql.Timestamp) value[i]).getDate();
                                    int mois = ((java.sql.Timestamp) value[i]).getMonth()+1;
                                    int annee = ((java.sql.Timestamp) value[i]).getYear()+1900;
                                    int hours=((java.sql.Timestamp) value[i]).getHours();
                                    int minutes=((java.sql.Timestamp) value[i]).getMinutes();
                                    int secondes=((java.sql.Timestamp) value[i]).getSeconds();
                                    req += "TO_TIMESTAMP('"+jour+"-"+mois+"-"+annee+" "+hours+":"+minutes+":"+secondes+"','DD-MM-YYYY HH24:MI:SS'))";
            
                                }
                                else if(value[i] instanceof java.util.Date) {
                                    int jour = ((java.util.Date) value[i]).getDate();
                                    int mois = ((java.util.Date) value[i]).getMonth()+1;
                                    int annee = ((java.util.Date) value[i]).getYear()+1900;
                                    req += "TO_DATE('"+jour+"-"+mois+"-"+annee+"','DD-MM-YYYY'))";
                                }
                                else if(value[i] instanceof String){
                                        req += "'"+value[i]+"')";
                                }
                                else {
                                    if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                    Field[]foreign=value[i].getClass().getDeclaredFields();
                                                    int val=-1;
                                                                   for(int k=0;k<foreign.length;k++){
                                                                       Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                       if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                           Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                           val=(int)met.invoke(value[i]);
                                                                       }
                                                                   }
                                                    if(val!=-1){
                                                         req += ""+val+")";
                                                    }else{
                                                        throw new Exception("manque de foreign key");
                                                    }
                                                    
                                                }
                                            }
                                        }
                                }    
                        }else{
                            req += "null)";
                        }
                    }
                }
            }
        
        System.out.println(req);
        PreparedStatement stmt = c.prepareStatement(req);
        stmt.executeUpdate();
        c.commit();
        stmt.close();

        
    }
    public  void update(Connection c)throws Exception
    {
        Field[] fields = this.getClass().getDeclaredFields();
        Method[] method = new Method[fields.length];
        Object[] value = new Object[fields.length];
        NomTable nomTable=(NomTable)this.getClass().getAnnotation(NomTable.class);
        Attribut att=null;
        int countForeignkey=0;
        for(int i=0;i<fields.length;i++) {
            att=fields[i].getAnnotation(Attribut.class);
             if(att.isforeignkey()==true){
                countForeignkey++;
             }
            method[i] = this.getClass().getMethod("get"+toUpperFirst(fields[i].getName()));
            value[i] = method[i].invoke(this);
        }
        int[]fkey=null;
        if(countForeignkey>0){
            fkey=new int[countForeignkey];
            int count=0;
            for(int i=0;i<fields.length;i++) {
                    att=fields[i].getAnnotation(Attribut.class);
                    if(att.isforeignkey()==true){
                            fkey[count]=i;
                            count++;
                    }
            }
        }
        String req = "UPDATE "+nomTable.nom()+" SET ";
        for(int i=1;i<value.length;i++) {
            Attribut atte=fields[i].getAnnotation(Attribut.class);
            if(i<value.length-1) {
               if (value[i]!=null) 
               {
                     if(value[i] instanceof Number) {
                            req += atte.columnName()+" = "+value[i]+", ";
                        }else if(value[i] instanceof Timestamp){
                            int jour = ((java.sql.Timestamp) value[i]).getDate();
                            int mois = ((java.sql.Timestamp) value[i]).getMonth()+1;
                            int annee = ((java.sql.Timestamp) value[i]).getYear()+1900;
                            int hours=((java.sql.Timestamp) value[i]).getHours();
                            int minutes=((java.sql.Timestamp) value[i]).getMinutes();
                            int secondes=((java.sql.Timestamp) value[i]).getSeconds();
                            req +=atte.columnName()+"=TO_TIMESTAMP('"+jour+"-"+mois+"-"+annee+" "+hours+":"+minutes+":"+secondes+"','DD-MM-YYYY HH24:MI:SS'),";
    
                        }
                        else if(value[i] instanceof java.util.Date) {
                            int jour = ((java.util.Date) value[i]).getDate();
                            int mois = ((java.util.Date) value[i]).getMonth()+1;
                            int annee = ((java.util.Date) value[i]).getYear()+1900;
                            req += atte.columnName()+"=TO_DATE('"+jour+"-"+mois+"-"+annee+"','DD-MM-YYYY'),";
                        }
                        else if(value[i] instanceof String){
                            req += atte.columnName()+" = '"+value[i]+"', ";
                        }else{
                            if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     int  val=-1;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(int)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                    if(val!=-1){
                                                        req += atter.columnName()+" = "+val+", ";
                                                    }else{
                                                        throw new Exception("manque de foreign key");
                                                    }
                                                    
                                                }
                                            }
                                        }
                        }    
               }else{
                     req += "null,";
               }
            }
            else {
                if (value[i]!=null) 
                {
                    if(value[i] instanceof Number) {
                    req += atte.columnName()+" = "+value[i];
                    }else if(value[i] instanceof Timestamp){
                        int jour = ((java.sql.Timestamp) value[i]).getDate();
                        int mois = ((java.sql.Timestamp) value[i]).getMonth()+1;
                        int annee = ((java.sql.Timestamp) value[i]).getYear()+1900;
                        int hours=((java.sql.Timestamp) value[i]).getHours();
                        int minutes=((java.sql.Timestamp) value[i]).getMinutes();
                        int secondes=((java.sql.Timestamp) value[i]).getSeconds();
                        req +=atte.columnName()+"=TO_TIMESTAMP('"+jour+"-"+mois+"-"+annee+" "+hours+":"+minutes+":"+secondes+"','DD-MM-YYYY HH24:MI:SS')";

                    }
                    else if(value[i] instanceof java.util.Date) {
                        int jour = ((java.util.Date) value[i]).getDate();
                        int mois = ((java.util.Date) value[i]).getMonth()+1;
                        int annee = ((java.util.Date) value[i]).getYear()+1900;
                        req += atte.columnName()+"=TO_DATE('"+jour+"-"+mois+"-"+annee+"','DD-MM-YYYY')";
                    }
                    else if(value[i] instanceof String){
                        req += atte.columnName()+" = '"+value[i]+"' ";
                    }
                    else {
                        if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     int val=-1;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(int)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                     if(val!=-1){
                                                       req += atter.columnName()+" = "+val+" ";
                                                    }else{
                                                        throw new Exception("manque de foreign key");
                                                    }
                                                    
                                                }
                                            }
                                        }
                    }    
                }else{
                    req += atte.columnName()+" = "+"null";
                }
            }
        }
        for(int i=0;i<fields.length;i++) {
            att=fields[i].getAnnotation(Attribut.class);
           if (att.isprimarykey()==true) 
           {
                req += " WHERE "+att.columnName()+" = ";
                if(value[i] instanceof Number) {
                    req += value[i];
                }
                else {
                    req += " '"+value[i]+"' ";
                }
           }
        }
        
        System.out.println(req);
        PreparedStatement stmt = c.prepareStatement(req);
        stmt.executeUpdate();
        c.commit();
        stmt.close();
    }
    public  Object[] select(Connection c)throws Exception{
            
        Field[] fields = this.getClass().getDeclaredFields();
        Method[] method = new Method[fields.length];
        Object[] value = new Object[fields.length];
        NomTable nomTable=(NomTable)this.getClass().getAnnotation(NomTable.class);
        Attribut att=null;
        int countForeignkey=0;
        for(int i=0;i<fields.length;i++) {
            att=fields[i].getAnnotation(Attribut.class);
             if(att.isforeignkey()==true){
                countForeignkey++;
             }
            method[i] = this.getClass().getMethod("get"+toUpperFirst(fields[i].getName()));
            value[i] = method[i].invoke(this);
        }
        int[]fkey=null;
        if(countForeignkey>0){
            fkey=new int[countForeignkey];
            int count=0;
            for(int i=0;i<fields.length;i++) {
                    att=fields[i].getAnnotation(Attribut.class);
                    if(att.isforeignkey()==true){
                            fkey[count]=i;
                            count++;
                    }
            }
        }
        for(int i=0;i<fields.length;i++) {
            method[i] = this.getClass().getMethod("get"+toUpperFirst(fields[i].getName()));
            value[i] = method[i].invoke(this);
        }
        String req = "SELECT * FROM "+nomTable.nom();
        boolean setWhere = false;
        for(int i=0;i<value.length;i++) {
            if(i==0) {
                Attribut atte=fields[i].getAnnotation(Attribut.class);
                if(value[i] instanceof Number) {
                    if(((Number)value[i]).doubleValue()>=-2001) {
                        
                        req += " WHERE "+atte.columnName()+"="+value[i];
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else if(value[i] instanceof Timestamp){
                    if(value[i]!=null) {
                        req += " WHERE "+atte.columnName()+" = TO_TIMESTAMP('"+((java.sql.Timestamp) value[i]).getDate()+"-"+(((java.sql.Timestamp) value[i]).getMonth()+1)+"-"+(((java.sql.Timestamp) value[i]).getYear()+1900)+" "+(((java.sql.Timestamp) value[i]).getHours())+":"+(((java.sql.Timestamp) value[i]).getMinutes())+":"+(((java.sql.Timestamp) value[i]).getSeconds())+"','DD-MM-YYYY HH24:MI:SS')";
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else if(value[i] instanceof java.util.Date) {
                    if(value[i]!=null) {
                        req += " WHERE "+atte.columnName()+" = TO_DATE('"+((java.util.Date) value[i]).getDate()+"-"+(((java.util.Date) value[i]).getMonth()+1)+"-"+(((java.util.Date) value[i]).getYear()+1900)+"','DD-MM-YYYY')";
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else {
                    if(value[i]!=null) {
                        if(value[i] instanceof String){
                                if (value[i].toString().contains("%")==false) {
                                    req += " WHERE "+atte.columnName()+"='"+value[i]+"' ";
                                }else{
                                    req += " WHERE "+atte.columnName()+" like '"+value[i]+"' ";
                                }
                        }else{
                            if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     int val=-1;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(int)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                     if(val!=-1){
                                                        req += " WHERE "+atter.columnName()+"="+val+" ";
                                                     }else{
                                                        req += " WHERE 1=1 ";
                                                     }
                                                }
                                            }
                                        }
                        }
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
            }
            if(i<value.length-1 && i>0) {
               Attribut atte=fields[i].getAnnotation(Attribut.class);
                if(value[i] instanceof Number) {
                    if(((Number)value[i]).doubleValue()>=-2001) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+"="+value[i];
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }else if(value[i] instanceof Timestamp) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+" = TO_TIMESTAMP('"+((java.sql.Timestamp) value[i]).getDate()+"-"+(((java.sql.Timestamp) value[i]).getMonth()+1)+"-"+(((java.sql.Timestamp) value[i]).getYear()+1900)+" "+(((java.sql.Timestamp) value[i]).getHours())+":"+(((java.sql.Timestamp) value[i]).getMinutes())+":"+(((java.sql.Timestamp) value[i]).getSeconds())+"','DD-MM-YYYY HH24:MI:SS')";
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else if(value[i] instanceof java.util.Date) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+" = TO_DATE('"+((java.util.Date) value[i]).getDate()+"-"+(((java.util.Date) value[i]).getMonth()+1)+"-"+(((java.util.Date) value[i]).getYear()+1900)+"','DD-MM-YYYY')";
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        if(value[i] instanceof String){
                            if(value[i].toString().contains("%")==false){
                                req += atte.columnName()+"='"+value[i]+"' ";    
                            }else{
                                req += atte.columnName()+" like '"+value[i]+"' ";
                            } 
                        }else{
                             if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     int val=-1;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(int)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                     if(val!=-1){
                                                        req +=atter.columnName()+"="+val+" ";
                                                     }else{
                                                        req +="1=1 ";
                                                     }
                                                }
                                            }
                                        }
                        }
                        
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
            }
            if(i==value.length-1) {
                Attribut atte=fields[i].getAnnotation(Attribut.class);
                if(value[i] instanceof Number) {
                    if(((Number)value[i]).doubleValue()>=-2001) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+"="+value[i];
                    }
                }
                else if(value[i] instanceof Timestamp) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+" = TO_TIMESTAMP('"+((java.sql.Timestamp) value[i]).getDate()+"-"+(((java.sql.Timestamp) value[i]).getMonth()+1)+"-"+(((java.sql.Timestamp) value[i]).getYear()+1900)+" "+(((java.sql.Timestamp) value[i]).getHours())+":"+(((java.sql.Timestamp) value[i]).getMinutes())+":"+(((java.sql.Timestamp) value[i]).getSeconds())+"','DD-MM-YYYY HH24:MI:SS')";
                    }
                }
                else if(value[i] instanceof java.util.Date) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+"  = TO_DATE('"+((java.util.Date) value[i]).getDate()+"-"+(((java.util.Date) value[i]).getMonth()+1)+"-"+(((java.util.Date) value[i]).getYear()+1900)+"','DD-MM-YYYY')";
                    }
                }
                
                else {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        if(value[i] instanceof String){
                                if (value[i].toString().contains("%")==false) {
                                req += atte.columnName()+"='"+value[i]+"'";
                            }else{
                                req += atte.columnName()+" like '"+value[i]+"'";
                            }
                        }else{
                            if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     int val=-1;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(int)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                                    if(val!=-1){
                                                                        req +=atter.columnName()+"="+val+" ";
                                                                     }else{
                                                                        req +="1=1 ";
                                                                     }
                                                }
                                            }
                                        }
                        }
                    }
                }
            }
        }
        System.out.println(req);
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        int nb = 0;
        while(res.next()) {
            nb++;
        }
        //System.out.println("fa maninona"+nb);
        Object[] retour = new Object[nb];
        ResultSet res2 = stmt.executeQuery(req);
        int indice = 0;
        int id = 1;
        while(res2.next()) {
            retour[indice] = this.getClass().getDeclaredConstructor().newInstance();
            for(int i=0;i<method.length;i++) {
                Class[] classe = new Class[1];
                classe[0] = fields[i].getType();
                Attribut roa=fields[i].getAnnotation(Attribut.class);
                if(classe[0].getSimpleName().equals("int")){
                    this.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),classe).invoke(retour[indice], res2.getInt(id));
                }else if(classe[0].getSimpleName().equals("double")){
                     this.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),classe).invoke(retour[indice], res2.getDouble(id));
                }else if(classe[0].getSimpleName().equals("String")){
                    this.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),classe).invoke(retour[indice], res2.getString(id));
                }else if(classe[0].getSimpleName().equals("Timestamp")){
                    this.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),classe).invoke(retour[indice], res2.getTimestamp(id));
                }else if(classe[0].getSimpleName().equals("Date")){
                     this.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),classe).invoke(retour[indice], res2.getDate(id));
                }else{
                    if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(fields[i].getType().getSimpleName())==0){
                                                    Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                    Object temp=fields[fkey[j]].getType().getDeclaredConstructor().newInstance();
                                                    String exemple="mechant";
                                                    Object obj=new Object();
                                                    Field[]foreign=temp.getClass().getDeclaredFields();
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                               temp.getClass().getMethod("set"+toUpperFirst(foreign[k].getName()),int.class).invoke(temp,res2.getInt(id));
                                                                        }
                                                                    }
                                                    this.getClass().getMethod("set"+toUpperFirst(fields[i].getName()),obj.getClass()).invoke(retour[indice],temp);
                                                }
                                            }
                                        }
                }
                id++;
            }
            id=1;
            indice++;
        }
        stmt.close();
        res.close();
        res2.close();
        return retour;
    }
    public  void delete(Connection c)throws Exception{
        Field[] fields = this.getClass().getDeclaredFields();
        Method[] method = new Method[fields.length];
        Object[] value = new Object[fields.length];
        NomTable nomTable=(NomTable)this.getClass().getAnnotation(NomTable.class);
        Attribut att=null;
        int countForeignkey=0;
        for(int i=0;i<fields.length;i++) {
            att=fields[i].getAnnotation(Attribut.class);
             if(att.isforeignkey()==true){
                countForeignkey++;
             }
            method[i] = this.getClass().getMethod("get"+toUpperFirst(fields[i].getName()));
            value[i] = method[i].invoke(this);
        }
        int[]fkey=null;
        if(countForeignkey>0){
            fkey=new int[countForeignkey];
            int count=0;
            for(int i=0;i<fields.length;i++) {
                    att=fields[i].getAnnotation(Attribut.class);
                    if(att.isforeignkey()==true){
                            fkey[count]=i;
                            count++;
                    }
            }
        }
        for(int i=0;i<fields.length;i++) {
            method[i] = this.getClass().getMethod("get"+toUpperFirst(fields[i].getName()));
            value[i] = method[i].invoke(this);
        }
        String req = "DELETE FROM "+nomTable.nom();
        boolean setWhere = false;
        for(int i=0;i<value.length;i++) {
            if(i==0) {
                Attribut atte=fields[i].getAnnotation(Attribut.class);
                if(value[i] instanceof Number) {
                    if(((Number)value[i]).doubleValue()>=-2001) {
                        
                        req += " WHERE "+atte.columnName()+"="+value[i];
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else if(value[i] instanceof Timestamp){
                    if(value[i]!=null) {
                        req += " WHERE "+atte.columnName()+" = TO_TIMESTAMP('"+((java.sql.Timestamp) value[i]).getDate()+"-"+(((java.sql.Timestamp) value[i]).getMonth()+1)+"-"+(((java.sql.Timestamp) value[i]).getYear()+1900)+" "+(((java.sql.Timestamp) value[i]).getHours())+":"+(((java.sql.Timestamp) value[i]).getMinutes())+":"+(((java.sql.Timestamp) value[i]).getSeconds())+"','DD-MM-YYYY HH24:MI:SS')";
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else if(value[i] instanceof java.util.Date) {
                    if(value[i]!=null) {
                        req += " WHERE "+atte.columnName()+" = TO_DATE('"+((java.util.Date) value[i]).getDate()+"-"+(((java.util.Date) value[i]).getMonth()+1)+"-"+(((java.util.Date) value[i]).getYear()+1900)+"','DD-MM-YYYY')";
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else {
                    if(value[i]!=null) {
                        if(value[i] instanceof String){
                                if (value[i].toString().contains("%")==false) {
                                    req += " WHERE "+atte.columnName()+"='"+value[i]+"' ";
                                }else{
                                    req += " WHERE "+atte.columnName()+" like '"+value[i]+"' ";
                                }
                        }else{
                            if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     int val=-1;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(int)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                     if(val!=-1){
                                                        req += " WHERE "+atter.columnName()+"="+val+" ";
                                                     }else{
                                                        req += " WHERE 1=1 ";
                                                     }
                                                }
                                            }
                                        }
                        }
                        setWhere = true;
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
            }
            if(i<value.length-1 && i>0) {
               Attribut atte=fields[i].getAnnotation(Attribut.class);
                if(value[i] instanceof Number) {
                    if(((Number)value[i]).doubleValue()>=-2001) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+"="+value[i];
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }else if(value[i] instanceof Timestamp) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+" = TO_TIMESTAMP('"+((java.sql.Timestamp) value[i]).getDate()+"-"+(((java.sql.Timestamp) value[i]).getMonth()+1)+"-"+(((java.sql.Timestamp) value[i]).getYear()+1900)+" "+(((java.sql.Timestamp) value[i]).getHours())+":"+(((java.sql.Timestamp) value[i]).getMinutes())+":"+(((java.sql.Timestamp) value[i]).getSeconds())+"','DD-MM-YYYY HH24:MI:SS')";
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else if(value[i] instanceof java.util.Date) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+" = TO_DATE('"+((java.util.Date) value[i]).getDate()+"-"+(((java.util.Date) value[i]).getMonth()+1)+"-"+(((java.util.Date) value[i]).getYear()+1900)+"','DD-MM-YYYY')";
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
                else {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        if(value[i] instanceof String){
                            if(value[i].toString().contains("%")==false){
                                req += atte.columnName()+"='"+value[i]+"' ";    
                            }else{
                                req += atte.columnName()+" like '"+value[i]+"' ";
                            } 
                        }else{
                             if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     int val=-1;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(int)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                     if(val!=-1){
                                                        req +=atter.columnName()+"="+val+" ";
                                                     }else{
                                                        req +="1=1 ";
                                                     }
                                                }
                                            }
                                        }
                        }
                        
                        if(value[i+1] instanceof Number) {
                            if(((Number)value[i+1]).doubleValue()>=-2001) req += " AND ";
                        }
                        else {
                            if(value[i+1]!=null) req += " AND ";
                        }
                    }
                }
            }
            if(i==value.length-1) {
                Attribut atte=fields[i].getAnnotation(Attribut.class);
                if(value[i] instanceof Number) {
                    if(((Number)value[i]).doubleValue()>=-2001) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+"="+value[i];
                    }
                }
                else if(value[i] instanceof Timestamp) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+" = TO_TIMESTAMP('"+((java.sql.Timestamp) value[i]).getDate()+"-"+(((java.sql.Timestamp) value[i]).getMonth()+1)+"-"+(((java.sql.Timestamp) value[i]).getYear()+1900)+" "+(((java.sql.Timestamp) value[i]).getHours())+":"+(((java.sql.Timestamp) value[i]).getMinutes())+":"+(((java.sql.Timestamp) value[i]).getSeconds())+"','DD-MM-YYYY HH24:MI:SS')";
                    }
                }
                else if(value[i] instanceof java.util.Date) {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        req += atte.columnName()+"  = TO_DATE('"+((java.util.Date) value[i]).getDate()+"-"+(((java.util.Date) value[i]).getMonth()+1)+"-"+(((java.util.Date) value[i]).getYear()+1900)+"','DD-MM-YYYY')";
                    }
                }
                
                else {
                    if(value[i]!=null) {
                        int count = 0;
                        for(int j=0;j<=i-1;j++) {
                            if(value[j] instanceof Number) {
                                if((((Number)value[j]).doubleValue())>=-2001) count++;
                            }
                            else {
                                if(value[j]!=null) count++;
                            }
                        }
                        if(count==0 && setWhere==false) req += " WHERE ";
                        setWhere = true;
                        if(count>0) {
                            if(value[i-1] instanceof Number) {
                                if(((Number)value[i-1]).doubleValue()<-2001) req += " AND ";
                            }
                            else {
                                if(value[i-1]==null) req += " AND ";
                            }
                        }
                        if(value[i] instanceof String){
                                if (value[i].toString().contains("%")==false) {
                                req += atte.columnName()+"='"+value[i]+"'";
                            }else{
                                req += atte.columnName()+" like '"+value[i]+"'";
                            }
                        }else{
                            if(countForeignkey>0){
                                            for(int j=0;j<fkey.length;j++){
                                                if(fields[fkey[j]].getType().getSimpleName().compareToIgnoreCase(value[i].getClass().getSimpleName())==0){
                                                    Attribut atter=fields[fkey[j]].getAnnotation(Attribut.class);
                                                     Foreignkey fore=fields[fkey[j]].getAnnotation(Foreignkey.class);
                                                     Field[]foreign=value[i].getClass().getDeclaredFields();
                                                     int val=-1;
                                                                    for(int k=0;k<foreign.length;k++){
                                                                        Attribut attri=foreign[k].getAnnotation(Attribut.class);
                                                                        if(attri.columnName().compareToIgnoreCase(fore.columnRef())==0){
                                                                            Method met=value[i].getClass().getMethod("get"+toUpperFirst(foreign[k].getName()));
                                                                            val=(int)met.invoke(value[i]);
                                                                        }
                                                                    }
                                                                    if(val!=-1){
                                                                        req +=atter.columnName()+"="+val+" ";
                                                                     }else{
                                                                        req +="1=1 ";
                                                                     }
                                                }
                                            }
                                        }
                        }
                    }
                }
            }
        }
        PreparedStatement stmt = c.prepareStatement(req);
        stmt.executeUpdate();
        c.commit();
    }
}