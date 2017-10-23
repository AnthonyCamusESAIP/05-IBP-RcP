-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 20 oct. 2017 à 08:18
-- Version du serveur :  10.1.22-MariaDB
-- Version de PHP :  7.1.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ibp-rcp`
--
CREATE DATABASE IF NOT EXISTS `ibp-rcp` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ibp-rcp`;

-- --------------------------------------------------------

--
-- Structure de la table `campagne`
--

CREATE TABLE `campagne` (
  `idCampagne` int(11) NOT NULL,
  `label` varchar(255) NOT NULL,
  `idProjet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

CREATE TABLE `projet` (
  `idProjet` int(11) NOT NULL,
  `label` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `test`
--

CREATE TABLE `test` (
  `idTest` int(11) NOT NULL,
  `date` date NOT NULL,
  `heure` time NOT NULL,
  `statut` enum('N/A','Passed','Failed','Not Completed') NOT NULL,
  `nomTesteur` varchar(255) NOT NULL,
  `idProjet` int(11) NOT NULL,
  `idCampagne` int(11) NOT NULL,
  `idTesteur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `testeur`
--

CREATE TABLE `testeur` (
  `idTesteur` int(11) NOT NULL,
  `nomTesteur` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `campagne`
--
ALTER TABLE `campagne`
  ADD PRIMARY KEY (`idCampagne`),
  ADD KEY `idProjet` (`idProjet`);

--
-- Index pour la table `projet`
--
ALTER TABLE `projet`
  ADD PRIMARY KEY (`idProjet`);

--
-- Index pour la table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`idTest`),
  ADD KEY `idTesteur` (`idTesteur`),
  ADD KEY `idCampagne` (`idCampagne`),
  ADD KEY `idProjet` (`idProjet`);

--
-- Index pour la table `testeur`
--
ALTER TABLE `testeur`
  ADD PRIMARY KEY (`idTesteur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `campagne`
--
ALTER TABLE `campagne`
  MODIFY `idCampagne` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `projet`
--
ALTER TABLE `projet`
  MODIFY `idProjet` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `test`
--
ALTER TABLE `test`
  MODIFY `idTest` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `testeur`
--
ALTER TABLE `testeur`
  MODIFY `idTesteur` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `campagne`
--
ALTER TABLE `campagne`
  ADD CONSTRAINT `campagne_ibfk_1` FOREIGN KEY (`idProjet`) REFERENCES `projet` (`idProjet`);

--
-- Contraintes pour la table `test`
--
ALTER TABLE `test`
  ADD CONSTRAINT `test_ibfk_1` FOREIGN KEY (`idCampagne`) REFERENCES `campagne` (`idCampagne`),
  ADD CONSTRAINT `test_ibfk_2` FOREIGN KEY (`idProjet`) REFERENCES `projet` (`idProjet`),
  ADD CONSTRAINT `test_ibfk_3` FOREIGN KEY (`idTesteur`) REFERENCES `testeur` (`idTesteur`);
--
-- Base de données :  `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Structure de la table `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(11) NOT NULL,
  `dbase` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `user` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `query` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Structure de la table `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_length` text COLLATE utf8_bin,
  `col_collation` varchar(64) COLLATE utf8_bin NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) COLLATE utf8_bin DEFAULT '',
  `col_default` text COLLATE utf8_bin
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Structure de la table `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `column_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `settings_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

--
-- Déchargement des données de la table `pma__designer_settings`
--

INSERT INTO `pma__designer_settings` (`username`, `settings_data`) VALUES
('root', '{\"angular_direct\":\"angular\",\"snap_to_grid\":\"off\",\"relation_lines\":\"true\",\"side_menu\":\"false\"}');

-- --------------------------------------------------------

--
-- Structure de la table `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `export_type` varchar(10) COLLATE utf8_bin NOT NULL,
  `template_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `template_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Structure de la table `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Structure de la table `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `sqlquery` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `item_type` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Structure de la table `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

--
-- Déchargement des données de la table `pma__pdf_pages`
--

INSERT INTO `pma__pdf_pages` (`db_name`, `page_nr`, `page_descr`) VALUES
('searchtrainer_non_ef', 2, 'SearchTrainer'),
('toutoull', 3, 'Commande'),
('ibp-rcp', 4, 'IBP-RcP');

-- --------------------------------------------------------

--
-- Structure de la table `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `tables` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Déchargement des données de la table `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"ibp-rcp\",\"table\":\"test\"},{\"db\":\"ibp-rcp\",\"table\":\"testeur\"},{\"db\":\"ibp-rcp\",\"table\":\"Campagne\"},{\"db\":\"ibp-rcp\",\"table\":\"Projet\"},{\"db\":\"ibp-rcp\",\"table\":\"project\"},{\"db\":\"IBP-RcP\",\"table\":\"Project\"},{\"db\":\"toutoull\",\"table\":\"commande\"},{\"db\":\"toutoull\",\"table\":\"bondecommande\"},{\"db\":\"toutoull\",\"table\":\"fournisseur\"},{\"db\":\"toutoull\",\"table\":\"produit\"}]');

-- --------------------------------------------------------

--
-- Structure de la table `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `master_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_db` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_table` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `foreign_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Structure de la table `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `search_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Structure de la table `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT '0',
  `x` float UNSIGNED NOT NULL DEFAULT '0',
  `y` float UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

--
-- Déchargement des données de la table `pma__table_coords`
--

INSERT INTO `pma__table_coords` (`db_name`, `table_name`, `pdf_page_number`, `x`, `y`) VALUES
('ibp-rcp', 'campagne', 4, 419, 28),
('ibp-rcp', 'projet', 4, 686, 42),
('ibp-rcp', 'test', 4, 459, 220),
('ibp-rcp', 'testeur', 4, 500, 467),
('searchtrainer_non_ef', 'annonce', 2, 1267, 35),
('searchtrainer_non_ef', 'annonce_vue', 2, 1242, 189),
('searchtrainer_non_ef', 'appartenir', 2, 480, 588),
('searchtrainer_non_ef', 'calendrier', 2, 160, 188),
('searchtrainer_non_ef', 'candidature', 2, 936, 411),
('searchtrainer_non_ef', 'client', 2, 598, 58),
('searchtrainer_non_ef', 'competence', 2, 177, 568),
('searchtrainer_non_ef', 'formateur', 2, 508, 353),
('searchtrainer_non_ef', 'groupeformateur', 2, 480, 699),
('searchtrainer_non_ef', 'mission', 2, 640, 169),
('searchtrainer_non_ef', 'mobilite', 2, 935, 655),
('searchtrainer_non_ef', 'savoirfaire', 2, 93, 347),
('searchtrainer_non_ef', 'technologie', 2, 145, 468),
('searchtrainer_non_ef', 'utilisateur', 2, 75, 32),
('searchtrainer_non_ef', 'zone_geographique', 2, 1216, 650),
('toutoull', 'bondecommande', 3, 693, 133),
('toutoull', 'client', 3, 1006, 276),
('toutoull', 'commande', 3, 728, 269),
('toutoull', 'fournisseur', 3, 66, 174),
('toutoull', 'produit', 3, 388, 54),
('toutoull', 'typeenvoi', 3, 692, 405);

-- --------------------------------------------------------

--
-- Structure de la table `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT '',
  `display_field` varchar(64) COLLATE utf8_bin NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `prefs` text COLLATE utf8_bin NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Structure de la table `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `table_name` varchar(64) COLLATE utf8_bin NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text COLLATE utf8_bin NOT NULL,
  `schema_sql` text COLLATE utf8_bin,
  `data_sql` longtext COLLATE utf8_bin,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') COLLATE utf8_bin DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Structure de la table `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `config_data` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Déchargement des données de la table `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2017-06-20 18:49:41', '{\"lang\":\"fr\",\"collation_connection\":\"utf8mb4_unicode_ci\"}');

-- --------------------------------------------------------

--
-- Structure de la table `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL,
  `tab` varchar(64) COLLATE utf8_bin NOT NULL,
  `allowed` enum('Y','N') COLLATE utf8_bin NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Structure de la table `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) COLLATE utf8_bin NOT NULL,
  `usergroup` varchar(64) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Index pour la table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Index pour la table `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Index pour la table `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Index pour la table `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Index pour la table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Index pour la table `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Index pour la table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Index pour la table `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Index pour la table `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Index pour la table `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Index pour la table `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Index pour la table `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Index pour la table `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;--
