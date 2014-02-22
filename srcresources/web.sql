create database if not exists `web`;
USE `myproject`;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (           
               `ID` int(8) NOT NULL auto_increment,
               `username` varchar(16) default NULL,
               `password` varchar(16) default NULL,
               `email` varchar(32) default NULL,    
               PRIMARY KEY (`ID`)                  
             ) ENGINE=InnoDB DEFAULT CHARSET=UTF-8;  