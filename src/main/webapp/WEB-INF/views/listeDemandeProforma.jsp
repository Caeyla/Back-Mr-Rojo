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
    <p><a href="${pathContext.request.contextPath}/Entreprise/CreerdemandeProforma">creer nouveau demande proforma</a></p>
    <p>liste des demandes proforma en cours</p>
    <table border="1">
        <tr>
            <th>demande</th>
            <th>ajouter nouveau fournisseur</th>
            <th>ajouter nouveau fournisseur</th>
        </tr>
        <c:forEach var="p" items="${listedemandeProforma}">
            <tr>
                <th>${p.dateDemande}</th>
                <th><a href="${pathContext.request.contextPath}/Entreprise/detailFournisseur?idDemandeProforma=${p.idDemandeProforma}">addFournisseur</a></th>
                <th><a href="${pathContext.request.contextPath}/Entreprise/detailproduit?idDemandeProforma=${p.idDemandeProforma}">addProduit</a></th>
            </tr>
        </c:forEach>
        
    </table>
    
</body>
</html>