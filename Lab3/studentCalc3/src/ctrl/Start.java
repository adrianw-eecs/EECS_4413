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


/**
 * Servlet implementation class Start
 */
@WebServlet({"/Start", "/Startup", "/Startup/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Loan loanobj;
	boolean error = false;
	String errorMsg;
	       
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

		String target = "";

		

		
		boolean submitQuery= request.getParameterMap().containsKey("submit");
		boolean resetQuery= request.getParameterMap().containsKey("restart");
		String form = "";
		if(submitQuery) {
			
			
			String principal = request.getParameter("principalone");
//			if(principal == null || principal.isEmpty())
//			{
//				principal = getServletContext().getInitParameter("principal");
//			}
//			System.out.println("777");
			
			String period = request.getParameter("periodone");
//			if(period == null ||period.isEmpty())
//			{
//				period = getServletContext().getInitParameter("period");
//			}
//			System.out.println("767");
			
			String interest = request.getParameter("interestone");
//			if(interest == null || interest.isEmpty())
//			{
//				interest = getServletContext().getInitParameter("interest");
//			}
			String grace = request.getParameter("graceP");
			boolean graceVal = false;
//			System.out.println("|" + grace + "|");
			if(grace == null)
			{
				graceVal = false;
			} else {
				graceVal = true;
			}
//			System.out.println("787");
			
//			double principalVal = Double.parseDouble(principal);
//			double interestVal = Double.parseDouble(interest) / 100;
//			double periodVal =  Double.parseDouble(period);
			String fixedinterest = getServletContext().getInitParameter("interest");
			String gracePeriod = getServletContext().getInitParameter("gracePeriod");
			
			
			//Calculations
			double graceInterest;
			double monthlyPayment;
			try {
				graceInterest = loanobj.computeGraceInterest(principal, interest, fixedinterest, 
						graceVal, gracePeriod);
				
				monthlyPayment = loanobj.computePayment(principal, interest, period, 
						fixedinterest, graceVal, String.valueOf(graceInterest), 
						gracePeriod);
				
				target = "/Results.jspx";
				error = false;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				
				error = true;
				errorMsg = e.getMessage();
//				System.out.println(errorMsg);
				graceInterest = 0.0;
				monthlyPayment = 0;
				target = "/UI.jspx";
				
			}

			request.getSession().setAttribute("savedPrincipal", principal);
			request.getSession().setAttribute("savedInterest", interest);
			request.getSession().setAttribute("savedPeriod", period);
			request.getSession().setAttribute("savedchecked", grace);
			request.getSession().setAttribute("error", error);
			request.getSession().setAttribute("errorMsg", errorMsg);

		
			
			
//			double payment = (interestVal / 12) * principalVal / (1-  Math.pow((1 + (interestVal / 12)),(-1 * periodVal)));
//			double totalInterest = interestVal + Double.valueOf(getServletContext().getInitParameter("fixedInterest"));
//			double graceInterest = principalVal * (totalInterest/12) * Double.valueOf(getServletContext().getInitParameter("gracePeriod"));
//			double monthlyPayment = payment + (graceInterest /  Double.valueOf(getServletContext().getInitParameter("gracePeriod")));
//			double gracefinal = 0;
			
//
//			if(grace != null)
//			{
//				gracefinal = Double.valueOf(graceInterest);
//			} 
//
//			gracefinal = gracefinal/100;
			
//			gracefinal = Math.round(gracefinal * 100.0) / 100.0;
//			monthlyPayment = Math.round(monthlyPayment * 100.0) / 100.0;

					
			request.getSession().setAttribute("gracePerInt", graceInterest);
			request.getSession().setAttribute("monthlyPay", monthlyPayment);
			
			
		}else {
			if(!resetQuery) {
				error = false;
				request.getSession().setAttribute("savedPrincipal", 0);
				request.getSession().setAttribute("savedInterest", 0.0);
				request.getSession().setAttribute("savedPeriod", 0);
				request.getSession().setAttribute("savedchecked", null);
			}
			target = "/UI.jspx";

		}
//		request.setAttribute("form_html", form);
		request.getRequestDispatcher(target).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
