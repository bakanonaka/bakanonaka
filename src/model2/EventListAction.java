package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.EventDAO;
import model1.EventListTO;
import model1.EventTO;

public class EventListAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			
			String cpage_text = request.getParameter("cpage");
			
			int cpage = Integer.parseInt(cpage_text);
			
			System.out.println("eventlist cpage : " + cpage);
			
			EventListTO eventListTO = new EventListTO();
			eventListTO.setCpage(cpage);
			
			EventDAO dao = new EventDAO();
			eventListTO = dao.getEventsList(eventListTO);
			
			int flag = 1;
			
			if(eventListTO.getEventsList().size() > 0) {
				flag = 0;
			} else if(eventListTO.getEventsList().size() == 0) {
				flag = 2;
			}
			
			request.setAttribute("eventsList", eventListTO);
			request.setAttribute("flag", flag);
			
	}

}
