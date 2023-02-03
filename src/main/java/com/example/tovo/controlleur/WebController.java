package com.example.tovo.controlleur;
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

import com.example.tovo.modele.*;
import com.example.tovo.database.*;
import com.example.tovo.obj.*;
@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
//web service
public class WebController {
    @PostMapping("/Admin")
    public HashMap<String,Object> connexion(@RequestBody Admin ray){
        HashMap<String,Object>mp=new HashMap<>();
       
            try{
                ray.setIdadmin(-2002);
                if(ray.user()!=null){
                    mp.put("data",ray.user());
                }else{
                    mp.put("error",new Exception("ts mety"));
                }
            }catch(Exception e){
                mp.put("error",new Exception(e));
            }
                
            
                
            return mp;
        
    }
    @PostMapping("/User")
    public HashMap<String,Object> connexionUser(@RequestBody User ray){
        HashMap<String,Object>mp=new HashMap<>();
       
            try{
                ray.setIduser(-2002);
                if(ray.user()!=null){
                    mp.put("data",ray.user());
                }else{
                    mp.put("error",new Exception("ts mety"));
                }
            }catch(Exception e){
                mp.put("error",new Exception(e));
            }
                
            
                
            return mp;
        
    }
    @PostMapping("/Categorie")
    public HashMap<String,Object> categ(@RequestBody Categorie ray){
        HashMap<String,Object>mp=new HashMap<>();
       
            try{
                ray.setIdcategorie(-2002);
                ray.insert();
                mp.put("data","success");
            }catch(Exception e){
                mp.put("error",new Exception(e));
            }
                
            
                
            return mp;
        
    }
    @PostMapping("/User")
    public HashMap<String,Object> inscription(@RequestBody User ray){
        HashMap<String,Object>mp=new HashMap<>();
       
            try{
                ray.setIduser(-2002);
                ray.insert();
                mp.put("data","success");
            }catch(Exception e){
                mp.put("error",new Exception(e));
            }
                
            
                
            return mp;
        
    }
    @PostMapping("/Enchere")
    public HashMap<String,Object> insertionEnchere(@RequestBody Enchere ray){
        HashMap<String,Object>mp=new HashMap<>();
       
            try{
                ray.setIduser(-2002);
                ray.insert();
                mp.put("data","success");
            }catch(Exception e){
                mp.put("error",new Exception(e));
            }
                
            
                
            return mp;
        
    }
    @GetMapping("/Enchere")
    public HashMap<String,Object> lsiteEnchere(){
        HashMap<String,Object>mp=new HashMap<>();
       
            try{
                
                mp.put("data",Enchere.EnchereNonfini());
            }catch(Exception e){
                mp.put("error",new Exception(e));
            }
                
            
                
            return mp;
        
    }
    @PostMapping("/Compte")
    public HashMap<String,Object> rechargerCompte(@RequestBody Compte ray){
        HashMap<String,Object>mp=new HashMap<>();
       
            try{
                ray.setIdcompte(-2002);
                double mont=ray.getMontant();
                ray.setMontant(-2002);
                if(ray.user()!=null){
                    Compte vao2=ray.user();
                    mont=mont+vao2.getMontant();
                    vao2.setMontant(mont);
                        vao2.update();

                }
                mp.put("data",Enchere.EnchereNonfini());
            }catch(Exception e){
                mp.put("error",new Exception(e));
            }
                
            
                
            return mp;
        
    }
    @GetMapping("/Participation/{id}")
    public HashMap<String,Object> listeParticipation(@PathVariable int id){
        HashMap<String,Object>mp=new HashMap<>();
       
            try{
                
                mp.put("data",Participation.getAll(id));
            }catch(Exception e){
                mp.put("error",new Exception(e));
            }
                
            
                
            return mp;
        
    }
    // @PostMapping("/inscription")
    // public Users inscription(Users ray){
    //     try
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
