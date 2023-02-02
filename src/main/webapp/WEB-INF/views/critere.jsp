<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<p>login</p>
       
            <form action="${pathContext.request.contextPath}/insertCritere" method="get">
		
            <p>finoana: <input type="text" name="minfinoana" > <input type="text" name="maxfinoana" > <input type="text" name="coefffinoana" ></p>
            <p>salaire: <input type="text" name="minsalaire" > <input type="text" name="maxsalaire" > <input type="text" name="coeffsalaire" > </p>
            <p>poids: <input type="text" name="minpoids" > <input type="text" name="maxpoids" > <input type="text" name="coeffpoids" ></p>
            <p>taille: <input type="text" name="mintaille" > <input type="text" name="maxtaille" > <input type="text" name="coefftaille" ></p>
            <p>couleur: <input type="text" name="mincouleur" > <input type="text" name="maxcouleur" > <input type="text" name="coeffcouleur" ></p>
            <p>bacc: <input type="text" name="minbacc" > <input type="text" name="maxbacc" > <input type="text" name="coeffbacc" ></p>
            <p>date de naissance: <input type="text" name="mindateNaissance" > <input type="text" name="maxdateNaissance" > <input type="text" name="coeffdateNaissance" ></p>
            <p>moyenne: <input type="text" name="moyenne" ></p>
            <p>iduser: ${idusers.idusers}</p>
            <p>mdp: <input type="hidden" name="user" value="${idusers.idusers}" ></p>
            <p>mdp: <input type="hidden" name="class" value="${classi}" ></p>
            <input type="submit" value="ok">
        </form>
	
</html>