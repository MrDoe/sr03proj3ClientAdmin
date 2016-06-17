package ClientAdmin.controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import sr03projet3.beans.Address;
import sr03projet3.beans.Advertisement;
import sr03projet3.beans.Category;
import sr03projet3.service.Directory;

public class AdvertisementController extends ControllerBase {
	// Fields declarations 
	public static final String ID_FIELD = "idAdvertisement";
	public static final String NAME_FIELD = "nameAdvertisement";
	public static final String PHONE_FIELD = "phoneAdvertisement";
	public static final String STREET_FIELD = "streetAdvertisement";
	public static final String CITY_FIELD = "cityAdvertisement";
	public static final String POSTALCODE_FIELD = "postalCodeAdvertisement";
	public static final String CATEGORY_FIELD = "idCategory";
	
	// Service attribute
	Directory proxy;

	/**
	 * Constructor : call super() and set the service instance  
	 * @param directory the service instance
	 * @throws NullPointerException the service instance is null
	 */
	public AdvertisementController(Directory directory) throws NullPointerException{
		super();
		if(directory==null) throw new NullPointerException();
		proxy = directory;
	}
	
	/**
	 * Search an advertisement by bulding a sample Advertisement object depending on the given fields in the JSP from
	 * @param request
	 * @return ArrayList<Advertisement> the advertisements matched 
	 * @throws RemoteException if the service use has troubles
	 */
	public ArrayList<Advertisement> search(HttpServletRequest request) throws RemoteException{
		// Get the category from JSP page
		String category = request.getParameter(CATEGORY_FIELD);
		Advertisement sample = new Advertisement();
		
		// '-' character represents a null value 
		if(category != null && !category.equals("-")){
			Category cat = proxy.getCategory(Long.parseLong(category));
			sample.setCategory(cat);
			request.setAttribute(CATEGORY_FIELD, cat.getId());
		}
		
		// Retrive ads from the webservice, and return all results as ArrayList
		ArrayList<Advertisement> list = new ArrayList<>();
		Advertisement[] ads = proxy.searchAdvertisements(sample);
		// If there are advertisements, make a list from the array returned by the web-service
		if(ads != null)
			list = new ArrayList<>(Arrays.asList(ads));
		return list;
	}
	
	/**
	 * Process the request fields to edit an advertisement through Directory.
	 * Process a validation before attempting to call the service
	 * @param request
	 * @return Advertisement the modified advertisement
	 * @throws RemoteException if the service use has troubles
	 */	
	public Advertisement edit(HttpServletRequest request) throws RemoteException{
		// Get the fields from JSP page
		String name = request.getParameter(NAME_FIELD);
		String phone = request.getParameter(PHONE_FIELD);
		String street = request.getParameter(STREET_FIELD);
		String city = request.getParameter(CITY_FIELD);
		String postalCode = request.getParameter(POSTALCODE_FIELD);
		
		// Set up an Advertisement object with the form's values 
		Long id;  
		Advertisement advertisement = new Advertisement();
		Address address = new Address();
		advertisement.setName(name);
		advertisement.setPhone(phone);
		address.setStreet(street);
		address.setCity(city);
		address.setPostalCode(postalCode);
		advertisement.setAddress(address);
		
		
		try {
			// Try to parse the id as long
			id = Long.parseLong(request.getParameter(ID_FIELD));
			advertisement.setId(id);
			
			// Process validation
			this.checkAdvertisement(advertisement);
			
			// Set the selected category
			Long categoryId = Long.parseLong(request.getParameter(CATEGORY_FIELD));			
			advertisement.setCategory(proxy.getCategory(categoryId));
			
			// Update the advertisement throug the web-service
			proxy.updateAdvertisement(advertisement);
		} catch (NumberFormatException e) {
			// Failed to parse the form's values as long
			result = false;
			this.addMessage("Le formulaire est mal formé.");
		}catch (ControllerException e) {
			result = false;
		}
		return advertisement;
	}

	/**
	 * Process the request fields to create a new advertisement through Directory.
	 * Process a validation before attempting to call the service
	 * @param request
	 * @return Advertisement the new advertisement
	 * @throws RemoteException if the service use has troubles
	 */
	public Advertisement add(HttpServletRequest request) throws RemoteException{
		// Get the fields from JSP page
		String name = request.getParameter(NAME_FIELD);
		String phone = request.getParameter(PHONE_FIELD);
		String street = request.getParameter(STREET_FIELD);
		String city = request.getParameter(CITY_FIELD);
		String postalCode = request.getParameter(POSTALCODE_FIELD);
		
		// Set up an Advertisement object with the form's values 
		Advertisement advertisement = new Advertisement();
		Address address = new Address();
		advertisement.setName(name);
		advertisement.setName(name);
		advertisement.setPhone(phone);
		address.setStreet(street);
		address.setCity(city);
		address.setPostalCode(postalCode);
		advertisement.setAddress(address);
		
		try {
			// Try to parse the category's id as long and set the corresponding object as the advertisement's category
			advertisement.setCategory(proxy.getCategory(Long.parseLong(request.getParameter(CATEGORY_FIELD))));			
			this.checkAdvertisement(advertisement);

			// Process advertisement creation through the web-service
			advertisement = proxy.addAdvertisement(advertisement);
		} catch (NumberFormatException e) {
			this.addMessage("Le formulaire est mal formé.");
			result = false;
		}catch (ControllerException e) {
			result = false;
		}
		return advertisement;
	}

	/**
	 * Delete an advertisement through Directory
	 * @param request
	 * @throws RemoteException
	 */
	public void delete(HttpServletRequest request) throws RemoteException{
		Long id;
		try {
			id = Long.parseLong(request.getParameter(ID_FIELD));	
			proxy.removeAdvertisement(id);
		} catch (NumberFormatException e) {
			this.addMessage("Le formulaire est mal formé.");
			result = false;
		}
	}
	
	/**
	 * Check all the fields of an Advertisement object and set suitable messages according to the mistakes
	 * @param advertisement
	 * @throws ControllerException if the advertisement is not valid
	 * @throws NullPointerException if the advertisement is null
	 */
	private void checkAdvertisement(Advertisement advertisement) throws ControllerException{
		if(advertisement == null) throw new NullPointerException();
		// Use bit comparison to check all the fields
		if(!checkName(advertisement.getName())
				| !checkPhone(advertisement.getPhone())
				| ! checkAddress(advertisement.getAddress()))
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
	
	/**
	 * Check phone validity : 10 numerics character string
	 * @param phone
	 * @return boolean
	 */
	private boolean checkPhone(String phone) {
		boolean state = true;
		if(phone==null || phone.isEmpty()) {
			this.addMessage("Le numéro de téléphone ne peut pas être vide");
			state = false;
		}
		if(phone.matches(".*[a-zA-Z]+.*")) {
			this.addMessage("Le numéro de téléphone ne doit contenir que des chiffres");
			state = false;
		}
		if(phone.length()<10 || phone.length()>10) {
			this.addMessage("Le numéro de téléphone doit contenir 10 chiffres");
			state = false;
		}
		return state;
	}
	
	/**
	 * Check address validity.
	 * street and city must be set and filled
	 * postal code must be a 5 numeric character string
	 * @param address
	 * @return boolean
	 * @throws NullPointerException if address is null
	 */
	private boolean checkAddress(Address address) throws NullPointerException{
		// Flag to give the validity state
		boolean state = true;
		if(address == null) throw new NullPointerException();
		if(address.getStreet()==null || address.getStreet().isEmpty()) {
			this.addMessage("La rue ne peut pas être vide");
			state = false;
		}
		if(address.getCity()==null || address.getCity().isEmpty()) {
			this.addMessage("La ville ne peut pas être vide");
			state = false;
		}
		if(address.getPostalCode()==null || address.getPostalCode().isEmpty()) {
			this.addMessage("Le code postal ne peut pas être vide");
			state = false;
		}
		if(address.getPostalCode().matches(".*[a-zA-Z]+.*") || address.getPostalCode().length()<5 || address.getPostalCode().length()>5){
			this.addMessage("Le code postal doit contenir 5 chiffres");
			state = false;			
		}
		return state;
	}
}
