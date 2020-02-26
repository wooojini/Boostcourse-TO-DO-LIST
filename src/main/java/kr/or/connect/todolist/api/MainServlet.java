package kr.or.connect.todolist.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todolist.dao.TodoDao;
import kr.or.connect.todolist.dto.TodoDto;

@WebServlet("/main/todos")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		TodoDao todoDao = new TodoDao();
		List<TodoDto> todos = todoDao.getTodos();
		
		if(todos != null) {
			request.setAttribute("todos", todos);
			
			RequestDispatcher requestDispatcher =  request.getRequestDispatcher("/main.jsp");
			requestDispatcher.forward(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
