package ClientAdmin.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletBase
 */
public abstract class ServletBaseAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Define actions values  
    public static final String ACTION_PARAM = "action";
    public static final String DEFAULT_ACTION = "";
    public static final String ADD_ACTION = "add";
    public static final String EDIT_ACTION= "edit";
    public static final String DELETE_ACTION= "delete";

    // Attributes to access the action and the view from subclasses
    protected String action=null;
    protected String view=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBaseAdmin() {
        super();
    }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		// Get the action to proceed by a servlet init param
		this.action = this.getInitParameter(ACTION_PARAM) ;
	}
    
	/**
	 * Execute the administration action corresponding to the url, in order to GET informations.
	 * Consider a delete action shouldn't be given in GET method, thus send a METHOD_NOT_ALLOWED HTTP error code. 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (action) {
		case "add":
			addGETAction(request, response);
			break;
		case "edit":
			editGETAction(request, response);
			break;
		case "":
			defaultGETAction(request, response);
			break;
		case "delete":
		default:
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			break;
		}
	}

	/**
	 * Execute the administration action corresponding to the url, in order to POST informations. 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		switch (action) {
		case "add":
			addPOSTAction(request, response);
			break;
		case "edit":
			editPOSTAction(request, response);
			break;
		case "delete":
			deletePOSTAction(request, response);
			break;
		case "":
			defaultPOSTAction(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			break;
		}
	}

	
	/**
	 * Perform the default action requested with GET method. Usually used to list elements.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void defaultGETAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * Perform the add action requested with GET method. Usually used to give a creation form for the first time.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void addGETAction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
	
	/**
	 * Perform the edit action requested with GET method. Usually used to give a edition form for the first time.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void editGETAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	/**
	 * Perform the default action requested with POST method. Could be useful in some cases. 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void defaultPOSTAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * Perform the add action requested with POST method. Usually used to get informations from a form and process the data to create a record.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void addPOSTAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * Perform the edit action requested with POST method. Usually used to get informations from a form and process the data to edit a record.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void editPOSTAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	
	/**
	 * Perform the delete action requested with POST method. Usually used to delete a record.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void deletePOSTAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	/**
	 * Current action accessor.
	 * @return String the current action
	 */
	protected String getAction(){
		return this.action;
	}
	
	/**
	 * Tool to build an url GET param with the simple pattern 'name=value'
	 * @param name
	 * @param value
	 * @return String the partial param string
	 */
	protected String urlParam(String name, Object value){
		return name+"="+value;
	}
	
}
