<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion des catégories</title>
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
	<h1>Gestion des annonces</h1>
	
	<form action="" id='filtre'>
		<label for='categoryIdAdvertisement'>Catégorie</label>
		<select name='categoryIdAdvertisement'>
			<option>-
			<c:forEach items="${categories}" var="category">
				<option value='${category.getId()}'>${category.getName()}			
			</c:forEach>
		</select>
		
		<input type='submit' value='Filtrer'/>
	</form>
	
	<table>
		<thead>
			<tr>
				<th>Nom</th>
				<th>Téléphone</th>
				<!-- <th>Catégorie</th> -->
				<th>Adresse</th>
				<th colspan="2">Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${advertisements}" var="advertisement">
				<form id="actionForm${advertisement.getId()}" action=""><input type="hidden" name="idAdvertisement" value="${advertisement.getId()}"/> </form>
				<tr>
					<td>${advertisement.getName()}</td>			
					<td>${advertisement.getPhone()}</td>			
					<%-- <td>${advertisement.getPhone()}</td> --%>
					<td>${advertisement.getAddress().getStreet()} 
						${advertisement.getAddress().getCity()} 
						${advertisement.getAddress().getPostalCode()}
					</td>	
					<td><button form="actionForm${advertisement.getId()}" formmethod="get" type="submit" formAction="<c:url value="/advertisements/edit" />">Modifier</button></td>
					<td><button form="actionForm${advertisement.getId()}" formmethod="post" type="submit" formAction="<c:url value="/advertisements/delete" />">Supprimer</button></td>
				</tr>	
			</c:forEach>
		</tbody>
	</table>
	<form method="get" action="<c:url value="/advertisements/add" /> ">
		<input type="submit" value="+ Ajouter +" />
	</form>
	
</body>
</html>