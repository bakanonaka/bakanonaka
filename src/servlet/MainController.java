package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BasicAction;
import model2.BrandListAction;
import model2.ByebyeOkAction;
import model2.CommentListOkAction;
import model2.CommentWriteOkAction;
import model2.ContextPath;
import model2.EventAllListAction;
import model2.EventAllListForManageAction;
import model2.EventDeleteOkAction;
import model2.EventListAction;
import model2.EventModifyAction;
import model2.EventModifyOkAction;
import model2.EventWriteOkAction;
import model2.InputProductDataOkAction;
import model2.LoginFBAction;
import model2.LoginOkAction;
import model2.LogoutOkAction;
import model2.MailOkAction;
import model2.MailOkActionAdmin;
import model2.MailOkActionAdminKick;
import model2.MemSelectedAction;
import model2.MemberListAction;
import model2.ModifyOkAction;
import model2.ModifySuccessAction;
import model2.ProductAction;
import model2.ProductActionByBrand;
import model2.ProductActionByProduct;
import model2.ProductListAction;
import model2.ProductListWithColorAction;
import model2.ProductOtherListAction;
import model2.ProductViewAction;
import model2.QnADeleteOkAction;
import model2.QnAListAction;
import model2.QnAModifyAction;
import model2.QnAModifyOkAction;
import model2.QnAViewAction;
import model2.QnAWriteOkAction;
import model2.RequestDeleteOkAction;
import model2.RequestListAction;
import model2.RequestListForBrandAction;
import model2.RequestModifyAction;
import model2.RequestModifyOkAction;
import model2.RequestViewAction;
import model2.RequestWriteOkAction;
import model2.ReviewDeleteOkAction;
import model2.ReviewListAction;
import model2.ReviewListOfProductAction;
import model2.ReviewModifyAction;
import model2.ReviewModifyOkAction;
import model2.ReviewViewAction;
import model2.ReviewWriteOkAction;
import model2.SignUpFBAction;
import model2.SignUpOkAction;
import model2.checkOkAction;

@WebServlet("*.board")
public class MainController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		ContextPath.createInstance(getServletContext().getRealPath(""));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doGet(req, resp);
		
