<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<p>login</p>
       
            <form action="${pathContext.request.contextPath}/updateCritere" method="get">
            
            <p>finoana: <input type="text" name="minfinoana" value="${listeCritere.get('finoana').getMin()}" > <input type="text" name="maxfinoana" value="${listeCritere.get('finoana').getMax()}"> <input type="text" name="coefffinoana" value="${listeCritere.get('finoana').getCoeff()}" ></p>
            <p>salaire: <input type="text" name="minsalaire" value="${listeCritere.get('salaire').getMin()}" > <input type="text" name="maxsalaire" value="${listeCritere.get('salaire').getMax()}"> <input type="text" name="coeffsalaire" value="${listeCritere.get('salaire').getCoeff()}"> </p>
            <p>poids: <input type="text" name="minpoids" value="${listeCritere.get('poids').getMin()}"> <input type="text" name="maxpoids" value="${listeCritere.get('poids').getMax()}"> <input type="text" name="coeffpoids" value="${listeCritere.get('poids').getCoeff()}"></p>
            <p>taille: <input type="text" name="mintaille" value="${listeCritere.get('taille').getMin()}"> <input type="text" name="maxtaille" value="${listeCritere.get('taille').getMax()}"> <input type="text" name="coefftaille" value="${listeCritere.get('taille').getCoeff()}"></p>
            <p>couleur: <input type="text" name="mincouleur" value="${listeCritere.get('couleur').getMin()}"> <input type="text" name="maxcouleur" value="${listeCritere.get('couleur').getMax()}"> <input type="text" name="coeffcouleur" value="${listeCritere.get('couleur').getCoeff()}"></p>
            <p>bacc: <input type="text" name="minbacc" value="${listeCritere.get('bacc').getMin()}"> <input type="text" name="maxbacc" value="${listeCritere.get('bacc').getMax()}"> <input type="text" name="coeffbacc" value="${listeCritere.get('bacc').getCoeff()}"></p>
            <p>date de naissance: <input type="text" name="mindateNaissance" value="${listeCritere.get('dateNaissance').getMin()}"> <input type="text" name="maxdateNaissance" value="${listeCritere.get('dateNaissance').getMax()}"> <input type="text" name="coeffdateNaissance" value="${listeCritere.get('dateNaissance').getCoeff()}"></p>
            <p>moyenne: <input type="text" name="moyenne" value="${moyenne.moyenne}"></p>
            <p>mdp: <input type="hidden" name="user" value="${idusers.idusers}" ></p>
            <p>mdp: <input type="hidden" name="class" value="${classi}" ></p>
            <input type="submit" value="ok">
        </form>
	
</html>