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
	<h1>Modifier une annonce</h1>
	<p>${message}</p>
	<form action="" method="POST">
		<label for="nameAdvertisement">Nom : </label>
		<input type="text" name="nameAdvertisement"/>
		<label for="phoneAdvertisement">Téléphone : </label>
		<input type="tel" name="phoneAdvertisement"/>
		<input type="submit" value="Valider" />
		<fieldset>
			<legend>Adresse</legend>
				<label for="streetAdvertisement">Rue</label>
				<input type="text" name="streetAdvertisement"/>
				<label for="cityAdvertisement">Rue</label>
				<input type="text" name="cityAdvertisement"/>
				<label for="postlCodeAdvertisement">Rue</label>
				<input type="text" name="postalCodeAdvertisement"/>
		</fieldset>
		<input type="submit" value="Valider" />		
	</form>
</body>
</html>