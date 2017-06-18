CREATE TABLE `activity` (
  `idactivity` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `result` longtext,
  `datetime` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idactivity`)
) ENGINE=InnoDB AUTO_INCREMENT=395 DEFAULT CHARSET=utf8;

CREATE TABLE `optiongroup` (
  `groupid` int(11) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(45) NOT NULL,
  PRIMARY KEY (`groupid`),
  UNIQUE KEY `groupid_UNIQUE` (`groupid`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

CREATE TABLE `options` (
  `optionid` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) DEFAULT NULL,
  `optionname` varchar(1024) COLLATE utf8_bin DEFAULT NULL,
  `probability` int(11) DEFAULT NULL,
  PRIMARY KEY (`optionid`),
  UNIQUE KEY `idchoice_UNIQUE` (`optionid`),
  KEY `GROUP_ID_idx` (`groupid`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `problems` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `problem_no` varchar(256) NOT NULL,
  `date` varchar(256) NOT NULL,
  `respondent` varchar(256) NOT NULL,
  `status` varchar(256) NOT NULL COMMENT '0:未看题，1:已阅题,2:已完成',
  `type` varchar(45) DEFAULT 'A',
  `deadline` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=923 DEFAULT CHARSET=utf8;

