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
	<a href="<c:url value='/' /> ">Retour</a>
	<h1>Gestion de l'annuaire</h1>
	<h2>Gestion des annonces</h2>
	<form action="" id='filtre'>
		<label for='idCategory'>Catégorie</label>
		<select name='idCategory'>
			<option>-
			<c:forEach items="${categories}" var="category">
				<option value='${category.getId()}' <c:if test="${idCategory==category.getId()}">selected="selected"</c:if>>${category.getName()}			
			</c:forEach>
		</select>
		<input type='submit' value='Filtrer'/>
	</form>
	<form method="get" action="<c:url value="/advertisements/add" /> ">
		<input type="submit" value="+ Ajouter +" />
	</form>	
	<table>
		<thead>
			<tr>
				<th>Nom</th>
				<th>Téléphone</th>
				<th>Catégorie</th>
				<th>Adresse</th>
				<th colspan="2">Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${advertisements}" var="advertisement">
				<form id="actionForm${advertisement.getId()}" action=""><input type="hidden" name="idAdvertisement" value="${advertisement.getId()}"/> </form>
				<form id="actionForm${advertisement.getId()}" action=""><input type="hidden" name="idCategory" value="${advertisement.getCategory().getId()}"/> </form>
				<tr>
					<td>${advertisement.getName()}</td>			
					<td>${advertisement.getPhone()}</td>			
					<td>${advertisement.getCategory().getName()}</td>
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
</body>
</html>