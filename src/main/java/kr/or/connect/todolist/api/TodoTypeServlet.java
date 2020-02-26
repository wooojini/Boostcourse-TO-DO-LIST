package kr.or.connect.todolist.api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todolist.config.TodoType;
import kr.or.connect.todolist.dao.TodoDao;
import kr.or.connect.todolist.dto.TodoDto;


@WebServlet("/todos/*")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public TodoTypeServlet() {
        super();
    }

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		try{
			Long todoId = Long.parseLong(request.getParameter("id"));
			TodoType todoType = TodoType.valueOf(request.getParameter("type"));
		
			TodoDto todoDto = new TodoDto();
			todoDto.setId(todoId);
			todoDto.setType(TodoType.getNextTypeName(todoType));
			
			TodoDao todoDao = new TodoDao();
			todoDao.updateTodo(todoDto);
			
			response.setStatus(HttpServletResponse.SC_OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
