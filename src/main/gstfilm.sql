-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 31 mars 2024 à 23:44
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gstfilm`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL,
  `emailAdmin` varchar(255) DEFAULT NULL,
  `nomAdmin` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenomAdmin` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `msgDesc` varchar(255) DEFAULT NULL,
  `idFilm` int(11) DEFAULT NULL,
  `idUser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `msgDesc`, `idFilm`, `idUser`) VALUES
(1, 'This is a test comment', 1, 1),
(2, 'Best film', 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `film`
--

CREATE TABLE `film` (
  `idFilm` int(11) NOT NULL,
  `categ` varchar(255) DEFAULT NULL,
  `descrp` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `film`
--

INSERT INTO `film` (`idFilm`, `categ`, `descrp`, `name`, `photo`) VALUES
(1, 'comedy', 'boring movie', 'azoz', 'Path not yet ready'),
(2, 'comedy', 'movie', 'ayman', 'Path not yet ready'),
(3, 'funny', 'movie', 'ayman', 'Path not yet ready'),
(4, 'drama', 'movie', 'ayman', 'Path not yet ready');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `mdp` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `mail`, `mdp`, `name`) VALUES
(1, 'ayman@User.com', '123', 'Ayman'),
(2, 'mohssine@user.com', 'mohssine', 'mohssine'),
(3, 'mohssine@user.com', 'mohssine', 'mohssine');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKl8n29y50iomydo4sud0pt4vc6` (`idFilm`),
  ADD KEY `FK4wpde9jw1x8frfy9157js4k59` (`idUser`);

--
-- Index pour la table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`idFilm`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `film`
--
ALTER TABLE `film`
  MODIFY `idFilm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK4wpde9jw1x8frfy9157js4k59` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`),
  ADD CONSTRAINT `FKl8n29y50iomydo4sud0pt4vc6` FOREIGN KEY (`idFilm`) REFERENCES `film` (`idFilm`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
