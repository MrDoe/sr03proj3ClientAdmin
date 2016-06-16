<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier une catégorie</title>
<style type="text/css">
	table {
		border-width:1px; 
		border-style:solid; 
		border-color:black;
		border-collapse:collapse;
		width:50%;
	}
	td,th {
		text-align: center; 
		border-width:1px;
		border-style:solid; 
		border-color:black;		
	 }
</style>

</head>
<body>
	<h1>Gestion des catégories</h1>
	<h2>Modification</h2>
	<p>${message}</p>
	<form action="" method="POST">
		<input type="hidden" name="idCategory" value="${category.getId()}"/>
		
		<label for="nameCategory">Nom : </label>
		<input type="text" name="nameCategory" value="${category.getName()}" />
		<input type="submit" value="Valider" />
	</form>
</body>
</html>