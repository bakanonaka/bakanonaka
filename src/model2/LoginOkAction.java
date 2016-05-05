package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.LoginDAO;
import model1.MemberTO;

public class LoginOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
			String id = request.getParameter("id");
			String password = request.getParameter("password");

			MemberTO to = new MemberTO();
			to.setId(id);
			
			LoginDAO dao = new LoginDAO();
			to = dao.loginOk(to);
			
			int flag = 1;
			
//			System.out.println("id 입력값 : " + id);
//			System.out.println("password 입력값 : " + password);
//			System.out.println("admin 계정의 password : " + to.getPassword());
			
			if(password.equals(to.getPassword())) {
				flag = 0; //성공
			} else {
				flag = 1; //실패
			}

			request.setAttribute("flag", flag);
			request.setAttribute("id", id);
			request.setAttribute("name", to.getName());
			request.setAttribute("memNo", to.getMemNo());
	}

}

