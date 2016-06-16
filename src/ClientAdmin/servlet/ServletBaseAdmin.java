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
    public static final String ACTION_PARAM = "action";
    public static final String DEFAULT_ACTION = "";
    public static final String ADD_ACTION = "add";
    public static final String EDIT_ACTION= "edit";
    public static final String DELETE_ACTION= "delete";
    
    protected String action=null;
    protected String view=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBaseAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		this.action = this.getInitParameter(ACTION_PARAM) ;
	}
    
	/**
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

	protected abstract void defaultGETAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	protected abstract void addGETAction(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
	protected abstract void editGETAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	//protected abstract void deleteGETAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	protected abstract void defaultPOSTAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	protected abstract void addPOSTAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	protected abstract void editPOSTAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	protected abstract void deletePOSTAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	protected String getAction(){
		return this.action;
	}
	
}
