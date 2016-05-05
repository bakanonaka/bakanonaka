package model1;

public class MemberTO {
	private int memNo;
	private String id;				
	private String password;		
	private String name;
	private String email;
	private int receiveMailFlag;	//0:받는다, 1:안받는다
	private String signUpDate;		//가입일자
	private String loginDate;			//최근 로그인 일자
	private int likeColor;	//1 ~ 10 (빨 ~ 보)
	private String seasonOfLikeColor;	// spring ~ winter
	private int numOfText;			//게시글 수
	private int numOfComment;		//댓글 수
	
	
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getReceiveMailFlag() {
		return receiveMailFlag;
	}
	public void setReceiveMailFlag(int receiveMailFlag) {
		this.receiveMailFlag = receiveMailFlag;
	}
	public String getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}
	public String getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}
	public int getLikeColor() {
		return likeColor;
	}
	public void setLikeColor(int likeColor) {
		this.likeColor = likeColor;
	}
	public String getSeasonOfLikeColor() {
		return seasonOfLikeColor;
	}
	public void setSeasonOfLikeColor(String seasonOfLikeColor) {
		this.seasonOfLikeColor = seasonOfLikeColor;
	}
	public int getNumOfText() {
		return numOfText;
	}
	public void setNumOfText(int numOfText) {
		this.numOfText = numOfText;
	}
	public int getNumOfComment() {
		return numOfComment;
	}
	public void setNumOfComment(int numOfComment) {
		this.numOfComment = numOfComment;
	}
	

}
