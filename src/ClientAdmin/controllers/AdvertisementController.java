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
	public static final String ID_FIELD = "idAdvertisement";
	public static final String NAME_FIELD = "nameAdvertisement";
	public static final String PHONE_FIELD = "phoneAdvertisement";
	public static final String STREET_FIELD = "streetAdvertisement";
	public static final String CITY_FIELD = "cityAdvertisement";
	public static final String POSTALCODE_FIELD = "postalCodeAdvertisement";
	public static final String CATEGORY_FIELD = "idCategory";
	Directory proxy;

	public AdvertisementController(Directory directory) {
		super();
		proxy = directory;
	}
	
	public ArrayList<Advertisement> search(HttpServletRequest request) throws RemoteException{
		// Get the fields from JSP page
		String name = request.getParameter(NAME_FIELD);
		String phone = request.getParameter(PHONE_FIELD);
		String category = request.getParameter(CATEGORY_FIELD);
		String street = request.getParameter(STREET_FIELD);
		String city = request.getParameter(CITY_FIELD);
		String postalCode = request.getParameter(POSTALCODE_FIELD);
		Advertisement sample = new Advertisement();
		Address address = new Address();
		
		// Set up a sample advertisement with given fields
		if(name != null) {
			sample.setName(name);
			request.setAttribute(NAME_FIELD, name);
		}
		if(phone != null) {
			sample.setPhone(phone);
			request.setAttribute(PHONE_FIELD, phone);
		}
		if(street != null) {
			address.setStreet(street);
			request.setAttribute(STREET_FIELD, street);
		}
		if(city != null) {
			address.setCity(city);
			request.setAttribute(CITY_FIELD, city);
		}
		if(postalCode != null) {
			address.setPostalCode(postalCode);
			request.setAttribute(POSTALCODE_FIELD, postalCode);
		}
		sample.setAddress(address);
		if(category != null && !category.equals("-")){
			Category cat = proxy.getCategory(Long.parseLong(category));
			sample.setCategory(cat);
			request.setAttribute(CATEGORY_FIELD, cat.getId());
		}
		
		// Retrive ads from the webservice, and return all results as ArrayList
		ArrayList<Advertisement> list = new ArrayList<>();
		Advertisement[] ads = proxy.searchAdvertisements(sample);
		if(ads != null)
			list = new ArrayList<>(Arrays.asList(ads));
		return list;
	}
	
	public Advertisement edit(HttpServletRequest request) throws RemoteException{
		// Get the fields from JSP page
		String name = request.getParameter(NAME_FIELD);
		String phone = request.getParameter(PHONE_FIELD);
		String street = request.getParameter(STREET_FIELD);
		String city = request.getParameter(CITY_FIELD);
		String postalCode = request.getParameter(POSTALCODE_FIELD);
		
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
			id = Long.parseLong(request.getParameter(ID_FIELD));
			advertisement.setId(id);
			this.checkAdvertisement(advertisement);
			Long categoryId = Long.parseLong(request.getParameter(CATEGORY_FIELD));			
			advertisement.setCategory(proxy.getCategory(categoryId));
			
			proxy.updateAdvertisement(advertisement);
		} catch (NumberFormatException e) {
			result = false;
			this.addMessage("Le formulaire est mal formé.");
		}catch (ControllerException e) {
			result = false;
		}
		return advertisement;
	}

	public Advertisement add(HttpServletRequest request) throws RemoteException{
		String name = request.getParameter(NAME_FIELD);
		String phone = request.getParameter(PHONE_FIELD);
		String street = request.getParameter(STREET_FIELD);
		System.out.println("street : "+street);
		String city = request.getParameter(CITY_FIELD);
		String postalCode = request.getParameter(POSTALCODE_FIELD);
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
			advertisement.setCategory(proxy.getCategory(Long.parseLong(request.getParameter(CATEGORY_FIELD))));			
			advertisement = proxy.addAdvertisement(advertisement);
		} catch (NumberFormatException e) {
			result = false;
		}
		return advertisement;
	}

	public void delete(HttpServletRequest request) throws RemoteException{
		Long id;
		try {
			id = Long.parseLong(request.getParameter(ID_FIELD));	
			proxy.removeAdvertisement(id);
		} catch (NumberFormatException e) {
			result = false;
		}
	}
	
	private void checkAdvertisement(Advertisement ad) throws ControllerException{
		if(ad == null) throw new NullPointerException();
		if(!checkName(ad.getName())
				|| !checkPhone(ad.getPhone())
				|| ! checkAddress(ad.getAddress()))
			throw new ControllerException("Formulaire invalide");
	}
	
	// *********************** Fields validations methods ************************************* 
	
	private boolean checkName(String name) {
		if(name==null || name.isEmpty()) {
			this.addMessage("Le nom ne peut pas être vide");
			return false;
		}
		return true;
	}
	
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
	
	private boolean checkAddress(Address address){
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
		if(address.getPostalCode().matches(".*[a-zA-Z]+.*")){
			this.addMessage("Le code postal ne peut contenir que des chiffres");
			state = false;			
		}
		if(address.getPostalCode().length()<5 || address.getPostalCode().length()>5){
			this.addMessage("Le code postal doit contenir 5 chiffres");
			state = false;			
		}
		return state;
	}
}
