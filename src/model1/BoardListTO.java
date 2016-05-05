package model1;

import java.util.ArrayList;

//page 처리를 위한 TO
public class BoardListTO {
	private int cpage;
	private int recordPerPage;
	
	private int recordPerPageForQnaBoard;
	private int recordPerPageForRequestBoard;
	
	private int blockPerPage;
	private int totalPage;
	private int startBlock;
	private int endBlock;
	private ArrayList<RequestBoardTO> requestBoardList;
	private ArrayList<ReviewBoardTO> reviewBoardList;
	private ArrayList<QnABoardTO> qnaBoardList;
	
	public BoardListTO() {
		// TODO Auto-generated constructor stub
		
		this.cpage = 1;
		this.recordPerPage = 1000;	//후기 게시판 게시글 수 8개로
		this.blockPerPage = 5;
		this.totalPage = 1;
		
		
		this.recordPerPageForQnaBoard = 6; // 이벤트 QnA 게시판 게시글 수 6개로
		this.recordPerPageForRequestBoard = 10; // 요청 게시판 게시글 수 10개로
	}
	
	public int getCpage() {
		return cpage;
	}
	public void setCpage(int cpage) {
		this.cpage = cpage;
	}
	public int getRecordPerPage() {
		return recordPerPage;
	}
	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}
	
	
	public int getRecordPerPageForQnaBoard() {
		return recordPerPageForQnaBoard;
	}

	public void setRecordPerPageForQnaBoard(int recordPerPageForQnaBoard) {
		this.recordPerPageForQnaBoard = recordPerPageForQnaBoard;
	}
	
	public int getRecordPerPageForRequestBoard() {
		return recordPerPageForRequestBoard;
	}

	public void setRecordPerPageForRequestBoard(int recordPerPageForRequestBoard) {
		this.recordPerPageForRequestBoard = recordPerPageForRequestBoard;
	}

	public int getBlockPerPage() {
		return blockPerPage;
	}
	public void setBlockPerPage(int blockPerPage) {
		this.blockPerPage = blockPerPage;
	}

	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartBlock() {
		return startBlock;
	}
	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}
	public int getEndBlock() {
		return endBlock;
	}
	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}

	public ArrayList<RequestBoardTO> getRequestBoardList() {
		return requestBoardList;
	}

	public void setRequestBoardList(ArrayList<RequestBoardTO> requestBoardList) {
		this.requestBoardList = requestBoardList;
	}

	public ArrayList<ReviewBoardTO> getReviewBoardList() {
		return reviewBoardList;
	}

	public void setReviewBoardList(ArrayList<ReviewBoardTO> reviewBoardList) {
		this.reviewBoardList = reviewBoardList;
	}

	public ArrayList<QnABoardTO> getQnaBoardList() {
		return qnaBoardList;
	}

	public void setQnaBoardList(ArrayList<QnABoardTO> qnaBoardList) {
		this.qnaBoardList = qnaBoardList;
	}
	
}
