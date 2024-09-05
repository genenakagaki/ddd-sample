CREATE TABLE `checklist`.`user` (
	`user_id` CHAR(36) NOT NULL,
	`username` VARCHAR(32) NOT NULL,
	`encoded_password` VARCHAR(128) NOT NULL,
	`created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (user_id),
	UNIQUE KEY (username)
);
