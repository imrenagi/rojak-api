CREATE TABLE `tbl_social_media` (
    `id` bigint(20) NOT NULL auto_increment,
    `facebook_url` varchar(255),
    `twitter_id` varchar(255),
    `instagram_id` varchar(255),
    PRIMARY KEY (`id`)
);

CREATE TABLE `tbl_postal_address` (
    `id` bigint(20) NOT NULL auto_increment,
    `street_address` varchar(255),
    `country` varchar(100),
    `province` varchar(100),
    `city` varchar(100),
    `zip_code` varchar(10),
    PRIMARY KEY (`id`)
);

CREATE TABLE `tbl_medias` (
    `id` bigint(20) NOT NULL auto_increment,
    `media_id` varchar(36) NOT NULL,
    `name` varchar(50) NOT NULL,
    `website_url` varchar(255) NOT NULL,
    `mobile_website_url` varchar(255),
    `social_media_id` bigint(20),
    `postal_info_id` bigint(20),
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP ,
    `updated_at` datetime DEFAULT CURRENT_TIMESTAMP on UPDATE CURRENT_TIMESTAMP,
    KEY `k_media_id` (`media_id`),
    UNIQUE KEY `k_media_id_unique` (`media_id`),
    CONSTRAINT `fk_social_media_id` FOREIGN KEY (`social_media_id`) REFERENCES `tbl_social_media` (`id`),
    CONSTRAINT `fk_postal_id` FOREIGN KEY (`postal_info_id`) REFERENCES `tbl_postal_address` (`id`),
    PRIMARY KEY (`id`)
);

-- CREATE TABLE `tbl_supported_candidate_of_media` (
--     `id` bigint(20) NOT NULL auto_increment,
--     `media_id_id` bigint(20) NOT NULL,
--     `media_id` varchar(36) NOT NULL,
--     `election_id` varchar(36) NOT NULL,
--     `candidate_id` varchar(36) NOT NULL,
--     `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (`id`)
-- );
--
-- CREATE TABLE `tbl_news` (
--     `id` bigint(20) NOT NULL auto_increment,
--     `title` varchar(255) NOT NULL,
--     `url` varchar(255) NOT NULL,
--     `content` text NOT NULL,
--     `election_id` varchar(36) NOT NULL,
--     `media_id_id` bigint(20) NOT NULL,
-- --     `media_id` varchar(36) NOT NULL,
--     `timestamp` datetime NOT NULL,
--     `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
--     KEY `k_election_id` (`election_id`),
--     UNIQUE KEY (`uk_election_media`) (`election_id`, `media_id_id`)
--     CONSTRAINT `fk_media_id` FOREIGN KEY (`media_id_id`) REFERENCES `tbl_medias` (`id`),
--     PRIMARY KEY (`id`)
-- );
--
-- CREATE TABLE `tbl_news_sentiment` (
--     `id` bigint(20) NOT NULL auto_increment,
--     `news_id` bigint(20) NOT NULL,
--     `election_id` varchar(36) NOT NULL,
--     `candidate_id` varchar(36) NOT NULL,
--     `media_id` varchar(36) NOT NULL,
--     `media_id_id` bigint(20) NOT NULL,
--     `sentiment_type` varchar(20) NOT NULL,
--     `timestamp` datetime NOT NULL,
--     KEY `k_election_id` (`election_id`),
--     KEY `k_media_id_id` (`media_id_id`),
--     KEY `k_candidate_id` (`candidate_id`),
--     PRIMARY KEY (`id`)
-- );
--
-- CREATE TABLE `tbl_hoax_analyzer_result` (
--     `id` bigint(20) NOT NULL auto_increment,
--     `news_id` bigint(20) NOT NULL,
--     `is_fact` tinyint NOT NULL,
--     `fact_score` double NOT NULL,
--     `result_url` varchar(255) NOT NULL,
--     CONSTRAINT `fk_news_id` FOREIGN KEY (`news_id`) REFERENCES `tbl_news` (`id`),
--     PRIMARY KEY (`id`)
-- );
--

