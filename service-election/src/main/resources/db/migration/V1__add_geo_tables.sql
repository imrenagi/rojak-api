CREATE TABLE `tbl_countries` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `code` varchar(10) NOT NULL,
  KEY `k_name_idx` (`name`),
  KEY `k_code_idx` (`code`),
  UNIQUE KEY `k_name_code` (`name`, `code`),
  PRIMARY KEY (`id`)
);

CREATE TABLE `tbl_provinces` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `code` varchar(10) NOT NULL,
  `country_id` bigint(20) unsigned NOT NULL,
  KEY `k_name_idx` (`name`),
  KEY `k_code_idx` (`code`),
  UNIQUE KEY `k_name_code` (`name`, `code`),
  CONSTRAINT `fk_country_id` FOREIGN KEY (`country_id`) REFERENCES `tbl_countries` (`id`),
  PRIMARY KEY (`id`)
);

CREATE TABLE `tbl_cities` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `province_id` bigint(20) unsigned NOT NULL,
  `latitude` decimal(10,8),
  `longitude` decimal(11,8),
  KEY `k_name_idx` (`name`),
  UNIQUE KEY `k_name` (`name`),
  CONSTRAINT `fk_city_provinces` FOREIGN KEY (`province_id`) REFERENCES `tbl_provinces` (`id`),
  PRIMARY KEY (`id`)
);

INSERT INTO `tbl_countries` (`id`, `name`, `code`) VALUES
	(1, 'Indonesia', 'ID');

INSERT INTO `tbl_provinces` (`id`, `name`, `code`, `country_id`)	VALUES
  (1,'Bali','Bali',1),
  (2,'Bangka Belitung','Babel',1),
  (3,'Banten','Banten',1),
  (4,'Bengkulu','Bengkulu',1),
  (5,'DI Yogyakarta','DIY',1),
  (6,'DKI Jakarta','JKT',1),
  (7,'Gorontalo','Gorontalo',1),
  (8,'Jambi','Jambi',1),
  (9,'Jawa Barat','Jabar',1),
  (10,'Jawa Tengah','Jateng',1),
  (11,'Jawa Timur','Jatim',1),
  (12,'Kalimantan Barat','Kalbar',1),
  (13,'Kalimantan Selatan','Kalsel',1),
  (14,'Kalimantan Tengah','Kalteng',1),
  (15,'Kalimantan Timur','Kaltim',1),
  (16,'Kalimantan Utara','Kalut',1),
  (17,'Kepulauan Riau','Kepri',1),
  (18,'Lampung','Lampung',1),
  (19,'Maluku','Maluku',1),
  (20,'Maluku Utara','Malut',1),
  (21,'Nanggroe Aceh Darussalam (NAD)','NAD',1),
  (22,'Nusa Tenggara Barat (NTB)','NTB',1),
  (23,'Nusa Tenggara Timur (NTT)','NTT',1),
  (24,'Papua','Papua',1),
  (25,'Papua Barat','Papbar',1),
  (26,'Riau','Riau',1),
  (27,'Sulawesi Barat','Sulbar',1),
  (28,'Sulawesi Selatan','Sulsel',1),
  (29,'Sulawesi Tengah','Sulteng',1),
  (30,'Sulawesi Tenggara','Sul28teng',1),
  (31,'Sulawesi Utara','Sulut',1),
  (32,'Sumatera Barat','Sumbar',1),
  (33,'Sumatera Selatan','Sumsel',1),
  (34,'Sumatera Utara','Sumut',1);

