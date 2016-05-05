package model2;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.MemberTO;
import model1.ModifyDAO;

public class ModifySuccessAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			int receiveMailFlag = Integer.parseInt(request.getParameter("receivemailflag"));
			System.out.println("abcd");
			int likeColor = Integer.parseInt(request.getParameter("likecolor"));
			System.out.println("abc");
			String seasonOfLikeColor = request.getParameter("seasonoflikecolor");
			
			MemberTO to = new MemberTO();
			
			to.setId(id);
			to.setPassword(password);
			to.setName(name);
			to.setEmail(email);
			to.setReceiveMailFlag(receiveMailFlag);
			
			to.setLikeColor(likeColor);
			to.setSeasonOfLikeColor(seasonOfLikeColor);

			ModifyDAO dao = new ModifyDAO();
			
			int flag = dao.modifyOk(to);

			request.setAttribute("flag", flag);			
			request.setAttribute("name", name);
			//System.out.println(name);
	}

}
