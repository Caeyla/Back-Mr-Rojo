package com.example.enchere.controlleur;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.enchere.modele.*;
import com.example.enchere.database.*;
import com.example.enchere.obj.*;
@RestController
//web service
public class WebController {
    @PostMapping("connexion")
    public HashMap<String,Object> connexion(@RequestBody Admin ray){
        HashMap<String,Object>mp=new HashMap<>();
        try{
            ray.setIdadmin(-2002);
            mp.put("tay",ray);
            return mp;
        }catch(Exception e){
            mp.put("error",new Exception("error"));
            return new Admin(-2002, null, null);
        }
    }
    @GetMapping("connexion")
    public String conne(){
            return "hello+";
    }
    // @PostMapping("/inscription")
    // public Users inscription(Users ray){
    //     try{
    //         ray.insert();
    //     }catch(Exception e){
            
    //     }
    //     Connection conn=null;
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //         Object[]tot=new Users(-2002,null,null).select(conn);
	// 		Users hafa=(Users)(tot[tot.length-1]);
    //         // System.out.println(ray.getIdusers());
    //             return hafa;
    //     }catch(Exception e){
    //         return new Users(-2002,null,null);
    //     }
        
    // }
    // @PostMapping("/info")
    // public  Users info(InfoUser inf){
    //     try{
    //         inf.insert();
    //     }catch(Exception e){
            
    //     }
    //     Connection conn=null;
    //     try{
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //         conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //         Object[]tot=new Users(inf.getUser().getIdusers(),null,null).select(conn);
	// 		Users ray=(Users)(tot[0]);
    //         System.out.println(ray.getIdusers());
    //         return ray;
    //     }catch(Exception e){
    //         return new Users(-2002,null,null);
    //     }
        
    // }
    // @PostMapping("/critere")
    // public  String crit(Critere inf){
    //     Connection conn=null;
    //     try{
    //             conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //         inf.insert(conn,"postgres");
    //         return "success";
    //     }catch(Exception e){
    //         return "failed";
    //     }

        
    // }
    // @PostMapping("/moyenne")
    // public  String moyenne(Moyenne inf){
    //     Connection conn=null;
    //     try{
    //             conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //         inf.insert(conn,"postgres");
    //         return "success";
    //     }catch(Exception e){
    //         return "failed";
    //     }

        
    // }
    // @PostMapping("/critereUpdate")
    // public  String critUpdate(Critere inf){
    //     Connection conn=null;
    //     try{
    //             conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //             inf.update(conn);
    //         return "success";
    //     }catch(Exception e){
    //         return "failed";
    //     }

        
    // }
    // @PostMapping("/moyenneUpdate")
    // public  String moyenneUpdate(Moyenne inf){
    //     Connection conn=null;
    //     try{
    //             conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
    //             inf.update(conn);
    //         return "success";
    //     }catch(Exception e){
    //         return "failed";
    //     }

        
    // }
    // @PostMapping("/elu/{id}")
    // public ArrayList<Users> elu(@PathVariable int id){
    //     Connection conn=null;
	// 	try{
	// 		conn=Connexion.getConnection("jdbc:postgresql://localhost:5432/rencontre","postgres","mahary");
	// 		Users user=(Users)(new Users(id,null,null).select(conn)[0]);
	// 	    return user.nyTenaIzy();
	// 	}catch(Exception e){
	// 		return new ArrayList<Users>();
	// 	}
    // }
   
}
