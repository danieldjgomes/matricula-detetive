CREATE TABLE `disciplina` (
  `identificadorUFABC` int(11) NOT NULL,
  `nomeDisciplina` varchar(100) NOT NULL,
  `periodo` varchar(50) NOT NULL,
  `vagasDisponibilizadas` int(11) NOT NULL,
  `vagasDisponiveis` int(11) NOT NULL,
  `vagasIngressantes` int(11) NOT NULL,
  `creditos` int(11) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `campus` varchar(50) NOT NULL,
  PRIMARY KEY (`identificadorUFABC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
