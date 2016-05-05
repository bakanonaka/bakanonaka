package model2;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductDAO;
import model1.ReviewBoardDAO;
import model1.ReviewBoardTO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewWriteOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		
		int memNo = 0;
		String subject = "";
		String brandNoName = "";
		String productNoName = "";
		String content = "";
		String reviewImgName = "";
		int countOfStar = 0;
		
		try {
			
			//Server Option 변경해줘야함. Serve moules without publishing
			String uploadPath = ContextPath.getInstance().getPath() + "/uploadReviewImage";	//사진 올린거 어디다가 저장할지 경로?
//			System.out.println("uploadPath : " + uploadPath);
			
			MultipartRequest multi = new MultipartRequest(request, uploadPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());

			memNo = Integer.parseInt(multi.getParameter("memNo").trim());
			subject = multi.getParameter("subject");
			brandNoName = multi.getParameter("brandNo");
			productNoName = multi.getParameter("colorNo");
			content = multi.getParameter("content");
			reviewImgName = multi.getOriginalFileName("reviewImgName");
			countOfStar = Integer.parseInt(multi.getParameter("countOfStar"));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest NumberFormatException" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("MultipartRequest IOException" + e.getMessage());
		}
		
		System.out.println("memNo : " + memNo);
		System.out.println("subject : " + subject);
		System.out.println("brandNoName : " + brandNoName);
		System.out.println("productNoName : " + productNoName);
		System.out.println("content : " + content);
		System.out.println("reviewImgName : " + reviewImgName);
		System.out.println("countOfStar : " + countOfStar);
		
		
		int brandNo = Integer.parseInt(brandNoName.substring(0, brandNoName.indexOf(".")));
		int productNo = Integer.parseInt(productNoName.substring(0, productNoName.indexOf(".")));
		
		//split으로 쪼갤 수 없음.
//		String[] arr = eventNoName.split(".");
//		System.out.println("arr.length : " + arr.length);
		
		ReviewBoardDAO dao = new ReviewBoardDAO();

		ReviewBoardTO to = new ReviewBoardTO();
		to.setSubject(subject);
		to.setContent(content);
		to.setReviewImgName(reviewImgName);
		to.setCountOfStar(countOfStar);
		
		System.out.println("productNo : " + productNo);
		System.out.println("brandNo : " + brandNo);
		
		int flag = dao.writeOk(to, memNo, productNo, brandNo);
		
		if(flag == 0) {
			System.out.println("Review 글 입력 성공");
			
			
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
			
		} else {
			System.out.println("Review 글 입력 실패");
		}
		
		request.setAttribute("flag", flag);
		
	}

}
