<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion de l'annuaire</title>
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
	<a href="<c:url value='/categories' /> ">Retour</a>
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
			<c:forEach items="${category.getAdvertisements()}" var="advertisement">
				<form id="actionForm${advertisement.getId()}" action=""><input type="hidden" name="idAdvertisement" value="${advertisement.getId()}"/> </form>
				<form id="actionForm${advertisement.getId()}" action=""><input type="hidden" name="idCategory" value="${category.getId()}"/> </form>
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