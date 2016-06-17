package ClientAdmin.controllers;

import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;

import sr03projet3.beans.Advertisement;
import sr03projet3.beans.Category;
import sr03projet3.service.Directory;

public class CategoryController extends ControllerBase {
	// Fields declaration
	public static final String ID_FIELD = "idCategory";
	public static final String NAME_FIELD = "nameCategory";
	
	// Service attribute
	Directory proxy;

	/**
	 * Constructor : call super() and the the service instance
	 * @param directory the service instance
	 * @throws NullPointerException the service instance is null
	 */
	public CategoryController(Directory directory) throws NullPointerException{
		super();
		if(directory==null) throw new NullPointerException();
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
			checkCategory(category);
			proxy.updateCategory(category);
		} catch (NumberFormatException e) {
			this.addMessage("Le formulaire est mal formé.");
			result = false;
		} catch (ControllerException e) {
			result = false;
		} 
		return category;
	}

	public Category add(HttpServletRequest request) throws RemoteException{
		String name = request.getParameter(NAME_FIELD);
		Category category = new Category();
		try {
			category.setName(name);
			checkCategory(category);
			proxy.addCategory(category);
		} catch (NumberFormatException e) {
			this.addMessage("Le formulaire est mal formé.");
			result = false;
		}catch (ControllerException e) {
			result = false;
		} 
		return category;
	}

	/**
	 * Delete a category through the Directory
	 * @param request
	 * @throws RemoteException
	 */
	public void delete(HttpServletRequest request) throws RemoteException{
		Long id;
		try {
			id = Long.parseLong(request.getParameter(ID_FIELD));	
			proxy.removeCategory(id);
		} catch (NumberFormatException e) {
			this.addMessage("Le formulaire est mal formé.");
			result = false;
		}
	}
	/**
	 * Check all the fields of an Category object and set suitable messages according to the mistakes
	 * @param category
	 * @throws ControllerException if the category is not valid
	 * @throws NullPointerException if the category is null
	 */
	private void checkCategory(Category category) throws ControllerException{
		if(category == null) throw new NullPointerException();
		// Use bit comparison to check all the fields
		if(!checkName(category.getName()))
			throw new ControllerException("Formulaire invalide");
	}

	// *********************** Fields validations methods ************************************* 
	
	/**
	 * Check name validity. Name must be set and filled 
	 * @param name
	 * @return boolean
	 */
	private boolean checkName(String name) {
		if(name==null || name.isEmpty()) {
			this.addMessage("Le nom ne peut pas être vide");
			return false;
		}
		return true;
	}

}
