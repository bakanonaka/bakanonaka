package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.MemberTO;
import model1.ModifyDAO;

public class ModifyOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
			String id = request.getParameter("id");			

			MemberTO to = new MemberTO();
			to.setId(id);
			
			ModifyDAO dao = new ModifyDAO();
			to = dao.modifyCheck(to);			
			
			request.setAttribute("MemberTO", to);
			
	}

}
