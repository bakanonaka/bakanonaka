package model2;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductDAO;
import model1.RequestBoardDAO;
import model1.RequestBoardTO;
import model1.ReviewBoardDAO;
import model1.ReviewBoardTO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewModifyOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int seq = 0;
		String subject = "";
		String brandNoName = "";
		String productNoName = "";
		String content = "";
		String newReviewImgName = "";
		int countOfStar = 0;
		
		try {
			
			//Server Option 변경해줘야함. Serve moules without publishing
			String uploadPath = ContextPath.getInstance().getPath() + "/uploadReviewImage";	//사진 올린거 어디다가 저장할지 경로?
//			System.out.println("uploadPath : " + uploadPath);
			
//			String uploadPath = "C://java//workspace//ProjectR//WebContent//uploadRequestImage";
			MultipartRequest multi = new MultipartRequest(request, uploadPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());

			seq = Integer.parseInt(multi.getParameter("seq"));
			subject = multi.getParameter("subject");
			brandNoName = multi.getParameter("brandNo");
			productNoName = multi.getParameter("colorNo");
			content = multi.getParameter("content");
			newReviewImgName = multi.getFilesystemName("newReviewImgName");
			countOfStar = Integer.parseInt(multi.getParameter("countOfStar"));

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
		System.out.println("brandNoName : " + brandNoName);
		System.out.println("productNoName : " + productNoName);
		System.out.println("content : " + content);
		System.out.println("newReviewImgName : " + newReviewImgName);
		System.out.println("countOfStar : " + countOfStar);
		//		System.out.println(brandNoName.substring(0, brandNoName.indexOf(".")));
		
		int brandNo = Integer.parseInt(brandNoName.substring(0, brandNoName.indexOf(".")));
		int productNo = Integer.parseInt(productNoName.substring(0, productNoName.indexOf(".")));
		
		//split으로 쪼갤 수 없음.
//		String[] arr = brandNoName.split(".");
//		System.out.println("arr.length : " + arr.length);
		
		ReviewBoardDAO dao = new ReviewBoardDAO();

		ReviewBoardTO to = new ReviewBoardTO();
		to.setSubject(subject);
		to.setContent(content);
		to.setCountOfStar(countOfStar);
		/*to.setBrandName(brandNoName);
		to.setProductName(productNoName);
		to.setUploadImgName(newuploadImgName);
		to.setProductImgName(newproductImgName);
		*/
		

		//기존에 DB에 저장된어 있던 이미지 이름 가져옴
		String storedImageName = dao.getImageNameBeforeModify(seq);
		
		int flag = 1;
		
		if(newReviewImgName == null || newReviewImgName.equals("")) {
			//새로 이미지를 올리지 않은 경우 기존 DB에 저장된 것 그대로 다시 저장
			flag = dao.modifyOk(to, seq, productNo, brandNo, storedImageName);
		} else {
			//새 이미지를 올린 경우
			flag = dao.modifyOk(to, seq, brandNo, productNo, newReviewImgName);
		}
		
		if(flag == 0) {
			
			System.out.println("수정 성공");

			// 후기글 중 productNo에 대한 별점 합과 글 갯수를 가져온다.
			Integer[] dataOfStar = dao.getSumOfStarOfProduct(productNo);
			
			int productCountOfStar = dataOfStar[0];
			int count = dataOfStar[1];

			if(count > 0) {//DB에 제품의 후기가 있으면,
				ProductDAO pDao = new ProductDAO();
				// productNo의 별점을 몫으로 계산하여 저장한다.
				int pFlag = pDao.updateCountOfStarOfProduct(productNo, productCountOfStar/count);
				
				if(pFlag == 0) {
					System.out.println("화장품 별점 입력 성공");	
				} else {
					System.out.println("화장품 별점 입력 실패");
				}
			} else {// 제품의 후기가 없으면 처리 안함.
				
			}	
						
			if(newReviewImgName == null || newReviewImgName.equals("")) {	//새로 이미지를 올리지 않은 경우
				
			} else {// 새 이미지를 올린 경우
			
				if(storedImageName == null || storedImageName.equals("")) {// 기존 이미지가 없었을때
					
					System.out.println("여기로 온다.");
				} else {
					
					//기존에 저장되어 있던 이미지 삭제
					String imagePath = ContextPath.getInstance().getPath() + "/uploadReviewImage";
					File imagefile2 = new File(imagePath, storedImageName);
					imagefile2.delete();
				}
			}
			
		} else {
			System.out.println("수정 실패");
			
			if(newReviewImgName == null || newReviewImgName.equals("")) {	//새로 이미지를 올리지 않은 경우
				
			} else {
				//새로 올린 이미지 삭제
				String imagePath = ContextPath.getInstance().getPath() + "/uploadReviewImage";
				File imagefile2 = new File(imagePath, newReviewImgName);
				imagefile2.delete();
			}
		}
		
		request.setAttribute("flag", flag);
		
	}

}
