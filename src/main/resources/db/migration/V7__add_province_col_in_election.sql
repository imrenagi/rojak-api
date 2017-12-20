ALTER TABLE tbl_elections
    ADD province_id bigint(20) unsigned NOT NULL default 1,
    ADD CONSTRAINT FOREIGN KEY(province_id) REFERENCES tbl_provinces(id);