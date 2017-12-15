CREATE TABLE `tbl_nominees` (
    `id` bigint(20) NOT NULL auto_increment,
    `first_name` varchar(50) NOT NULL,
    `middle_name` varchar(50),
    `last_name` varchar(50) NOT NULL,
    `photo_url` varchar(255),
    `nick_name` varchar(10) NOT NULL,
    `nominee_id` varchar(36) NOT NULL,
    `social_media_web_url` varchar(255),
    `social_media_twitter_id` varchar(255),
    `social_media_instagram_id` varchar(255),
    `social_media_facebook_url` varchar(255),
    KEY `k_nominee_id` (`nominee_id`),
    UNIQUE KEY `k_nominee_id_unique` (`nominee_id`),
    PRIMARY KEY (`id`)
);

CREATE TABLE `tbl_elections` (
    `id` bigint(20) NOT NULL auto_increment,
    `election_id` varchar(36) NOT NULL,
    `name` varchar(250) NOT NULL,
    `city_id` bigint(20) unsigned NOT NULL,
    `type` varchar(20) NOT NULL,
    `election_date` datetime NOT NULL,
    `election_campaign_start_date` datetime NOT NULL,
    `election_campaign_end_date` datetime NOT NULL,
    KEY `k_election_id` (`election_id`),
    CONSTRAINT `fk_election_city_id` FOREIGN KEY (`city_id`) REFERENCES `tbl_cities` (`id`),
    PRIMARY KEY (`id`)
);

CREATE TABLE `tbl_candidates` (
    `id` bigint(20) NOT NULL auto_increment,
    `candidate_id` varchar(36) NOT NULL,
    `election_id` varchar(36) NOT NULL,
    `election_id_id` bigint(20) NOT NULL,
    `candidate_number` int NOT NULL,
    `main_nominee_id` bigint(20) NOT NULL,
    `vice_nominee_id` bigint(20) NOT NULL,
    `image_url` varchar(255),
    `tag_line` varchar(255),
    `social_media_web_url` varchar(255),
    `social_media_twitter_id` varchar(255),
    `social_media_instagram_id` varchar(255),
    `social_media_facebook_url` varchar(255),
    KEY `k_candidate_id` (`candidate_id`),
    KEY `k_election_id` (`election_id`),
    UNIQUE KEY `k_election_candidate` (`election_id`, `candidate_id`),
    UNIQUE KEY `k_election_candidate_number` (`election_id`, `candidate_number`),
    CONSTRAINT `fk_main_nominee_id` FOREIGN KEY (`main_nominee_id`) REFERENCES `tbl_nominees` (`id`),
    CONSTRAINT `fk_vice_nominee_id` FOREIGN KEY (`vice_nominee_id`) REFERENCES `tbl_nominees` (`id`),
    CONSTRAINT `fk_election_id` FOREIGN KEY (`election_id_id`) REFERENCES `tbl_elections` (`id`),
    PRIMARY KEY (`id`)
);


