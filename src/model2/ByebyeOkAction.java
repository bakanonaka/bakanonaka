package model2;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.MemberTO;
import model1.ModifyDAO;

public class ByebyeOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String why = request.getParameter("why") == null? "" : request.getParameter("why");

			MemberTO to = new MemberTO();
			
			to.setId(id);
			to.setPassword(password);
			
			ModifyDAO dao = new ModifyDAO();
			
			int flag = dao.byebyeOk(to, why);

			request.setAttribute("flag", flag);
			
	}

}
