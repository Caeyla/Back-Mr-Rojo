<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<p>${mess}</p>
	<c:forEach var="p" items="${sourceEsti}">
		
		<p>${p.ray.nom}</p>
        <p>${p.efficacite}t</p>
	</c:forEach>
	
</html>