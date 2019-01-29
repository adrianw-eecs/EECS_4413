package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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
		resOut.write("Hello, World! \n");
		
		String clientIP = request.getRemoteAddr();
		resOut.write("Client IP: " + clientIP + "\n");
		
		int clientPort = request.getRemotePort();
		resOut.write("Client Port: " + clientPort + "\n");
		
		List strList = new ArrayList();
		if(strList.contains(clientIP))
		{
			resOut.write("Your IP is Flagged and has visited this site before\n");
		}
		else {
			strList.add(clientIP);
			resOut.write("IP has been Flagged\n");
		}
		
		
		String clientProtocol = request.getProtocol();
		resOut.write("Client Protocol: " + clientProtocol + "\n");
		
		
		String clientQueryString = request.getQueryString();
		resOut.write("Query string: " + clientQueryString + "\n");
		
		String URI = request.getRequestURI();
		resOut.write("URI: " + URI + "\n");
		
		String contextPath = request.getContextPath();
		resOut.write("Context Path: " + contextPath + "\n");
		
		String servletPath = request.getServletPath();
		resOut.write("Servlet Path: " + servletPath + "\n");
		
		String pathInfo = request.getPathInfo();
		resOut.write("Path Info: " + pathInfo + "\n");
		
		String redirectPath = servletPath + pathInfo;
		String searchPath = "/Startup/YorkBank";
		if(redirectPath.compareTo(searchPath) == 0) {
			String redirectURL = this.getServletContext().getContextPath() + "/Start";
			response.sendRedirect(redirectURL);
		}
		
		resOut.write("-------------------------------------------------------------------------------------------------------" + "\n");
		
		String webXmlParam = getServletContext().getInitParameter("appName");
		resOut.write("Web.xml Parameter: " + webXmlParam + "\n");
//		System.out.println("Hello, Got a request!");
		

		String principal = request.getParameter("principal");
		if(principal == null)
		{
			principal = getServletContext().getInitParameter("principal");
		}
		resOut.write("Query Param principal: " + principal + "\n");
		
		String period = request.getParameter("period");
		if(period == null)
		{
			period = getServletContext().getInitParameter("period");
		}
		resOut.write("Query Param period: " + period + "\n");
		
		String interest = request.getParameter("interest");
		if(interest == null)
		{
			interest = getServletContext().getInitParameter("interest");
		}
		resOut.write("Query Param interest: " + interest + "\n");
		
		double principalVal = Double.parseDouble(principal);
		double interestVal = Double.parseDouble(interest) / 100;
		double periodVal =  Double.parseDouble(period);
		double payment = (interestVal / 12) * principalVal / (1-  Math.pow((1 + (interestVal / 12)),(-1 * periodVal)));
		
		double roundOff = Math.round(payment * 100.0) / 100.0;
		resOut.write("Monthly Payment: " + roundOff+ "\n");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
