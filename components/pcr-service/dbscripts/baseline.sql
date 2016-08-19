CREATE DATABASE  IF NOT EXISTS `pcr_service`;
USE `pcr_service`; 

DROP TABLE IF EXISTS `pctapplication`;

CREATE TABLE `pctapplication` (
  `appdid` int(11) NOT NULL AUTO_INCREMENT,
  `appid` varchar(100) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isactive` BIT(1) NOT NULL DEFAULT 1,
  CONSTRAINT PKpctapplication PRIMARY KEY (`appdid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `pctuser`;

CREATE TABLE `pctuser` (
  `userdid` int(11) NOT NULL AUTO_INCREMENT,
  `useruuid` varchar(100) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isactive` BIT(1) NOT NULL DEFAULT 1,
  CONSTRAINT PKpctuser PRIMARY KEY (`userdid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `pctsector`;

CREATE TABLE `pctsector` (
  `sectordid` int(11) NOT NULL AUTO_INCREMENT,
  `sectorid` varchar(100) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT PKpctsector PRIMARY KEY (`sectordid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `pctuserassignment`;

CREATE TABLE `pctuserassignment` (
  `assignmentdid` int(11) NOT NULL AUTO_INCREMENT,
  `appdid` int(11) NOT NULL,
  `userdid` int(11) NOT NULL,
  `sectordid` int(11) NOT NULL,
  `pcr` varchar(100) NOT NULL,
  CONSTRAINT PKpctuserassignment PRIMARY KEY (`assignmentdid`),
  CONSTRAINT FK01pctuserassignment FOREIGN KEY (appdid) REFERENCES pctapplication(appdid),
  CONSTRAINT FK02pctuserassignment FOREIGN KEY (userdid) REFERENCES pctuser(userdid),
  CONSTRAINT FK03pctuserassignment FOREIGN KEY (sectordid) REFERENCES pctsector(sectordid)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


