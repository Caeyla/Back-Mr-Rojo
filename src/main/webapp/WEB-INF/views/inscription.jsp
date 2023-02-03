<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<p>login</p>
       
            <form action="${pathContext.request.contextPath}/insertUser" method="get">
		
            <p>nom: <input type="text" name="nom" ></p>
            <p>mdp: <input type="password" name="mdp" ></p>
            <p><input type="hidden" name="class" value="${classi}" ></p>
            <input type="submit" value="ok">
        </form>
	
</html>