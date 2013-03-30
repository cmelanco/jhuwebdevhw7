package cmelanconhw7;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.rbevans.bookingrate.BookingDay;
import com.rbevans.bookingrate.Rates;

/**
 * Servlet implementation class TourCostServlet
 */
@WebServlet("/TourCostServlet")
public class TourCostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TourCostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
		        response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();
		        String month = null;
		        String day = null;
		        String year = null;
		        String hike = null;
		        String numDays = null;
		        try {
		            
		        	out.println("<html>");
		            out.println("<head>");
		            out.println("<title>Beartooth Hiking Reservation</title>");
		            out.println("</head>");
		            out.println("<body bgcolor=\"#00CCFF\" text=\"#000000\">");
		            out.println("<h1>Beartooth Hiking Reservation</h1>");
		            
		        	month = request.getParameter("months");
		        	int monthInt;
		        	try {
		        		monthInt = Integer.parseInt(month);
		        	} catch (NumberFormatException e) {
		        		out.println("Invalid booking month entered!");
		        		out.println("</body>");
			            out.println("</html>");
			            return;
		        	}
		        	
		        	day = request.getParameter("days");
		        	int dayInt;
		        	try {
		        		dayInt = Integer.parseInt(day);
		        	} catch (NumberFormatException e) {
		        		out.println("Invalid booking day entered!");
		        		out.println("</body>");
			            out.println("</html>");
			            return;
		        	}
		        	
		        	year = request.getParameter("years");
		        	int yearInt;
		        	try {
		        		yearInt = Integer.parseInt(year);
		        	} catch (NumberFormatException e) {
		        		out.println("Invalid booking year entered!");
		        		out.println("</body>");
			            out.println("</html>");
			            return;
		        	}
		        	
		        	numDays = request.getParameter("numdays");
		        	int numDaysInt;
		        	try {
		        		numDaysInt = Integer.parseInt(numDays);
		        	} catch (NumberFormatException e) {
		        		out.println("Invalid booking number of days entered!");
		        		out.println("</body>");
			            out.println("</html>");
			            return;
		        	}
		        	

		        	
		            hike = request.getParameter("hike");
		            if (hike == null) {
		            	hike = "<none entered>";
		            }
		        	double baseCost = 0;
		        	if (hike.equalsIgnoreCase("gardiner")) {
		        		if ( numDaysInt==3 || numDaysInt==5 ) {
		        			baseCost = 40.0;
		        		} else {
			        		out.println("Invalid hike duration entered!");
			        		out.println("</body>");
				            out.println("</html>");
				            return;
		        		}
		        	
		        	} else if (hike.equalsIgnoreCase("hellroaring")) {
		        		if ( numDaysInt==2 || numDaysInt==3 || numDaysInt==4 ) {
		        			baseCost = 35.0;
		        		} else {
			        		out.println("Invalid hike duration entered!");
			        		out.println("</body>");
				            out.println("</html>");
				            return;
		        		}
		        	} else if (hike.equalsIgnoreCase("beaten")) {
		        		if ( numDaysInt==5 || numDaysInt==7 ) {
		        			baseCost = 45.0;
		        		} else {
			        		out.println("Invalid hike duration entered!");
			        		out.println("</body>");
				            out.println("</html>");
				            return;
		        		}
		        	} else{
		        		out.println("Invalid hike entered!");
		        		out.println("</body>");
			            out.println("</html>");
			            return;
		        	}
		            
		            BookingDay startDay = new BookingDay(yearInt, monthInt, dayInt);
		        	if (!startDay.isValidDate()) {
		        		out.println("Invalid booking start date entered!");
		        		out.println(startDay.getValidationStatus());
		        		out.println("</body>");
			            out.println("</html>");
			            return;
		        	}
		        	
			    	Calendar endDate =  startDay.getDate();
			    	endDate.add(Calendar.DAY_OF_MONTH, numDaysInt-1);
			    	BookingDay endDay = new BookingDay(endDate.get(Calendar.YEAR),
			    			endDate.get(Calendar.MONTH)+1, 
			    			endDate.get(Calendar.DAY_OF_MONTH));
		        	if (!endDay.isValidDate()) {
		        		out.println("Invalid booking end date entered!");
		        		out.println(endDay.getValidationStatus());
		        		out.println("</body>");
			            out.println("</html>");
			            return;
		        	}
		        	
		        	Rates rates = new Rates();
		        	rates.setBaseRate(baseCost);
		        	rates.setBeginDate(startDay);
		        	rates.setEndDate(endDay);
		        	if (!rates.isValidDates()) {
		        		out.println("Invalid booking date entered!");
		        		out.println(rates.getDetails());
		        		out.println("</body>");
			            out.println("</html>");
			            return;
		        	}
		        	
			    	
		        	
		        	NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		        	out.println("<table>");
		        	
		        	out.println("<tr>");
		        	out.println("<td>");
		        	out.println("Tour Dates: " + startDay.toString() + " to " + endDay.toString());
		        	out.println("</td>");
		        	out.println("</tr>");
		        	
		        	
		        	out.println("<tr>");
		        	out.println("<td>");
		        	out.println(Integer.toString(rates.getPremiumDays()) + " Premium days at " + formatter.format(rates.getPremiumRate()) + " per day");
		        	out.println("</td>");
		        	out.println("</tr>");
		        	
		        	out.println("<tr>");
		        	out.println("<td>");
		        	out.println(Integer.toString(rates.getNormalDays()) + " Normal days at " + formatter.format(rates.getBaseRate()) + " per day");
		        	out.println("</td>");
		        	out.println("</tr>");
		        	
		        	out.println("<tr>");
		        	out.println("<td>");
		        	out.println("Total Cost = " + formatter.format(rates.getCost()));
		        	out.println("</td>");
		        	out.println("</tr>");
		        	
		        	out.println("</table>");
		            out.println("</body>");
		            out.println("</html>");

		        } finally { 
		            out.close();
		        }
		    } 

}
