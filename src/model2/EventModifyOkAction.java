package model2;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model1.EventDAO;
import model1.EventTO;

public class EventModifyOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int no = 0;
		String subject = "";
		String brandNoName = "";
		String eventDate = "";
		String newEventImgName = "";
		String url = "";
		int status = 2;	
		
		try {
			
			//Server Option 변경해줘야함. Serve moules without publishing
			String uploadPath = ContextPath.getInstance().getPath() + "/eventImg";
//			System.out.println("uploadPath : " + uploadPath);
			
//			String uploadPath = "C://java//workspace//ProjectR//WebContent//uploadRequestImage";
			MultipartRequest multi = new MultipartRequest(request, uploadPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());

			no = Integer.parseInt(multi.getParameter("no"));
			subject = multi.getParameter("subject");
			brandNoName = multi.getParameter("brandNo");
			eventDate = multi.getParameter("eventDate");
			url = multi.getParameter("url");
			status = Integer.parseInt(multi.getParameter("status"));
			
			newEventImgName = multi.getFilesystemName("newEventImgName");
			
			// out.println(multi.getFilesystemName("upload1") + "<br/>");	//변경된 파일명
			
			// out.println(multi.getOriginalFileName("upload1") + "<br/>");	//실제 파일명
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest NumberFormatException" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest IOException" + e.getMessage());
		}
		
		System.out.println("no" + no);
		System.out.println("subject : " + subject);
		System.out.println("brandNoName : " + brandNoName);
		System.out.println("eventDate : " + eventDate);
		System.out.println("newEventImgName : " + newEventImgName);
		System.out.println("url : " + url);
		
//		System.out.println(brandNoName.substring(0, brandNoName.indexOf(".")));
		
		int brandNo = Integer.parseInt(brandNoName.substring(0, brandNoName.indexOf(".")));
		
		//split으로 쪼갤 수 없음.
//		String[] arr = brandNoName.split(".");
//		System.out.println("arr.length : " + arr.length);
		
		
		EventDAO dao = new EventDAO();
		
		EventTO to = new EventTO();
		to.setNo(no);
		to.setTitle(subject);
		to.setUrl(url);
		to.setStatus(status);
		
		
		//기존에 DB에 저장된어 있던 이미지 이름 가져옴
		String storedImageName = dao.getImageNameBeforeModify(to);
		
		System.out.println("storedImageName : " + storedImageName);
		
		
		int flag = 1;
		
		if(newEventImgName == null || newEventImgName.equals("")) {
			//새로 이미지를 올리지 않은 경우 기존 DB에 저장된 것 그대로 다시 저장
			flag = dao.modifyOk(to, no, brandNo, storedImageName, eventDate);
		} else {
			//새 이미지를 올린 경우
			flag = dao.modifyOk(to, no, brandNo, newEventImgName, eventDate);
		}
		
		if(flag == 0) {
			System.out.println("수정 성공");

			if(newEventImgName == null || newEventImgName.equals("")) {	//새로 이미지를 올리지 않은 경우
				
			} else {// 새 이미지를 올린 경우
			
				if(storedImageName == null || storedImageName.equals("")) {// 기존 이미지가 없었을때
					
//					System.out.println("여기로 온다.");
				} else {
					
					//기존에 저장되어 있던 이미지 삭제
					String imagePath = ContextPath.getInstance().getPath() + "/eventImg";
					File imagefile2 = new File(imagePath, storedImageName);
					imagefile2.delete();
				}
			}
			
		} else {
			System.out.println("수정 실패");
			
			if(newEventImgName == null || newEventImgName.equals("")) {	//새로 이미지를 올리지 않은 경우
				
			} else {
				//새로 올린 이미지 삭제
				String imagePath = ContextPath.getInstance().getPath() + "/eventImg";
				File imagefile2 = new File(imagePath, newEventImgName);
				imagefile2.delete();
			}
		}
		
		request.setAttribute("flag", flag);
		
	}

}
