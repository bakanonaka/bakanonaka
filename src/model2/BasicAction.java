package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BasicAction {
	public void execute(HttpServletRequest request, HttpServletResponse response);
}
