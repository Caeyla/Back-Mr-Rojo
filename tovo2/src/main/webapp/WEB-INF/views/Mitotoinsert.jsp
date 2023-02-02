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
    <p>reste ao @ vato napoaka:${restePoka}</p>
    <table border="1">
        <tr>
            <th>datedebut</th>
            <th>datefin</th>
            <th>quantite</th>
        </tr>
        <c:forEach var="p" items="${listemitoto}">
            <tr>
                <td>${p.debut}</td>
                <td>${p.fin}</td>
                <td>${p.quantite}</td>
            </tr>
        </c:forEach>
        
    </table>
<form action="/insert" method="get">
<p><input name="debut" type="datetime-local">datedebut</p>
<p><input name="fin" type="datetime-local">datefin</p>
<p><input name="source" value="${idsource}" type="hidden" type="text"/></p>
<p><input name="class" value="${classi}" type="hidden" type="text"/></p>
<p><input name="quantite">quantite</p>
<p><input name="consommation">consommation</p>
<p><input type="submit" value="ok"></p>
</form>
</body>
</html>