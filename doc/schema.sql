
/*1.지은이 */

CREATE TABLE `author` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `author_div` int(1) NOT NULL,
  `book_idNum` int(11) NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*2.책 */

CREATE TABLE `book` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `isbn10` char(10) NOT NULL,
  `isbn13` char(13) NOT NULL,
  `publish_comp` varchar(20) NOT NULL,
  `publish_date` varchar(10) NOT NULL,
  `price` int(5) DEFAULT NULL,
  `corver` varchar(100) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*3.책 별점 */

CREATE TABLE `bookgrade` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `book_idNum` int(11) NOT NULL,
  `user_idNum` int(11) NOT NULL,
  `grade` int(1) NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*4. 인용구 */

CREATE TABLE `bookmark` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `book_idNum` int(11) NOT NULL,
  `vibe_num` int(11) NOT NULL,
  `page` int(11) DEFAULT NULL,
  `input_date` datetime NOT NULL,
  `content` varchar(200) NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*5. 리뷰  */

CREATE TABLE `bookreview` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `book_idNum` int(11) NOT NULL,
  `user_idNum` int(11) NOT NULL,
  `recommend` int(11) DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `review` text,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*6. 내서재 */

CREATE TABLE `library` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `isopen` int(1) NOT NULL,
  `notice` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*7. 내서재 */

CREATE TABLE `librarybook` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `book_idNum` int(11) NOT NULL,
  `library_idNum` int(11) NOT NULL,
  `read_Date` datetime DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `ispossess` int(1) DEFAULT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*8. 보유하고 있는 책 */

CREATE TABLE `possessbook` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `book_idNum` int(11) NOT NULL,
  `purchase_date` datetime DEFAULT NULL,
  `purchase_price` datetime DEFAULT NULL,
  `begin_read` datetime DEFAULT NULL,
  `end_read` datetime DEFAULT NULL,
  `quality` int(1) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*9. 리뷰 추천 */

CREATE TABLE `recommend` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `review_idNum` int(11) NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*10.사용자 */

CREATE TABLE `user` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `isdelete` int(1) NOT NULL,
  `input_date` datetime NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*11.사용자 비밀번호 */

CREATE TABLE `user_pw` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `pw` varbinary(50) NOT NULL,
  `salt` varbinary(50) NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*12.사용자 추가정보*/

CREATE TABLE `useraddinfo` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `blog` varchar(50) DEFAULT NULL,
  `thumnail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*13.인용구 북마크 */

CREATE TABLE `vibe` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `bookmark_idNum` int(11) NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*14. 우편번호 */

CREATE TABLE `zipcode` (
  `idNum` int(11) NOT NULL,
  `zipcode` varchar(7) NOT NULL,
  `sido` varchar(4) DEFAULT NULL,
  `gugun` varchar(15) DEFAULT NULL,
  `dong` varchar(52) DEFAULT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*15.생활반경 */

CREATE TABLE `zone` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `zone` int(11) NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
