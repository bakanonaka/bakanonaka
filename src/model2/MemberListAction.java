package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.MemberListDAO;
import model1.MemberTO;

public class MemberListAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		MemberListDAO dao = new MemberListDAO();
		ArrayList<MemberTO> memberList = dao.boardList();
		request.setAttribute("memberList", memberList);
	}

}
