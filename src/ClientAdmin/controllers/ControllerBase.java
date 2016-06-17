/**
 * Base class for controllers
 * Defines some custome methods to manage messages, and result state 
 */

package ClientAdmin.controllers;

import java.util.HashMap;
import java.util.Map;

public class ControllerBase {
	
	// The message to print if the form processing has encountered troubles
	protected String message;
	public String getMessage(){
		return message;
	}
	
	// Concat messages with new lines 
	public void addMessage(String msg){
		if(!this.message.equals("")){
			this.message+="<br/>";
		}
		this.message += msg;
	}
	
	// Attribute to get the form result after processing
	protected Boolean result;
    public Boolean getResult() {
        return result;
    }
//    protected Map<String, String> errors = new HashMap<String, String>();
//    public Map<String, String> getErrors() {
//        return errors;
//    }
    
    /**
     * Constructor : initialize both message and result attribute
     */
    public ControllerBase(){
    	message = "";
    	result = true;
    	
    }
}
