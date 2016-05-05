package model2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.RequestBoardDAO;
import model1.RequestBoardTO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RequestWriteOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		
		int memNo = 0;
		String subject = "";
		String brandNoName = "";
		String content = "";
		String requestImgName = "";
		
		
		try {
			
			//Server Option 변경해줘야함. Serve moules without publishing
			String uploadPath = ContextPath.getInstance().getPath() + "/uploadRequestImage";
//			System.out.println("uploadPath : " + uploadPath);
			
//			String uploadPath = "C://java//workspace//ProjectR//WebContent//uploadRequestImage";
			MultipartRequest multi = new MultipartRequest(request, uploadPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());

			memNo = Integer.parseInt(multi.getParameter("memNo"));
			subject = multi.getParameter("subject");
			brandNoName = multi.getParameter("brandNo");
			content = multi.getParameter("content");
			requestImgName = multi.getOriginalFileName("requestImgName");
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest NumberFormatException" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest IOException" + e.getMessage());
		}
//		
//		
//		int memNo = Integer.parseInt(request.getParameter("memNo"));
//		String subject = request.getParameter("subject");
//		String brandNoName = request.getParameter("brandNoName");
//		String content = request.getParameter("content");
//		String requestImgName = request.getParameter("requestImgName")==null? "" : request.getParameter("requestImgName");
		
		System.out.println("memNo : " + memNo);
		System.out.println("subject : " + subject);
		System.out.println("brandNoName : " + brandNoName);
		System.out.println("content : " + content);
		System.out.println("requestImgName : " + requestImgName);
		System.out.println(brandNoName.substring(0, brandNoName.indexOf(".")));
		
		
		int brandNo = Integer.parseInt(brandNoName.substring(0, brandNoName.indexOf(".")));
		
		//split으로 쪼갤 수 없음.
//		String[] arr = eventNoName.split(".");
//		System.out.println("arr.length : " + arr.length);
		
		RequestBoardDAO dao = new RequestBoardDAO();

		RequestBoardTO to = new RequestBoardTO();
		to.setSubject(subject);
		to.setContent(content);
		to.setRequestImgName(requestImgName);
		
		int flag = dao.writeOk(to, memNo, brandNo);
		
		if(flag == 0) {
			System.out.println("Request 글 입력 성공");
		} else {
			System.out.println("Request 글 입력 실패");
		}
		
		request.setAttribute("flag", flag);
		
	}

}
