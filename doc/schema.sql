
/* 1.지은이  */
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `name` varchar(20) NOT NULL COMMENT '지은이이름',
  `author_div` int(1) NOT NULL COMMENT '지은이구분',
  `book_ref` int(11) NOT NULL COMMENT '책일련번호',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 2.책 */
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `name` varchar(100) NOT NULL COMMENT '책이름',
  `isbn10` char(10) NOT NULL COMMENT 'ISBN10자리',
  `isbn13` char(13) NOT NULL COMMENT 'ISBN13자리',
  `publish_comp` varchar(20) NOT NULL COMMENT '출판사',
  `publish_date` varchar(10) NOT NULL COMMENT '출판일',
  `price` int(5) DEFAULT NULL COMMENT '가격',
  `corver` varchar(100) DEFAULT NULL COMMENT '책표지이미지',
  `category` varchar(50) DEFAULT NULL COMMENT '카테고리',
  `description` text COMMENT '설명',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 3.별점 */
CREATE TABLE `bookgrade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `book_id` int(11) NOT NULL COMMENT '책',
  `user_id` int(11) NOT NULL COMMENT '사용자',
  `grade` int(1) NOT NULL COMMENT '별점',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 4.리뷰 */
CREATE TABLE `bookreview` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `book_id` int(11) NOT NULL COMMENT '책',
  `user_id` int(11) NOT NULL COMMENT '사용자',
  `recommend` int(11) DEFAULT NULL COMMENT '추천수',
  `title` varchar(50) NOT NULL COMMENT '제목',
  `review` text COMMENT '내용',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 5.사용자 */
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_id` varchar(15) NOT NULL COMMENT '사용자ID',
  `email` varchar(30) NOT NULL COMMENT '이메일',
  `nickname` varchar(20) NOT NULL COMMENT '별명',
  `name` varchar(20) NOT NULL COMMENT '이름',
  `delete_y_n` int(1) NOT NULL COMMENT '탈퇴유무',
  `input_date` datetime NOT NULL COMMENT '가입날짜',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 6.사용자 비밀번호 */
CREATE TABLE `user_pw` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_id` int(11) NOT NULL COMMENT '사용자 일련번호 ',
  `pw` varbinary(50) NOT NULL COMMENT '비밀번호 Digest한것',
  `salt` varbinary(50) NOT NULL COMMENT '비밀번호 Key역활',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 7.추천 */
CREATE TABLE `recommend` (                                  
   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',  
   `user_id` int(11) NOT NULL COMMENT '사용자일련번호',      
   `review_id` int(11) NOT NULL COMMENT '리뷰일련번호',      
   PRIMARY KEY (`id`)                                        
) ENGINE=InnoDB DEFAULT CHARSET=utf8


/* 8.인용구 */
CREATE TABLE `bookmark` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_id` int(11) NOT NULL COMMENT '사용자 일련번호',
  `book_id` int(11) NOT NULL COMMENT '책 일련번호',
  `vibe_num` int(11) NOT NULL COMMENT '공감수',
  `page` int(11) DEFAULT NULL COMMENT '페이지',
  `input_date` datetime NOT NULL COMMENT '등록날짜',
  `content` varchar(200) NOT NULL COMMENT '인용구',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 9.공감 */
CREATE TABLE `vibe` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_id` int(11) NOT NULL COMMENT '사용자일련번호',
  `bookmark_id` int(11) NOT NULL COMMENT '북마크일련번호',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



/* 10.사용자 추가정보 */

CREATE TABLE `useraddinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_id` int(11) NOT NULL COMMENT '사용자 일련번호',
  `blog` varchar(50) DEFAULT NULL COMMENT '블로그주소',
  `thumnail` varchar(50) DEFAULT NULL COMMENT '썸네일이미지',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;



/* 11.생활반경 */

CREATE TABLE `zone` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_id` int(11) NOT NULL COMMENT '사용자 일련번호',
  `zone` int(11) NOT NULL COMMENT '주소일련번호',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


/* 12.주소 */
CREATE TABLE `zipcode` (
  `seq` int(11) NOT NULL COMMENT '일련번호',
  `sido` varchar(5) DEFAULT NULL COMMENT '특별시,광역시,도',
  `gugun` varchar(15) DEFAULT NULL COMMENT '시,군,구',
  `dong` varchar(52) DEFAULT NULL COMMENT '읍,면,동,리,건물명',
  PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


