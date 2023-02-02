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
	<p><a href="${pathContext.request.contextPath}/sources?idCarre=${idcarre}">inserer une source</a></p>
	<p><a href="${pathContext.request.contextPath}/tri?idCarre=${idcarre}">tri</a></p>
	<p><a href="${pathContext.request.contextPath}/couche?idCarre=${idcarre}&type=1">couche par durete</a></p>
	<p><a href="${pathContext.request.contextPath}/couche?idCarre=${idcarre}&type=2">couche par proportion</a></p>
	<p><a href="${pathContext.request.contextPath}/couche?idCarre=${idcarre}&type=3">couche par efficacite</a></p>
	<p><a href="${pathContext.request.contextPath}/couche?idCarre=${idcarre}&type=4">couche par exploitation</a></p>
	<p><a href="${pathContext.request.contextPath}/couche1?idCarre=${idcarre}&type=4">couche total</a></p>
	<p><a href="${pathContext.request.contextPath}/pageEstimation?idCarre=${idcarre}">estimation</a></p>
	<table border="1">
		
		<tr>
			<th>nom</th>
			<th></th>
			<th></th>
			<th></th>
		</tr>
		
			<c:forEach var="p" items="${mess}">
				<tr>
					<td><p>${p.nom}</p></td>
					<td><p><a href="${pathContext.request.contextPath}/poka?idSource=${p.idSource}">poka</a></p></td>
					<td><p><a href="${pathContext.request.contextPath}/mitoto?idSource=${p.idSource}">mitoto</a></p></td>
					<td><p><a href="${pathContext.request.contextPath}/centrifugeuse?idSource=${p.idSource}">centrifugeuse</a></p></td>
				</tr>
			</c:forEach>
			
		</tr>
	</table>

</body>
</html>