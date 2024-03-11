package project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.controller.StatusController;
import project.controller.TaskListController;
import project.model.TaskListModel;

import java.io.IOException;

/**
 * Servlet implementation class StatusServlet
 */
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String username=request.getParameter("username");
		String task=request.getParameter("task");
        TaskListModel model=new TaskListModel();
  
        model.settask(task); 
        model.setusername(username);
        model.setstatus("Completed");
        
     //   System.out.println(request.getParameter("username"));
        StatusController control=new StatusController(model);
        if(control.update())
		{
			System.out.println("Status change Success");
			if(control.get())
			{
				request.setAttribute("username", username);
				request.setAttribute("resultList", control.resultList);
				request.getRequestDispatcher("ToDoListView.jsp").forward(request, response);
				System.out.println("Status get Success");
			}
			else {
				System.out.println("Status get Fail");
			}
		}
		else
		{
			System.out.println("Status change Fail");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
