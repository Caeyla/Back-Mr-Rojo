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
    <p>liste des elus</p>
    <table border="1">
        <tr>
            <th>demande</th>
            <th>info</th>
            <th>degager</th>
           
        </tr>
        <c:forEach var="p" items="${listeElu}">
            <tr>
                <th>${p.nom}</th>
                <th><a href="${pathContext.request.contextPath}/detailUser?user=${idusers.idusers}&user2=${p.idusers}">consulter</a></th>
                <th><a href="${pathContext.request.contextPath}/passer?user=${idusers.idusers}&user2=${p.idusers}">passer</a></th>
            </tr>
        </c:forEach>
        
    </table>
    
</body>
</html>