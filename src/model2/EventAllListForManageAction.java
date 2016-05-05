package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.EventDAO;
import model1.EventTO;

public class EventAllListForManageAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

			EventDAO dao = new EventDAO();
			ArrayList<EventTO> eventAllListForMange = dao.getAllEventsListForManage();
			
			int flag = 1;
			
			if(eventAllListForMange.size() > 0) {
				flag = 0;
			} else if(eventAllListForMange.size() == 0) {
				flag = 2;
			}
			
			request.setAttribute("eventAllListForManage", eventAllListForMange);
			request.setAttribute("flag", flag);
			
	}

}
