package model2;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.MemberTO;
import model1.SignUpDAO;

public class SignUpOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			int receiveMailFlag = Integer.parseInt(request.getParameter("receivemailflag"));
			//String signUpDate = request.getParameter("signupdate");
			//String loginDate = request.getParameter("logindate");
			int likeColor = Integer.parseInt(request.getParameter("likecolor"));
			String seasonOfLikeColor = request.getParameter("seasonoflikecolor");
			//int numOfText = Integer.parseInt(request.getParameter("numoftext"));
			//int numOfText = 0;
			//int numOfComment = Integer.parseInt(request.getParameter("numofcomment"));
			//int numOfComment = 0;
			MemberTO to = new MemberTO();
			to.setId(id);
			to.setPassword(password);
			to.setName(name);
			to.setEmail(email);
			to.setReceiveMailFlag(receiveMailFlag);
			//to.setSignUpDate(signUpDate);
			//to.setLoginDate(loginDate);
			to.setLikeColor(likeColor);
			to.setSeasonOfLikeColor(seasonOfLikeColor);
			//to.setNumOfText(numOfText);
			//to.setNumOfComment(numOfComment);

			//확인
			//System.out.println(id+name+email+"signupokaction");
			//System.out.println(likeColor);
			
			SignUpDAO dao = new SignUpDAO();
			
			int flag = dao.signUpOk(to);

			request.setAttribute("flag", flag);
			
	}

}
