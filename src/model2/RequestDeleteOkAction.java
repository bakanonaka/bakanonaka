package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.RequestBoardDAO;

import java.io.File;

public class RequestDeleteOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		
		RequestBoardDAO dao = new RequestBoardDAO();
		
		//기존에 DB에 저장된어 있던 이미지 이름 가져옴
		String storedImageName = dao.getImageNameBeforeModify(seq);
				
		
		int flag = dao.deleteOk(seq, memNo);
		
		if(flag == 0) {

			if(storedImageName == null || storedImageName.equals("")) {// 기존 이미지가 없었을때
				
//				System.out.println("여기로 온다.");
			} else {
				
				//기존에 저장되어 있던 이미지 삭제
				String imagePath = ContextPath.getInstance().getPath() + "/uploadRequestImage";
				File imagefile = new File(imagePath, storedImageName);
				imagefile.delete();
			}
			
//			System.out.println("imagePath : " + imagePath);
//			System.out.println("requestImgName : " + requestImgName);
			System.out.println("삭제 성공");
		} else {
			System.out.println("삭제 실패");
		}
		
		request.setAttribute("flag", flag);
		
	}

}
