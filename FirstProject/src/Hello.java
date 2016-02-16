

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DBConnection;

import java.sql.*;
/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	public static Statement st;
	public static ResultSet rs;
	public static Connection conn;	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnection db = new DBConnection();
		String msg = null;
		try {
			 
			
			conn = db.getConnect();			//DB Connection
			st = conn.createStatement();
			
			rs = st.executeQuery("select * from student");
			msg = "<tr><th>Student ID</th><th>Name</th><th>Subjects Taken</th></tr>";
			int n=0;
			while(rs.next())
			{
				n++;
			msg = msg+"<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getInt(3)+"</td></tr>";	
			}
			if(n==0)
				msg = "<pre>\n Student record not found</pre>";
			
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			db.closeConnect();
		}
		
		request.setAttribute("message", msg);
		RequestDispatcher rs = getServletContext().getRequestDispatcher("/Results.jsp");
		
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
