package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.EventDAO;


public class EventDeleteOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		EventDAO dao = new EventDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		
//		System.out.println("no : " + no);
		
		int flag = dao.deleteOk(no);
		
		request.setAttribute("flag", flag);
		
	}

}
