<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<table>
		<thead>
			<th>Nom</th>
			<th colspan="2">Actions</th>
		</thead>
		<tbody>
			<c:forEach items="${categories}" var="category">
				<form id="actionForm${category.getId()}" action=""><input type="hidden" name="idCategory" value="${category.getId()}"/> </form>
				<tr>
					<td>${category.getName()}</td>
					<td><button form="actionForm${category.getId()}" formmethod="get" type="submit" formAction="<c:url value="/categories/edit" />">Modifier</button></td>
					<td><button form="actionForm${category.getId()}" formmethod="post" type="submit" formAction="<c:url value="/categories/delete" />">Supprimer</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<form method="get" action="<c:url value="/categories/add" /> ">
		<input type="submit" value="+ Ajouter +" />
	</form>
	
</body>
</html>