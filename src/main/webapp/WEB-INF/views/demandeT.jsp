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
    <p>liste des demandes</p>
    <table border="1">
        <tr>
            <th>matricule</th>
            <th>nom du produit</th>
            <th>quantite</th>
            <th>nom de departement</th>
        </tr>
        <c:forEach var="p" items="${listedemande}">
            <tr>
                <th>${p.matricule}</th>
                <th>${p.pnom}</th>
                <th>${p.quantite}</th>
                <th>${p.deptnom}</th>
            </tr>
        </c:forEach>
        
    </table>
    <br/>
    <p>totalite:</p>
    <table border="1">
        <tr>
            <th>total</th>
            <th>matricule</th>
            <th>nom du produit</th>
        </tr>
        <c:forEach var="p" items="${totalite}">
            <tr>
                <th>${p.total}</th>
                <th>${p.matricule}</th>
                <th>${p.nom}</th>
            </tr>
        </c:forEach>
        
    </table>
</body>
</html>