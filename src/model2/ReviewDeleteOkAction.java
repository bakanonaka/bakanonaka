package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model1.ProductDAO;
import model1.ReviewBoardDAO;

import java.io.File;

public class ReviewDeleteOkAction implements BasicAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int seq = Integer.parseInt(request.getParameter("seq"));
//		int memNo = Integer.parseInt(request.getParameter("memNo")); 맴버 없이 그냥 삭제 됨
		
		ReviewBoardDAO dao = new ReviewBoardDAO();
		
		//기존에 DB에 저장된어 있던 이미지 이름 가져옴
		String storedImageName = dao.getImageNameBeforeModify(seq);
				

		//지우기 전에 seq로 productNo를 가져옴
		int productNo = dao.getProductNo(seq);
		
		int flag = dao.deleteOk(seq);
		
		if(flag == 0) {

			if(storedImageName == null || storedImageName.equals("")) {// 기존 이미지가 없었을때
				
//				System.out.println("여기로 온다.");
			} else {
				
				//기존에 저장되어 있던 이미지 삭제
				String imagePath = ContextPath.getInstance().getPath() + "/uploadReviewImage";
				File imagefile = new File(imagePath, storedImageName);
				imagefile.delete();
			}
			
//			System.out.println("imagePath : " + imagePath);
//			System.out.println("requestImgName : " + requestImgName);
			System.out.println("삭제 성공");
			
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
			System.out.println("삭제 실패");
		}
		
		request.setAttribute("flag", flag);
		
	}

}
