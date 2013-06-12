-- phpMyAdmin SQL Dump
-- version 4.0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 12. Jun 2013 um 10:55
-- Server Version: 5.0.51a-24+lenny5
-- PHP-Version: 5.3.3-7+squeeze15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `krm_db`
--
CREATE DATABASE IF NOT EXISTS `krm_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `krm_db`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `exRules`
--

CREATE TABLE IF NOT EXISTS `exRules` (
  `exRulesTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`exRulesTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `exRules`
--

INSERT INTO `exRules` (`exRulesTitle`) VALUES
('PO2010'),
('PO2012');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `field`
--

CREATE TABLE IF NOT EXISTS `field` (
  `fieldTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `version` int(11) NOT NULL,
  `subTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `modTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `description` varchar(255) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`fieldTitle`,`version`,`subTitle`,`modTitle`),
  KEY `subTitle` (`subTitle`),
  KEY `version` (`version`),
  KEY `modTitle` (`modTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `field`
--

INSERT INTO `field` (`fieldTitle`, `version`, `subTitle`, `modTitle`, `description`) VALUES
('Beschreibung', 1, 'Algorithmen und Datenstrukturen', 'Algorithmen und Datenstrukturen', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At'),
('Beschreibung', 1, 'Analysis I für Ingenieure und Informatiker', 'Analysis für Informatiker', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At'),
('Leistungsnachweis', 1, 'Analysis I für Ingenieure und Informatiker', 'Analysis für Informatiker', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `modAccess`
--

CREATE TABLE IF NOT EXISTS `modAccess` (
  `modTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `email` varchar(50) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`modTitle`,`email`),
  KEY `modTitle` (`modTitle`),
  KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `modAccess`
--

INSERT INTO `modAccess` (`modTitle`, `email`) VALUES
('Algorithmen und Datenstrukturen', 'marianne.modulverantwortlicher@uni-ulm.de'),
('Algorithmen und Datenstrukturen', 'markus.modulverantwortlicher@uni-ulm.de'),
('Analysis für Informatiker', 'marianne.modulverantwortlicher@uni-ulm.de'),
('Angewandte Mathematik', 'max.modulverantwortlicher@uni-ulm.de'),
('Informationssysteme', 'michael.modulverantwortlicher@uni-ulm.de'),
('Konzepte der Programmierung', 'markus.modulverantwortlicher@uni-ulm.de'),
('Lineare Algebra für Ingenieure und Informatiker', 'michael.modulverantwortlicher@uni-ulm.de'),
('Mediale Informatik', 'michael.modulverantwortlicher@uni-ulm.de'),
('Softwareprojekt', 'mo.modulverantwortlicher@uni-ulm.de'),
('Technische Grundlagen der Informatik', 'mo.modulverantwortlicher@uni-ulm.de');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `modManAccess`
--

