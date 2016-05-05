package model1;

import java.util.ArrayList;

//page 처리를 위한 TO
public class EventListTO {
	private int cpage;
	private int recordPerPage;
	private int blockPerPage;
	private int totalPage;
	private int startBlock;
	private int endBlock;
	private ArrayList<EventTO> eventsList;
	
	public EventListTO() {
		// TODO Auto-generated constructor stub
		
		this.cpage = 1;
		this.recordPerPage = 3;
		this.blockPerPage = 3;
		this.totalPage = 1;
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

	public ArrayList<EventTO> getEventsList() {
		return eventsList;
	}

	public void setEventsList(ArrayList<EventTO> eventsList) {
		this.eventsList = eventsList;
	}

}
