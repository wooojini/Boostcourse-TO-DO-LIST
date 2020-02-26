package kr.or.connect.todolist.config;

public enum TodoType {
	TODO, DOING, DONE;
	
	public static String getNextTypeName(TodoType type) {
		String nextTypeName = null;
		
		switch (type) {
		case TODO:
			nextTypeName = DOING.name();
			break;
			
		case DOING:
			nextTypeName = DONE.name();
			break;
			
		case DONE:
			throw new IllegalArgumentException();
		}
		
		return nextTypeName;
	}
}
