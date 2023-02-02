package com.example.tovo.controlleur;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.*;
import com.example.tovo.modele.*;
import com.example.tovo.obj.Util;
import database.*;

import java.util.*;
import java.sql.Connection;
import java.time.*;
@Controller
public class TestController {
	//public static double prixCarburant=15000;
	// @RequestMapping(value="/index",method=RequestMethod.GET)
	// public String ray(ModelMap mod) {
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		mod.put("mess",Util.castCarre(new Carre(null,-1,-1,-1,-1,-1,-1,-1,-1,null).select(ray)));
	// 		ray.close();
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
		
	// 	return "index";
		
	// }
	// @RequestMapping(value="/sources",method=RequestMethod.GET)
	// public String folo(ModelMap mod,@RequestParam String idCarre) {
			
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		mod.put("idcarre",idCarre);
	// 		mod.put("classi", new Source().getClass().getName());
	// 		ray.close();
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
		
	// 	return "Sourceinsert";
		
	// }
	// @RequestMapping(value="/source",method=RequestMethod.GET)
	// public String roa(ModelMap mod,@RequestParam String id) {
			
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		mod.put("idcarre", id);
	// 		mod.put("mess",Util.castSource(new Source(null,-1,-1,null,-1,-1,new Carre(id,-1,-1,-1,-1,-1,-1,-1,-1,null)).select(ray)));
	// 		ray.close();
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
		
	// 	return "SourceListe";
		
	// }
	// @RequestMapping(value="/poka",method=RequestMethod.GET)
	// public String telo(ModelMap mod,@RequestParam String idSource) {
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		mod.put("listepoka", Util.castPoka(new Poka(null,null,-1,new Source(idSource,-1,-1,null,-1,-1,null)).select(ray)));
	// 		mod.put("idsource", idSource);
	// 		mod.put("classi", new Poka().getClass().getName());
	// 		ray.close();
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
		
	// 	return "Pokainsert";
		
	// }
	// @RequestMapping(value="/mitoto",method=RequestMethod.GET)
	// public String fito(ModelMap mod,@RequestParam String idSource) {
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		mod.put("listemitoto", Util.castMitoto(new Mitoto(null,-1,null,null,-1,new Source(idSource,-1,-1,null,-1,-1,null)).select(ray)));
	// 		mod.put("restePoka",new Source(idSource,-1,-1,null,-1,-1,null).restePoka());
	// 		mod.put("idsource", idSource);
	// 		mod.put("classi", new Mitoto().getClass().getName());
	// 		ray.close();
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
		
	// 	return "Mitotoinsert";
		
	// }
	// @RequestMapping(value="/centrifugeuse",method=RequestMethod.GET)
	// public String dimy(ModelMap mod,@RequestParam String idSource) {
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		mod.put("listecentrifugeuse", Util.castCentrifugeuse(new Centrifugeuse(null, -1, -1, -1, null, null, new Source(idSource,-1,-1,null,-1,-1,null)).select(ray)));
	// 		mod.put("resteToto",new Source(idSource,-1,-1,null,-1,-1,null).resteToto());
	// 		mod.put("idsource", idSource);
	// 		mod.put("classi", new Centrifugeuse().getClass().getName());
	// 		ray.close();
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
	// 	return "Centrifugeuseinsert";
		
