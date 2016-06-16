<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Administration de l'annuaire</h1>
	<ul>
		<li><a href='<c:url value="/categories" />'>Gérer les catégories</a>
		<li><a href='<c:url value="/advertisements" />'>Gérer les annonces</a>
	</ul>
<% %>
</body>
</html>