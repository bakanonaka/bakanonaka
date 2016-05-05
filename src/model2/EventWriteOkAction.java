package model2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model1.EventDAO;
import model1.EventTO;

public class EventWriteOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		
		String subject = "";
		String brandNoName = "";
		String eventDate = "";
		String eventImgName = "";
		String url = "";

		try {
			
			//Server Option 변경해줘야함. Serve moules without publishing
			String uploadPath = ContextPath.getInstance().getPath() + "/eventImg";
//			System.out.println("uploadPath : " + uploadPath);
			
//			String uploadPath = "C://java//workspace//ProjectR//WebContent//uploadRequestImage";
			MultipartRequest multi = new MultipartRequest(request, uploadPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());

			subject = multi.getParameter("subject");
			brandNoName = multi.getParameter("brandNo");
			eventDate = multi.getParameter("eventDate");
			eventImgName = multi.getOriginalFileName("eventImgName");
			url = multi.getOriginalFileName("url");
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest NumberFormatException" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest IOException" + e.getMessage());
		}

		System.out.println("subject : " + subject);
		System.out.println("brandNoName : " + brandNoName);
		System.out.println("eventDate : " + eventDate);
		System.out.println("eventImgName : " + eventImgName);
		System.out.println(brandNoName.substring(0, brandNoName.indexOf(".")));
		System.out.println("url : " + url);
		
		int brandNo = Integer.parseInt(brandNoName.substring(0, brandNoName.indexOf(".")));
		
		//split으로 쪼갤 수 없음.
//		String[] arr = eventNoName.split(".");
//		System.out.println("arr.length : " + arr.length);
		
		EventDAO dao = new EventDAO();

		EventTO to = new EventTO();
		to.setTitle(subject);
		to.setImgName(eventImgName);
		to.setUrl(url);
		
		int flag = dao.writeOk(to, eventDate, brandNo);
		
		if(flag == 0) {
			System.out.println("Request 글 입력 성공");
		} else {
			System.out.println("Request 글 입력 실패");
		}
		
		request.setAttribute("flag", flag);
		
	}

}
