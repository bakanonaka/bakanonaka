package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ManageDAO;

public class InputProductDataOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			ManageDAO dao = new ManageDAO();
			
			int flag = dao.readFileAndInputProductToDB();
			
			request.setAttribute("flag", flag);
	}

}