-- Base de données :  `searchtrainer`
--
CREATE DATABASE IF NOT EXISTS `searchtrainer` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `searchtrainer`;

-- --------------------------------------------------------

--
-- Structure de la table `__migrationhistory`
--

CREATE TABLE `__migrationhistory` (
  `MigrationId` varchar(150) CHARACTER SET utf8 NOT NULL,
  `ContextKey` varchar(300) CHARACTER SET utf8 NOT NULL,
  `Model` longblob NOT NULL,
  `ProductVersion` varchar(32) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `adhesion`
--

CREATE TABLE `adhesion` (
  `FormateurId` int(11) NOT NULL,
  `GroupeFormateurId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adhesion`
--

INSERT INTO `adhesion` (`FormateurId`, `GroupeFormateurId`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

CREATE TABLE `annonce` (
  `AnnonceId` int(11) NOT NULL,
  `ClientId` int(11) NOT NULL,
  `Description` longtext,
  `DateDebut` datetime NOT NULL,
  `DateFin` datetime NOT NULL,
  `Lieu` longtext,
  `Salaire` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `annonce`
--

INSERT INTO `annonce` (`AnnonceId`, `ClientId`, `Description`, `DateDebut`, `DateFin`, `Lieu`, `Salaire`) VALUES
(1, 1, 'IDK', '2017-06-30 00:00:00', '2017-07-24 00:00:00', 'Dijon', 2000);

-- --------------------------------------------------------

--
-- Structure de la table `annoncevue`
--

CREATE TABLE `annoncevue` (
  `AnnonceVueId` int(11) NOT NULL,
  `AnnonceId` int(11) NOT NULL,
  `FormateurId` int(11) NOT NULL,
  `Statut` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `annoncevue`
--

INSERT INTO `annoncevue` (`AnnonceVueId`, `AnnonceId`, `FormateurId`, `Statut`) VALUES
(1, 1, 1, 'Lu');

-- --------------------------------------------------------

--
-- Structure de la table `calendrier`
--

CREATE TABLE `calendrier` (
  `CalendrierId` int(11) NOT NULL,
  `UtilisateurId` int(11) NOT NULL,
  `DateDebut` datetime NOT NULL,
  `DateFin` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `calendrier`
--

INSERT INTO `calendrier` (`CalendrierId`, `UtilisateurId`, `DateDebut`, `DateFin`) VALUES
(1, 2, '2017-05-02 00:00:00', '2017-06-06 00:00:00'),
(2, 2, '2017-06-20 00:00:00', '2017-06-24 00:00:00'),
(3, 3, '2017-05-02 00:00:00', '2017-06-06 00:00:00'),
(4, 3, '2017-06-20 00:00:00', '2017-06-24 00:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `candidature`
--

CREATE TABLE `candidature` (
  `FormateurId` int(11) NOT NULL,
  `AnnonceId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `candidature`
--

INSERT INTO `candidature` (`FormateurId`, `AnnonceId`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `ClientId` int(11) NOT NULL,
  `Nom` longtext,
  `Type` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`ClientId`, `Nom`, `Type`) VALUES
(1, 'OpenBrain IT', 'Client interne');

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

CREATE TABLE `competence` (
  `CompetenceId` int(11) NOT NULL,
  `Libelle` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `competence`
--

INSERT INTO `competence` (`CompetenceId`, `Libelle`) VALUES
(1, 'Analyse programmation'),
(2, 'Architecture bases de données'),
(3, 'Architecture des logiciels'),
(4, 'Conception web'),
(5, 'Développement d\'application'),
(6, 'Développement web'),
(7, 'Ingénierie d\'études'),
(8, 'Conception-réalisation'),
(9, 'Webdesign'),
(10, 'Webmaster/ créateur de sites web'),
(11, 'Responsive Design'),
(12, 'Hotliner');

-- --------------------------------------------------------

--
-- Structure de la table `exigence`
--

CREATE TABLE `exigence` (
  `ExigenceId` int(11) NOT NULL,
  `AnnonceId` int(11) NOT NULL,
  `CompetenceId` int(11) NOT NULL,
  `TechnologieId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `formateur`
--

CREATE TABLE `formateur` (
  `FormateurId` int(11) NOT NULL,
  `Nom` longtext,
  `Prenom` longtext,
  `Adresse` longtext,
  `Ville` longtext,
  `CodePostal` longtext,
  `TJM` int(11) NOT NULL,
  `Blackliste` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `formateur`
--

INSERT INTO `formateur` (`FormateurId`, `Nom`, `Prenom`, `Adresse`, `Ville`, `CodePostal`, `TJM`, `Blackliste`) VALUES
(1, 'Camus', 'Anthony', '166 rue d\'Auxonne', 'Dijon', '21000', 500, 0);

-- --------------------------------------------------------

--
-- Structure de la table `groupeformateur`
--

CREATE TABLE `groupeformateur` (
  `GroupeFormateurId` int(11) NOT NULL,
  `Nom` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupeformateur`
--

INSERT INTO `groupeformateur` (`GroupeFormateurId`, `Nom`) VALUES
(1, 'IDK Company');

-- --------------------------------------------------------

--
-- Structure de la table `mission`
--

CREATE TABLE `mission` (
  `MissionId` int(11) NOT NULL,
  `FormateurId` int(11) NOT NULL,
  `ClientId` int(11) NOT NULL,
  `Description` longtext,
  `Note` int(11) NOT NULL,
  `Salaire` float NOT NULL,
  `DateDebut` datetime NOT NULL,
  `DateFin` datetime NOT NULL,
  `Lieu` longtext,
  `Statut` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `mission`
--

INSERT INTO `mission` (`MissionId`, `FormateurId`, `ClientId`, `Description`, `Note`, `Salaire`, `DateDebut`, `DateFin`, `Lieu`, `Statut`) VALUES
(1, 1, 1, 'Une desc', 10, 500, '2017-05-02 00:00:00', '2017-06-06 00:00:00', '166 Rue d\'auxonne', 'Confirmée'),
(2, 1, 1, 'IDK', 8, 300, '2017-06-20 00:00:00', '2017-06-24 00:00:00', '19 rue de la colombière', 'Confirmée');

-- --------------------------------------------------------

--
-- Structure de la table `mobilite`
--

CREATE TABLE `mobilite` (
  `MobiliteId` int(11) NOT NULL,
  `FormateurId` int(11) NOT NULL,
  `Region` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `mobilite`
--

INSERT INTO `mobilite` (`MobiliteId`, `FormateurId`, `Region`) VALUES
(4, 1, 'Alpes-de-Haute-Provence'),
(5, 1, 'Hautes-Alpes'),
(6, 1, 'Alpes-Maritimes'),
(13, 1, 'Bouches-du-Rhône'),
(21, 1, 'Côte d\'Or'),
(83, 1, 'Var'),
(84, 1, 'Vaucluse');

-- --------------------------------------------------------

--
-- Structure de la table `savoirfaire`
--

CREATE TABLE `savoirfaire` (
  `FormateurId` int(11) NOT NULL,
  `CompetenceId` int(11) NOT NULL,
  `TechnologieId` int(11) NOT NULL,
  `Domaine` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `savoirfaire`
--

INSERT INTO `savoirfaire` (`FormateurId`, `CompetenceId`, `TechnologieId`, `Domaine`) VALUES
(1, 1, 4, 'Developpement'),
(1, 1, 6, 'Developpement'),
(1, 1, 7, 'Developpement'),
(1, 1, 8, 'Developpement'),
(1, 1, 10, 'Developpement'),
(1, 1, 11, 'Developpement'),
(1, 1, 12, 'Developpement'),
(1, 1, 13, 'Developpement'),
(1, 1, 18, 'Developpement'),
(1, 1, 20, 'Developpement'),
(1, 1, 23, 'Developpement'),
(1, 1, 24, 'Developpement'),
(1, 2, 5, 'Developpement'),
(1, 2, 14, 'Developpement'),
(1, 2, 19, 'Developpement'),
(1, 2, 21, 'Developpement'),
(1, 2, 22, 'Developpement'),
(1, 2, 25, 'Developpement'),
(1, 2, 26, 'Developpement');

-- --------------------------------------------------------

--
-- Structure de la table `technologie`
--

CREATE TABLE `technologie` (
  `TechnologieId` int(11) NOT NULL,
  `Libelle` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `technologie`
--

INSERT INTO `technologie` (`TechnologieId`, `Libelle`) VALUES
(1, 'ADOBE ILLUSTRATOR'),
(2, 'ADOBE PHOTOSHOP'),
(3, 'APACHE MAVEN'),
(4, 'C'),
(5, 'DB2'),
(6, 'EXT JS'),
(7, 'HIBERNATE'),
(8, 'HTML5'),
(9, 'IBM Websphere'),
(10, 'J2EE'),
(11, 'JAVA'),
(12, 'JAVA SERVLETS'),
(13, 'JAVASCRIPT'),
(14, 'JDBC'),
(15, 'LINUX'),
(16, 'MERISE'),
(17, 'MICROSOFT IIS'),
(18, 'MVC'),
(19, 'MYSQL'),
(20, 'PHP'),
(21, 'ORACLE'),
(22, 'PL/SQL'),
(23, 'SHELL SCRIPT'),
(24, 'SPRING'),
(25, 'SQL'),
(26, 'SQL SERVER'),
(27, 'SUBVERSION (SVN)'),
(28, 'TALEND'),
(29, 'TOMCAT'),
(30, 'W3C');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `UtilisateurId` int(11) NOT NULL,
  `Login` longtext,
  `Password` longtext,
  `Type` longtext,
  `FormateurId` int(11) DEFAULT NULL,
  `ClientId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`UtilisateurId`, `Login`, `Password`, `Type`, `FormateurId`, `ClientId`) VALUES
(1, 'root', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', 'Administrateur', NULL, NULL),
(2, 'anthony', '502913bfdd49eab564282dff101e6d167321237eeec66eedb2a438ed80fdeaa0', 'Formateur', 1, NULL),
(3, 'openbrainit', '4813494d137e1631bba301d5acab6e7bb7aa74ce1185d456565ef51d737677b2', 'Client', NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `zonegeographique`
--

CREATE TABLE `zonegeographique` (
  `ZoneGeographiqueId` int(11) NOT NULL,
  `MissionId` int(11) NOT NULL,
  `Region` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `zonegeographique`
--

INSERT INTO `zonegeographique` (`ZoneGeographiqueId`, `MissionId`, `Region`) VALUES
(21, 1, 'Côte d\'Or');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adhesion`
--
ALTER TABLE `adhesion`
  ADD PRIMARY KEY (`FormateurId`,`GroupeFormateurId`),
  ADD KEY `Adhesion_GroupeFormateur` (`GroupeFormateurId`);

--
-- Index pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD PRIMARY KEY (`AnnonceId`),
  ADD UNIQUE KEY `AnnonceId` (`AnnonceId`),
  ADD KEY `ClientId` (`ClientId`);

--
-- Index pour la table `annoncevue`
--
ALTER TABLE `annoncevue`
  ADD PRIMARY KEY (`AnnonceVueId`),
  ADD UNIQUE KEY `AnnonceVueId` (`AnnonceVueId`),
  ADD KEY `AnnonceId` (`AnnonceId`),
  ADD KEY `FormateurId` (`FormateurId`);

--
-- Index pour la table `calendrier`
--
ALTER TABLE `calendrier`
  ADD PRIMARY KEY (`CalendrierId`),
  ADD UNIQUE KEY `CalendrierId` (`CalendrierId`),
  ADD KEY `UtilisateurId` (`UtilisateurId`);

--
-- Index pour la table `candidature`
--
ALTER TABLE `candidature`
  ADD PRIMARY KEY (`FormateurId`,`AnnonceId`),
  ADD KEY `Candidature_Annonce` (`AnnonceId`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`ClientId`),
  ADD UNIQUE KEY `ClientId` (`ClientId`);

--
-- Index pour la table `competence`
--
ALTER TABLE `competence`
  ADD PRIMARY KEY (`CompetenceId`),
  ADD UNIQUE KEY `CompetenceId` (`CompetenceId`);

--
-- Index pour la table `exigence`
--
ALTER TABLE `exigence`
  ADD PRIMARY KEY (`ExigenceId`),
  ADD UNIQUE KEY `ExigenceId` (`ExigenceId`),
  ADD KEY `AnnonceId` (`AnnonceId`),
  ADD KEY `CompetenceId` (`CompetenceId`),
  ADD KEY `TechnologieId` (`TechnologieId`);

--
-- Index pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD PRIMARY KEY (`FormateurId`),
  ADD UNIQUE KEY `FormateurId` (`FormateurId`);

--
-- Index pour la table `groupeformateur`
--
ALTER TABLE `groupeformateur`
  ADD PRIMARY KEY (`GroupeFormateurId`),
  ADD UNIQUE KEY `GroupeFormateurId` (`GroupeFormateurId`);

--
-- Index pour la table `mission`
--
ALTER TABLE `mission`
  ADD PRIMARY KEY (`MissionId`),
  ADD UNIQUE KEY `MissionId` (`MissionId`),
  ADD KEY `ClientId` (`ClientId`),
  ADD KEY `FormateurId` (`FormateurId`);

--
-- Index pour la table `mobilite`
--
ALTER TABLE `mobilite`
  ADD PRIMARY KEY (`MobiliteId`),
  ADD UNIQUE KEY `MobiliteId` (`MobiliteId`),
  ADD KEY `FormateurId` (`FormateurId`);

--
-- Index pour la table `savoirfaire`
--
ALTER TABLE `savoirfaire`
  ADD PRIMARY KEY (`FormateurId`,`CompetenceId`,`TechnologieId`),
  ADD KEY `SavoirFaire_Competence` (`CompetenceId`),
  ADD KEY `SavoirFaire_Technologie` (`TechnologieId`);

--
-- Index pour la table `technologie`
--
ALTER TABLE `technologie`
  ADD PRIMARY KEY (`TechnologieId`),
  ADD UNIQUE KEY `TechnologieId` (`TechnologieId`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`UtilisateurId`),
  ADD UNIQUE KEY `UtilisateurId` (`UtilisateurId`),
  ADD KEY `ClientId` (`ClientId`),
  ADD KEY `FormateurId` (`FormateurId`);

--
-- Index pour la table `zonegeographique`
--
ALTER TABLE `zonegeographique`
  ADD PRIMARY KEY (`ZoneGeographiqueId`),
  ADD UNIQUE KEY `ZoneGeographiqueId` (`ZoneGeographiqueId`),
  ADD KEY `MissionId` (`MissionId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `annonce`
--
ALTER TABLE `annonce`
  MODIFY `AnnonceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `annoncevue`
--
ALTER TABLE `annoncevue`
  MODIFY `AnnonceVueId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `calendrier`
--
ALTER TABLE `calendrier`
  MODIFY `CalendrierId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `ClientId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `competence`
--
ALTER TABLE `competence`
  MODIFY `CompetenceId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `exigence`
--
ALTER TABLE `exigence`
  MODIFY `ExigenceId` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `formateur`
--
ALTER TABLE `formateur`
  MODIFY `FormateurId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `groupeformateur`
--
ALTER TABLE `groupeformateur`
  MODIFY `GroupeFormateurId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `mission`
--
ALTER TABLE `mission`
  MODIFY `MissionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `mobilite`
--
ALTER TABLE `mobilite`
  MODIFY `MobiliteId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;
--
-- AUTO_INCREMENT pour la table `technologie`
--
ALTER TABLE `technologie`
  MODIFY `TechnologieId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `UtilisateurId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `zonegeographique`
--
ALTER TABLE `zonegeographique`
  MODIFY `ZoneGeographiqueId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `adhesion`
--
ALTER TABLE `adhesion`
  ADD CONSTRAINT `Adhesion_Formateur` FOREIGN KEY (`FormateurId`) REFERENCES `formateur` (`FormateurId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `Adhesion_GroupeFormateur` FOREIGN KEY (`GroupeFormateurId`) REFERENCES `groupeformateur` (`GroupeFormateurId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD CONSTRAINT `Client_Annonces` FOREIGN KEY (`ClientId`) REFERENCES `client` (`ClientId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `annoncevue`
--
ALTER TABLE `annoncevue`
  ADD CONSTRAINT `AnnonceVue_Annonce` FOREIGN KEY (`AnnonceId`) REFERENCES `annonce` (`AnnonceId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `AnnonceVue_Formateur` FOREIGN KEY (`FormateurId`) REFERENCES `formateur` (`FormateurId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `calendrier`
--
ALTER TABLE `calendrier`
  ADD CONSTRAINT `Utilisateur_plagesNonDisponible` FOREIGN KEY (`UtilisateurId`) REFERENCES `utilisateur` (`UtilisateurId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `candidature`
--
ALTER TABLE `candidature`
  ADD CONSTRAINT `Candidature_Annonce` FOREIGN KEY (`AnnonceId`) REFERENCES `annonce` (`AnnonceId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `Candidature_Formateur` FOREIGN KEY (`FormateurId`) REFERENCES `formateur` (`FormateurId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `exigence`
--
ALTER TABLE `exigence`
  ADD CONSTRAINT `Competence_Exigences` FOREIGN KEY (`CompetenceId`) REFERENCES `competence` (`CompetenceId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `Exigence_Annonce` FOREIGN KEY (`AnnonceId`) REFERENCES `annonce` (`AnnonceId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `Technologie_Exigences` FOREIGN KEY (`TechnologieId`) REFERENCES `technologie` (`TechnologieId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `Mission_Client` FOREIGN KEY (`ClientId`) REFERENCES `client` (`ClientId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `Mission_Formateur` FOREIGN KEY (`FormateurId`) REFERENCES `formateur` (`FormateurId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `mobilite`
--
ALTER TABLE `mobilite`
  ADD CONSTRAINT `Mobilite_Formateur` FOREIGN KEY (`FormateurId`) REFERENCES `formateur` (`FormateurId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `savoirfaire`
--
ALTER TABLE `savoirfaire`
  ADD CONSTRAINT `SavoirFaire_Competence` FOREIGN KEY (`CompetenceId`) REFERENCES `competence` (`CompetenceId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `SavoirFaire_Formateur` FOREIGN KEY (`FormateurId`) REFERENCES `formateur` (`FormateurId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `SavoirFaire_Technologie` FOREIGN KEY (`TechnologieId`) REFERENCES `technologie` (`TechnologieId`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `Utilisateur_Client` FOREIGN KEY (`ClientId`) REFERENCES `client` (`ClientId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Utilisateur_Formateur` FOREIGN KEY (`FormateurId`) REFERENCES `formateur` (`FormateurId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `zonegeographique`
--
ALTER TABLE `zonegeographique`
  ADD CONSTRAINT `ZoneGeographique_Mission` FOREIGN KEY (`MissionId`) REFERENCES `mission` (`MissionId`) ON DELETE CASCADE ON UPDATE NO ACTION;
--
-- Base de données :  `searchtrainer_non_ef`
--
CREATE DATABASE IF NOT EXISTS `searchtrainer_non_ef` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `searchtrainer_non_ef`;

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

CREATE TABLE `annonce` (
  `IdAnnonce` int(11) NOT NULL,
  `IdClient` int(11) NOT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date NOT NULL,
  `Lieu` varchar(255) NOT NULL,
  `Salaire` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `annonce_vue`
--

CREATE TABLE `annonce_vue` (
  `IdAnnonce` int(11) NOT NULL,
  `IdFormateur` int(11) NOT NULL,
  `Statut` enum('Lu','Non lu') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `appartenir`
--

CREATE TABLE `appartenir` (
  `IdGroupeFormateur` int(11) NOT NULL,
  `IdFormateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `calendrier`
--

CREATE TABLE `calendrier` (
  `IdPlageCalendrier` int(11) NOT NULL,
  `IdFormateur` int(11) DEFAULT NULL,
  `IdClient` int(11) DEFAULT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Structure de la table `candidature`
--

CREATE TABLE `candidature` (
  `IdAnnonce` int(11) NOT NULL,
  `IdFormateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `IdClient` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Type` enum('Société externe','Client externe','Client interne','Société interne') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`IdClient`, `Nom`, `Type`) VALUES
(1, 'OpenBrain IT', 'Client interne');

-- --------------------------------------------------------

--
-- Structure de la table `competence`
--

CREATE TABLE `competence` (
  `IdCompetence` int(11) NOT NULL,
  `Libelle` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `competence`
--

INSERT INTO `competence` (`IdCompetence`, `Libelle`) VALUES
(1, 'Analyse programmation'),
(2, 'Architecture bases de données'),
(3, 'Architecture des logiciels'),
(4, 'Conception web'),
(5, 'Développement d\'application'),
(6, 'Développement web'),
(7, 'Ingénierie d\'études'),
(8, 'Conception-réalisation'),
(9, 'Webdesign'),
(10, 'Webmaster/ créateur de sites web'),
(11, 'Responsive Design'),
(12, 'Hotliner');

-- --------------------------------------------------------

--
-- Structure de la table `formateur`
--

CREATE TABLE `formateur` (
  `IdFormateur` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL,
  `Prenom` varchar(100) NOT NULL,
  `Adresse` varchar(255) NOT NULL,
  `Ville` varchar(150) NOT NULL,
  `CodePostal` tinytext NOT NULL,
  `TJM` int(11) NOT NULL,
  `Blackliste` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `formateur`
--

INSERT INTO `formateur` (`IdFormateur`, `Nom`, `Prenom`, `Adresse`, `Ville`, `CodePostal`, `TJM`, `Blackliste`) VALUES
(1, 'DOMENICI', 'Ludovic', '21 promenade de la plage', 'Cagnes-sur-Mer', '06800', 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `groupeformateur`
--

CREATE TABLE `groupeformateur` (
  `IdGroupeFormateur` int(11) NOT NULL,
  `Nom` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `mission`
--

CREATE TABLE `mission` (
  `IdMission` int(11) NOT NULL,
  `IdFormateur` int(11) NOT NULL,
  `IdClient` int(11) NOT NULL,
  `Note` int(11) NOT NULL,
  `Salaire` float NOT NULL,
  `DateDebut` date NOT NULL,
  `DateFin` date NOT NULL,
  `Lieu` varchar(255) NOT NULL,
  `Statut` enum('Confirmée','Option','Annulée') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `mission`
--

INSERT INTO `mission` (`IdMission`, `IdFormateur`, `IdClient`, `Note`, `Salaire`, `DateDebut`, `DateFin`, `Lieu`, `Statut`) VALUES
(1, 1, 1, 10, 500, '2017-05-02', '2017-06-06', '166 Rue d\'auxonne', 'Confirmée'),
(2, 1, 1, 8, 300, '2017-06-20', '2017-06-24', '19 rue de la colombière', 'Confirmée');

-- --------------------------------------------------------

--
-- Structure de la table `mobilite`
--

CREATE TABLE `mobilite` (
  `CodeINSEE` varchar(2) NOT NULL,
  `IdFormateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `mobilite`
--

INSERT INTO `mobilite` (`CodeINSEE`, `IdFormateur`) VALUES
('04', 1),
('05', 1),
('06', 1),
('13', 1),
('83', 1),
('84', 1);

-- --------------------------------------------------------

--
-- Structure de la table `savoirfaire`
--

CREATE TABLE `savoirfaire` (
  `IdFormateur` int(11) NOT NULL,
  `IdCompetence` int(11) NOT NULL,
  `IdTechnologie` int(11) NOT NULL,
  `Domaine` enum('Developpement','Reseau/Infra') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `savoirfaire`
--

INSERT INTO `savoirfaire` (`IdFormateur`, `IdCompetence`, `IdTechnologie`, `Domaine`) VALUES
(1, 1, 4, 'Developpement'),
(1, 1, 6, 'Developpement'),
(1, 1, 7, 'Developpement'),
(1, 1, 8, 'Developpement'),
(1, 1, 10, 'Developpement'),
(1, 1, 11, 'Developpement'),
(1, 1, 12, 'Developpement'),
(1, 1, 13, 'Developpement'),
(1, 1, 18, 'Developpement'),
(1, 1, 20, 'Developpement'),
(1, 1, 23, 'Developpement'),
(1, 1, 24, 'Developpement'),
(1, 2, 5, 'Developpement'),
(1, 2, 14, 'Developpement'),
(1, 2, 19, 'Developpement'),
(1, 2, 21, 'Developpement'),
(1, 2, 22, 'Developpement'),
(1, 2, 25, 'Developpement'),
(1, 2, 26, 'Developpement');

-- --------------------------------------------------------

--
-- Structure de la table `technologie`
--

CREATE TABLE `technologie` (
  `IdTechnologie` int(11) NOT NULL,
  `Libelle` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `technologie`
--

INSERT INTO `technologie` (`IdTechnologie`, `Libelle`) VALUES
(1, 'ADOBE ILLUSTRATOR'),
(2, 'ADOBE PHOTOSHOP'),
(3, 'APACHE MAVEN'),
(4, 'C'),
(5, 'DB2'),
(6, 'EXT JS'),
(7, 'HIBERNATE'),
(8, 'HTML5'),
(9, 'IBM Websphere'),
(10, 'J2EE'),
(11, 'JAVA'),
(12, 'JAVA SERVLETS'),
(13, 'JAVASCRIPT'),
(14, 'JDBC'),
(15, 'LINUX'),
(16, 'MERISE'),
(17, 'MICROSOFT IIS'),
(18, 'MVC'),
(19, 'MYSQL'),
(20, 'PHP'),
(21, 'ORACLE'),
(22, 'PL/SQL'),
(23, 'SHELL SCRIPT'),
(24, 'SPRING'),
(25, 'SQL'),
(26, 'SQL SERVER'),
(27, 'SUBVERSION (SVN)'),
(28, 'TALEND'),
(29, 'TOMCAT'),
(30, 'W3C');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `IdUtilisateur` int(11) NOT NULL,
  `Login` varchar(100) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Type` enum('Formateur','Client','Centre de formation','Administrateur') NOT NULL,
  `IdFormateur` int(11) DEFAULT NULL,
  `IdClient` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`IdUtilisateur`, `Login`, `Password`, `Type`, `IdFormateur`, `IdClient`) VALUES
(1, 'root', 'dc76e9f0c0006e8f919e0c515c66dbba3982f785', 'Administrateur', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `zone_geographique`
--

CREATE TABLE `zone_geographique` (
  `CodeINSEE` varchar(2) NOT NULL,
  `Libelle` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `zone_geographique`
--

INSERT INTO `zone_geographique` (`CodeINSEE`, `Libelle`) VALUES
('04', 'Alpes-de-Haute-Provence'),
('05', 'Hautes-Alpes'),
('06', 'Alpes-Maritimes'),
('13', 'Bouches-du-Rhône'),
('83', 'Var'),
('84', 'Vaucluse');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD PRIMARY KEY (`IdAnnonce`),
  ADD KEY `IdClient` (`IdClient`);

--
-- Index pour la table `annonce_vue`
--
ALTER TABLE `annonce_vue`
  ADD PRIMARY KEY (`IdAnnonce`,`IdFormateur`),
  ADD KEY `IdFormateur` (`IdFormateur`);

--
-- Index pour la table `appartenir`
--
ALTER TABLE `appartenir`
  ADD PRIMARY KEY (`IdGroupeFormateur`,`IdFormateur`),
  ADD KEY `IdFormateur` (`IdFormateur`);

--
-- Index pour la table `calendrier`
--
ALTER TABLE `calendrier`
  ADD PRIMARY KEY (`IdPlageCalendrier`),
  ADD KEY `IdFormateur` (`IdFormateur`),
  ADD KEY `IdClient` (`IdClient`);

--
-- Index pour la table `candidature`
--
ALTER TABLE `candidature`
  ADD PRIMARY KEY (`IdAnnonce`,`IdFormateur`),
  ADD KEY `IdAnnonce` (`IdAnnonce`),
  ADD KEY `IdFormateur` (`IdFormateur`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`IdClient`);

--
-- Index pour la table `competence`
--
ALTER TABLE `competence`
  ADD PRIMARY KEY (`IdCompetence`);

--
-- Index pour la table `formateur`
--
ALTER TABLE `formateur`
  ADD PRIMARY KEY (`IdFormateur`);

--
-- Index pour la table `groupeformateur`
--
ALTER TABLE `groupeformateur`
  ADD PRIMARY KEY (`IdGroupeFormateur`);

--
-- Index pour la table `mission`
--
ALTER TABLE `mission`
  ADD PRIMARY KEY (`IdMission`),
  ADD KEY `IdFormateur` (`IdFormateur`),
  ADD KEY `IdClient` (`IdClient`);

--
-- Index pour la table `mobilite`
--
ALTER TABLE `mobilite`
  ADD PRIMARY KEY (`CodeINSEE`,`IdFormateur`),
  ADD KEY `IdFormateur` (`IdFormateur`);

--
-- Index pour la table `savoirfaire`
--
ALTER TABLE `savoirfaire`
  ADD PRIMARY KEY (`IdFormateur`,`IdCompetence`,`IdTechnologie`),
  ADD KEY `IdFormateur` (`IdFormateur`),
  ADD KEY `IdCompetence` (`IdCompetence`),
  ADD KEY `IdTechnologie` (`IdTechnologie`);

--
-- Index pour la table `technologie`
--
ALTER TABLE `technologie`
  ADD PRIMARY KEY (`IdTechnologie`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`IdUtilisateur`),
  ADD KEY `IdFormateur` (`IdFormateur`),
  ADD KEY `IdClient` (`IdClient`);

--
-- Index pour la table `zone_geographique`
--
ALTER TABLE `zone_geographique`
  ADD PRIMARY KEY (`CodeINSEE`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `annonce`
--
ALTER TABLE `annonce`
  MODIFY `IdAnnonce` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `calendrier`
--
ALTER TABLE `calendrier`
  MODIFY `IdPlageCalendrier` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `IdClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `competence`
--
ALTER TABLE `competence`
  MODIFY `IdCompetence` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `formateur`
--
ALTER TABLE `formateur`
  MODIFY `IdFormateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `groupeformateur`
--
ALTER TABLE `groupeformateur`
  MODIFY `IdGroupeFormateur` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `mission`
--
ALTER TABLE `mission`
  MODIFY `IdMission` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `technologie`
--
ALTER TABLE `technologie`
  MODIFY `IdTechnologie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `IdUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD CONSTRAINT `annonce_ibfk_1` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`);

--
-- Contraintes pour la table `annonce_vue`
--
ALTER TABLE `annonce_vue`
  ADD CONSTRAINT `annonce_vue_ibfk_1` FOREIGN KEY (`IdAnnonce`) REFERENCES `annonce` (`IdAnnonce`),
  ADD CONSTRAINT `annonce_vue_ibfk_2` FOREIGN KEY (`IdFormateur`) REFERENCES `formateur` (`IdFormateur`);

--
-- Contraintes pour la table `appartenir`
--
ALTER TABLE `appartenir`
  ADD CONSTRAINT `appartenir_ibfk_1` FOREIGN KEY (`IdFormateur`) REFERENCES `formateur` (`IdFormateur`),
  ADD CONSTRAINT `appartenir_ibfk_2` FOREIGN KEY (`IdGroupeFormateur`) REFERENCES `groupeformateur` (`IdGroupeFormateur`);

--
-- Contraintes pour la table `calendrier`
--
ALTER TABLE `calendrier`
  ADD CONSTRAINT `calendrier_ibfk_1` FOREIGN KEY (`IdFormateur`) REFERENCES `formateur` (`IdFormateur`),
  ADD CONSTRAINT `calendrier_ibfk_2` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`);

--
-- Contraintes pour la table `candidature`
--
ALTER TABLE `candidature`
  ADD CONSTRAINT `candidature_ibfk_1` FOREIGN KEY (`IdFormateur`) REFERENCES `formateur` (`IdFormateur`),
  ADD CONSTRAINT `candidature_ibfk_2` FOREIGN KEY (`IdAnnonce`) REFERENCES `annonce` (`IdAnnonce`);

--
-- Contraintes pour la table `mission`
--
ALTER TABLE `mission`
  ADD CONSTRAINT `mission_ibfk_1` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`),
  ADD CONSTRAINT `mission_ibfk_2` FOREIGN KEY (`IdFormateur`) REFERENCES `formateur` (`IdFormateur`);

--
-- Contraintes pour la table `mobilite`
--
ALTER TABLE `mobilite`
  ADD CONSTRAINT `mobilite_ibfk_1` FOREIGN KEY (`IdFormateur`) REFERENCES `formateur` (`IdFormateur`),
  ADD CONSTRAINT `mobilite_ibfk_2` FOREIGN KEY (`CodeINSEE`) REFERENCES `zone_geographique` (`CodeINSEE`);

--
-- Contraintes pour la table `savoirfaire`
--
ALTER TABLE `savoirfaire`
  ADD CONSTRAINT `savoirfaire_ibfk_1` FOREIGN KEY (`IdCompetence`) REFERENCES `competence` (`IdCompetence`),
  ADD CONSTRAINT `savoirfaire_ibfk_2` FOREIGN KEY (`IdFormateur`) REFERENCES `formateur` (`IdFormateur`),
  ADD CONSTRAINT `savoirfaire_ibfk_3` FOREIGN KEY (`IdTechnologie`) REFERENCES `technologie` (`IdTechnologie`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `utilisateur_ibfk_1` FOREIGN KEY (`IdClient`) REFERENCES `client` (`IdClient`),
  ADD CONSTRAINT `utilisateur_ibfk_2` FOREIGN KEY (`IdFormateur`) REFERENCES `formateur` (`IdFormateur`);
--
-- Base de données :  `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
--
-- Base de données :  `toutoull`
--
CREATE DATABASE IF NOT EXISTS `toutoull` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `toutoull`;

-- --------------------------------------------------------

--
-- Structure de la table `bondecommande`
--

CREATE TABLE `bondecommande` (
  `IDBonDeCommande` int(11) NOT NULL,
  `IDCommande` int(11) NOT NULL,
  `IDProduit` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `IDClient` int(11) NOT NULL,
  `NomClient` varchar(50) NOT NULL,
  `MailClient` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`IDClient`, `NomClient`, `MailClient`) VALUES
(1, 'test', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `IDCommande` int(11) NOT NULL,
  `DateCommande` date NOT NULL,
  `IDTypeEnvoi` int(11) NOT NULL,
  `IDClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`IDCommande`, `DateCommande`, `IDTypeEnvoi`, `IDClient`) VALUES
(1, '2017-07-17', 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `IDFournisseur` int(11) NOT NULL,
  `NomFournisseur` int(11) NOT NULL,
  `MailFournisseur` int(11) NOT NULL,
  `Franco` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`IDFournisseur`, `NomFournisseur`, `MailFournisseur`, `Franco`) VALUES
(1, 0, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `IDProduit` int(11) NOT NULL,
  `Machine` varchar(200) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `PhotoP` varchar(255) NOT NULL,
  `NomProduit` varchar(100) NOT NULL,
  `DescriptionProduit` varchar(255) NOT NULL,
  `RefProduit` varchar(150) NOT NULL,
  `IDFournisseur` int(11) NOT NULL,
  `PrixProduit` int(11) NOT NULL,
  `PdfProduit` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`IDProduit`, `Machine`, `Type`, `PhotoP`, `NomProduit`, `DescriptionProduit`, `RefProduit`, `IDFournisseur`, `PrixProduit`, `PdfProduit`) VALUES
(1, 'Canon xpz', 'imprimante', 'URL', 'IDK', 'blabla', 'HJGH9', 1, 50, 'URL');

-- --------------------------------------------------------

--
-- Structure de la table `typeenvoi`
--

CREATE TABLE `typeenvoi` (
  `IDTypeEnvoi` int(11) NOT NULL,
  `NomTypeEnvoi` varchar(50) NOT NULL,
  `Description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `typeenvoi`
--

INSERT INTO `typeenvoi` (`IDTypeEnvoi`, `NomTypeEnvoi`, `Description`) VALUES
(1, 'Chronopost', 'Chronopost');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bondecommande`
--
ALTER TABLE `bondecommande`
  ADD PRIMARY KEY (`IDBonDeCommande`),
  ADD KEY `IDCommande` (`IDCommande`),
  ADD KEY `IDProduit` (`IDProduit`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`IDClient`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`IDCommande`),
  ADD KEY `IDTypeEnvoi` (`IDTypeEnvoi`),
  ADD KEY `IDClient` (`IDClient`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`IDFournisseur`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`IDProduit`),
  ADD UNIQUE KEY `IDFournisseur` (`IDFournisseur`);

--
-- Index pour la table `typeenvoi`
--
ALTER TABLE `typeenvoi`
  ADD PRIMARY KEY (`IDTypeEnvoi`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bondecommande`
--
ALTER TABLE `bondecommande`
  MODIFY `IDBonDeCommande` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `IDClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `IDCommande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `IDFournisseur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `IDProduit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `typeenvoi`
--
ALTER TABLE `typeenvoi`
  MODIFY `IDTypeEnvoi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `bondecommande`
--
ALTER TABLE `bondecommande`
  ADD CONSTRAINT `bondecommande_ibfk_1` FOREIGN KEY (`IDProduit`) REFERENCES `produit` (`IDProduit`),
  ADD CONSTRAINT `bondecommande_ibfk_2` FOREIGN KEY (`IDCommande`) REFERENCES `commande` (`IDCommande`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`IDTypeEnvoi`) REFERENCES `typeenvoi` (`IDTypeEnvoi`),
  ADD CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`IDClient`) REFERENCES `client` (`IDClient`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`IDFournisseur`) REFERENCES `fournisseur` (`IDFournisseur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
