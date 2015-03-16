# 개발 진행 경과 #

## 1주차 (시작 ~ 2009/02/01) ##
  * 개발 환경 설정
    * Spring Servlet 및 기본 ApplicationContext 설치 : Annotation 방식으로 설정
    * MySQL 설치 : 5.1 설치.
    * Tomcat 6 설치
    * Tomcat 과 애플리케이션 연동 :  : Eclipse와 연동
    * Hello BookTogether 띄우기

  * Spring MVC와 Service, Dao 개념 이해
  * 2주차 개발 계획 잡고 할일 나누기


## 2주차 (02/02 ~ 02/08) ##
  * Dependency 추가할 것들
    * JSTL : taglibs/standard 1.1.2 추가
    * MySQL JDBC 드라이버
    * Commons DBCP 커넥션 풀
    * Log4J 1.2
  * 읽어볼 문서
    * Log4J 설정 : http://kwon37xi.egloos.com/2176487
    * Tomcat 과 한글 http://kwon37xi.egloos.com/1462013
    * [JSP 2.0 뭐가 바뀌었나](http://kr.geocities.com/kwon37xi/jsp20/WhatsNewJSP20-1.html)
    * JSTL 1.1

---

  * Log4J 설정하기 : 기본적으로 화면으로만 로그 남기기
    * 로그 찍을 때는 Commons Logging 사용하기
  * DB 연동(Apache Commons DBCP 이용)
    * MySQL 5.x
    * UTF-8 사용
  * SimpleJdbcDaoSupport 사용하여 DaoImpl 구현하기
  * 도메인 클래스용 DB 테이블 설계
    * users
    * books
  * 사용자 추가, 수정, 삭제
    * User : 도메인 클래스
    * UserDao/UserDaoJdbcImpl
    * UserService/UserServiceImpl
    * UserController
  * 책 정보 추가, 수정, 삭제
    * Book : 도메인 클래스
    * BookDao/BookDaoJdbcImpl
    * BookService/BookServiceImpl
    * BookController

## 3주차 (02/09 ~ 02/15) ##

> 아직 미정


## 이후 할 일들 ##
  * JNDI 설정 :  [JNDI Tomcat](http://kwon37xi.egloos.com/2852803)
  * JNDI 연동