//		System.out.println("MainController - doGet, ip : " + request.getRemoteAddr() + "\t" + new Date());
		
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		
//		System.out.println("MainController - doPost, ip : " + request.getRemoteAddr() + "\t" + new Date());
		
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
//		System.out.println("req.getContextPath() : " + request.getContextPath());
		
		String path = request.getRequestURI().replaceAll(request.getContextPath(), "");
		
		String url = "";
		BasicAction basicAction = null;
		
		if(path.equals("/*.board") || path.equals("/index.board")) {	//--------메인 페이지
			
			url = "/index.jsp";
			
		} else if(path.equals("/initProductList.board")) {

			basicAction = new ProductListAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/initProductList_json.jsp";
		} else if(path.equals("/initProductListWithColor.board")) {

			basicAction = new ProductListWithColorAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/initProductList_json.jsp";
		} else if(path.equals("/productViewInfo.board")) {
			
			basicAction = new ProductViewAction();
			basicAction.execute(request, response);
			
			basicAction = new ProductOtherListAction();
			basicAction.execute(request, response);
			
			basicAction = new ReviewListOfProductAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/productView_json.jsp";
			
		} else if(path.equals("/event.board")) {	//-------------------이벤트 페이지
			
			url = "/model2/eventList.jsp";
			
		} else if(path.equals("/initEventList.board")) {
			
			basicAction = new EventListAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/initEventList_json.jsp";
			
		} else if(path.equals("/reviewBoard.board")) {	//----------------------후기 게시판
			
			url = "/model2/reviewBoardList.jsp";
			// <script src="js/custom_by_yyj_for_reviewBoardList.js"></script> 여기로 감
		} else if(path.equals("/initReviewBoardList.board")) {
			
			basicAction = new ReviewListAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/initReviewBoardList_json.jsp";
			
		} else if(path.equals("/reviewBoardViewInfo.board")) {
			
			basicAction = new ReviewViewAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/reviewBoardView_json.jsp";
			
		} else if(path.equals("/reviewBoardWrite.board")) {
			
//			basicAction = new ProductAction();	
//			basicAction.execute(request, response);
			
			basicAction = new BrandListAction();	
			basicAction.execute(request, response);
		
			url = "/model2/reviewBoardWrite.jsp";
			
		} else if(path.equals("/reviewBoardWritebyBrand.board")) {
			
			basicAction = new ProductActionByBrand();	
			basicAction.execute(request, response);
			
			url = "/ajaxResult/productsForRivewBoardWrite.jsp";
			
		} else if(path.equals("/reviewBoardWritebyProduct.board")) {
			
			basicAction = new ProductActionByProduct();	
			basicAction.execute(request, response);
			
			url = "/ajaxResult/colorsForRivewBoardWrite.jsp";
			
		} else if(path.equals("/reviewBoardWrite_ok.board")) {
			//			
			basicAction = new ReviewWriteOkAction();
			basicAction.execute(request, response);

			url = "/reviewBoard.board";	//url = "reviewBoard.board"; 이게 아님
			
		} else if(path.equals("/reviewBoardModify.board")) {
			
			basicAction = new ReviewModifyAction();	
			basicAction.execute(request, response);
			
//			basicAction = new ProductAction();
//			basicAction.execute(request, response);
			
			basicAction = new BrandListAction();	
			basicAction.execute(request, response);
			
			url = "/model2/reviewBoardModify.jsp";
			
		} else if(path.equals("/reviewBoardModify_ok.board")) {
			
			basicAction = new ReviewModifyOkAction();
			basicAction.execute(request, response);
			
			url = "reviewBoard.board";
			
		} else if(path.equals("/reviewBoardDelete_ok.board")) {
			
			basicAction = new ReviewDeleteOkAction();
			basicAction.execute(request, response);
			
			url = "reviewBoard.board";
			
// 후기 게시판 끝

// qna 게시판 시작
			
		} else if(path.equals("/qnaBoard.board")) {		//---------------------- 이벤트 Q&A 게시판
			
			url = "/model2/qnaBoardList.jsp";
			
		} else if(path.equals("/qnaEventInfo.board")) {
			
			basicAction = new EventAllListAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/initQnaAllEventList_json.jsp";
			
		} else if(path.equals("/initQnaBoardList.board")) {
			
			basicAction = new QnAListAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/initQnaBoardList_json.jsp";
			
		} else if(path.equals("/qnaBoardView.board")) {
			
			basicAction = new QnAViewAction();
			basicAction.execute(request, response);
			
			url = "/model2/qnaBoardView.jsp";

		} else if(path.equals("/qnaBoardWrite.board")) {
	//			
			basicAction = new EventAllListAction();
			basicAction.execute(request, response);
	//			
			url = "/model2/qnaBoardWrite.jsp";
			
		} else if(path.equals("/qnaBoardWrite_ok.board")) {
			//			
			basicAction = new QnAWriteOkAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/qnaBoardWriteOk.jsp";
		
		} else if(path.equals("/qnaBoardModify.board")) {
			
			basicAction = new QnAModifyAction();
			basicAction.execute(request, response);
			
			basicAction = new EventAllListAction();
			basicAction.execute(request, response);
			
			url = "/model2/qnaBoardModify.jsp";
			
		} else if(path.equals("/qnaBoardModify_ok.board")) {
			
			basicAction = new QnAModifyOkAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/qnaBoardModifyOk.jsp";
			
		} else if(path.equals("/qnaBoardDelete_ok.board")) {
			
			basicAction = new QnADeleteOkAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/qnaBoardDeleteOk.jsp";
	//	
		} else if(path.equals("/requestBoard.board")) {
			
			url = "/model2/requestBoardList.jsp";
			
		} else if(path.equals("/initRequestBoardList.board")) {	//---------------------- 요청 게시판
			
			basicAction = new RequestListAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/initRequestBoardList_json.jsp";
			
		} else if(path.equals("/requestBoardListForBrand.board")) {	
			
			basicAction = new RequestListForBrandAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/initRequestBoardList_json.jsp";
			
		} else if(path.equals("/requestBoardView.board")) {
			
			basicAction = new RequestViewAction();		//RequestViewAction 만들었음
			basicAction.execute(request, response);
			
			url = "/model2/requestBoardView.jsp";		//안에 qnaboard_view.js도 만들었음
			
		} else if(path.equals("/requestBoardWrite.board")) {
			
			basicAction = new BrandListAction();		//BrandListAction으로 만들어야겠지 > 굿
			basicAction.execute(request, response);
		
			url = "/model2/requestBoardWrite.jsp";
			
		} else if(path.equals("/requestBoardWrite_ok.board")) {
			//			
			basicAction = new RequestWriteOkAction();
			basicAction.execute(request, response);

			url = "requestBoard.board";
			
		} else if(path.equals("/requestBoardModify.board")) {
			
			basicAction = new RequestModifyAction();	//만들었음
			basicAction.execute(request, response);
			
			basicAction = new BrandListAction();
			basicAction.execute(request, response);
			
			url = "/model2/requestBoardModify.jsp";	//만들었음
			
		} else if(path.equals("/requestBoardModify_ok.board")) {
			
			basicAction = new RequestModifyOkAction();
			basicAction.execute(request, response);
			
			url = "requestBoard.board";
			
		} else if(path.equals("/requestBoardDelete_ok.board")) {
			
			basicAction = new RequestDeleteOkAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/requestBoardDeleteOk.jsp";
			
		} else if(path.equals("/loginOk.board")) {	//------------------------------------로그인
			
			basicAction = new LoginOkAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/loginOk.jsp";	
			
		} else if(path.equals("/signUpOk.board")) {
			
			basicAction = new SignUpOkAction();
			basicAction.execute(request, response);
			//확인
			//System.out.println("123");
			url = "/ajaxResult/signUpOk.jsp";	
			
//			url = "/ajaxResult/loginOk_json.jsp";	//json 방식으로 결과 처리
			
		} else if(path.equals("/signUpFB.board")) {//-------- 페이스북 로그인
			
			basicAction = new SignUpFBAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/signUpFB.jsp";						
		} else if(path.equals("/loginFB.board")) {
			
			basicAction = new LoginFBAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/loginFBOk.jsp";						
		} else if(path.equals("/logoutOk.board")) {

			basicAction = new LogoutOkAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/logoutOk.jsp";
		} else if(path.equals("/checkOk.board")) {

			basicAction = new checkOkAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/checkOk.jsp";
		} else if(path.equals("/modifyOk.board")) {

			basicAction = new ModifyOkAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/modifyOk.jsp";
		} else if(path.equals("/byebye.board")) {

			basicAction = new ByebyeOkAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/byebyeOk.jsp";
		} else if(path.equals("/modifySuccess.board")) {

			basicAction = new ModifySuccessAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/modifySuccess.jsp";
		} else if(path.equals("/adminPage.board")) {

			basicAction = new MemberListAction();
			basicAction.execute(request, response);
			
			url = "/model2/manageMember.jsp";
//			url = "/model2/adminPage.jsp";	//-화장품 데이터 입력용
		} else if(path.equals("/memSelected.board")) {

			basicAction = new MemSelectedAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/memberSelected.jsp";
		} else if(path.equals("/kickedMemSelected.board")) {

			basicAction = new MemSelectedAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/memberSelected.jsp";
		} else if(path.equals("/sendMailAdmin.board")) {

			basicAction = new MailOkActionAdmin();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/sendMail.jsp";
		} else if(path.equals("/sendMailAdminKick.board")) {

			basicAction = new MailOkActionAdminKick();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/sendMail.jsp";
		} else if(path.equals("/sendMail.board")) {

			basicAction = new MailOkAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/sendMail.jsp";
		} else if(path.equals("/commentOk.board")) {

			basicAction = new CommentWriteOkAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/commentOk.jsp";
		} else if(path.equals("/commentList.board")) {

			basicAction = new CommentListOkAction();	//내용 없음
			basicAction.execute(request, response);
			
			url = "/ajaxResult/commentList.jsp";
		} else if(path.equals("/adminEventManagePage.board")) {
			
			basicAction = new EventAllListForManageAction();
			basicAction.execute(request, response);
			
			url = "/model2/manageEvent.jsp";
			
		} else if(path.equals("/eventManageWrite.board")) {
			
			basicAction = new BrandListAction();	
			basicAction.execute(request, response);
			
			url = "/model2/manageEventWrite.jsp";
			
		} else if(path.equals("/eventManageWrite_ok.board")) {
			
			basicAction = new EventWriteOkAction();	
			basicAction.execute(request, response);
			
			url = "adminEventManagePage.board";
			
		} else if(path.equals("/eventManageModify.board")) {
			
			basicAction = new EventModifyAction();	
			basicAction.execute(request, response);
			
			basicAction = new BrandListAction();	
			basicAction.execute(request, response);
			
			url = "/model2/manageEventModify.jsp";
			
		} else if(path.equals("/eventManageModify_ok.board")) {
			
			basicAction = new EventModifyOkAction();	
			basicAction.execute(request, response);
			
			url = "adminEventManagePage.board";
			
		} else if(path.equals("/eventManageDelete_ok.board")) {
			
			basicAction = new EventDeleteOkAction();	
			basicAction.execute(request, response);
			
			url = "adminEventManagePage.board";
			
		} else if(path.equals("/inputProductDataOk.board")) {	//-------- txt파일의 csv 데이터 DB 입력하기

			basicAction = new InputProductDataOkAction();
			basicAction.execute(request, response);
			
			url = "/ajaxResult/inputProductDataOk.jsp";
		} 
			
			
		if(!url.equals("")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
	}	
	
	
}
