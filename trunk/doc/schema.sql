CREATE TABLE `author` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `author_div` int(1) NOT NULL,
  `book_idNum` int(11) NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_author_1` (`book_idNum`),
  CONSTRAINT `FK_author_1` FOREIGN KEY (`book_idNum`) REFERENCES `book` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bestsellers` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `book_idNum` int(11) NOT NULL,
  `input_date` datetime NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_bestsellers_1` (`book_idNum`),
  CONSTRAINT `FK_bestsellers_1` FOREIGN KEY (`book_idNum`) REFERENCES `book` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `book` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `isbn10` char(10) NOT NULL,
  `isbn13` char(13) NOT NULL,
  `publish_comp` varchar(20) NOT NULL,
  `publish_date` varchar(10) NOT NULL,
  `price` int(5) DEFAULT NULL,
  `cover` varchar(100) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bookgrade` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `book_idNum` int(11) NOT NULL,
  `user_idNum` int(11) NOT NULL,
  `grade` int(1) NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_bookgrade_1` (`book_idNum`),
  KEY `FK_bookgrade_2` (`user_idNum`),
  CONSTRAINT `FK_bookgrade_2` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`),
  CONSTRAINT `FK_bookgrade_1` FOREIGN KEY (`book_idNum`) REFERENCES `book` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bookhits` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `hits` int(11) NOT NULL DEFAULT '0',
  `book_idNum` int(11) NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_bookhits_1` (`book_idNum`),
  CONSTRAINT `FK_bookhits_1` FOREIGN KEY (`book_idNum`) REFERENCES `book` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bookmark` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `book_idNum` int(11) NOT NULL,
  `vibe_num` int(11) NOT NULL,
  `page` int(11) DEFAULT NULL,
  `input_date` datetime NOT NULL,
  `content` varchar(200) NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_bookmark_1` (`user_idNum`),
  KEY `FK_bookmark_2` (`book_idNum`),
  CONSTRAINT `FK_bookmark_2` FOREIGN KEY (`book_idNum`) REFERENCES `book` (`idNum`),
  CONSTRAINT `FK_bookmark_1` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `bookreview` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `book_idNum` int(11) NOT NULL,
  `user_idNum` int(11) NOT NULL,
  `recommend` int(11) DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `review` text,
  PRIMARY KEY (`idNum`),
  KEY `FK_bookreview_1` (`book_idNum`),
  KEY `FK_bookreview_2` (`user_idNum`),
  CONSTRAINT `FK_bookreview_1` FOREIGN KEY (`book_idNum`) REFERENCES `book` (`idNum`),
  CONSTRAINT `FK_bookreview_2` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `goodwriter` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `input_date` datetime NOT NULL,
  `content` varchar(200) NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `interestlibrary` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `standard` int(11) NOT NULL,
  `target` int(11) NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_interestlibrary_1` (`standard`),
  KEY `FK_interestlibrary_2` (`target`),
  CONSTRAINT `FK_interestlibrary_2` FOREIGN KEY (`target`) REFERENCES `user` (`idNum`),
  CONSTRAINT `FK_interestlibrary_1` FOREIGN KEY (`standard`) REFERENCES `user` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `library` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `isopen` int(1) NOT NULL,
  `notice` text,
  PRIMARY KEY (`idNum`),
  KEY `FK_library_1` (`user_idNum`),
  CONSTRAINT `FK_library_1` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `libraryboard` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `library_idNum` int(11) NOT NULL,
  `writer` int(11) NOT NULL,
  `content` varchar(100) NOT NULL,
  `input_date` datetime NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_libraryboard_1` (`library_idNum`),
  CONSTRAINT `FK_libraryboard_1` FOREIGN KEY (`library_idNum`) REFERENCES `library` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `librarybook` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `book_idNum` int(11) NOT NULL,
  `library_idNum` int(11) NOT NULL,
  `read_Date` datetime DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `ispossess` int(1) DEFAULT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_librarybook_1` (`book_idNum`),
  KEY `FK_librarybook_2` (`library_idNum`),
  CONSTRAINT `FK_librarybook_2` FOREIGN KEY (`library_idNum`) REFERENCES `library` (`idNum`),
  CONSTRAINT `FK_librarybook_1` FOREIGN KEY (`book_idNum`) REFERENCES `book` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `libraryrank` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `total` int(11) NOT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `possessbook` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `book_idNum` int(11) NOT NULL,
  `user_idNum` int(11) NOT NULL,
  `purchase_date` datetime DEFAULT NULL,
  `purchase_price` int(11) DEFAULT NULL,
  `begin_read` datetime DEFAULT NULL,
  `end_read` datetime DEFAULT NULL,
  `quality` int(1) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_possessbook_1` (`book_idNum`),
  KEY `FK_possessbook_2` (`user_idNum`),
  CONSTRAINT `FK_possessbook_2` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`),
  CONSTRAINT `FK_possessbook_1` FOREIGN KEY (`book_idNum`) REFERENCES `book` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `recommend` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `review_idNum` int(11) NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_recommend_1` (`user_idNum`),
  KEY `FK_recommend_2` (`review_idNum`),
  CONSTRAINT `FK_recommend_2` FOREIGN KEY (`review_idNum`) REFERENCES `bookreview` (`idNum`),
  CONSTRAINT `FK_recommend_1` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `searchquery` (
  `query` varchar(50) NOT NULL,
  `search_num` int(11) NOT NULL,
  PRIMARY KEY (`query`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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


CREATE TABLE `user_pw` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `pw` varbinary(50) NOT NULL,
  `salt` varbinary(50) NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_user_pw_1` (`user_idNum`),
  CONSTRAINT `FK_user_pw_1` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `useraddinfo` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `blog` varchar(50) DEFAULT NULL,
  `thumnail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_useraddinfo_1` (`user_idNum`),
  CONSTRAINT `FK_useraddinfo_1` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `vibe` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `bookmark_idNum` int(11) NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_vibe_1` (`user_idNum`),
  KEY `FK_vibe_2` (`bookmark_idNum`),
  CONSTRAINT `FK_vibe_2` FOREIGN KEY (`bookmark_idNum`) REFERENCES `bookmark` (`idNum`),
  CONSTRAINT `FK_vibe_1` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `zipcode` (
  `idNum` int(11) NOT NULL,
  `zipcode` varchar(7) NOT NULL,
  `sido` varchar(4) DEFAULT NULL,
  `gugun` varchar(15) DEFAULT NULL,
  `dong` varchar(52) DEFAULT NULL,
  PRIMARY KEY (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `zone` (
  `idNum` int(11) NOT NULL AUTO_INCREMENT,
  `user_idNum` int(11) NOT NULL,
  `zone` int(11) NOT NULL,
  PRIMARY KEY (`idNum`),
  KEY `FK_zone_1` (`zone`),
  KEY `FK_zone_2` (`user_idNum`),
  CONSTRAINT `FK_zone_2` FOREIGN KEY (`user_idNum`) REFERENCES `user` (`idNum`),
  CONSTRAINT `FK_zone_1` FOREIGN KEY (`zone`) REFERENCES `zipcode` (`idNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

