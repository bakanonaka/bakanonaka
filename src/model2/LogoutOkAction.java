package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.LoginDAO;
import model1.MemberTO;

public class LogoutOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

//		String name =request.getParameter("name");
//		System.out.println("로그인 중이던 유저의 name : " + name);
		
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		MemberTO to = new MemberTO();
//		to.setName(name);
		to.setMemNo(memNo);
		
		LoginDAO dao = new LoginDAO();
		int flag = dao.logoutOk(to);
		
		request.setAttribute("flag", flag);
	}

}