	// }
	// @RequestMapping(value = "/tri",method=RequestMethod.GET)
    // public String listTri(ModelMap mod,@RequestParam String idCarre){
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		ArrayList<Source>sr=Util.castSource(new Source(null,-1,-1,null,-1,-1,new Carre(idCarre,-1,-1,-1,-1,-1,-1,-1,-1,null)).select(ray));
	// 		ray.close();
	// 		ArrayList<Source>vao=Carre.max(sr,3);
	// 		ArrayList<Double>va=Carre.val(vao,3);
	// 		mod.addAttribute("listval",Carre.listG(vao,va) );
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
	// 	return "tri";
	// }
	// @RequestMapping(value = "/couche1",method = RequestMethod.GET)
	// public String couche1(ModelMap mod,@RequestParam String idCarre,@RequestParam String type){
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		mod.put("carre",new Carre(idCarre,-1,-1,-1,-1,-1,-1,-1,-1,null).select(ray)[0]);
	// 		ArrayList<Source>sr=Util.castSource(new Source(null,-1,-1,null,-1,-1,new Carre(idCarre,-1,-1,-1,-1,-1,-1,-1,-1,null)).select(ray));
	// 		mod.put("type", Integer.parseInt(type));
	// 		ArrayList<Source>vaoDure=Carre.max(sr,1);
	// 		ArrayList<Double>vaDure=Carre.val(vaoDure,1);
	// 		ArrayList<Source>vaoProp=Carre.max(sr,2);
	// 		ArrayList<Double>vaProp=Carre.val(vaoProp,2);
	// 		ArrayList<Source>vaoEfficacite=Carre.max(sr,3);
	// 		ArrayList<Double>vaEfficacite=Carre.val(vaoEfficacite,3);
	// 		ArrayList<Source>vaoExploitation=Carre.max(sr,4);
	// 		ArrayList<Double>vaExploitation=Carre.val(vaoEfficacite,4);
	// 		Source worst1=vaoDure.get(vaoDure.size()-2);
	// 		Source best1=vaoDure.get(vaoDure.size()-1);
	// 		Source worst2=vaoProp.get(vaoProp.size()-2);
	// 		Source best2=vaoProp.get(vaoProp.size()-1);
	// 		Source worst3=vaoEfficacite.get(vaoEfficacite.size()-2);
	// 		Source best3=vaoEfficacite.get(vaoEfficacite.size()-1);
	// 		Source worst4=vaoExploitation.get(vaoExploitation.size()-2);
	// 		Source best4=vaoExploitation.get(vaoExploitation.size()-1);
	// 		Val[]un=Carre.listG(vaoDure,vaDure);
	// 		Val[]deux=Carre.listG(vaoProp,vaProp);
	// 		Val[]trois=Carre.listG(vaoEfficacite,vaEfficacite);
	// 		Val[]quatre=Carre.listG(vaoExploitation,vaExploitation);
	// 		mod.addAttribute("worst1",worst1);
	// 		mod.addAttribute("best1",best1 );
	// 		mod.addAttribute("worst2",worst2);
	// 		mod.addAttribute("best2",best2 );
	// 		mod.addAttribute("worst3",worst3);
	// 		mod.addAttribute("best3",best3 );
	// 		mod.addAttribute("worst4",worst4);
	// 		mod.addAttribute("best4",best4 );
	// 		mod.addAttribute("listval1",un );
	// 		mod.addAttribute("listval2",deux );
	// 		mod.addAttribute("listval3",trois );
	// 		mod.addAttribute("listval4",quatre );
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
	// 	return "couche1";
	// }
	// @RequestMapping(value = "/couche",method = RequestMethod.GET)
	// public String couche(ModelMap mod,@RequestParam String idCarre,@RequestParam String type){
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		mod.put("carre",new Carre(idCarre,-1,-1,-1,-1,-1,-1,-1,-1,null).select(ray)[0]);
	// 		ArrayList<Source>sr=Util.castSource(new Source(null,-1,-1,null,-1,-1,new Carre(idCarre,-1,-1,-1,-1,-1,-1,-1,-1,null)).select(ray));
	// 		mod.put("type", Integer.parseInt(type));
	// 		ArrayList<Source>vaoDure=Carre.max(sr, Integer.parseInt(type));
	// 		ArrayList<Double>vaDure=Carre.val(vaoDure, Integer.parseInt(type));
	// 		Source worst=vaoDure.get(vaoDure.size()-2);
	// 		Source best=vaoDure.get(vaoDure.size()-1);
	// 		mod.addAttribute("worst",worst);
	// 		mod.addAttribute("best",best );
	// 		mod.addAttribute("listval",Carre.listG(vaoDure,vaDure));
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
	// 	return "couche";
	// }
	// @RequestMapping(value = "/pageEstimation",method = RequestMethod.GET)
	// public String pageEstimation(ModelMap mod,@RequestParam String idCarre){
	// 	mod.addAttribute("idCarre", idCarre);
	// 	return "Estimation";
	// }
	// @RequestMapping(value = "/estimation",method = RequestMethod.GET)
	// public String estimation(ModelMap mod,@RequestParam String idCarre,@RequestParam String month,String prix){
	// 	try{
	// 		Connection ray=Connexion.getConnection("jdbc:postgresql://localhost:5432/minier","postgres","mahary");
	// 		ArrayList<Source>sr=Util.castSource(new Source(null,-1,-1,null,-1,-1,new Carre(idCarre,-1,-1,-1,-1,-1,-1,-1,-1,null)).select(ray));
	// 	    try{
	// 				mod.addAttribute("sourceEsti", Carre.listPossible(sr,Double.parseDouble(prix), Integer.parseInt(month)));
	// 			}catch(Exception e){
	// 				mod.addAttribute("mess", "mission impossible");
	// 			}
	// 	}catch(Exception e){
	// 		mod.put("message", e.getMessage());
	// 	}
	// 	return "Valiny";
	// }
}
