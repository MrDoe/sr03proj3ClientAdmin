package ClientAdmin.servlet;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClientAdmin.controllers.AdvertisementController;
import ClientAdmin.controllers.CategoryController;
import sr03projet3.beans.*;
import sr03projet3.service.*;
/**
 * Servlet implementation class AdvertisementManagement
 */
public class AdvertisementManagement extends ServletBaseAdmin {
	private static final long serialVersionUID = 1L;
	public static final String BASE_PATH = "advertisements";
	
	
	// View locations declarations
    private static final String DEFAULT_VIEW = "/WEB-INF/admin/advertisement/showAllAdvertisements.jsp";
    private static final String EDIT_VIEW = "/WEB-INF/admin/advertisement/editAdvertisement.jsp";
    private static final String ADD_VIEW = "/WEB-INF/admin/advertisement/addAdvertisement.jsp";
    
    // Custom request attributes declarations :
    public static final String CATEGORY_LIST_ATTRIBUTE = "categories";
    public static final String CATEGORY_ATTRIBUTE = "category";
    public static final String ADS_LIST_ATTRIBUTE = "advertisements";
    public static final String AD_ATTRIBUTE = "advertisement";
    
    
    // Server-side objects
    private Directory directoryService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvertisementManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		super.init();
		// Instanciate the proxy to use Directory service 
		directoryService = new DirectoryProxy();
		
		// Set the view to use depending on the action requested
		switch (action) {
		case DEFAULT_ACTION:
		case DELETE_ACTION:
			this.view = DEFAULT_VIEW;
			break;
		case EDIT_ACTION:
			this.view = EDIT_VIEW;
			break;
		case ADD_ACTION:
			this.view = ADD_VIEW;
			break;
		default:
			// Manage an unexcepted action given through url
			throw new ServletException("Invalid action '"+this.getAction()+"' given, request ignored.");
		}
	}

	/**
	 * @see ServletBase#addGETAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void addGETAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// Retrive all categories to show them in the edit form
			ArrayList<Category> catList = new ArrayList<>(Arrays.asList(directoryService.getCategories()));		
			request.setAttribute(CATEGORY_LIST_ATTRIBUTE, catList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(this.view).forward(request, response);
	}

	/**
	 * @see ServletBase#defaultGETAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void defaultGETAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdvertisementController controller = new AdvertisementController(directoryService);
		try {
			// Retrive all categories to show them in the edit form
			ArrayList<Category> catList = new ArrayList<>(Arrays.asList(directoryService.getCategories()));
			
			// Filter advertisements
			ArrayList<Advertisement> ads = controller.search(request);
			
			// Pass the results in the request before dispatching
			request.setAttribute(CATEGORY_LIST_ATTRIBUTE, catList);
			request.setAttribute(ADS_LIST_ATTRIBUTE, ads);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(this.view).forward(request, response);
	}

	/**
	 * @see ServletBase#editGETAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void editGETAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Get the advertisement by its id to fill the edit form 
		Long idAd = Long.parseLong(request.getParameter(AdvertisementController.ID_FIELD));
		try {
			Advertisement advertisement = directoryService.getAdvertisement(idAd);
			ArrayList<Category> catList = new ArrayList<>(Arrays.asList(directoryService.getCategories()));		
			
			request.setAttribute(CATEGORY_LIST_ATTRIBUTE, catList);
			request.setAttribute(AD_ATTRIBUTE, advertisement);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(this.view).forward(request, response);
	}

	/**
	 * @see ServletBase#defaultPOSTAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void defaultPOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// No action to process for the default advertisement list with POST method
		response.sendRedirect(request.getContextPath()+"/"+BASE_PATH);
	}

	/**
	 * @see ServletBase#addPOSTAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void addPOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdvertisementController controller = new AdvertisementController(directoryService);
		
		// Process the form with the controller
		Advertisement advertisement = controller.add(request);
		// If creation has succeeded, redirect to the main ads page
		if(controller.getResult()){
			response.sendRedirect(request.getContextPath()+"/"+BASE_PATH);
		}
		else{ // If creation has failed, get messages from the controller, retore the form context and shw the form again  
			request.setAttribute("message", controller.getMessage());
			request.setAttribute(AD_ATTRIBUTE, advertisement);
			request.setAttribute(CATEGORY_LIST_ATTRIBUTE, directoryService.getCategories());
			request.getRequestDispatcher(this.view).forward(request, response);
		}
	}

	/**
	 * @see ServletBase#editPOSTAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void editPOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdvertisementController controller = new AdvertisementController(directoryService);
		
		// Process the form with the controller
		Advertisement advertisement = controller.edit(request);
		// If edition has succeeded, redirect to the main ads page
		if(controller.getResult()){
			response.sendRedirect(request.getContextPath()+"/"+BASE_PATH);
		}
		else{
			// If edition has failed, get messages from the controller, retore the form context and shw the form again
			request.setAttribute("message", controller.getMessage());
			request.setAttribute(AD_ATTRIBUTE, advertisement);
			request.setAttribute(CATEGORY_LIST_ATTRIBUTE, directoryService.getCategories());
			request.getRequestDispatcher(this.view).forward(request, response);
		}
	}

	/**
	 * @see ServletBase#deletePOSTAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void deletePOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AdvertisementController controller = new AdvertisementController(directoryService);
		// Process deletion with the controller
		controller.delete(request);
		// If deletion has succeded, redirect to the main page
		if(controller.getResult())
			response.sendRedirect(request.getContextPath()+"/"+BASE_PATH);
		else {
			// Else show errors in the correct view
			request.setAttribute("message", controller.getMessage());
			request.getRequestDispatcher(this.view).forward(request, response);
		}
	}
}
