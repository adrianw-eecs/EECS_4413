package ctrl;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Loan;
import listener.MaxPrincipal;



/**
 * Servlet implementation class Start
 */
@WebServlet({"/Start", "/Startup", "/Startup/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Loan loanobj;
	private String target;
	private boolean error = false;
	private String errorMsg;
	private String principal; 
	private String interest; 
	private String period;
	private String grace;
	private String fixedInterest;
	private String gracePeriod;
	private boolean graceVal;
	private double graceInterest; 
	private double monthlyPayment;
	private final String APPLICATION_NAME = "Student Loan Calculator";
	// STRINGS FOR QUERY URL BELOW
	private final String principalSTR = "principalone";
	private final String interestSTR = "interestone";
	private final String periodSTR = "periodone";
	private final String graceSTR = "graceP";
	private final String graceInterestSTR = "gracePerInt";
	private final String monthlyPaySTR = "monthlyPay";
	private final String fixedInterestSTR = "interest";
	private final String gracePeriodSTR = "gracePeriod";
	private final String submitSTR = "submit";
	private final String restartSTR = "restart";
	private final String ajaxSTR = "/Ajax";
	// STRINGS FOR SAVING/PUTTING VALUES ON THE PAGE
	private final String principalSVD = "savedPrincipal";
	private final String interestSVD= "savedInterest";
	private final String periodSVD = "savedPeriod";
	private final String graceSVD = "savedchecked";
	private final String errorSVD = "error";
	private final String errorMsgSVD = "errorMsg";
	       

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
        loanobj = new Loan();
		getServletContext().setAttribute("model", loanobj);
	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/plain");

		target = "";
		request.getServletContext().setAttribute("applicationName", APPLICATION_NAME);

		boolean submitQuery= request.getParameterMap().containsKey(submitSTR);
		boolean resetQuery= request.getParameterMap().containsKey(restartSTR);
//		searchs for /Ajax
		if(request.getPathInfo() != null && request.getPathInfo().contains("/Ajax")){

			extractAllQueryString(request);
			setUIAttributes(request);
			getWebXMLParams();
//			System.out.println("abc" + graceVal);
			computeValues();
			graceInterest = Math.round(graceInterest*100)/100;
			monthlyPayment = Math.round(monthlyPayment*100)/100;
			System.out.println("|" + principal  + "|" + interest  + "|" + period  + "|" + graceVal + "|" + graceInterest + "|" + monthlyPayment + "|" );
			response.getWriter().write("Grace Period Interest: " +  graceInterest + "</br> Monthly Payments: " + monthlyPayment);
		}
		else if(submitQuery) {
			getDefaultWebXML(request);
			extractAllQueryString(request);
//			System.out.println("|" + principal  + "|" + interest  + "|" + period  + "|" + grace);
			setUIAttributes(request);
			getWebXMLParams();
			
			computeValues();
			
					
			setResultsAttributes(request);
			
			request.getRequestDispatcher(target).forward(request,response);
		}else {
			if(!resetQuery) {
				request.getSession().setAttribute(principalSVD, principal);
				request.getSession().setAttribute(interestSVD, interest);
				request.getSession().setAttribute(periodSVD, period);
				request.getSession().setAttribute(graceSVD, null);
				request.getSession().setAttribute(errorSVD, false);
				request.getSession().setAttribute(errorMsgSVD, "");
			}
			target = "/UI.jspx";
			request.getRequestDispatcher(target).forward(request,response);
		}

		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}
	
	private void computeValues() {
		// TODO Auto-generated method stub
		try {
			graceInterest = loanobj.computeGraceInterest(principal, interest, fixedInterest, 
					graceVal, gracePeriod);
			
			monthlyPayment = loanobj.computePayment(principal, interest, period, 
					fixedInterest, graceVal, String.valueOf(graceInterest), 
					gracePeriod);
			
			target = "/Results.jspx";
			error = false;
			
		} catch (Exception e) {
			
			
			error = true;
			errorMsg = e.getMessage();
			System.out.println(errorMsg);
			graceInterest = 0.0;
			monthlyPayment = 0.0;
			target = "/UI.jspx";
			
		}
	}
	
	private void getDefaultWebXML(HttpServletRequest request) {
		principal = getServletContext().getInitParameter("principal");
		interest = getServletContext().getInitParameter("interest");
		period = getServletContext().getInitParameter("period");
		
		
	}
	
	private void getWebXMLParams() {
		fixedInterest = getServletContext().getInitParameter(fixedInterestSTR);
		gracePeriod = getServletContext().getInitParameter(gracePeriodSTR);
		
	}

	private void setUIAttributes(HttpServletRequest request) {
		request.getSession().setAttribute(principalSVD, principal);
		request.getSession().setAttribute(principalSVD, principal);
		request.getSession().setAttribute(interestSVD, interest);
		request.getSession().setAttribute(periodSVD, period);
		request.getSession().setAttribute(graceSVD, grace);
		request.getSession().setAttribute(errorSVD, error);
		request.getSession().setAttribute(errorMsgSVD, errorMsg);
		
	}
	private void setResultsAttributes(HttpServletRequest request) {
		request.getSession().setAttribute(graceInterestSTR, graceInterest);
		request.getSession().setAttribute(monthlyPaySTR, monthlyPayment);
	}

	
	private String extractFromQueryString(HttpServletRequest request, String param){
		return request.getParameter(param);
	}
	private void extractAllQueryString(HttpServletRequest request) {
		principal = extractFromQueryString(request, principalSTR); 
		interest = extractFromQueryString(request, interestSTR); 
		period = extractFromQueryString(request, periodSTR);
		grace = extractFromQueryString(request, graceSTR);
//		System.out.println(grace + "____");
//		System.out.println(grace.equals("false"));
		if(grace == null || grace.equals("false"))
		{
			graceVal = false;
		} else {
			graceVal = true;
		}
		
	}

}
