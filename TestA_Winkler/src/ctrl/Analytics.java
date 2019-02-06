package ctrl;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import model.Loan;
//import listener.MaxPrincipal;


/**
 * Servlet implementation class Start
 */
@WebServlet({"/Admin", "/admin", "/Admin/*"})
public class Analytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analytics() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {

	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String adminPage = "/AdminPage.jspx";
		String target = adminPage;
		response.setContentType("text/plain");
		Writer resOut = response.getWriter();
		resOut.write("" + request.getServletContext().getAttribute("maxPrincipal"));
		double maxPrincipal = listener.MaxPrincipal.getMaxPrincipal();
//		resOut.write("" + maxPrincipal);
		
		request.getServletContext().setAttribute("maxPrincipal", maxPrincipal);
		
		
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
