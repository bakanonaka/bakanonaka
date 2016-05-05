package model2;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.RequestBoardDAO;
import model1.RequestBoardTO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class RequestModifyOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int seq = 0;
		String subject = "";
		String brandNoName = "";
		String content = "";
		String newRequestImgName = "";
		
		try {
			
			//Server Option 변경해줘야함. Serve moules without publishing
			String uploadPath = ContextPath.getInstance().getPath() + "/uploadRequestImage";
//			System.out.println("uploadPath : " + uploadPath);
			
//			String uploadPath = "C://java//workspace//ProjectR//WebContent//uploadRequestImage";
			MultipartRequest multi = new MultipartRequest(request, uploadPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());

			seq = Integer.parseInt(multi.getParameter("seq"));
			subject = multi.getParameter("subject");
			brandNoName = multi.getParameter("brandNo");
			content = multi.getParameter("content");
			newRequestImgName = multi.getFilesystemName("newRequestImgName");
			// out.println(multi.getFilesystemName("upload1") + "<br/>");	//변경된 파일명
			
			// out.println(multi.getOriginalFileName("upload1") + "<br/>");	//실제 파일명
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest NumberFormatException" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest IOException" + e.getMessage());
		}
		
		System.out.println("seq : " + seq);
		System.out.println("subject : " + subject);
		System.out.println("newRequestImgName : " + newRequestImgName);
		System.out.println("brandNoName : " + brandNoName);
		System.out.println("content : " + content);
//		System.out.println(brandNoName.substring(0, brandNoName.indexOf(".")));
		
		int brandNo = Integer.parseInt(brandNoName.substring(0, brandNoName.indexOf(".")));
		
		//split으로 쪼갤 수 없음.
//		String[] arr = brandNoName.split(".");
//		System.out.println("arr.length : " + arr.length);
		
		RequestBoardDAO dao = new RequestBoardDAO();

		RequestBoardTO to = new RequestBoardTO();
		to.setSubject(subject);
		to.setContent(content);

		//기존에 DB에 저장된어 있던 이미지 이름 가져옴
		String storedImageName = dao.getImageNameBeforeModify(seq);
		
		int flag = 1;
		
		if(newRequestImgName == null || newRequestImgName.equals("")) {
			//새로 이미지를 올리지 않은 경우 기존 DB에 저장된 것 그대로 다시 저장
			flag = dao.modifyOk(to, seq, brandNo, storedImageName);
		} else {
			//새 이미지를 올린 경우
			flag = dao.modifyOk(to, seq, brandNo, newRequestImgName);
		}
		
		if(flag == 0) {
			System.out.println("수정 성공");

			if(newRequestImgName == null || newRequestImgName.equals("")) {	//새로 이미지를 올리지 않은 경우
				
			} else {// 새 이미지를 올린 경우
			
				if(storedImageName == null || storedImageName.equals("")) {// 기존 이미지가 없었을때
					
//					System.out.println("여기로 온다.");
				} else {
					
					//기존에 저장되어 있던 이미지 삭제
					String imagePath = ContextPath.getInstance().getPath() + "/uploadRequestImage";
					File imagefile2 = new File(imagePath, storedImageName);
					imagefile2.delete();
				}
			}
			
		} else {
			System.out.println("수정 실패");
			
			if(newRequestImgName == null || newRequestImgName.equals("")) {	//새로 이미지를 올리지 않은 경우
		
			} else {
				//새로 올린 이미지 삭제
				String imagePath = ContextPath.getInstance().getPath() + "/uploadRequestImage";
				File imagefile2 = new File(imagePath, newRequestImgName);
				imagefile2.delete();		
			}
		}
		
		request.setAttribute("flag", flag);
		
	}

}
