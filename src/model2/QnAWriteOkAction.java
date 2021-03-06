package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.QnABoardDAO;
import model1.QnABoardTO;

public class QnAWriteOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String subject = request.getParameter("subject");
		String eventNoName = request.getParameter("eventNoName");
		String content = request.getParameter("content");
		
		
		System.out.println("memNo : " + memNo);
		System.out.println("subject : " + subject);
		System.out.println("eventNoName : " + eventNoName);
		System.out.println("content : " + content);
		System.out.println(eventNoName.substring(0, eventNoName.indexOf(".")));
		
		int eventNo = Integer.parseInt(eventNoName.substring(0, eventNoName.indexOf(".")));
		
		//split으로 쪼갤 수 없음.
//		String[] arr = eventNoName.split(".");
//		System.out.println("arr.length : " + arr.length);
		
		QnABoardDAO dao = new QnABoardDAO();

		QnABoardTO to = new QnABoardTO();
		to.setSubject(subject);
		to.setContent(content);
		
		int flag = dao.writeOk(to, memNo, eventNo);
		
		if(flag == 0) {
			System.out.println("QnA 글 입력 성공");
		} else {
			System.out.println("QnA 글 입력 실패");
		}
		
		request.setAttribute("flag", flag);
		
	}

}
