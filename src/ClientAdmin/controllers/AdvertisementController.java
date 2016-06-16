package ClientAdmin.controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import sr03projet3.beans.Address;
import sr03projet3.beans.Advertisement;
import sr03projet3.beans.AdvertisementList;
import sr03projet3.service.Directory;

public class AdvertisementController extends ControllerBase {
	public static final String ID_FIELD = "idAdvertisement";
	public static final String NAME_FIELD = "nameAdvertisement";
	public static final String PHONE_FIELD = "phoneAdvertisement";
	public static final String STREET_FIELD = "streetAdvertisement";
	public static final String CITY_FIELD = "cityAdvertisement";
	public static final String POSTALCODE_FIELD = "postalCodeAdvertisement";
	public static final String CATEGORY_FIELD = "categoryIdAdvertisement";
	Directory proxy;

	public AdvertisementController(Directory directory) {
		proxy = directory;
	}
	
	public ArrayList<Advertisement> search(HttpServletRequest request) throws RemoteException{
		// Get the fields from JSP page
		String name = request.getParameter(NAME_FIELD);
		String phone = request.getParameter(PHONE_FIELD);
		String category = request.getParameter(CATEGORY_FIELD);
		Long categoryId;
		String street = request.getParameter(STREET_FIELD);
		String city = request.getParameter(CITY_FIELD);
		String postalCode = request.getParameter(POSTALCODE_FIELD);
		Advertisement sample = new Advertisement();
		Address address = new Address();
		
		// Set up a sample advertisement with given fields
		if(name != null) sample.setName(name);
		if(phone != null) sample.setPhone(phone);
		if(street != null) address.setStreet(street);
		if(city != null) address.setCity(city);
		if(postalCode != null) address.setPostalCode(postalCode);
		sample.setAddress(address);
		
		// Parse the category's id as Long if possible, else set a null id 
		try {
			categoryId = Long.parseLong(category);
		} catch (NumberFormatException e) {
			categoryId = (long) 0;
		}
		
		// Retrive ads from the webservice, and return all results as ArrayList
		ArrayList<Advertisement> list = new ArrayList<>();
		Advertisement[] ads = proxy.searchAdvertisements(categoryId, sample);
		if(ads != null)
			list = new ArrayList<>(Arrays.asList(ads));
		return list;
	}
	
	public Advertisement edit(HttpServletRequest request) throws RemoteException{
		String name = request.getParameter(NAME_FIELD);
		Long id; // To get the id after recording the ad 
		Advertisement advertisement = new Advertisement();
		try {
			id = Long.parseLong(request.getParameter(ID_FIELD));	
			advertisement.setId(id);
			advertisement.setName(name);
			proxy.updateAdvertisement(advertisement);
		} catch (NumberFormatException e) {
			result = false;
		}
		return advertisement;
	}

	public Advertisement add(HttpServletRequest request) throws RemoteException{
		String name = request.getParameter(NAME_FIELD);
		Advertisement advertisement = new Advertisement();
		try {
			advertisement.setName(name);
			proxy.addAdvertisement(advertisement, 0);
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

}
