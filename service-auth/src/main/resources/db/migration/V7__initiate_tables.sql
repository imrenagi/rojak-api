CREATE TABLE `tbl_role` (
    `id` bigint(20) NOT NULL auto_increment,
    `name` varchar(100) NOT NULL,
    `description` varchar(250) NOT NULL,
    UNIQUE KEY `k_name` (`name`),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `tbl_group` (
    `id` bigint(20) NOT NULL auto_increment,
    `name` varchar(100) NOT NULL,
    `description` varchar(250) NOT NULL,
    `supports_nesting` tinyint(1) NOT NULL,
    `role_id` bigint(20) NOT NULL,
    UNIQUE KEY `k_group_name` (`name`),
    CONSTRAINT `fk_role_id_tbl_group` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`id`),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `tbl_permissions` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `k_permission_name` (`name`),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `tbl_role_permissions` (
  `id` bigint(20) NOT NULL auto_increment,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT `fk_role_id_to_id` FOREIGN KEY (`role_id`) REFERENCES `tbl_role` (`id`),
  CONSTRAINT `fk_permission_id_to_id` FOREIGN KEY (`permission_id`) REFERENCES `tbl_permissions` (`id`),
  PRIMARY KEY (`id`)
)  ENGINE=InnoDB;

CREATE TABLE `tbl_group_member` (
    `id` bigint(20) NOT NULL auto_increment,
    `name` varchar(100) NOT NULL,
    `group_id` bigint(20) NOT NULL,
    `type` varchar(5) NOT NULL,
    KEY `k_group_id` (`group_id`),
    KEY `k_name` (`name`),
    CONSTRAINT `fk_tbl_group_member_tbl_group` FOREIGN KEY (`group_id`) REFERENCES `tbl_group` (`id`),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `tbl_user` (
    `id` bigint(20) NOT NULL auto_increment,
    `username` varchar(250) NOT NULL,
    `password` varchar(255) NOT NULL,
    `enablement_enabled` tinyint(1) NOT NULL,
    `enablement_end_date` datetime,
    `enablement_start_date` datetime,
    UNIQUE KEY `k_username` (`username`),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `tbl_person` (
    -- primary key is my parent's pk, which is table 'tbl_user'
    `id` bigint(20) NOT NULL auto_increment,
    `user_id` bigint(20) NOT NULL,
    `contact_information_email_address_address` varchar(100) NOT NULL,
    `contact_information_postal_address_city` varchar(100) NOT NULL,
    `contact_information_postal_address_country` varchar(100) NOT NULL,
    `contact_information_postal_address_postal_code` varchar(12) NOT NULL,
    `contact_information_postal_address_state_province` varchar(100) NOT NULL,
    `contact_information_postal_address_street_address` varchar(100),
    `contact_information_primary_telephone_number` varchar(20) NOT NULL,
    `contact_information_secondary_telephone_number` varchar(20) NOT NULL,
    `name_first_name` varchar(50) NOT NULL,
    `name_last_name` varchar(50) NOT NULL,
    CONSTRAINT `fk_tbl_person_tbl_user` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `tbl_registration_invitation` (
    `id` bigint(20) NOT NULL auto_increment,
    `description` varchar(100) NOT NULL,
    `invitation_id` varchar(36) NOT NULL,
    `starting_on` datetime,
    `until` datetime,
    UNIQUE KEY `k_invitation_id` (`invitation_id`),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;


