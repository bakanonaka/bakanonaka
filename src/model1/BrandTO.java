package model1;

public class BrandTO {
	private int no;			//구분자	
	private String name;		//브랜드 이름
	private String eventBoardUrl;		//브랜드 url
	private String storeUrl;	//지점 주소 페이징
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEventBoardUrl() {
		return eventBoardUrl;
	}
	public void setEventBoardUrl(String eventBoardUrl) {
		this.eventBoardUrl = eventBoardUrl;
	}
	public String getStoreUrl() {
		return storeUrl;
	}
	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}
	
	
	
}
