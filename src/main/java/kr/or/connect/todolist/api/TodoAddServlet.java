package kr.or.connect.todolist.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todolist.dao.TodoDao;
import kr.or.connect.todolist.dto.TodoDto;

@WebServlet("/todos")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TodoAddServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			String title = request.getParameter("title");
			String name = request.getParameter("name");
			int sequence = Integer.parseInt(request.getParameter("sequence")); //validation문제
			
			TodoDto todoDto = new TodoDto();
			todoDto.setTitle(title);
			todoDto.setName(name);
			todoDto.setSequence(sequence);
			
			TodoDao todoDao = new TodoDao();
			todoDao.addTodo(todoDto);
		
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect("./main/todos");
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
