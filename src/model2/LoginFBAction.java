package model2;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.LoginFBDAO;
import model1.MemberTO;

public class LoginFBAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
					
			String id = request.getParameter("id");
//System.out.println(id+password+name+email);
			MemberTO to = new MemberTO();

			to.setId(id);
			
			LoginFBDAO dao = new LoginFBDAO();
			
			to = dao.loginFBOk(to);

			int flag = 0;
		

			request.setAttribute("flag", flag);
			request.setAttribute("id", id);
			request.setAttribute("name", to.getName());
			request.setAttribute("memNo", to.getMemNo());			
			request.setAttribute("password", to.getPassword());
	}

}
