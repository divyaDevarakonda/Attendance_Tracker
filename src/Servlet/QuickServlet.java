package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import SheetPackageTest.SheetsQuickStart;
import service.AttendanceService;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuickServlet extends HttpServlet {

	/**
	 * this life-cycle method is invoked when this servlet is first accessed by
	 * the client
	 */
	public void init(ServletConfig config) {
		System.out.println("Servlet is being initialized");
	}

	/**
	 * handles HTTP GET request
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		PrintWriter writer = response.getWriter();

		writer.println("<html>Hello, I am a Java servlet!</html>");
		writer.flush();
	}

	/**
	 * handles HTTP POST request
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String StudentId = null;
			String key = "";
			String profKey = "";
			boolean marked = false;
			String email = "";
			PrintWriter writer = response.getWriter();
			response.setContentType("text/html");
			AttendanceService attendanceService = new AttendanceService();

			if (request.getParameter("student") != null
					&& (request.getParameter("student").equalsIgnoreCase("student"))) {
				boolean validip = attendanceService.getIPAddress(); // ip address validation
				if (!validip) {
					response.getWriter().println(
							"<html><body></html><div style=\"margin:100px 0 0 400px;padding-top:70px;width:350;height:130;background-color:#BEBEBE;border:2px solid red;text-align:center;font-color: red;\">"
									+ "<p>Attendance shall be submitted from CSC 131 class room.</p></div></body></html>");
					return;
				}

				String attTime = SheetsQuickStart.getTimeforAttendance(); // Timer
				long elapsedTime = attendanceService.calculateTimeLapsed(attTime);
				if (elapsedTime > 300000) { // timer
					response.getWriter().println(
							"<html><body></html><div style=\"margin:100px 0 0 400px;padding-top:70px;width:350;height:130;background-color:#BEBEBE;border:2px solid red;text-align:center;font-color: red;\">"
									+ "<p>Attendance Page is timed out.</p></div></body></html>");
					return;
				}

				if (request.getParameter("StudentId") != null && request.getParameter("StudentId") != "") {
					StudentId = request.getParameter("StudentId");
				}
				if (request.getParameter("Key") != null && request.getParameter("Key") != "") {
					key = request.getParameter("Key");
				}
				if (request.getParameter("email") != null && request.getParameter("email") != "") {
					email = request.getParameter("email");
				}
				Map<String, String> StudentMap = (Map<String, String>) SheetsQuickStart.getStudentIdFromSheets();
				String profInputKey = SheetsQuickStart.getProfkey();

				String rowNo = attendanceService.validateStudentId(StudentMap, StudentId); // validate student id
				boolean validKey = attendanceService.validateStudentKey(profInputKey, key); // validate key entered
																							
				if (!validKey) {
					response.getWriter().println(
							"<html><body></html><div style=\"margin:100px 0 0 400px;padding-top:70px;width:350;height:130;background-color:#BEBEBE;border:2px solid red;text-align:center;font-color: red;\">"
									+ "<p>Please enter valid key.</p></div></body></html>");
					return;
				}
				if (rowNo == "") {
					response.getWriter().println(
							"<html><body></html><div style=\"margin:100px 0 0 400px;padding-top:70px;width:350;height:130;background-color:#BEBEBE;border:2px solid red;text-align:center;font-color: red;\">"
									+ "<p>Please enter valid student id.</p></div></body></html>");
					return;
				}

				if (rowNo != "" && validKey) {
					SheetsQuickStart.writeDate();
					marked = SheetsQuickStart.markStudentAttendance(rowNo);
				}
				if (marked) {
					if (email != "")
						attendanceService.emailService(email);
					response.getWriter().println(
							"<html><body></html><div style=\"margin:100px 0 0 400px;padding-top:70px;width:350;height:130;background-color:#BEBEBE;border:2px solid red;text-align:center;font-color: red;\">"
									+ "<p>Attendance is successfully marked for student id " + StudentId
									+ "!</p></div></body></html>");
				}

			} else {
				if (request.getParameter("profKey") != null && request.getParameter("profKey") != "") {
					profKey = request.getParameter("profKey");
					SheetsQuickStart.writeProfKey(profKey);
					response.getWriter().println(
							"<html><body></html><div style=\"margin:100px 0 0 400px;padding-top:70px;width:350;height:130;background-color:#BEBEBE;border:2px solid red;text-align:center;font-color: red;\">"
									+ "<p>Key is successfully entered. Thankyou.</p></div></body></html>");
				}

			}

			writer.flush();
		} catch (Exception e) {
			e.getMessage();
		}

	}

	/**
	 * this life-cycle method is invoked when the application or the server is
	 * shutting down
	 */
	public void destroy() {
		System.out.println("Servlet is being destroyed");
	}
}