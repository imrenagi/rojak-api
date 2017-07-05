CREATE TABLE `tbl_elections` (
    `id` bigint(20) NOT NULL auto_increment,
    `election_id` varchar(36) NOT NULL,
    `name` varchar(250) NOT NULL,
	`city_id` int NOT NULL,
	`province_id` int NOT NULL,
	`type` varchar(20) NOT NULL,
	`election_date` datetime NOT NULL,
	`election_campaign_start_date` datetime NOT NULL,
	`election_campaign_end_date` datetime NOT NULL,
    KEY `k_election_id` (`election_id`),
    PRIMARY KEY (`id`)
)