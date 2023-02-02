<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<p>login</p>
    <form action="${pathContext.request.contextPath}/validationLogin" method="get">
            <p>nom: <input type="text" name="nom"></p>
            <p>mdp: <input type="text" name="mdp"></p>
            <input type="submit" value="ok">
    </form>
	
</html>