package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.MemberListDAO;
import model1.MemberTO;

public class MemSelectedAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
						
			String memNo = request.getParameter("memNo");			
			String[] memNos = memNo.split(",");
							
			
			MemberListDAO dao = new MemberListDAO();
			
			
			String emailsstr = dao.memberSelectedOk(memNos);		
			
			
			//String[] emails = emailsstr.split(";");
			//System.out.println(emails[6]);
			
			request.setAttribute("emails", emailsstr);
			
							
			
			
			
			
	}

}
