package ClientAdmin.controllers;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;

import sr03projet3.beans.Category;
import sr03projet3.service.Directory;

public class CategoryController extends ControllerBase {
	public static final String ID_FIELD = "idCategory";
	public static final String NAME_FIELD = "nameCategory";
	Directory proxy;

	public CategoryController(Directory directory) {
		proxy = directory;
	}
	
	public Category edit(HttpServletRequest request) throws RemoteException{
		String name = request.getParameter(NAME_FIELD);
		Long id;
		Category category = new Category();
		try {
			id = Long.parseLong(request.getParameter(ID_FIELD));	
			category.setId(id);
			category.setName(name);
			proxy.updateCategory(category);
		} catch (NumberFormatException e) {
			result = false;
		}
		return category;
	}

	public Category add(HttpServletRequest request) throws RemoteException{
		String name = request.getParameter(NAME_FIELD);
		Category category = new Category();
		try {
			category.setName(name);
			proxy.addCategory(category);
		} catch (NumberFormatException e) {
			result = false;
		}
		return category;
	}

	public void delete(HttpServletRequest request) throws RemoteException{
		Long id;
		try {
			id = Long.parseLong(request.getParameter(ID_FIELD));	
			proxy.removeCategory(id);
		} catch (NumberFormatException e) {
			result = false;
		}
	}

}
