package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.EventDAO;
import model1.EventTO;



public class EventModifyAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		EventDAO dao = new EventDAO();
		
		int no = Integer.parseInt(request.getParameter("no"));
		
//		System.out.println("no : " + no);
		
		EventTO to = new EventTO();
		to.setNo(no);
		
		to = dao.getEventModifyViewData(to);
		
		request.setAttribute("eventModifyViewData", to);
		
	}

}
