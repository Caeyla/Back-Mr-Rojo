<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<p>salut ${idEntreprise}</p>
	<p><a href="${pathContext.request.contextPath}/Entreprise/demandeT">tout le demandes et la totalité</a></p>
    <p><a href="${pathContext.request.contextPath}/Entreprise/listeProforma">liste proforma reçue par demande</a></p>
    <p><a href="${pathContext.request.contextPath}/Entreprise/listedemandeProforma">liste demande de proforma</a></p>
</html>