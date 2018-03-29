CREATE TABLE `student` (
  `id_student` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `surname` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `grade` int(11) DEFAULT '0',
  PRIMARY KEY (`id_student`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `gradebook_report` (
  `id_statistics` int(11) NOT NULL AUTO_INCREMENT,
  `numberStudents` int(11) DEFAULT NULL,
  `avgGrade` double DEFAULT NULL,
  `minGrade` int(11) DEFAULT NULL,
  `maxGrade` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id_statistics`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
