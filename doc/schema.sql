/**
 * 1.지은이
 */

CREATE TABLE `author` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `name` varchar(20) NOT NULL COMMENT '지은이이름',
  `author_div` int(1) NOT NULL COMMENT '지은이구분',
  `book_idNum` int(11) NOT NULL COMMENT '책일련번호',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 2.책
 */
CREATE TABLE `book` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `name` varchar(100) NOT NULL COMMENT '책이름',
  `isbn10` char(10) NOT NULL COMMENT 'ISBN10자리',
  `isbn13` char(13) NOT NULL COMMENT 'ISBN13자리',
  `publish_comp` varchar(20) NOT NULL COMMENT '출판사',
  `publish_date` varchar(10) NOT NULL COMMENT '출판일',
  `price` int(5) DEFAULT NULL COMMENT '가격',
  `corver` varchar(100) DEFAULT NULL COMMENT '책표지이미지',
  `category` varchar(50) DEFAULT NULL COMMENT '카테고리',
  `description` text COMMENT '설명',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/**
 * 3. 책 별점
 */

CREATE TABLE `bookgrade` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `book_idNum` int(11) NOT NULL COMMENT '책',
  `user_idNum` int(11) NOT NULL COMMENT '사용자',
  `grade` int(1) NOT NULL COMMENT '별점',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 4. 책 인용구
 */
CREATE TABLE `bookmark` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_idNum` int(11) NOT NULL COMMENT '사용자 일련번호',
  `book_idNum` int(11) NOT NULL COMMENT '책 일련번호',
  `vibe_num` int(11) NOT NULL COMMENT '공감수',
  `page` int(11) DEFAULT NULL COMMENT '페이지',
  `input_date` datetime NOT NULL COMMENT '등록날짜',
  `content` varchar(200) NOT NULL COMMENT '인용구',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 5. 책 리뷰
 */

CREATE TABLE `bookreview` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `book_idNum` int(11) NOT NULL COMMENT '책',
  `user_idNum` int(11) NOT NULL COMMENT '사용자',
  `recommend` int(11) DEFAULT NULL COMMENT '추천수',
  `title` varchar(50) NOT NULL COMMENT '제목',
  `review` text COMMENT '내용',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 6. 책 리뷰 추천
 */

CREATE TABLE `recommend` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_idNum` int(11) NOT NULL COMMENT '사용자일련번호',
  `review_idNum` int(11) NOT NULL COMMENT '리뷰일련번호',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/**
 * 7. 사용자 
 */

CREATE TABLE `user` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_id` varchar(15) NOT NULL COMMENT '사용자ID',
  `email` varchar(30) NOT NULL COMMENT '이메일',
  `nickname` varchar(20) NOT NULL COMMENT '별명',
  `name` varchar(20) NOT NULL COMMENT '이름',
  `isdelete` int(1) NOT NULL COMMENT '탈퇴유무',
  `input_date` datetime NOT NULL COMMENT '가입날짜',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 8. 사용자 비밀번호
 */
CREATE TABLE `user_pw` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_idNum` int(11) NOT NULL COMMENT '사용자 일련번호 ',
  `pw` varbinary(50) NOT NULL COMMENT '비밀번호 Digest한것',
  `salt` varbinary(50) NOT NULL COMMENT '비밀번호 Key역활',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 9. 사용자 추가정보
 */

CREATE TABLE `useraddinfo` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_idNum` int(11) NOT NULL COMMENT '사용자일련번호',
  `blog` varchar(50) DEFAULT NULL COMMENT '블로그주소 ',
  `thumnail` varchar(50) DEFAULT NULL COMMENT '썸네일이미지파일명',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 10. 인용구 공감하기
 */
CREATE TABLE `vibe` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_idNum` int(11) NOT NULL COMMENT '사용자일련번호',
  `bookmark_idNum` int(11) NOT NULL COMMENT '북마크일련번호',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 11. 생활반경 우편번호
 */
CREATE TABLE `zipcode` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `zipcode` varchar(7) NOT NULL COMMENT '우편번호',
  `sido` varchar(4) DEFAULT NULL COMMENT '특별시,광역시,도',
  `gugun` varchar(15) DEFAULT NULL COMMENT '시,군,구',
  `dong` varchar(52) DEFAULT NULL COMMENT '동,읍,면',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
 * 12. 생활반경
 */
CREATE TABLE `zone` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_idNum` int(11) NOT NULL COMMENT '사용자일련번호',
  `zone` int(11) NOT NULL COMMENT '주소일련번호',
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

