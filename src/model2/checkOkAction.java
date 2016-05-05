package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.CheckDAO;
import model1.MemberTO;

public class checkOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");

			MemberTO to = new MemberTO();
			to.setId(id);
			to.setName(name);
			to.setEmail(email);
			
			CheckDAO dao = new CheckDAO();
			
			int idFlag = dao.checkIdOk(to);
			int nameFlag = dao.checkNameOk(to);
			int emailFlag = dao.checkEmailOk(to);
			//System.out.println(idFlag);
			request.setAttribute("idflag", idFlag);
			request.setAttribute("nameflag", nameFlag);
			request.setAttribute("emailflag", emailFlag);
	}

}
