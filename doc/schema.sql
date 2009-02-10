
/* 지은이  */
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `name` varchar(20) NOT NULL COMMENT '지은이이름',
  `author_div` int(1) NOT NULL COMMENT '지은이구분',
  `book_ref` int(11) NOT NULL COMMENT '책일련번호',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 책 */
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


/* 별점 */
CREATE TABLE `bookgrade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `book_id` int(11) NOT NULL COMMENT '책',
  `user_id` int(11) NOT NULL COMMENT '사용자',
  `grade` int(1) NOT NULL COMMENT '별점',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 리뷰 */
CREATE TABLE `bookreview` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `book_id` int(11) NOT NULL COMMENT '책',
  `user_id` int(11) NOT NULL COMMENT '사용자',
  `recommend` int(11) DEFAULT NULL COMMENT '추천수',
  `title` varchar(50) NOT NULL COMMENT '제목',
  `review` text COMMENT '내용',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/* 사용자 */
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


/* 사용자 비밀번호 */
CREATE TABLE `user_pw` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `user_id` int(11) NOT NULL COMMENT '사용자 일련번호 ',
  `pw` varbinary(50) NOT NULL COMMENT '비밀번호 Digest한것',
  `salt` varbinary(50) NOT NULL COMMENT '비밀번호 Key역활',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

