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

import ClientAdmin.controllers.CategoryController;
import sr03projet3.beans.*;
import sr03projet3.service.*;
/**
 * Servlet implementation class CategoryManagement
 */
public class CategoryManagement extends ServletBaseAdmin {
	private static final long serialVersionUID = 1L;
	public static final String BASE_PATH = "categories";
	
	
	// View locations declarations
    private static final String DEFAULT_VIEW = "/WEB-INF/admin/category/showAllCategories.jsp";
    private static final String EDIT_VIEW = "/WEB-INF/admin/category/editCategory.jsp";
    private static final String ADD_VIEW = "/WEB-INF/admin/category/addCategory.jsp";
    
    // Custom request attributes declarations :
    public static final String CATEGORY_LIST_ATTRIBUTE = "categories";
    public static final String CATEGORY_ATTRIBUTE = "category";
    
    private Directory directoryService;
    private CategoryController controller;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		super.init();
		directoryService = new DirectoryProxy();
		controller = new CategoryController(directoryService);
		
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

	/**
	 * @see ServletBase#addGETAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void addGETAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(this.view).forward(request, response);
	}

	/**
	 * @see ServletBase#defaultGETAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void defaultGETAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DirectoryProxy proxy = new DirectoryProxy();
		try {
			ArrayList<Category> list = new ArrayList<>(Arrays.asList(proxy.getCategories()));
			request.setAttribute(CATEGORY_LIST_ATTRIBUTE, list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher(this.view).forward(request, response);
	}

	/**
	 * @see ServletBase#editGETAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void editGETAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DirectoryProxy proxy = new DirectoryProxy();
		Long id = Long.parseLong(request.getParameter(CategoryController.ID_FIELD));
		try {
			Category category = proxy.getCategory(id);
			request.setAttribute(CATEGORY_ATTRIBUTE, category);
			
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
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see ServletBase#addPOSTAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void addPOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		controller.add(request);
		response.sendRedirect(request.getContextPath()+"/"+BASE_PATH);
	}

	/**
	 * @see ServletBase#editPOSTAction(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void editPOSTAction(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Category category = controller.edit(request);
		if(controller.getResult()){
			response.sendRedirect(request.getContextPath()+"/"+BASE_PATH);
		}
		else{
			request.setAttribute("message", controller.getMessage());
			request.setAttribute(CATEGORY_ATTRIBUTE, category);
			request.getRequestDispatcher(this.view).forward(request, response);
		}
	}

	/**
	 * @see ServletBase#deletePOSTAction(HttpServletRequest request,HttpServletResponse response)
	 */
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
