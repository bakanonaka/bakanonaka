package model2;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.CommentTO;
import model1.commentDAO;



public class CommentListOkAction implements BasicAction {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
				
		int grp = 0;		
		String boardType = "";		
		
		grp = Integer.parseInt(request.getParameter("grp"));		
		boardType = request.getParameter("boardType");
			
		CommentTO commentList = new CommentTO();
		commentList.setGrp(grp);
		commentList.setBoardType(boardType);		
		
		commentDAO dao = new commentDAO();	
		ArrayList<CommentTO> commentLists = dao.commentListOk(commentList);
		
		
//		System.out.println("코멘트" + commentList.getMemNo());
//		request.setAttribute("memNo", commentList.getMemNo());
		request.setAttribute("commentLists", commentLists);
		
	}

}
