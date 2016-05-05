package model2;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.MemberTO;
import model1.SignUpDAO;

public class SignUpFBAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
//System.out.println(id+password+name+email);
			MemberTO to = new MemberTO();
			to.setId(id);
			to.setPassword(password);
			to.setName(name);
			to.setEmail(email);
			
			SignUpDAO dao = new SignUpDAO();
			
			int flag = dao.signUpFB(to);

			request.setAttribute("flag", flag);
			
			//request.setAttribute("id", id);
			//request.setAttribute("password", password);
			
	}

}
