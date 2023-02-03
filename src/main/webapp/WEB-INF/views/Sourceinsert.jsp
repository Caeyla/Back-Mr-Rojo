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
<form action="/insert" method="get">
<p><input name="x" type="text">x</p>
<p><input name="y" type="text">y</p>
<p><input name="carre" value="${idcarre}" type="hidden" type="text"/></p>
<p><input name="class" value="${classi}" type="hidden" type="text"/></p>
<p><input name="nom">nom</p>
<p><input name="consommationMachineMitoto">CMM</p>
<p><input name="consommationCentrifugeuse">CC</p>
<input type="submit" value="ok">
</form>
</body>
</html>