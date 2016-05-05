package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.commentDAO;



public class CommentWriteOkAction implements BasicAction {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int memNo = 0;
		int grp = 0;
		String comment = "";		
		String boardType = "";		

		memNo = Integer.parseInt(request.getParameter("memNo"));
		grp = Integer.parseInt(request.getParameter("grp"));
		comment = request.getParameter("comment");		
		boardType = request.getParameter("boardType");
			
		commentDAO dao = new commentDAO();		
		int flag = dao.commentOk(memNo, grp, comment, boardType);
		
		if(flag == 0) {
			System.out.println("comment 글 입력 성공");
		} else {
			System.out.println("comment 글 입력 실패");
		}
		
		request.setAttribute("flag", flag);
		
	}

}
