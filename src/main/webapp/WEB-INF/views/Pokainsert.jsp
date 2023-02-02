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
    <table border="1">
        <tr>
            <th>napohana</th>
            <th>quantite</th>
        </tr>
        <c:forEach var="p" items="${listepoka}">
            <tr>
                <td>${p.napohana}</td>
                <td>${p.quantite}T</td>
            </tr>
        </c:forEach>
        
    </table>
<form action="/insert" method="get">
<p><input name="napohana" type="datetime-local">datep</p>
<p><input name="source" value="${idsource}" type="hidden"/></p>
<p><input name="quantite">quantite</p>
<p><input name="class" value="${classi}" type="hidden" type="text"/></p>
<p><input type="submit" value="ok"></p>
</form>
</body>
</html>