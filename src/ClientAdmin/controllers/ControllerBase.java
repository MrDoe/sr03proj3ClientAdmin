package ClientAdmin.controllers;

import java.util.HashMap;
import java.util.Map;

public class ControllerBase {
	
	protected String message;
	public String getMessage(){
		return message;
	}
	public void addMessage(String msg){
		if(!this.message.equals("")){
			this.message+="<br/>";
		}
		this.message += msg;
	}
	protected Boolean result;
    public Boolean getResult() {
        return result;
    }
    protected Map<String, String> errors = new HashMap<String, String>();
    public Map<String, String> getErrors() {
        return errors;
    }
    
    public ControllerBase(){
    	message = "";
    	result = true;
    	
    }
}
