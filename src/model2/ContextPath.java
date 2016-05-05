package model2;

public class ContextPath {
	private static ContextPath instance;
	private final String path;
	
	private ContextPath(String path) {
		this.path = path;
	}
	
	public static void createInstance(String path) {
		instance = new ContextPath(path);
	}

	public static ContextPath getInstance() {
	
		if(instance == null) {
			throw new NullPointerException();
		} else {
			return instance;	
		}
		
	}

	public String getPath() {
		return path;
	}
	
	
}
