2월 15일 회의 내용 정리

# 작명규칙 정의 #

  * 직관적인 이름으로 변경 - 파일명이 어떤 기능을 하는 파일인지 알 수 있도록 하기
  * 변수명이나 메서드 명에서 단어와 단어를 연결하는 언더바 삭제
  * 메소드는 소문자로 시작


# 패키지, 인터페이스와 구현을 분리 #

  * 기능에 따라 패키지 분리
  * 인터페이스는 public으로 뺄 수 있는 것, 구현은 private으로 숨기는 것
  * 책 검색 부분 인터페이스로 빼고 bean으로 객체 한번만 생성하도록 변경


# XmlUtil.java 수정 사항 #

  * 직관적으로 파일명 수정 - Ex)XmlSqlParser.java
  * sql.xml 값을 코드로 고정시키지 말고 생성자에서 문자열의 List로 받는 방법


# Transaction 처리 #

  * @Transactional(....)을 클래스 정의 위에 적어주면 공통으로 적용
  * readOnly=true로 설정해 놓고 write가 발생할 수 있는 것들에만 readOnly=false로 따로 지정
  * Transactional의 default는 RuntimeException


# primitive data type은 사용하지 말 것 #

  * primitive data type에는 NULL 개념이 없음
  * ""와 NULL은 다르기 때문에 DB에서 IS NULL로 조회할 경우 ""은 조회되지 않는 문제
  * int --> Integer로 대체하는 등 primitive data type은 사용하지 말자


# Exception의 처리 #

  * Checked Exception의 Try catch 부분에서 error를 잡아먹는 실수를 하지 말것
  * 앞으로는 모든 Exception은 BooktogetherException을 상속하여 사용
  * 이유없는 Exception을 발생시키지 말 것, 반드시 message를 넣어주기
  * printStackTrace()는 절대 쓰지 말것


# 주소 검색 #

  * Type0 사용 - 읍,면,동까지
  * 나와 일치하는 생활반경 검색시 동이 일치하는 경우를 가장 위로 정렬해서 보여주기


# 이클립스에서 배포가 잘 되지 않는 문제 #

  * project > properties > java build path > source 탭 선택 > Excluded 삭제하기


# 생각해보기(차후에 개발할 내용) #

  * 관리자 페이지
  * 현재 인터페이스에만 주석처리 - 스프링에서 제공하는 링크를 걸 수 있는 주석이 있음, 참고
  * Validator에 대한 고려
  * 어떻게 하면 key 값만으로 주소(활동범위)를 비교할 수 있을지 생각해보기


# 이번주 할 일 #

  * Refactoring 작업
  * Interceptor의 적용(RequestContextHolder 클래스 참고)
  * 내 서재 만들기(책 상태 분류 기능 포함)
  * 스프링 공부 - 고급 기능을 사용하기 위하여
  * 어떤 기능을 추가할 수 있을지 생각해보기


# 참고사항 #

  * 개발자를 위한 font - DejaVu Sans mono(다운로드 링크:http://blog.naver.com/empireclan?Redirect=Log&logNo=140062917666)
  * 핵심기능에 집중할 것 - 부가적인 기능은 차후에 생각하자.
  * Ctrl + Shift + f - Formatting 수행, 저장하기 전에 잊지 말고 할 것
  * System.out.println 구문은 소스에 절대 포함되지 않도록 할 것
  * session은 메모리와 연결되므로, 남용하지 않고 잘 지워주도록 할 것
  * 참고 도서 - Effective Java, Java5, JQuery(위키북스)