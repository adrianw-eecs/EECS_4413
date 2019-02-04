package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MaxPrincipal
 *
 */
@WebListener
public class MaxPrincipal implements HttpSessionAttributeListener {

	private static double maxPrincipal = 0.0;
    /**
     * Default constructor. 
     */
    public MaxPrincipal() {
        // TODO Auto-generated constructor stub
    	
    }
    
    public static double getMaxPrincipal()
    {
//    	System.out.println(maxPrincipal);
    	return maxPrincipal;
    }
    
    public void setMaxPrincipal(double value)
    {
    	this.maxPrincipal = value;
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
//    	System.out.println("add" + arg0.getName() + ":" + arg0.getValue());
    	
    	updateMaxPrincipal(arg0);
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
//    	System.out.println("remove" + arg0.getName());
    	updateMaxPrincipal(arg0);
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
//    	System.out.println("repl: " + arg0.getName() + "		Val: " + arg0.getValue());
//    	System.out.println("rep" + arg0.getName() + ":" + arg0.getValue());
    	updateMaxPrincipal(arg0);
    	
    	
    }
    private void updateMaxPrincipal(HttpSessionBindingEvent arg0) {

    	if(arg0.getName().equals("savedPrincipal")){
//    		setAttribute("savedchecked", null);
//    		System.out.println("|" + arg0.getValue().toString() + "|");
    		double theDouble = Double.parseDouble(arg0.getSession().getAttribute("savedPrincipal").toString());
//    		System.out.println("||" + arg0.getSession().getAttribute("savedPrincipal") + "||");
    		if(theDouble > maxPrincipal) {
    			setMaxPrincipal(theDouble);
//    			System.out.println(getMaxPrincipal());
    			arg0.getSession().getServletContext().setAttribute("maxPrincipal", getMaxPrincipal());
    		}
    	}
    }
	
}