INSERT INTO `tbl_cities` (`id`, `name`, `province_id`)
VALUES
	(1, 'KABUPATEN SIMEULUE', 21),
	(2, 'KABUPATEN ACEH SINGKIL', 21),
	(3, 'KABUPATEN ACEH SELATAN', 21),
	(4, 'KABUPATEN ACEH TENGGARA', 21),
	(5, 'KABUPATEN ACEH TIMUR', 21),
	(6, 'KABUPATEN ACEH TENGAH', 21),
	(7, 'KABUPATEN ACEH BARAT', 21),
	(8, 'KABUPATEN ACEH BESAR', 21),
	(9, 'KABUPATEN PIDIE', 21),
	(10, 'KABUPATEN BIREUEN', 21),
	(11, 'KABUPATEN ACEH UTARA', 21),
	(12, 'KABUPATEN ACEH BARAT DAYA', 21),
	(13, 'KABUPATEN GAYO LUES', 21),
	(14, 'KABUPATEN ACEH TAMIANG', 21),
	(15, 'KABUPATEN NAGAN RAYA', 21),
	(16, 'KABUPATEN ACEH JAYA', 21),
	(17, 'KABUPATEN BENER MERIAH', 21),
	(18, 'KABUPATEN PIDIE JAYA', 21),
	(19, 'KOTA BANDA ACEH', 21),
	(20, 'KOTA SABANG', 21),
	(21, 'KOTA LANGSA', 21),
	(22, 'KOTA LHOKSEUMAWE', 21),
	(23, 'KOTA SUBULUSSALAM', 21),
	(24, 'KABUPATEN NIAS', 34),
	(25, 'KABUPATEN MANDAILING NATAL', 34),
	(26, 'KABUPATEN TAPANULI SELATAN', 34),
	(27, 'KABUPATEN TAPANULI TENGAH', 34),
	(28, 'KABUPATEN TAPANULI UTARA', 34),
	(29, 'KABUPATEN TOBA SAMOSIR', 34),
	(30, 'KABUPATEN LABUHAN BATU', 34),
	(31, 'KABUPATEN ASAHAN', 34),
	(32, 'KABUPATEN SIMALUNGUN', 34),
	(33, 'KABUPATEN DAIRI', 34),
	(34, 'KABUPATEN KARO', 34),
	(35, 'KABUPATEN DELI SERDANG', 34),
	(36, 'KABUPATEN LANGKAT', 34),
	(37, 'KABUPATEN NIAS SELATAN', 34),
	(38, 'KABUPATEN HUMBANG HASUNDUTAN', 34),
	(39, 'KABUPATEN PAKPAK BHARAT', 34),
	(40, 'KABUPATEN SAMOSIR', 34),
	(41, 'KABUPATEN SERDANG BEDAGAI', 34),
	(42, 'KABUPATEN BATU BARA', 34),
	(43, 'KABUPATEN PADANG LAWAS UTARA', 34),
	(44, 'KABUPATEN PADANG LAWAS', 34),
	(45, 'KABUPATEN LABUHAN BATU SELATAN', 34),
	(46, 'KABUPATEN LABUHAN BATU UTARA', 34),
	(47, 'KABUPATEN NIAS UTARA', 34),
	(48, 'KABUPATEN NIAS BARAT', 34),
	(49, 'KOTA SIBOLGA', 34),
	(50, 'KOTA TANJUNG BALAI', 34),
	(51, 'KOTA PEMATANG SIANTAR', 34),
	(52, 'KOTA TEBING TINGGI', 34),
	(53, 'KOTA MEDAN', 34),
	(54, 'KOTA BINJAI', 34),
	(55, 'KOTA PADANGSIDIMPUAN', 34),
	(56, 'KOTA GUNUNGSITOLI', 34),
	(57, 'KABUPATEN KEPULAUAN MENTAWAI', 32),
	(58, 'KABUPATEN PESISIR SELATAN', 32),
	(59, 'KABUPATEN SOLOK', 32),
	(60, 'KABUPATEN SIJUNJUNG', 32),
	(61, 'KABUPATEN TANAH DATAR', 32),
	(62, 'KABUPATEN PADANG PARIAMAN', 32),
	(63, 'KABUPATEN AGAM', 32),
	(64, 'KABUPATEN LIMA PULUH KOTA', 32),
	(65, 'KABUPATEN PASAMAN', 32),
	(66, 'KABUPATEN SOLOK SELATAN', 32),
	(67, 'KABUPATEN DHARMASRAYA', 32),
	(68, 'KABUPATEN PASAMAN BARAT', 32),
	(69, 'KOTA PADANG', 32),
	(70, 'KOTA SOLOK', 32),
	(71, 'KOTA SAWAH LUNTO', 32),
	(72, 'KOTA PADANG PANJANG', 32),
	(73, 'KOTA BUKITTINGGI', 32),
	(74, 'KOTA PAYAKUMBUH', 32),
	(75, 'KOTA PARIAMAN', 32),
	(76, 'KABUPATEN KUANTAN SINGINGI', 26),
	(77, 'KABUPATEN INDRAGIRI HULU', 26),
	(78, 'KABUPATEN INDRAGIRI HILIR', 26),
	(79, 'KABUPATEN PELALAWAN', 26),
	(80, 'KABUPATEN S I A K', 26),
	(81, 'KABUPATEN KAMPAR', 26),
	(82, 'KABUPATEN ROKAN HULU', 26),
	(83, 'KABUPATEN BENGKALIS', 26),
	(84, 'KABUPATEN ROKAN HILIR', 26),
	(85, 'KABUPATEN KEPULAUAN MERANTI', 26),
	(86, 'KOTA PEKANBARU', 26),
	(87, 'KOTA D U M A I', 26),
	(88, 'KABUPATEN KERINCI', 8),
	(89, 'KABUPATEN MERANGIN', 8),
	(90, 'KABUPATEN SAROLANGUN', 8),
	(91, 'KABUPATEN BATANG HARI', 8),
	(92, 'KABUPATEN MUARO JAMBI', 8),
	(93, 'KABUPATEN TANJUNG JABUNG TIMUR', 8),
	(94, 'KABUPATEN TANJUNG JABUNG BARAT', 8),
	(95, 'KABUPATEN TEBO', 8),
	(96, 'KABUPATEN BUNGO', 8),
	(97, 'KOTA JAMBI', 8),
	(98, 'KOTA SUNGAI PENUH', 8),
	(99, 'KABUPATEN OGAN KOMERING ULU', 33),
	(100, 'KABUPATEN OGAN KOMERING ILIR', 33),
	(101, 'KABUPATEN MUARA ENIM', 33),
	(102, 'KABUPATEN LAHAT', 33),
	(103, 'KABUPATEN MUSI RAWAS', 33),
	(104, 'KABUPATEN MUSI BANYUASIN', 33),
	(105, 'KABUPATEN BANYU ASIN', 33),
	(106, 'KABUPATEN OGAN KOMERING ULU SELATAN', 33),
	(107, 'KABUPATEN OGAN KOMERING ULU TIMUR', 33),
	(108, 'KABUPATEN OGAN ILIR', 33),
	(109, 'KABUPATEN EMPAT LAWANG', 33),
	(110, 'KABUPATEN PENUKAL ABAB LEMATANG ILIR', 33),
	(111, 'KABUPATEN MUSI RAWAS UTARA', 33),
	(112, 'KOTA PALEMBANG', 33),
	(113, 'KOTA PRABUMULIH', 33),
	(114, 'KOTA PAGAR ALAM', 33),
	(115, 'KOTA LUBUKLINGGAU', 33),
	(116, 'KABUPATEN BENGKULU SELATAN', 4),
	(117, 'KABUPATEN REJANG LEBONG', 4),
	(118, 'KABUPATEN BENGKULU UTARA', 4),
	(119, 'KABUPATEN KAUR', 4),
	(120, 'KABUPATEN SELUMA', 4),
	(121, 'KABUPATEN MUKOMUKO', 4),
	(122, 'KABUPATEN LEBONG', 4),
	(123, 'KABUPATEN KEPAHIANG', 4),
	(124, 'KABUPATEN BENGKULU TENGAH', 4),
	(125, 'KOTA BENGKULU', 4),
	(126, 'KABUPATEN LAMPUNG BARAT', 18),
	(127, 'KABUPATEN TANGGAMUS', 18),
	(128, 'KABUPATEN LAMPUNG SELATAN', 18),
	(129, 'KABUPATEN LAMPUNG TIMUR', 18),
	(130, 'KABUPATEN LAMPUNG TENGAH', 18),
	(131, 'KABUPATEN LAMPUNG UTARA', 18),
	(132, 'KABUPATEN WAY KANAN', 18),
	(133, 'KABUPATEN TULANGBAWANG', 18),
	(134, 'KABUPATEN PESAWARAN', 18),
	(135, 'KABUPATEN PRINGSEWU', 18),
	(136, 'KABUPATEN MESUJI', 18),
	(137, 'KABUPATEN TULANG BAWANG BARAT', 18),
	(138, 'KABUPATEN PESISIR BARAT', 18),
	(139, 'KOTA BANDAR LAMPUNG', 18),
	(140, 'KOTA METRO', 18),
	(141, 'KABUPATEN BANGKA', 2),
	(142, 'KABUPATEN BELITUNG', 2),
	(143, 'KABUPATEN BANGKA BARAT', 2),
	(144, 'KABUPATEN BANGKA TENGAH', 2),
	(145, 'KABUPATEN BANGKA SELATAN', 2),
	(146, 'KABUPATEN BELITUNG TIMUR', 2),
	(147, 'KOTA PANGKAL PINANG', 2),
	(148, 'KABUPATEN KARIMUN', 17),
	(149, 'KABUPATEN BINTAN', 17),
	(150, 'KABUPATEN NATUNA', 17),
	(151, 'KABUPATEN LINGGA', 17),
	(152, 'KABUPATEN KEPULAUAN ANAMBAS', 17),
	(153, 'KOTA B A T A M', 17),
	(154, 'KOTA TANJUNG PINANG', 17),
	(155, 'KABUPATEN KEPULAUAN SERIBU', 6),
	(156, 'KOTA JAKARTA SELATAN', 6),
	(157, 'KOTA JAKARTA TIMUR', 6),
	(158, 'KOTA JAKARTA PUSAT', 6),
	(159, 'KOTA JAKARTA BARAT', 6),
	(160, 'KOTA JAKARTA UTARA', 6),
	(161, 'KABUPATEN BOGOR', 9),
	(162, 'KABUPATEN SUKABUMI', 9),
	(163, 'KABUPATEN CIANJUR', 9),
	(164, 'KABUPATEN BANDUNG', 9),
	(165, 'KABUPATEN GARUT', 9),
	(166, 'KABUPATEN TASIKMALAYA', 9),
	(167, 'KABUPATEN CIAMIS', 9),
	(168, 'KABUPATEN KUNINGAN', 9),
	(169, 'KABUPATEN CIREBON', 9),
	(170, 'KABUPATEN MAJALENGKA', 9),
	(171, 'KABUPATEN SUMEDANG', 9),
	(172, 'KABUPATEN INDRAMAYU', 9),
	(173, 'KABUPATEN SUBANG', 9),
	(174, 'KABUPATEN PURWAKARTA', 9),
	(175, 'KABUPATEN KARAWANG', 9),
	(176, 'KABUPATEN BEKASI', 9),
	(177, 'KABUPATEN BANDUNG BARAT', 9),
	(178, 'KABUPATEN PANGANDARAN', 9),
	(179, 'KOTA BOGOR', 9),
	(180, 'KOTA SUKABUMI', 9),
	(181, 'KOTA BANDUNG', 9),
	(182, 'KOTA CIREBON', 9),
	(183, 'KOTA BEKASI', 9),
	(184, 'KOTA DEPOK', 9),
	(185, 'KOTA CIMAHI', 9),
	(186, 'KOTA TASIKMALAYA', 9),
	(187, 'KOTA BANJAR', 9),
	(188, 'KABUPATEN CILACAP', 10),
	(189, 'KABUPATEN BANYUMAS', 10),
	(190, 'KABUPATEN PURBALINGGA', 10),
	(191, 'KABUPATEN BANJARNEGARA', 10),
	(192, 'KABUPATEN KEBUMEN', 10),
	(193, 'KABUPATEN PURWOREJO', 10),
	(194, 'KABUPATEN WONOSOBO', 10),
	(195, 'KABUPATEN MAGELANG', 10),
	(196, 'KABUPATEN BOYOLALI', 10),
	(197, 'KABUPATEN KLATEN', 10),
	(198, 'KABUPATEN SUKOHARJO', 10),
	(199, 'KABUPATEN WONOGIRI', 10),
	(200, 'KABUPATEN KARANGANYAR', 10),
	(201, 'KABUPATEN SRAGEN', 10),
	(202, 'KABUPATEN GROBOGAN', 10),
	(203, 'KABUPATEN BLORA', 10),
	(204, 'KABUPATEN REMBANG', 10),
	(205, 'KABUPATEN PATI', 10),
	(206, 'KABUPATEN KUDUS', 10),
	(207, 'KABUPATEN JEPARA', 10),
	(208, 'KABUPATEN DEMAK', 10),
	(209, 'KABUPATEN SEMARANG', 10),
	(210, 'KABUPATEN TEMANGGUNG', 10),
	(211, 'KABUPATEN KENDAL', 10),
	(212, 'KABUPATEN BATANG', 10),
	(213, 'KABUPATEN PEKALONGAN', 10),
	(214, 'KABUPATEN PEMALANG', 10),
	(215, 'KABUPATEN TEGAL', 10),
	(216, 'KABUPATEN BREBES', 10),
	(217, 'KOTA MAGELANG', 10),
	(218, 'KOTA SURAKARTA', 10),
	(219, 'KOTA SALATIGA', 10),
	(220, 'KOTA SEMARANG', 10),
	(221, 'KOTA PEKALONGAN', 10),
	(222, 'KOTA TEGAL', 10),
	(223, 'KABUPATEN KULON PROGO', 5),
	(224, 'KABUPATEN BANTUL', 5),
	(225, 'KABUPATEN GUNUNG KIDUL', 5),
	(226, 'KABUPATEN SLEMAN', 5),
	(227, 'KOTA YOGYAKARTA', 5),
	(228, 'KABUPATEN PACITAN', 11),
	(229, 'KABUPATEN PONOROGO', 11),
	(230, 'KABUPATEN TRENGGALEK', 11),
	(231, 'KABUPATEN TULUNGAGUNG', 11),
	(232, 'KABUPATEN BLITAR', 11),
	(233, 'KABUPATEN KEDIRI', 11),
	(234, 'KABUPATEN MALANG', 11),
	(235, 'KABUPATEN LUMAJANG', 11),
	(236, 'KABUPATEN JEMBER', 11),
	(237, 'KABUPATEN BANYUWANGI', 11),
	(238, 'KABUPATEN BONDOWOSO', 11),
	(239, 'KABUPATEN SITUBONDO', 11),
	(240, 'KABUPATEN PROBOLINGGO', 11),
	(241, 'KABUPATEN PASURUAN', 11),
	(242, 'KABUPATEN SIDOARJO', 11),
	(243, 'KABUPATEN MOJOKERTO', 11),
	(244, 'KABUPATEN JOMBANG', 11),
	(245, 'KABUPATEN NGANJUK', 11),
	(246, 'KABUPATEN MADIUN', 11),
	(247, 'KABUPATEN MAGETAN', 11),
	(248, 'KABUPATEN NGAWI', 11),
	(249, 'KABUPATEN BOJONEGORO', 11),
	(250, 'KABUPATEN TUBAN', 11),
	(251, 'KABUPATEN LAMONGAN', 11),
	(252, 'KABUPATEN GRESIK', 11),
	(253, 'KABUPATEN BANGKALAN', 11),
	(254, 'KABUPATEN SAMPANG', 11),
	(255, 'KABUPATEN PAMEKASAN', 11),
	(256, 'KABUPATEN SUMENEP', 11),
	(257, 'KOTA KEDIRI', 11),
	(258, 'KOTA BLITAR', 11),
	(259, 'KOTA MALANG', 11),
	(260, 'KOTA PROBOLINGGO', 11),
	(261, 'KOTA PASURUAN', 11),
	(262, 'KOTA MOJOKERTO', 11),
	(263, 'KOTA MADIUN', 11),
	(264, 'KOTA SURABAYA', 11),
	(265, 'KOTA BATU', 11),
	(266, 'KABUPATEN PANDEGLANG', 3),
	(267, 'KABUPATEN LEBAK', 3),
	(268, 'KABUPATEN TANGERANG', 3),
	(269, 'KABUPATEN SERANG', 3),
	(270, 'KOTA TANGERANG', 3),
	(271, 'KOTA CILEGON', 3),
	(272, 'KOTA SERANG', 3),
	(273, 'KOTA TANGERANG SELATAN', 3),
	(274, 'KABUPATEN JEMBRANA', 1),
	(275, 'KABUPATEN TABANAN', 1),
	(276, 'KABUPATEN BADUNG', 1),
	(277, 'KABUPATEN GIANYAR', 1),
	(278, 'KABUPATEN KLUNGKUNG', 1),
	(279, 'KABUPATEN BANGLI', 1),
	(280, 'KABUPATEN KARANG ASEM', 1),
	(281, 'KABUPATEN BULELENG', 1),
	(282, 'KOTA DENPASAR', 1),
	(283, 'KABUPATEN LOMBOK BARAT', 22),
	(284, 'KABUPATEN LOMBOK TENGAH', 22),
	(285, 'KABUPATEN LOMBOK TIMUR', 22),
	(286, 'KABUPATEN SUMBAWA', 22),
	(287, 'KABUPATEN DOMPU', 22),
	(288, 'KABUPATEN BIMA', 22),
	(289, 'KABUPATEN SUMBAWA BARAT', 22),
	(290, 'KABUPATEN LOMBOK UTARA', 22),
	(291, 'KOTA MATARAM', 22),
	(292, 'KOTA BIMA', 22),
	(293, 'KABUPATEN SUMBA BARAT', 23),
	(294, 'KABUPATEN SUMBA TIMUR', 23),
	(295, 'KABUPATEN KUPANG', 23),
	(296, 'KABUPATEN TIMOR TENGAH SELATAN', 23),
	(297, 'KABUPATEN TIMOR TENGAH UTARA', 23),
	(298, 'KABUPATEN BELU', 23),
	(299, 'KABUPATEN ALOR', 23),
	(300, 'KABUPATEN LEMBATA', 23),
	(301, 'KABUPATEN FLORES TIMUR', 23),
	(302, 'KABUPATEN SIKKA', 23),
	(303, 'KABUPATEN ENDE', 23),
	(304, 'KABUPATEN NGADA', 23),
	(305, 'KABUPATEN MANGGARAI', 23),
	(306, 'KABUPATEN ROTE NDAO', 23),
	(307, 'KABUPATEN MANGGARAI BARAT', 23),
	(308, 'KABUPATEN SUMBA TENGAH', 23),
	(309, 'KABUPATEN SUMBA BARAT DAYA', 23),
	(310, 'KABUPATEN NAGEKEO', 23),
	(311, 'KABUPATEN MANGGARAI TIMUR', 23),
	(312, 'KABUPATEN SABU RAIJUA', 23),
	(313, 'KABUPATEN MALAKA', 23),
	(314, 'KOTA KUPANG', 23),
	(315, 'KABUPATEN SAMBAS', 12),
	(316, 'KABUPATEN BENGKAYANG', 12),
	(317, 'KABUPATEN LANDAK', 12),
	(318, 'KABUPATEN MEMPAWAH', 12),
	(319, 'KABUPATEN SANGGAU', 12),
	(320, 'KABUPATEN KETAPANG', 12),
	(321, 'KABUPATEN SINTANG', 12),
	(322, 'KABUPATEN KAPUAS HULU', 12),
	(323, 'KABUPATEN SEKADAU', 12),
	(324, 'KABUPATEN MELAWI', 12),
	(325, 'KABUPATEN KAYONG UTARA', 12),
	(326, 'KABUPATEN KUBU RAYA', 12),
	(327, 'KOTA PONTIANAK', 12),
	(328, 'KOTA SINGKAWANG', 12),
	(329, 'KABUPATEN KOTAWARINGIN BARAT', 14),
	(330, 'KABUPATEN KOTAWARINGIN TIMUR', 14),
	(331, 'KABUPATEN KAPUAS', 14),
	(332, 'KABUPATEN BARITO SELATAN', 14),
	(333, 'KABUPATEN BARITO UTARA', 14),
	(334, 'KABUPATEN SUKAMARA', 14),
	(335, 'KABUPATEN LAMANDAU', 14),
	(336, 'KABUPATEN SERUYAN', 14),
	(337, 'KABUPATEN KATINGAN', 14),
	(338, 'KABUPATEN PULANG PISAU', 14),
	(339, 'KABUPATEN GUNUNG MAS', 14),
	(340, 'KABUPATEN BARITO TIMUR', 14),
	(341, 'KABUPATEN MURUNG RAYA', 14),
	(342, 'KOTA PALANGKA RAYA', 14),
	(343, 'KABUPATEN TANAH LAUT', 13),
	(344, 'KABUPATEN KOTA BARU', 13),
	(345, 'KABUPATEN BANJAR', 13),
	(346, 'KABUPATEN BARITO KUALA', 13),
	(347, 'KABUPATEN TAPIN', 13),
	(348, 'KABUPATEN HULU SUNGAI SELATAN', 13),
	(349, 'KABUPATEN HULU SUNGAI TENGAH', 13),
	(350, 'KABUPATEN HULU SUNGAI UTARA', 13),
	(351, 'KABUPATEN TABALONG', 13),
	(352, 'KABUPATEN TANAH BUMBU', 13),
	(353, 'KABUPATEN BALANGAN', 13),
	(354, 'KOTA BANJARMASIN', 13),
	(355, 'KOTA BANJAR BARU', 13),
	(356, 'KABUPATEN PASER', 15),
	(357, 'KABUPATEN KUTAI BARAT', 15),
	(358, 'KABUPATEN KUTAI KARTANEGARA', 15),
	(359, 'KABUPATEN KUTAI TIMUR', 15),
	(360, 'KABUPATEN BERAU', 15),
	(361, 'KABUPATEN PENAJAM PASER UTARA', 15),
	(362, 'KABUPATEN MAHAKAM HULU', 15),
	(363, 'KOTA BALIKPAPAN', 15),
	(364, 'KOTA SAMARINDA', 15),
	(365, 'KOTA BONTANG', 15),
	(366, 'KABUPATEN MALINAU', 16),
	(367, 'KABUPATEN BULUNGAN', 16),
	(368, 'KABUPATEN TANA TIDUNG', 16),
	(369, 'KABUPATEN NUNUKAN', 16),
	(370, 'KOTA TARAKAN', 16),
	(371, 'KABUPATEN BOLAANG MONGONDOW', 31),
	(372, 'KABUPATEN MINAHASA', 31),
	(373, 'KABUPATEN KEPULAUAN SANGIHE', 31),
	(374, 'KABUPATEN KEPULAUAN TALAUD', 31),
	(375, 'KABUPATEN MINAHASA SELATAN', 31),
	(376, 'KABUPATEN MINAHASA UTARA', 31),
	(377, 'KABUPATEN BOLAANG MONGONDOW UTARA', 31),
	(378, 'KABUPATEN SIAU TAGULANDANG BIARO', 31),
	(379, 'KABUPATEN MINAHASA TENGGARA', 31),
	(380, 'KABUPATEN BOLAANG MONGONDOW SELATAN', 31),
	(381, 'KABUPATEN BOLAANG MONGONDOW TIMUR', 31),
	(382, 'KOTA MANADO', 31),
	(383, 'KOTA BITUNG', 31),
	(384, 'KOTA TOMOHON', 31),
	(385, 'KOTA KOTAMOBAGU', 31),
	(386, 'KABUPATEN BANGGAI KEPULAUAN', 29),
	(387, 'KABUPATEN BANGGAI', 29),
	(388, 'KABUPATEN MOROWALI', 29),
	(389, 'KABUPATEN POSO', 29),
	(390, 'KABUPATEN DONGGALA', 29),
	(391, 'KABUPATEN TOLI-TOLI', 29),
	(392, 'KABUPATEN BUOL', 29),
	(393, 'KABUPATEN PARIGI MOUTONG', 29),
	(394, 'KABUPATEN TOJO UNA-UNA', 29),
	(395, 'KABUPATEN SIGI', 29),
	(396, 'KABUPATEN BANGGAI LAUT', 29),
	(397, 'KABUPATEN MOROWALI UTARA', 29),
	(398, 'KOTA PALU', 29),
	(399, 'KABUPATEN KEPULAUAN SELAYAR', 28),
	(400, 'KABUPATEN BULUKUMBA', 28),
	(401, 'KABUPATEN BANTAENG', 28),
	(402, 'KABUPATEN JENEPONTO', 28),
	(403, 'KABUPATEN TAKALAR', 28),
	(404, 'KABUPATEN GOWA', 28),
	(405, 'KABUPATEN SINJAI', 28),
	(406, 'KABUPATEN MAROS', 28),
	(407, 'KABUPATEN PANGKAJENE DAN KEPULAUAN', 28),
	(408, 'KABUPATEN BARRU', 28),
	(409, 'KABUPATEN BONE', 28),
	(410, 'KABUPATEN SOPPENG', 28),
	(411, 'KABUPATEN WAJO', 28),
	(412, 'KABUPATEN SIDENRENG RAPPANG', 28),
	(413, 'KABUPATEN PINRANG', 28),
	(414, 'KABUPATEN ENREKANG', 28),
	(415, 'KABUPATEN LUWU', 28),
	(416, 'KABUPATEN TANA TORAJA', 28),
	(417, 'KABUPATEN LUWU UTARA', 28),
	(418, 'KABUPATEN LUWU TIMUR', 28),
	(419, 'KABUPATEN TORAJA UTARA', 28),
	(420, 'KOTA MAKASSAR', 28),
	(421, 'KOTA PAREPARE', 28),
	(422, 'KOTA PALOPO', 28),
	(423, 'KABUPATEN BUTON', 30),
	(424, 'KABUPATEN MUNA', 30),
	(425, 'KABUPATEN KONAWE', 30),
	(426, 'KABUPATEN KOLAKA', 30),
	(427, 'KABUPATEN KONAWE SELATAN', 30),
	(428, 'KABUPATEN BOMBANA', 30),
	(429, 'KABUPATEN WAKATOBI', 30),
	(430, 'KABUPATEN KOLAKA UTARA', 30),
	(431, 'KABUPATEN BUTON UTARA', 30),
	(432, 'KABUPATEN KONAWE UTARA', 30),
	(433, 'KABUPATEN KOLAKA TIMUR', 30),
	(434, 'KABUPATEN KONAWE KEPULAUAN', 30),
	(435, 'KABUPATEN MUNA BARAT', 30),
	(436, 'KABUPATEN BUTON TENGAH', 30),
	(437, 'KABUPATEN BUTON SELATAN', 30),
	(438, 'KOTA KENDARI', 30),
	(439, 'KOTA BAUBAU', 30),
	(440, 'KABUPATEN BOALEMO', 7),
	(441, 'KABUPATEN GORONTALO', 7),
	(442, 'KABUPATEN POHUWATO', 7),
	(443, 'KABUPATEN BONE BOLANGO', 7),
	(444, 'KABUPATEN GORONTALO UTARA', 7),
	(445, 'KOTA GORONTALO', 7),
	(446, 'KABUPATEN MAJENE', 27),
	(447, 'KABUPATEN POLEWALI MANDAR', 27),
	(448, 'KABUPATEN MAMASA', 27),
	(449, 'KABUPATEN MAMUJU', 27),
	(450, 'KABUPATEN MAMUJU UTARA', 27),
	(451, 'KABUPATEN MAMUJU TENGAH', 27),
	(452, 'KABUPATEN MALUKU TENGGARA BARAT', 19),
	(453, 'KABUPATEN MALUKU TENGGARA', 19),
	(454, 'KABUPATEN MALUKU TENGAH', 19),
	(455, 'KABUPATEN BURU', 19),
	(456, 'KABUPATEN KEPULAUAN ARU', 19),
	(457, 'KABUPATEN SERAM BAGIAN BARAT', 19),
	(458, 'KABUPATEN SERAM BAGIAN TIMUR', 19),
	(459, 'KABUPATEN MALUKU BARAT DAYA', 19),
	(460, 'KABUPATEN BURU SELATAN', 19),
	(461, 'KOTA AMBON', 19),
	(462, 'KOTA TUAL', 19),
	(463, 'KABUPATEN HALMAHERA BARAT', 20),
	(464, 'KABUPATEN HALMAHERA TENGAH', 20),
	(465, 'KABUPATEN KEPULAUAN SULA', 20),
	(466, 'KABUPATEN HALMAHERA SELATAN', 20),
	(467, 'KABUPATEN HALMAHERA UTARA', 20),
	(468, 'KABUPATEN HALMAHERA TIMUR', 20),
	(469, 'KABUPATEN PULAU MOROTAI', 20),
	(470, 'KABUPATEN PULAU TALIABU', 20),
	(471, 'KOTA TERNATE', 20),
	(472, 'KOTA TIDORE KEPULAUAN', 20),
	(473, 'KABUPATEN FAKFAK', 25),
	(474, 'KABUPATEN KAIMANA', 25),
	(475, 'KABUPATEN TELUK WONDAMA', 25),
	(476, 'KABUPATEN TELUK BINTUNI', 25),
	(477, 'KABUPATEN MANOKWARI', 25),
	(478, 'KABUPATEN SORONG SELATAN', 25),
	(479, 'KABUPATEN SORONG', 25),
	(480, 'KABUPATEN RAJA AMPAT', 25),
	(481, 'KABUPATEN TAMBRAUW', 25),
	(482, 'KABUPATEN MAYBRAT', 25),
	(483, 'KABUPATEN MANOKWARI SELATAN', 25),
	(484, 'KABUPATEN PEGUNUNGAN ARFAK', 25),
	(485, 'KOTA SORONG', 25),
	(486, 'KABUPATEN MERAUKE', 25),
	(487, 'KABUPATEN JAYAWIJAYA', 25),
	(488, 'KABUPATEN JAYAPURA', 25),
	(489, 'KABUPATEN NABIRE', 25),
	(490, 'KABUPATEN KEPULAUAN YAPEN', 25),
	(491, 'KABUPATEN BIAK NUMFOR', 25),
	(492, 'KABUPATEN PANIAI', 25),
	(493, 'KABUPATEN PUNCAK JAYA', 25),
	(494, 'KABUPATEN MIMIKA', 25),
	(495, 'KABUPATEN BOVEN DIGOEL', 25),
	(496, 'KABUPATEN MAPPI', 25),
	(497, 'KABUPATEN ASMAT', 25),
	(498, 'KABUPATEN YAHUKIMO', 25),
	(499, 'KABUPATEN PEGUNUNGAN BINTANG', 25),
	(500, 'KABUPATEN TOLIKARA', 25),
	(501, 'KABUPATEN SARMI', 25),
	(502, 'KABUPATEN KEEROM', 25),
	(503, 'KABUPATEN WAROPEN', 25),
	(504, 'KABUPATEN SUPIORI', 25),
	(505, 'KABUPATEN MAMBERAMO RAYA', 25),
	(506, 'KABUPATEN NDUGA', 25),
	(507, 'KABUPATEN LANNY JAYA', 25),
	(508, 'KABUPATEN MAMBERAMO TENGAH', 25),
	(509, 'KABUPATEN YALIMO', 25),
	(510, 'KABUPATEN PUNCAK', 25),
	(511, 'KABUPATEN DOGIYAI', 25),
	(512, 'KABUPATEN INTAN JAYA', 25),
	(513, 'KABUPATEN DEIYAI', 25),
	(514, 'KOTA JAYAPURA', 25);
