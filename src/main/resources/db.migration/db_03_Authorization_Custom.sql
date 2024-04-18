USE `employee_directory`;

DROP TABLE IF EXISTS `authorities_nishikant`;
DROP TABLE IF EXISTS `users_nishikant`;

CREATE TABLE `users_nishikant` (
  `name` varchar(50) NOT NULL,
  `pwd` varchar(100) NOT NULL,
  `activeyn` tinyint NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users_nishikant`
VALUES
('john','{noop}test123',1),
('mary','{noop}test123',1),
('susan','{noop}test123',1);

CREATE TABLE `authorities_nishikant` (
  `name` varchar(150) NOT NULL,
  `role` varchar(150) NOT NULL,
  UNIQUE KEY `authorities_nishikant_idx_1` (`name`,`role`),
  CONSTRAINT `authorities_nishikant_ibfk_1` FOREIGN KEY (`name`) REFERENCES `users_nishikant` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `authorities_nishikant`
VALUES
('john','ROLE_SITEUSER'),
('mary','ROLE_SITEUSER'),
('mary','ROLE_SITEADMIN'),
('susan','ROLE_SITEUSER'),
('susan','ROLE_SITEADMIN'),
('susan','ROLE_ADMIN');


