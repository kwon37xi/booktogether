# 수정사항 및 해야할 일 #

  1. ServiceImple if ~ else 구문 정리 - 가독성 높이기
  1. BaseObject 부분 abstract로 변경
  1. requestContextHolder 이용하여 코드 간단하게 변경
  1. xss(Cross Site Scripting) 보안 처리
    * {fn: } 이용해서 처리
    * server 단에서 기본 사용자에게 기본 tag만 허용하도록 처리
    * 사용자가 직접 작성하는 리뷰, 인용구의 경우게만 기본 tag 제공
    * 자바 정규표현식을 이용하여 필터링하는 방법
      * 정규식 알기 - 참고 : http://pupustory.tistory.com/132
      * htlm 태그 및 금지어 필터링을 적절히 믹스 - 참고 : http://www.java2go.net/blog/tag/Regular%20Expression
  1. OpenAPI에서 공통으로 사용되는 값을 정의
    * 공통되는 부분은 그대로 두고 API 종류에 따라 달라지는 것들만 Map을 이용해서 구현
  1. JQuery 공부하기
    * JQuery 강좌 링크 : http://blog.naver.com/guruby?Redirect=Log&logNo=140058679107
  1. 기타 구현할 기능 마무리
    * 내 생활반경에 등록된 책 리스트 조회
    * 내 관심서재 등록
    * 그 외의 사소한 이슈들~
  1. Logging 처리시 레벨 체크 메서드 사용이유
    * 비용계산문제에서 발생 - 참고 : http://okcode.egloos.com/1892085

# 차주 회의 내용 #

  * code review
  * UI 기획 및 디자인
  * 추가 기능 보완