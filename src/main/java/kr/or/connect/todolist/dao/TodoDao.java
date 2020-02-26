package kr.or.connect.todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.todolist.dto.TodoDto;

public class TodoDao {
	private static final String dbUrl = "jdbc:mysql://localhost:3306/todo_db"
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&"
			+ "useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String dbUser = "connect_todo";
	private static final String dbPasswd = "connecttodo123";
	private static final String mysqlDriver = "com.mysql.cj.jdbc.Driver";
	
	
	private static void loadMysqlDriver() {
		try {
			Class.forName(mysqlDriver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<TodoDto> getTodos(){
		List<TodoDto> todos = new ArrayList<>();

		loadMysqlDriver();

		String sql = "SELECT id, name, regDate, sequence, title, type FROM todo ORDER BY regDate";
		try(Connection conn = DriverManager.getConnection(dbUrl,dbUser,dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					Long id = rs.getLong("id");
					String name = rs.getString("name");
					String regDate = rs.getDate("regDate").toString();
					int sequence = rs.getInt("sequence");
					String title =  rs.getString("title");
					String type = rs.getString("type");
					
					TodoDto todoDto = new TodoDto();
					todoDto.setId(id);
					todoDto.setName(name);
					todoDto.setRegDate(regDate);
					todoDto.setSequence(sequence);
					todoDto.setTitle(title);
					todoDto.setType(type);
					
					todos.add(todoDto);
				}
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}

		return todos;
	}
	
	public int updateTodo(TodoDto todoDto) {
		int updateCount = 0;
		
		loadMysqlDriver();
		
		String sql = "UPDATE todo SET type = ? WHERE id = ?";
		try(Connection conn = DriverManager.getConnection(dbUrl,dbUser,dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, todoDto.getType());
				ps.setLong(2, todoDto.getId());
				
				updateCount = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return updateCount;
	}
	
	public int addTodo(TodoDto todoDto) {
		int addCount = 0;
		
		loadMysqlDriver();
		
		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";
		try(Connection conn = DriverManager.getConnection(dbUrl,dbUser,dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, todoDto.getTitle());
				ps.setString(2, todoDto.getName());
				ps.setInt(3, todoDto.getSequence());
				
				addCount = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return addCount;
	}
}

