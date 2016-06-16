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
    
    
    
    private Directory directoryService;
    private AdvertisementController controller;
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
		directoryService = new DirectoryProxy();
		controller = new AdvertisementController(directoryService);
		
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
			throw new ServletException("Invalid action '"+this.getAction()+"' given, request ignored.");
		}
	}

	@Override
	protected void addGETAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(this.view).forward(request, response);
	}

	@Override
	protected void defaultGETAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			ArrayList<Advertisement> ads = controller.search(request);
			ArrayList<Category> catList = new ArrayList<>(Arrays.asList(directoryService.getCategories()));
			
			request.setAttribute(CATEGORY_LIST_ATTRIBUTE, catList);
			request.setAttribute(ADS_LIST_ATTRIBUTE, ads);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(this.view).forward(request, response);
	}

	@Override
	protected void editGETAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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

//	@Override
//	protected void deleteGETAction(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	protected void defaultPOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addPOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		controller.add(request);
		response.sendRedirect(request.getContextPath()+"/"+BASE_PATH);
	}

	@Override
	protected void editPOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Advertisement advertisement = controller.edit(request);
		if(controller.getResult()){
			response.sendRedirect(request.getContextPath()+"/"+BASE_PATH);
		}
		else{
			request.setAttribute("message", controller.getMessage());
			request.setAttribute(AD_ATTRIBUTE, advertisement);
			request.setAttribute(CATEGORY_LIST_ATTRIBUTE, directoryService.getCategories());
			request.getRequestDispatcher(this.view).forward(request, response);
		}
	}

	@Override
	protected void deletePOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		controller.delete(request);
		if(controller.getResult())
			response.sendRedirect(request.getContextPath()+"/"+BASE_PATH);
		else {
			request.setAttribute("message", controller.getMessage());
			request.getRequestDispatcher(this.view).forward(request, response);
		}
	}


}
