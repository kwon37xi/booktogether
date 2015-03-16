# 코딩 규칙 #

### 패키지 구조 ###
  * com.google.code.booktogether.web : 웹 관련
    * com.google.code.booktogether.web.controller : 컨트롤러들
    * com.google.code.booktogether.web.interceptor : 인터셉터
  * com.google.code.booktogether.service : 서비스 인터페이스
    * com.google.code.booktogether.service.impl : 서비스 구현
  * com.google.code.booktogether.dao : dao 인터페이스
    * com.google.code.booktogether.dao.impl : dao 구현

### 명명 규칙 ###
  * 서비스 인터페이스가 CurrentDateService 라면 구현체는 CurrentDateServiceImpl
  * Dao 인터페이스가 UserDao 라면 구현체는 UserDaoJdbcImpl

### 파일명 규칙 ###
  * 디렉토리 구분자는 유닉스 방식으로 "/" 를 사용한다.