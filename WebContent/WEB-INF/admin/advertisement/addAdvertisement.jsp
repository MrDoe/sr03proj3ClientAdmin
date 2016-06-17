<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajouter une catégorie</title>
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
	<a href="<c:url value='/advertisements' /> ">Retour</a>
	<h1>Gestion de l'annuaire</h1>
	<h2>Ajouter une annonce</h2>
	<p>${message}</p>
	<form action="" method="POST">
		<label for="nameAdvertisement">Nom : </label>
		<input type="text" name="nameAdvertisement" value="${advertisement.getName()}"/>
		<label for="phoneAdvertisement">Téléphone : </label>
		<input type="tel" name="phoneAdvertisement" value="${advertisement.getPhone()}"/>
		<label for="idCategory">Catégorie</label>
		<select name="idCategory">
		<c:forEach items="${categories}" var="category">
			<option value="${category.getId()}" <c:if test="${advertisement.getCategory().getId()==category.getId()}">selected="selected"</c:if>>${category.getName()}</option>
		</c:forEach>
		</select>
		<fieldset>
			<legend>Adresse</legend>
				<label for="streetAdvertisement">Rue</label>
				<input type="text" name="streetAdvertisement" value="${advertisement.getAddress().getStreet()}"/>
				<label for="cityAdvertisement">Ville</label>
				<input type="text" name="cityAdvertisement" value="${advertisement.getAddress().getCity()}"/>
				<label for="postlCodeAdvertisement">Code Postal</label>
				<input type="text" name="postalCodeAdvertisement" value="${advertisement.getAddress().getPostalCode()}"/>
		</fieldset>
		<input type="submit" value="Valider" />		
	</form>
</body>
</html>