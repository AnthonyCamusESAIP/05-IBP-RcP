CREATE TABLE `campagne` (
  `idCampagne` int(11) NOT NULL,
  `nomCampagne` varchar(255) NOT NULL,
  `idProjet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `projet` (
  `idProjet` int(11) NOT NULL,
  `nomProjet` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `test` (
  `idTest` int(11) NOT NULL,
  `date` date NOT NULL,
  `heure` time NOT NULL,
  `statut` enum('N/A','Passed','Failed','Not Completed') NOT NULL,
  `idCampagne` int(11) NOT NULL,
  `idTesteur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `testeur` (
  `idTesteur` int(11) NOT NULL,
  `nomTesteur` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `campagne`
  ADD PRIMARY KEY (`idCampagne`),
  MODIFY `idCampagne` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
  
ALTER TABLE `projet`
  ADD PRIMARY KEY (`idProjet`),
  MODIFY `idProjet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

ALTER TABLE `test`
  ADD PRIMARY KEY (`idTest`),
  MODIFY `idTest` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `testeur`
  ADD PRIMARY KEY (`idTesteur`),
  MODIFY `idTesteur` int(11) NOT NULL AUTO_INCREMENT;

  
ALTER TABLE `campagne`
  ADD CONSTRAINT `campagne_ibfk_1` FOREIGN KEY (`idProjet`) REFERENCES `projet` (`idProjet`);

ALTER TABLE `test`
  ADD CONSTRAINT `test_ibfk_1` FOREIGN KEY (`idCampagne`) REFERENCES `campagne` (`idCampagne`),
  ADD CONSTRAINT `test_ibfk_3` FOREIGN KEY (`idTesteur`) REFERENCES `testeur` (`idTesteur`);

INSERT INTO `projet` (`idProjet`, `nomProjet`) VALUES
(1, 'Projet test 1'),
(2, 'Projet test 2');

INSERT INTO `campagne` (`idCampagne`, `nomCampagne`, `idProjet`) VALUES
(1, 'Campagne 1', 1),
(2, 'Campagne 2', 2);