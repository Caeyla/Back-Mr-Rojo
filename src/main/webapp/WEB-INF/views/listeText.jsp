<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <p>${message}</p>
    <p>liste des users avec leurs notes de vie</p>
    <table border="1">
        <tr>
            <th>nom</th>
            <th>moyenne</th>
           
        </tr>
        <c:forEach var="p" items="${listeUser}">
            <tr>
                <th>${p.ray.nom}</th>
                <th>${p.moyenne}</th>
            </tr>
        </c:forEach>
        
    </table>
    
</body>
</html>