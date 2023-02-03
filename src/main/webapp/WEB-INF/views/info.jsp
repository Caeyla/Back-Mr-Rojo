<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<p>login</p>
       
            <form action="${pathContext.request.contextPath}/insertInfo" method="get">
		
            <p>finoana: <input type="text" name="finoana" ></p>
            <p>salaire: <input type="text" name="salaire" ></p>
            <p>poids: <input type="text" name="poids" ></p>
            <p>taille: <input type="text" name="taille" ></p>
            <p>couleur: <input type="text" name="couleur" ></p>
            <p>bacc: <input type="text" name="bacc" ></p>
            <p>sexe: <input type="text" name="sexe" ></p>
            <p>vie: <input type="text" name="vie" ></p>
            <p>date de naissance: <input type="datetime-local" name="dateNaissance" ></p>
            
            <p>mdp: <input type="hidden" name="user" value="${idusers.idusers}" ></p>
            <p>mdp: <input type="hidden" name="class" value="${classi}" ></p>
            <input type="submit" value="ok">
        </form>
	
</html>