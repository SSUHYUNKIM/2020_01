import java.util.ArrayList;
import java.util.*;

class Book {
	String title;
	String author;
	int ID;
	int status;
	
	public Book(String title, String author, int ID, int status) {
		this.title = title;
		this.author = author;
		this.ID = ID;
	}

	public void main(String[] args) {
		ArrayList<Book> list = new ArrayList<Book>();
		int status = 0;
		list.add(new Book("어린 왕자","생텍쥐페리",1, status));
		list.add(new Book("페스트","알베르 카뮈",2, status));
		list.add(new Book("녹나무의 파수꾼","히가시노 게이고",3, status));
		list.add(new Book("어제가 없으면 내일도 없다","미야베 미유키",4, status));
		list.add(new Book("작은 아씨들","루이자 메이 올콧",5, status));
		list.add(new Book("호밀밭의 파수꾼","제롬 데이비드 샐린저",6, status));
		list.add(new Book("데미안","헤르만 헤세",7, status));
		list.add(new Book("멋진 신세계","올더스 헉슬리",8, status));
		list.add(new Book("나무를 심은 사람 ","장 지오노",9, status));
		list.add(new Book("어둠의 눈 ","딘 쿤츠",10, status));
		list.add(new Book("씨네21","씨네21 편집부",11, status));
		list.add(new Book("더스타","더스타 편집부",12, status));
		list.add(new Book("레전드 매거진","레전드 편집부",13, status));
	    list.add(new Book("원피스"," 오다 에이치로", 14,status));
	    list.add(new Book("침어", "panpanya", 15, status));
	    list.add(new Book("약속의 네버랜드","시라이 카이우", 16, status));
	    list.add(new Book("귀멸의 칼날","고토게 코요하루", 17, status));
	    list.add(new Book("연애혁명","232", 18, status));
	    list.add(new Book("굿바이 사돈","교교박", 19, status));
	    list.add(new Book("패션왕","기안84",20, status));
	    list.add(new Book("복학왕","기안84​", 21, status));
	}


public int PrintStatus(int ID){
	Book obj = new Book(title,author,ID,status);
	return obj.status;
}

public void RentBook() {
	Scanner sc = new Scanner(System.in);
	System.out.println("대여할 책의 ID 를 입력하세요.");
	int ID = sc.nextInt();
	if (ID < 0)
		System.out.println("등록되지 않은 ID 입니다.");
	else if(PrintStatus(ID) == 0){
		System.out.println("대여 가능합니다. 이용해 주셔서 감사합니다.");
		int RentBookCount = 0;
		RentBookCount++;
		Book obj = new Book(title,author,ID,status);
		obj.status = 1;
		}
	else {
		System.out.println("이미 대여된 도서입니다. 다른 책을 선택해주세요.");
	}
}

public void ReturnBook() {
	Scanner sc = new Scanner(System.in);
	System.out.println("반납할 책의 ID 를 입력하세요.");
	int ID = sc.nextInt();
	if (ID < 0)
		System.out.println("등록되지 않은 ID 입니다.");
	else if(PrintStatus(ID) == 1){
		System.out.println("반납이 완료되었습니다.");
		Book obj = new Book(title,author,ID,status);
		obj.status = 0;
		System.out.println("대여한 날짜를 입력해주세요: 04월 ?일");
		int day = sc.nextInt();
		int gap = today - day;
		ComicRentalFee(gap);
		NovelRentalFee(gap);
		MagazineRentalFee(gap);
		}
	else {
		System.out.println("이미 반납된 도서입니다. 다른 책을 선택해주세요.");
	}
}

public void ComicRentalFee(int gap) {
	int result = 200;
	if(gap>3)
		result += 100*gap;
	System.out.print("만화책 대여료는: "+result);
}


public void NovelRentalFee(int gap) {
	int result = 400;
	if(gap>3)
		result += 200*gap;
	System.out.print("소설 대여료는: "+result);
}

public void MagazineRentalFee(int gap) {
	int result = 600;
	if(gap>3)
		result += 300*gap;
	System.out.print("잡지 대여료는: "+result);
}

public class BookRent {
	public void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("오늘의 날짜를 입력하시오.");
		int today = sc.nextInt();
		boolean run = true;
		while(run) {
			System.out.println("대여 상태 출력--------------------------- ");
			System.out.println("총 21권 중 n권 대여 중입니다. (2020/04/+"+today+")");
			System.out.println("--------------------------------------- ");
    		Book obj1 = new Book(title, author, ID, status);
			System.out.print("소설 ==>");
    		for(int i = 1; i<11;i++) {
    				System.out.print(obj1.title +": " + obj1.status);
    		}
    		System.out.print("잡지 ==>");
    		for(int i = 11; i<14;i++) {
				System.out.print(obj1.title +": " + obj1.status);
		}
    		System.out.print("만화책 ==>");
    		for(int i = 14; i<22;i++) {
				System.out.print(obj1.title +": " + obj1.status);
		}
    		}
    		System.out.println("1. 도서 대여 | 2. 도서 반납 | 3. 종료");
    		int selectNum = sc.nextInt();
    		sc.nextLine();
    		if(selectNum == 1)
    			RentBook();
    		else if(selectNum == 2)
    			ReturnBook();
    		else if (selectNum == 3)
    			System.out.println("프로그램을 종료합니다.");
    			run = false;
		}
	}
}