CREATE TABLE IF NOT EXISTS `modManAccess` (
  `email` varchar(50) collate utf8_unicode_ci NOT NULL,
  `modManTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`email`,`modManTitle`),
  KEY `email` (`email`),
  KEY `modManTitle` (`modManTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `modManAccess`
--

INSERT INTO `modManAccess` (`email`, `modManTitle`) VALUES
('rabarbara.redakteur@uni-ulm.de', 'Medieninformatik'),
('richard.redakteur@uni-ulm.de', 'Medieninformatik'),
('robin.redakteur@uni-ulm.de', 'Informatik'),
('rolfgang.redakteur@uni-ulm.de', 'Medieninformatik');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `modManual`
--

CREATE TABLE IF NOT EXISTS `modManual` (
  `modManTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `description` varchar(500) collate utf8_unicode_ci NOT NULL,
  `exRulesTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `deadline` date NOT NULL,
  PRIMARY KEY  (`modManTitle`,`exRulesTitle`),
  KEY `exRuelesTitle` (`exRulesTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `modManual`
--

INSERT INTO `modManual` (`modManTitle`, `description`, `exRulesTitle`, `deadline`) VALUES
('Informatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'PO2010', '2013-06-30'),
('Mathematik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'PO2010', '2013-11-11'),
('Medieninformatik', 'Modulhandbuch des Medieninformatik Studiengangs', 'PO2012', '2013-02-14'),
('Psychologie', 'Modulhandbuch der Psychologie', 'PO2012', '2013-07-17'),
('Softwareengineering', 'Modulhandbuch des Softwareengineering', 'PO2012', '2013-05-24'),
('Test', 'Testmodulhandbuch', 'PO2010', '2013-05-16');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `module`
--

CREATE TABLE IF NOT EXISTS `module` (
  `modTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `description` varchar(500) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`modTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `module`
--

INSERT INTO `module` (`modTitle`, `description`) VALUES
('Additive Schlüsselqualifikationen (ASQ)', 'asq Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Algorithmen und Datenstrukturen', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.'),
('Analysis für Informatiker', 'anafi Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Angewandte Mathematik', 'am Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Anwendungsmodul Medieninformatik', 'anwmmi Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Bachelorarbeit', 'bachelor Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Einführung in die Informatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.'),
('Formale Grundlagen der Medieninformatik', 'f d Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Grundlagen der Gestaltung', 'gdg Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Informationssysteme', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.'),
('Konzepte der Programmierung', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.'),
('Lineare Algebra für Ingenieure und Informatiker', 'lafiui Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Mediale Informatik', 'mi Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Medienpsychologie / -pädagogik', 'mpp Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Proseminar Informatik/Medieninformatik', 'e Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Schwerpunktmodul Medieninformatik', 'smi Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Seminar Medieninformatik', 'smi Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Softwareprojekt', 'Sgp Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.'),
('Technische Grundlagen der Informatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `moduleModMan`
--

CREATE TABLE IF NOT EXISTS `moduleModMan` (
  `exRulesTitle` varchar(30) collate utf8_unicode_ci NOT NULL,
  `modManTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `modTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`exRulesTitle`,`modManTitle`,`modTitle`),
  KEY `modManTitle` (`modManTitle`),
  KEY `modTitle` (`modTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `moduleModMan`
--

INSERT INTO `moduleModMan` (`exRulesTitle`, `modManTitle`, `modTitle`) VALUES
('PO2010', 'Informatik', 'Algorithmen und Datenstrukturen'),
('PO2010', 'Informatik', 'Einführung in die Informatik'),
('PO2010', 'Informatik', 'Formale Grundlagen der Medieninformatik'),
('PO2010', 'Informatik', 'Konzepte der Programmierung'),
('PO2010', 'Informatik', 'Proseminar Informatik/Medieninformatik'),
('PO2010', 'Informatik', 'Softwareprojekt'),
('PO2010', 'Informatik', 'Technische Grundlagen der Informatik'),
('PO2010', 'Mathematik', 'Angewandte Mathematik'),
('PO2012', 'Medieninformatik', 'Additive Schlüsselqualifikationen (ASQ)'),
('PO2012', 'Medieninformatik', 'Algorithmen und Datenstrukturen'),
('PO2012', 'Medieninformatik', 'Analysis für Informatiker'),
('PO2012', 'Medieninformatik', 'Angewandte Mathematik'),
('PO2012', 'Medieninformatik', 'Anwendungsmodul Medieninformatik'),
('PO2012', 'Medieninformatik', 'Bachelorarbeit'),
('PO2012', 'Medieninformatik', 'Einführung in die Informatik'),
('PO2012', 'Medieninformatik', 'Formale Grundlagen der Medieninformatik'),
('PO2012', 'Medieninformatik', 'Grundlagen der Gestaltung'),
('PO2012', 'Medieninformatik', 'Informationssysteme'),
('PO2012', 'Medieninformatik', 'Konzepte der Programmierung'),
('PO2012', 'Medieninformatik', 'Lineare Algebra für Ingenieure und Informatiker'),
('PO2012', 'Medieninformatik', 'Mediale Informatik'),
('PO2012', 'Medieninformatik', 'Medienpsychologie / -pädagogik'),
('PO2012', 'Medieninformatik', 'Proseminar Informatik/Medieninformatik'),
('PO2012', 'Medieninformatik', 'Schwerpunktmodul Medieninformatik'),
('PO2012', 'Medieninformatik', 'Seminar Medieninformatik'),
('PO2012', 'Medieninformatik', 'Softwareprojekt'),
('PO2012', 'Medieninformatik', 'Technische Grundlagen der Informatik'),
('PO2012', 'Psychologie', 'Medienpsychologie / -pädagogik');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `notification`
--

CREATE TABLE IF NOT EXISTS `notification` (
  `recipientEmail` varchar(50) collate utf8_unicode_ci NOT NULL,
  `senderEmail` varchar(50) collate utf8_unicode_ci NOT NULL,
  `timeStamp` timestamp NOT NULL default '0000-00-00 00:00:00',
  `message` varchar(500) collate utf8_unicode_ci NOT NULL,
  `action` varchar(50) collate utf8_unicode_ci NOT NULL,
  `status` varchar(50) collate utf8_unicode_ci NOT NULL,
  `isRead` tinyint(1) default NULL,
  `exRulesTitle` varchar(50) collate utf8_unicode_ci default NULL,
  `modManTitle` varchar(50) collate utf8_unicode_ci default NULL,
  `modTitle` varchar(50) collate utf8_unicode_ci default NULL,
  `subTitle` varchar(50) collate utf8_unicode_ci default NULL,
  `deadline` timestamp NULL default NULL,
  PRIMARY KEY  (`recipientEmail`,`senderEmail`,`timeStamp`),
  KEY `senderEmail` (`senderEmail`),
  KEY `exRulesTitle` (`exRulesTitle`,`modManTitle`,`modTitle`,`subTitle`),
  KEY `modManTitle` (`modManTitle`,`modTitle`,`subTitle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `version` int(11) NOT NULL,
  `subTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `modTitle` varchar(50) collate utf8_unicode_ci NOT NULL,
  `description` varchar(255) collate utf8_unicode_ci NOT NULL,
  `aim` varchar(255) collate utf8_unicode_ci NOT NULL,
  `ects` int(11) NOT NULL,
  `ack` tinyint(1) NOT NULL,
  PRIMARY KEY  (`version`,`subTitle`,`modTitle`),
  KEY `modTitle` (`modTitle`),
  KEY `subTitle` (`subTitle`),
  KEY `version` (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `subject`
--

INSERT INTO `subject` (`version`, `subTitle`, `modTitle`, `description`, `aim`, `ects`, `ack`) VALUES
(1, 'Algorithmen und Datenstrukturen', 'Algorithmen und Datenstrukturen', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Ziel: Verstehen von Algorithmen und Datenstrukturen', 8, 1),
(1, 'Analysis I für Ingenieure und Informatiker', 'Analysis für Informatiker', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'ana I', 8, 1),
(1, 'Analysis IIa für Informatiker', 'Analysis für Informatiker', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'ana2a', 4, 1),
(1, 'Angewandte diskrete Mathematik', 'Angewandte Mathematik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'wahl1', 4, 1),
(1, 'Angewandte Numerik I', 'Angewandte Mathematik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'wahl2', 4, 1),
(1, 'Anwendungsfach', 'Anwendungsmodul Medieninformatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Selbst wählen und verstehen', 6, 1),
(1, 'ASQ I', 'Additive Schlüsselqualifikationen (ASQ)', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Schlüsselqualifikationen', 3, 1),
(1, 'ASQ II ', 'Additive Schlüsselqualifikationen (ASQ)', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Schlüsselqualifikationen', 3, 1),
(1, 'Bachelorarbeit', 'Bachelorarbeit', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Bachelorarbeit schreiben', 12, 1),
(1, 'Digitale Medien', 'Mediale Informatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata', 'Digirale Medien', 4, 1),
(1, 'Formale Grundlagen', 'Formale Grundlagen der Medieninformatik', 'hd Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Einstieg', 8, 1),
(1, 'Grundlagen der Gestaltung II', 'Grundlagen der Gestaltung', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata', 'Visualisierung einer Tonsequenz', 6, 1),
(1, 'Grundlagen der Rechnernetze', 'Technische Grundlagen der Informatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Rechnernetze', 4, 1),
(1, 'Grundlagen Interaktiver Systeme', 'Mediale Informatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata', 'Einstieg', 4, 1),
(1, 'Informationssysteme', 'Informationssysteme', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Verstehen von ...', 6, 1),
(1, 'Kombinatorik', 'Angewandte Mathematik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'wahl3', 4, 1),
(1, 'Lineare Algebra für Ingenieure und Informatiker', 'Lineare Algebra für Ingenieure und Informatiker', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'la', 8, 1),
(1, 'Medienpsychologie / -pädagogik', 'Medienpsychologie / -pädagogik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Psychologische Sichtweise', 4, 1),
(1, 'Paradigmen der Programmierung', 'Konzepte der Programmierung', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Verstehen von ...', 4, 1),
(1, 'Praktische Informatik', 'Einführung in die Informatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata', 'Ziel ist es einen Einblick in die Informatik zu bekommen.', 8, 1),
(1, 'Programmierung von Systemen', 'Konzepte der Programmierung', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Verständnis für das Programmieren von Systemen', 8, 1),
(1, 'Proseminar', 'Proseminar Informatik/Medieninformatik', 'gd Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Erarbeiten', 4, 1),
(1, 'Seminar', 'Seminar Medieninformatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'seminararbeit', 4, 1),
(1, 'Softwaregrundprojekt I', 'Softwareprojekt', 'a Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Anfang von Softwareprojekt', 5, 1),
(1, 'Softwaregrundprojekt II', 'Softwareprojekt', 'b Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Softwareprojekt zuende bringen', 5, 1),
(1, 'Softwaretechik I', 'Softwareprojekt', 'c Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Ziel', 3, 1),
(1, 'Softwaretechik II', 'Softwareprojekt', 'd Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Ziel 2', 3, 1),
(1, 'Technische Informatik I', 'Technische Grundlagen der Informatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Grundlagen kennenlernen', 6, 1),
(1, 'User Interface Softwaretechnologie', 'Mediale Informatik', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata', 'A', 4, 1),
(1, 'Vorlesung + Übung', 'Schwerpunktmodul Medieninformatik', 'Aus einem Angebot auswählen was man machen will. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea ', 'Lernen', 6, 1),
(1, 'Vorlesung + Übungen II', 'Schwerpunktmodul Medieninformatik', 'Auwhählen Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua', 'selbst auswählen', 6, 1),
(2, 'Softwaretechik II', 'Softwareprojekt', 'd Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum.', 'Ziel 2 und 3', 3, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `email` varchar(50) collate utf8_unicode_ci NOT NULL,
  `password` blob NOT NULL,
  `name` varchar(50) collate utf8_unicode_ci NOT NULL,
  `firstname` varchar(50) collate utf8_unicode_ci NOT NULL,
  `role` varchar(50) collate utf8_unicode_ci NOT NULL,
  PRIMARY KEY  (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`email`, `password`, `name`, `firstname`, `role`) VALUES
('adam.admin@uni-ulm.de', 0x313030303a373063363863373836356537383738613a62306630326638316661363764316665, 'Admin', 'Adam', 'Administrator'),
('daniel.dezernat@uni-ulm.de', 0x313030303a333430313532653832396639383636653a31313333313533333165626334356230, 'Dezernat', 'Daniel', 'Dezernat'),
('dennis.dekan@uni-ulm.de', 0x313030303a396236343966383533386666303066333a33636131383038313636393634653763, 'Dekan', 'Dennis', 'Dekan'),
('detlef.dezernat@uni-ulm.de', 0x313030303a643137323537353030643231636166363a38623961316439356539353138633037, 'dezernat', 'detlef', 'Dezernat'),
('marianne.modulverantwortlicher@uni-ulm.de', 0x313030303a663461666162383665613932656632343a32363132663930633331663761396264, 'modulvreantwortlicher', 'marianne', 'Modulverantwortlicher'),
('markus.modulverantwortlicher@uni-ulm.de', 0x313030303a333432313363306434353437356331663a62303635623538633036393463623433, 'Modulverantwortlicher', 'Markus', 'Modulverantwortlicher'),
('max.modulverantwortlicher@uni-ulm.de', 0x313030303a323437316132633835396130333438393a31626331653335386365653031366137, 'modulvreantwortlicher', 'max', 'Modulverantwortlicher'),
('michael.modulverantwortlicher@uni-ulm.de', 0x313030303a323065326363336365623863626538343a65323230613663613131346666383032, 'modulvreantwortlicher', 'michael', 'Modulverantwortlicher'),
('mo.modulverantwortlicher@uni-ulm.de', 0x313030303a383737636165383864613237343338663a36633331373363623934313235346230, 'modulvreantwortlicher', 'mo', 'Modulverantwortlicher'),
('rabarbara.redakteur@uni-ulm.de', 0x313030303a353562353036316562343337333031373a63633762613665653762626632303738, 'redakteur', 'rabarbara', 'Redakteur'),
('richard.redakteur@uni-ulm.de', 0x313030303a633761363362646233656164356565383a35303037623232616535356163303036, 'redakteur', 'richard', 'Redakteur'),
('robin.redakteur@uni-ulm.de', 0x313030303a376633626330343736653261343539663a66313733373133303030336264393330, 'redakteur', 'robin', 'Redakteur'),
('rolfgang.redakteur@uni-ulm.de', 0x313030303a323564646166343435663239363737633a36343638396336313932633935306630, 'redakteur', 'rolfgang', 'Redakteur');

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `modAccess`
--
ALTER TABLE `modAccess`
  ADD CONSTRAINT `modAccess_ibfk_1` FOREIGN KEY (`modTitle`) REFERENCES `module` (`modTitle`),
  ADD CONSTRAINT `modAccess_ibfk_2` FOREIGN KEY (`email`) REFERENCES `user` (`email`);

--
-- Constraints der Tabelle `modManAccess`
--
ALTER TABLE `modManAccess`
  ADD CONSTRAINT `modManAccess_ibfk_1` FOREIGN KEY (`email`) REFERENCES `user` (`email`),
  ADD CONSTRAINT `modManAccess_ibfk_2` FOREIGN KEY (`modManTitle`) REFERENCES `modManual` (`modManTitle`);

--
-- Constraints der Tabelle `modManual`
--
ALTER TABLE `modManual`
  ADD CONSTRAINT `modManual_ibfk_1` FOREIGN KEY (`exRulesTitle`) REFERENCES `exRules` (`exRulesTitle`);

--
-- Constraints der Tabelle `moduleModMan`
--
ALTER TABLE `moduleModMan`
  ADD CONSTRAINT `moduleModMan_ibfk_1` FOREIGN KEY (`modManTitle`) REFERENCES `modManual` (`modManTitle`),
  ADD CONSTRAINT `moduleModMan_ibfk_2` FOREIGN KEY (`modTitle`) REFERENCES `module` (`modTitle`);

--
-- Constraints der Tabelle `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`recipientEmail`) REFERENCES `user` (`email`),
  ADD CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`senderEmail`) REFERENCES `user` (`email`);

--
-- Constraints der Tabelle `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`modTitle`) REFERENCES `module` (`modTitle`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
