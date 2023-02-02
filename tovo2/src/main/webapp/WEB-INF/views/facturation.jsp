<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<p>login</p>
       
            <form action="${pathContext.request.contextPath}/insererFacture" method="get">
            <p>categorie: 
                
                    <c:forEach var="p" items="${listeCategorie}">
                        <p>${p.intitule}: <input type="text" name="" ></p>
                        <option value="${p.idcategorie}">${p.intitule}</option>
                    </c:forEach>
                
            </p>

            <input type="submit" value="ok">
        </form>
	
</html>