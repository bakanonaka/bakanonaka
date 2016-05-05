package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.EventDAO;
import model1.EventTO;

public class EventAllListAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

			EventDAO dao = new EventDAO();
			ArrayList<EventTO> eventAllList = dao.getAllEventsList();
			
			int flag = 1;
			
			if(eventAllList.size() > 0) {
				flag = 0;
			} else if(eventAllList.size() == 0) {
				flag = 2;
			}
			
			request.setAttribute("eventAllList", eventAllList);
			request.setAttribute("flag", flag);
			
	}

}
