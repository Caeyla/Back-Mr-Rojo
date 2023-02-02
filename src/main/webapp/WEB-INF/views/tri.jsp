<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <p>${messa}</p>
    <p>${message}</p>
    <table border="1">
        <tr>
        <th>nom</th>
        <th>efficacite</th>
        </tr>
        
            <c:forEach var="p" items="${listval}">
            <tr>
                <td>${p.ray.nom}</td>
                <td>${p.efficacite}g/j</td>
            </tr>
	        </c:forEach>
    </table>
</html>