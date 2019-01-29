package ctrl;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Start
 */
@WebServlet({"/Start", "/Startup", "/Startup/*"})
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/plain");
		Writer resOut = response.getWriter();
		
		String target = "/UI.jspx";
		
		boolean submitQuery= request.getParameterMap().containsKey("submit");
		boolean resetQuery= request.getParameterMap().containsKey("reset");
		String form = "";
		if(submitQuery) {
			
			
			String principal = request.getParameter("principalone");
			if(principal == null || principal.isEmpty())
			{
				principal = getServletContext().getInitParameter("principal");
			}

			
			String period = request.getParameter("periodone");
			if(period == null ||period.isEmpty())
			{
				period = getServletContext().getInitParameter("period");
			}

			
			String interest = request.getParameter("interestone");
			if(interest == null || interest.isEmpty())
			{
				interest = getServletContext().getInitParameter("interest");
			}

			System.out.println("Principle");
			double principalVal = Double.parseDouble(principal);
			System.out.println("Interest");
			double interestVal = Double.parseDouble(interest) / 100;
			System.out.println("Period");
			double periodVal =  Double.parseDouble(period);
			double payment = (interestVal / 12) * principalVal / (1-  Math.pow((1 + (interestVal / 12)),(-1 * periodVal)));
			
			
			
			
			double totalInterest = interestVal + Double.valueOf(getServletContext().getInitParameter("fixedInterest"));
			double graceInterest = principalVal * (totalInterest/12) * Double.valueOf(getServletContext().getInitParameter("gracePeriod"));
			double monthlyPayment = payment + (graceInterest /  Double.valueOf(getServletContext().getInitParameter("gracePeriod")));
			
			form =  "<tr>" + 
					"<td><label for='gracePeriod'>Grace Period Interest: </label></td> <td><label>";
					
			String grace = request.getParameter("graceP");
			if(grace != null)
			{
				form = form + " " + graceInterest + " ";
			} else
			{
				form = form + " 0 ";
			}
					
			form = form + "</label> </td>" + 
					"			</tr>\r\n" + 
					"			<br/>\r\n" + 
					"			<tr>\r\n" + 
					"			<td><label for='monthlypayments'>Monthly Payments: </label></td> <td><label>" + monthlyPayment + "</label> </td>" + 
					"			</tr>\r\n" + 
					"			<br/>	\r\n" + 
					"			<tr>\r\n" + 
					"			<td><button action=\"\" name='restart' label='restart'> Restart </button></td>\r\n" + 
					"			</tr>";
		}else {
			
			form = "<tr>" + 
					"			<td><label for='principal'>Principal: </label></td>\r\n" + 
					"			<td><input id='principalone' name='principalone' type=\"number\"/></td>\r\n" + 
					"			</tr>\r\n" + 
					"			<br/>\r\n" + 
					"			<tr>\r\n" + 
					"			<td><label for='interest'>Interest Rate: </label></td>\r\n" + 
					"			<td><input id='interestone' step ='0.01' name='interestone' type=\"number\"/></td>\r\n" + 
					"			</tr>\r\n" + 
					"			<br/>\r\n" + 
					"			<tr>\r\n" + 
					"			<td><label for='period'>Period: </label></td>\r\n" + 
					"			<td><input id='periodone' name='periodone' type=\"number\"/></td>\r\n" + 
					"			</tr>\r\n" + 
					"			<br/>\r\n" + 
					"			<tr>\r\n" + 
					"			<td><label for='gracePeriod'>Grace Period: </label></td>\r\n" + 
					"			<td><input id='grace' name='graceP' type=\"checkbox\"/></td>\r\n" + 
					"			</tr>\r\n" + 
					"			<br/>\r\n" + 
					"			\r\n" + 
					"			<tr>\r\n" + 
					"			<td><button action=\"submit\" name='submit' label='submit'> Submit </button></td>\r\n" + 
					"			</tr>";

					
	//		double roundOff = Math.round(monthlyPayment * 100.0) / 100.0;
	//		resOut.write("Monthly Payment: " + roundOff+ "\n");
		}
		request.setAttribute("form_html", form);
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
