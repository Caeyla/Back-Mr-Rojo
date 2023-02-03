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
<form action="/estimation" method="get">
<p><input name="prix" type="text">prix</p>
<select name="definition" >
    <option value="1">journalier</option>
    <option value="5">hebdomadaire</option>
    <option value="22">mensuel</option>
</select>
<p><input name="idCarre"  type="hidden" value="${idCarre}"></p>
<input type="submit" value="ok">
</form>
</body>
</html>