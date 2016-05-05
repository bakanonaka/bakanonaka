package model1;

public class ProductTO {
	private int no;						//제품 구분자
	private String name;				//화장품 명
	private int price;					//가격
	private int countOfStar;			//별점 수
	private String colorName;			//화장품 색상명
	private int springNumber;			//봄계열 숫자
	private int summerNumber;			//여름계열 숫자
	private int fallNumber;				//가을계열 숫자
	private int winterNumber;			//겨울계열 숫자
	private String basicProductImgName;	//1. 화장품 기본 이미지
	private String colorProductImgName;	//2. 화장품 색상별 이미지 (1이 없으면, 보여줌)
	private String colorImgName;		//3. 색상 이미지 (2가 없으면, 1과 같이 보여줌)
	private String testImgName;			//4. 발색 이미지 (상세 보기에서 보여줌)
	private String brandName;			//브랜드명
	private String productKindName;		//화장품 종류 (립스틱)
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCountOfStar() {
		return countOfStar;
	}
	public void setCountOfStar(int countOfStar) {
		this.countOfStar = countOfStar;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public int getSpringNumber() {
		return springNumber;
	}
	public void setSpringNumber(int springNumber) {
		this.springNumber = springNumber;
	}
	public int getSummerNumber() {
		return summerNumber;
	}
	public void setSummerNumber(int summerNumber) {
		this.summerNumber = summerNumber;
	}
	public int getFallNumber() {
		return fallNumber;
	}
	public void setFallNumber(int fallNumber) {
		this.fallNumber = fallNumber;
	}
	public int getWinterNumber() {
		return winterNumber;
	}
	public void setWinterNumber(int winterNumber) {
		this.winterNumber = winterNumber;
	}
	public String getBasicProductImgName() {
		return basicProductImgName;
	}
	public void setBasicProductImgName(String basicProductImgName) {
		this.basicProductImgName = basicProductImgName;
	}
	public String getColorProductImgName() {
		return colorProductImgName;
	}
	public void setColorProductImgName(String colorProductImgName) {
		this.colorProductImgName = colorProductImgName;
	}
	public String getColorImgName() {
		return colorImgName;
	}
	public void setColorImgName(String colorImgName) {
		this.colorImgName = colorImgName;
	}
	public String getTestImgName() {
		return testImgName;
	}
	public void setTestImgName(String testImgName) {
		this.testImgName = testImgName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProductKindName() {
		return productKindName;
	}
	public void setProductKindName(String productKindName) {
		this.productKindName = productKindName;
	}
	
}
