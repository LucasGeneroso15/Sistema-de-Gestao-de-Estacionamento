CREATE TABLE `vagas` (
  `id_vaga` int NOT NULL AUTO_INCREMENT,
  `numero_vaga` int NOT NULL,
  `reservada` tinyint(1) DEFAULT '0',
  `status` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id_vaga`)
) ENGINE=InnoDB AUTO_INCREMENT=501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `ticket` (
  `id_ticket` int NOT NULL AUTO_INCREMENT,
  `placa_veiculo` varchar(10) NOT NULL,
  `hora_entrada` datetime NOT NULL,
  `hora_saida` datetime DEFAULT NULL,
  `cancela_entrada` tinyint(1) DEFAULT '0',
  `cancela_saida` tinyint(1) DEFAULT '0',
  `valor_pagar` decimal(10,2) DEFAULT NULL,
  `numero_vaga` int DEFAULT NULL,
  PRIMARY KEY (`id_ticket`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `veiculos_cadastrados` (
  `id_veiculo` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(10) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `categoria` varchar(50) DEFAULT NULL,
  `valor_pagar` decimal(10,2) DEFAULT NULL,
  `tamanho_vaga` int DEFAULT NULL,
  `numero_vaga` int DEFAULT NULL,
  PRIMARY KEY (`id_veiculo`),
  UNIQUE KEY `placa` (`placa`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `entrada_saida` (
  `id` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(10) NOT NULL,
  `categoria` varchar(50) DEFAULT NULL,
  `tipo` varchar(50) DEFAULT NULL,
  `tamanho_vaga` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci