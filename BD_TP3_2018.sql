/*
SQLyog Ultimate v9.02 
MySQL - 5.7.15-log : Database - indra_corp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`indra_corp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `indra_corp`;

/*Table structure for table `ca_capacitacion` */

DROP TABLE IF EXISTS `ca_capacitacion`;

CREATE TABLE `ca_capacitacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PLAN_CAPACITACION` int(11) NOT NULL,
  `FECHA_INICIO` date DEFAULT NULL,
  `FECHA_FIN` date DEFAULT NULL,
  `ESTADO` int(11) NOT NULL,
  `ID_DET_SOL` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CAPA_ESTADO` (`ESTADO`),
  KEY `FK_CAPA_ID_PLA_CAPA` (`ID_PLAN_CAPACITACION`),
  KEY `FK_CAPA_ID_DET_SOL` (`ID_DET_SOL`),
  CONSTRAINT `FK_CAPA_ESTADO` FOREIGN KEY (`ESTADO`) REFERENCES `ca_parametros` (`ID`),
  CONSTRAINT `FK_CAPA_ID_DET_SOL` FOREIGN KEY (`ID_DET_SOL`) REFERENCES `ca_detalle_solicitud` (`ID`),
  CONSTRAINT `FK_CAPA_ID_PLA_CAPA` FOREIGN KEY (`ID_PLAN_CAPACITACION`) REFERENCES `ca_plan_capacitacion` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Data for the table `ca_capacitacion` */

/*Table structure for table `ca_det_sol_participante` */

DROP TABLE IF EXISTS `ca_det_sol_participante`;

CREATE TABLE `ca_det_sol_participante` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_DET_SOLICITUD` int(11) NOT NULL,
  `ID_EMPLEADO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SOL_PART_ID_DET_SOL` (`ID_DET_SOLICITUD`),
  KEY `FK_SOL_PART_ID_PART` (`ID_EMPLEADO`),
  CONSTRAINT `FK_SOL_PART_ID_DET_SOL` FOREIGN KEY (`ID_DET_SOLICITUD`) REFERENCES `ca_detalle_solicitud` (`ID`),
  CONSTRAINT `ca_det_sol_participante_ibfk_1` FOREIGN KEY (`ID_EMPLEADO`) REFERENCES `ca_empleado` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=269 DEFAULT CHARSET=utf8;

/*Data for the table `ca_det_sol_participante` */

/*Table structure for table `ca_detalle_solicitud` */

DROP TABLE IF EXISTS `ca_detalle_solicitud`;

CREATE TABLE `ca_detalle_solicitud` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_FORMACION` int(11) NOT NULL,
  `ID_SOL_CAP` int(11) NOT NULL,
  `NUMERO_PARTICIPANTES` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DET_SOL_ID_FORMACION` (`ID_FORMACION`),
  KEY `FK_DET_SOL_ID_SOL_CAP` (`ID_SOL_CAP`),
  CONSTRAINT `FK_DET_SOL_ID_FORMACION` FOREIGN KEY (`ID_FORMACION`) REFERENCES `ca_formacion` (`ID`),
  CONSTRAINT `FK_DET_SOL_ID_SOL_CAP` FOREIGN KEY (`ID_SOL_CAP`) REFERENCES `ca_solicitud_capacitacion` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8;

/*Data for the table `ca_detalle_solicitud` */

/*Table structure for table `ca_empleado` */

DROP TABLE IF EXISTS `ca_empleado`;

CREATE TABLE `ca_empleado` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRES` varchar(50) NOT NULL,
  `APELLIDOS` varchar(50) NOT NULL,
  `FECHA_NACIMIENTO` date NOT NULL,
  `FECHA_ALTA` date NOT NULL,
  `FECHA_BAJA` date NOT NULL,
  `ESTADO` char(1) NOT NULL,
  `ID_ROL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_USUARIO_ID_PERFIL` (`ID_ROL`),
  CONSTRAINT `FK_USUARIO_ID_PERFIL` FOREIGN KEY (`ID_ROL`) REFERENCES `ss_rol` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

/*Data for the table `ca_empleado` */

insert  into `ca_empleado`(`ID`,`NOMBRES`,`APELLIDOS`,`FECHA_NACIMIENTO`,`FECHA_ALTA`,`FECHA_BAJA`,`ESTADO`,`ID_ROL`) values (1,'Renso Jesus','Valencia Ventura','2015-07-21','2018-04-23','2018-04-30','1',1),(3,'Ivonne','Porras','2018-05-02','2018-05-02','2018-05-02','1',14),(4,'Gerardo','Arce','2018-05-02','2018-05-02','2018-05-02','1',16),(5,'Eduardo','Ramirez','2018-05-02','2018-05-02','2018-05-02','1',10),(6,'Cesar','Cansaya','2018-05-02','2018-05-02','2018-05-02','1',1),(7,'Eduardo','Vargas','2018-05-02','2018-05-02','2018-05-02','1',7),(8,'Christian','Tello','2018-05-02','2018-05-02','2018-05-02','1',13),(9,'gabriel','quiroz','2018-05-04','2018-05-04','2018-05-04','1',2),(10,'Maruluz','Noriega','2018-05-04','2018-05-04','2018-05-04','1',1),(11,'Jorge','Ccolque Genebroso','1988-07-09','2018-05-10','2018-05-23','1',3),(12,'Ray','Lee diaz','1992-03-06','2018-05-24','2018-05-16','1',5),(13,'OMAR','AYALA DIAZ','1990-02-07','2018-05-21','2018-05-16','1',5),(14,'Juan Jose','Deyra Fiestas','1980-05-25','2018-05-07','2018-05-16','1',1),(15,'Benjy','Malca Bautista','1990-05-24','2018-05-24','2018-05-18','1',1),(16,'Karen','Bocanegra','2018-05-09','2018-05-09','2018-05-09','1',2),(17,'Fernando','Cancho','2018-05-08','2018-05-08','2018-05-08','1',1),(18,'contabilidad','contabilidad','2018-05-08','2018-05-08','2018-05-08','1',5),(19,'finanzas','finanzas','1990-05-15','2018-05-01','2018-05-06','1',5),(20,'marketing','finanzas','2018-05-22','2018-05-16','2018-05-23','1',5),(21,'Christian','Vargas','1990-05-08','2018-05-16','2018-05-07','1',1),(22,'UB','RIVERA MALCA','1991-05-23','2018-05-08','2018-05-24','1',2),(23,'GERMAN','CALDAS RIX','1982-05-09','2018-05-17','2018-05-16','1',2),(24,'LICET','POMA CRUZ','1993-05-02','2018-05-15','2018-05-14','1',2),(25,'EDGAR','VIVAR ALCA','2000-05-08','2018-05-23','2018-05-15','1',2),(26,'LIZ','ABAT CAMOS','1889-05-08','2018-05-16','2018-05-23','1',8),(27,'OMAR','GALLO LOPEZ','1994-05-07','2018-05-01','2018-05-22','1',8),(28,'LUIS','CAYCAY','1985-05-24','2018-05-16','2018-05-23','1',15),(29,'PEDRO','DIMAS ROJO','2000-05-02','2018-05-23','2018-05-17','1',8),(30,'ROY','CUCHO LIMA','1990-05-16','2018-05-16','2018-05-10','1',8),(31,'JESUS','LOPEZ DIMAS','2000-05-23','2018-05-03','2018-05-16','1',5),(32,'RIK','LIMAY JULS','1990-05-22','2018-05-22','2018-05-14','1',5),(33,'user 1','user 1','2018-05-08','2018-05-08','2018-05-08','1',5),(34,'user 2','user 2','2018-05-08','2018-05-08','2018-05-08','1',5),(35,'Rina','Quevedo','2018-05-08','2018-05-08','2018-05-08','1',1),(36,'user 1','user 2','2018-05-08','2018-05-08','2018-05-08','1',5),(37,'Fernando','Pacheco','2018-05-08','2018-05-08','2018-05-08','1',1),(38,'Anderson','Vazquez','2018-11-06','2018-11-06','2018-11-06','1',9),(39,'Gabriel','Quiroz','2018-11-08','2018-11-08','2018-11-08','1',11),(40,'Yanina','Guerrero','2018-11-08','2018-11-08','2018-11-08','1',12),(41,'Leon','Roslin','2018-11-10','2018-11-10','2018-11-10','1',11),(42,'Ursula','Aguirre','2018-11-16','2018-11-16','2018-11-16','1',12),(43,'Yesica','Reyes','2018-11-16','2018-11-16','2018-11-16','1',12),(44,'Pierina','Perez','2018-11-16','2018-11-16','2018-11-16','1',1),(45,'Claudio','Bravo','2018-11-16','2018-11-16','2018-11-16','1',6),(46,'Adrian','Cabenillas','2018-11-16','2018-11-16','2018-11-16','1',6),(47,'Charles','Aranguis','2018-11-16','2018-11-16','2018-11-16','1',7),(48,'Jean','Bozeyur','2018-11-16','2018-11-16','2018-11-16','1',1),(49,'Mauricio','Isla','2018-11-16','2018-11-16','2018-11-16','1',6),(50,'Arturo','Vidal','2018-11-16','2018-11-16','2018-11-16','1',7),(51,'Alexis','Sanchez','2018-11-16','2018-11-16','2018-11-16','1',6),(52,'Mauricio','Pinilla','2018-11-16','2018-11-16','2018-11-16','1',7),(53,'Pedro','Aquino','2018-11-16','2018-11-16','2018-11-16','1',2),(54,'Claudio','Pizarro','2018-11-16','2018-11-16','2018-11-16','1',2),(55,'Yosimar','Yogun','2018-11-16','2018-11-16','2018-11-16','1',2),(56,'Mirella','Alarcon','2018-11-16','2018-11-16','2018-11-16','1',8),(57,'Walter','Vela','2018-11-16','2018-11-16','2018-11-16','1',8),(58,'Italo','Gomez','2018-11-16','2018-11-16','2018-11-16','1',8),(59,'Pablo','Rosas','2018-11-16','2018-11-16','2018-11-16','1',8),(60,'Joe','Carrasco','2018-11-16','2018-11-16','2018-11-16','1',8),(61,'Luis','Carrasco','2018-11-16','2018-11-16','2018-11-16','1',2),(62,'Rafael','Barona','2018-11-16','2018-11-16','2018-11-16','1',17),(63,'Jesica','Vazques','2018-11-16','2018-11-16','2018-11-16','1',18),(64,'Rosa','Chicoma','2018-11-16','2018-11-16','2018-11-16','1',19),(65,'Virginia','Ore','2018-11-16','2018-11-16','2018-11-16','1',19),(66,'Brenda','Soler','2018-11-16','2018-11-16','2018-11-16','1',19),(67,'Milagros','Vega','2018-11-16','2018-11-16','2018-11-16','1',19),(68,'Tulio','Galvez','2018-11-16','2018-11-16','2018-11-16','1',19),(69,'Alonso','Moreno','2018-11-16','2018-11-16','2018-11-16','1',19),(70,'Dana','While','2018-11-16','2018-11-16','2018-11-16','1',19);

/*Table structure for table `ca_formacion` */

DROP TABLE IF EXISTS `ca_formacion`;

CREATE TABLE `ca_formacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  `ID_TIPO_FORMACION` int(11) DEFAULT NULL,
  `MAXIMO_PARTICIPANTES` int(11) NOT NULL,
  `TIPO_SALA` int(11) NOT NULL,
  `ID_AREA` int(11) DEFAULT NULL,
  `ID_TIPO_MODALIDAD` int(11) DEFAULT NULL,
  `NUMERO_HORAS` int(11) DEFAULT NULL,
  `PROPOSITO` varchar(100) NOT NULL,
  `TEMARIO` varchar(100) NOT NULL,
  `FECHA_CREACION` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_TIP_FORM` (`ID_TIPO_FORMACION`),
  KEY `FK_TIP_SALA` (`TIPO_SALA`),
  KEY `ID_AREA` (`ID_AREA`),
  KEY `ID_TIPO_CAPACITACION` (`ID_TIPO_MODALIDAD`),
  CONSTRAINT `FK_TIP_FORM` FOREIGN KEY (`ID_TIPO_FORMACION`) REFERENCES `ca_parametros` (`ID`),
  CONSTRAINT `FK_TIP_SALA` FOREIGN KEY (`TIPO_SALA`) REFERENCES `ca_parametros` (`ID`),
  CONSTRAINT `ca_formacion_ibfk_1` FOREIGN KEY (`ID_AREA`) REFERENCES `ss_area` (`ID`),
  CONSTRAINT `ca_formacion_ibfk_2` FOREIGN KEY (`ID_TIPO_MODALIDAD`) REFERENCES `ca_parametros` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `ca_formacion` */

insert  into `ca_formacion`(`ID`,`NOMBRE`,`ID_TIPO_FORMACION`,`MAXIMO_PARTICIPANTES`,`TIPO_SALA`,`ID_AREA`,`ID_TIPO_MODALIDAD`,`NUMERO_HORAS`,`PROPOSITO`,`TEMARIO`,`FECHA_CREACION`) values (3,'Introduccion a la Contabilidad',20,24,21,1,11,15,'Conocimientos basicos de la contabilidad.','Tema 1, Tema 2, Tema 3','2018-11-16 20:00:07'),(8,'Curso Espacializacion 1',19,14,22,4,13,NULL,'','','2018-11-12 19:25:29'),(9,'Curso Normal',19,50,22,4,13,1,'','','2018-11-12 19:25:29'),(14,'Clima laboral',20,50,21,8,11,20,'','','2018-11-12 19:25:29'),(18,'gestion proyecto',19,15,21,6,12,20,'','','2018-11-12 19:25:29'),(20,'Diseño gerencial',19,4,21,7,12,90,'','','2018-11-12 19:25:29'),(21,'Excel avanzado',19,30,22,7,13,60,'','','2018-11-12 19:25:29'),(23,'Programacion',19,8,21,2,12,100,'','','2018-11-12 19:25:29'),(24,'Introducción a Java',20,9,22,2,12,500,'','','2018-11-12 19:25:29'),(25,'Excel Macros',19,10,21,2,13,70,'','','2018-11-12 19:25:29'),(26,'taller 3',20,9,22,2,11,800,'','','2018-11-12 19:25:29'),(27,'taller 5',20,9,21,2,11,80,'','','2018-11-12 19:25:29'),(28,'taller 6',19,5,22,2,13,100,'mejorar','temario 1','2018-11-12 19:25:29'),(29,'taller 7',20,50,22,11,11,100,'aprobar','tema1, tema2, tema3','2018-11-12 19:25:29'),(30,'taller 8',19,50,22,11,11,140,'aprobar nomas','tema 1, tema 2, tema 3','2018-11-12 19:25:29'),(31,'taller 10',19,140,22,7,12,255,'aprobar nada mas','temario 1, temario 2, temario 3, temario 4','2018-11-12 19:25:29'),(32,'taller 12',19,17,21,7,13,180,'aprobar nada más','bla bla bla bla','2018-11-12 19:25:29'),(33,'taller 20',20,2,21,11,12,100,'proposito 20','tema 1, tema 2','2018-11-12 19:25:29'),(34,'taller 50',19,30,21,1,12,500,'taller 50','temaria 1, temario 2','2018-11-12 19:25:29'),(35,'taller 25',19,50,21,1,11,10,'proposito','proposito','2018-11-12 19:25:29'),(36,'taller 100',20,100,22,2,12,100,'es aprobar el curso','tameria 1, temario 2','2018-11-12 20:30:13'),(37,'taller 200',20,5,21,2,11,10,'sadsad','sdasdsada','2018-11-12 20:34:16'),(38,'Inductivo marketing',20,50,21,1,11,10,'Conocer el objetivo estrategico del area de marketing','tema 1, tema 2, tema 3','2018-11-16 19:42:08'),(39,'Bizagi',19,5,21,11,13,20,'Aprender a utilizar el sw bizagi','tema 1, tema 2','2018-11-16 19:45:07'),(40,'Mercadeo digital',19,30,21,4,13,10,'Aprender conocimientos sobre mercadeo digital.','Tema1, Tema 2, Tema 3','2018-11-16 19:54:12'),(41,'Marketing en redes sociales',19,35,21,4,12,15,'Aprender hacer publicidad en redes sociales.','Tema 1, Tema 2, Tema 3','2018-11-16 19:55:43'),(42,'Introduccion al Marketing',20,30,21,4,11,20,'Impartir conocimientos basicos de Marketing.','Tema 1, Tema 2, Tema 3','2018-11-16 19:57:18'),(43,'Macros para registro contable.',19,30,21,1,12,12,'Automatizar procesos para los registros.','Tema 1, Tema 2, Tema3, Tema 4','2018-11-16 20:01:37'),(44,'Retenciones especiales.',19,40,21,1,13,24,'Saber comopresentar las retenciones.','Tema 1, Tema 2, Tema 3','2018-11-16 20:02:40'),(45,'Ádministracion de Procesos',19,24,21,13,13,30,'Certificarse en PM','Tema 1, Tema 2, Tema 3','2018-11-16 20:04:11'),(46,'Introduccion a la Administracon',20,40,21,13,11,10,'Conoces las nociones basicas de la administracion.','Tema 1, Tema 2, Tema 3','2018-11-16 20:05:16'),(47,'Buenas practicas en la Gestion',19,35,21,13,12,30,'Conocer las buenas practicas en los procesos de administracion.','Tema 1, Tema 2, Tema 3','2018-11-16 20:06:25'),(48,'Progromación Java',19,15,22,3,12,35,'Programas rápido, Programas veloces','Temario 1, Temario 2, Temario 3, Temario 4','2018-11-16 21:49:27'),(49,'Contabilidad avanzadad',19,50,21,1,13,200,'proposito 1, proposito 2','temario 1, temario 2, temario 3','2018-11-17 09:51:15');

/*Table structure for table `ca_indicador_area` */

DROP TABLE IF EXISTS `ca_indicador_area`;

CREATE TABLE `ca_indicador_area` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERIODO` int(11) NOT NULL,
  `ID_AREA` int(11) NOT NULL,
  `SOLICITUDES` int(11) NOT NULL,
  `ATENDIDOS` int(11) NOT NULL,
  `PRIORIDAD` char(1) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_IND_AREA_ID_AREA` (`ID_AREA`),
  CONSTRAINT `FK_IND_AREA_ID_AREA` FOREIGN KEY (`ID_AREA`) REFERENCES `ss_area` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `ca_indicador_area` */

insert  into `ca_indicador_area`(`ID`,`PERIODO`,`ID_AREA`,`SOLICITUDES`,`ATENDIDOS`,`PRIORIDAD`) values (1,2018,2,5,4,'1'),(2,2018,4,8,8,'0'),(3,2017,4,5,4,'1'),(4,2017,7,5,5,'2');

/*Table structure for table `ca_marca_material` */

DROP TABLE IF EXISTS `ca_marca_material`;

CREATE TABLE `ca_marca_material` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `ca_marca_material` */

insert  into `ca_marca_material`(`ID`,`NOMBRE`) values (1,'COREFO'),(2,'FABER CASTEL'),(3,'ESTANFORD'),(6,'ARTESCO'),(7,'PELICAN'),(8,'PATITO'),(9,'COPIMAZ'),(10,'KEROCOPY'),(11,'ARSTAR'),(12,'APLI'),(13,'PAPER MATE'),(14,'STANFORD'),(15,'BELINS'),(16,'APEX'),(17,'ALPINO'),(18,'REXEL'),(19,'SONY'),(20,'EPSON'),(21,'OPTOMA'),(22,'VUEWSONIC'),(23,'ACER'),(24,'3M'),(25,'BELINS'),(26,'APEX'),(27,'ALPINO'),(28,'REXEL');

/*Table structure for table `ca_materiales_escritorio` */

DROP TABLE IF EXISTS `ca_materiales_escritorio`;

CREATE TABLE `ca_materiales_escritorio` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO` varchar(20) NOT NULL,
  `DESCRIPCION` varchar(100) NOT NULL,
  `ID_TIPO_MAT` int(11) NOT NULL,
  `ID_MARCA` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_MAT_ESC` (`ID_TIPO_MAT`),
  KEY `FK_ID_MAR_MAT` (`ID_MARCA`),
  CONSTRAINT `FK_ID_MAR_MAT` FOREIGN KEY (`ID_MARCA`) REFERENCES `ca_marca_material` (`ID`),
  CONSTRAINT `FK_MAT_ESC` FOREIGN KEY (`ID_TIPO_MAT`) REFERENCES `ca_tipo_material` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `ca_materiales_escritorio` */

insert  into `ca_materiales_escritorio`(`ID`,`CODIGO`,`DESCRIPCION`,`ID_TIPO_MAT`,`ID_MARCA`) values (6,'HB01','HOJA BOND 200',1,1),(7,'HB02','HOJA BOND 300',1,1),(8,'HB04','HOJA BOND 400',1,1),(9,'HB05','HOJA BOND 500',1,1),(10,'0001','CUADERNO GRANDE',1,1),(11,'F001','Util de escritorio',1,1),(12,'HB06','HOJA BOND A4',1,1),(13,'HB10','HOJA BOND A3',1,1),(14,'HB09','HOJA BOND A0',1,1),(15,'P01','PLUMON',5,6),(16,'B01','BORRADOR',6,1),(17,'R001','REGLA',8,2),(18,'LQ01','CORRECTOR',7,13),(19,'PI01','PIZARRA',2,6),(20,'QW1','BORRADOR GRANDE',6,2),(21,'B02','BORRADOR MEDIANO',6,2);

/*Table structure for table `ca_parametros` */

DROP TABLE IF EXISTS `ca_parametros`;

CREATE TABLE `ca_parametros` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `GRUPO` int(11) NOT NULL,
  `CODIGO` int(11) NOT NULL,
  `DESCRIPCION` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Data for the table `ca_parametros` */

insert  into `ca_parametros`(`ID`,`GRUPO`,`CODIGO`,`DESCRIPCION`) values (1,1,0,'ESTADOS DE LA SOLICITUD DE CAPACITACION'),(2,2,0,'Tipos de formacion'),(3,3,0,'Tipos de sala'),(4,4,0,'Tipos de modalidad'),(5,5,0,'Nivel detalle de solicitud'),(9,7,0,'Estados de plan de planificacion'),(10,7,1,'Pendiente'),(11,4,1,'Inductivo'),(12,4,2,'Instructivo'),(13,4,3,'Especializacion'),(14,5,1,'Basico'),(15,5,2,'Intermedio'),(16,5,3,'Avanzado'),(17,1,1,'PENDIENTE'),(18,1,2,'ENVIADA'),(19,2,1,'CURSO'),(20,2,2,'TALLER'),(21,3,1,'AUDITORIO'),(22,3,2,'AULA'),(23,8,1,'Pendiente'),(24,8,2,'Programado'),(25,8,3,'En proceso'),(26,1,3,'APROBADO'),(27,8,0,'Estados de capacitación'),(28,8,4,'Finalizado'),(29,9,0,'Tipo de recurso'),(30,9,1,'Material escritorio'),(31,9,2,'Recursos informaticos'),(32,1,4,'OBSERVADO'),(33,7,2,'En proceso'),(34,9,3,'Perfil de capacitador'),(35,1,5,'EN PROCESO'),(36,7,3,'Aprobado'),(37,7,4,'Observado'),(38,10,0,'Listado de formación academica'),(39,10,1,'Universitario'),(40,10,2,'Tecnico'),(41,10,3,'Maestria'),(42,11,0,'Listado de grados academicos'),(43,11,1,'Post Grado'),(44,11,2,'Bachiller'),(45,11,3,'Ingeniero');

/*Table structure for table `ca_perfil_capacitador` */

DROP TABLE IF EXISTS `ca_perfil_capacitador`;

CREATE TABLE `ca_perfil_capacitador` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  `DESCRIPCION` varchar(50) NOT NULL,
  `EXPERIENCIA_GENERAL` varchar(300) DEFAULT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `EXPERIENCIA_ESPECIFICA` varchar(300) DEFAULT NULL,
  `COMPETENCIA` varchar(300) DEFAULT NULL,
  `ID_GRADO_ACADEMICO` int(11) DEFAULT NULL,
  `ID_FORMACION_ACADEMICA` int(11) DEFAULT NULL,
  `CURSOS` varchar(300) DEFAULT NULL,
  `CONOCIMIENTO` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `ca_perfil_capacitador` */

insert  into `ca_perfil_capacitador`(`ID`,`NOMBRE`,`DESCRIPCION`,`EXPERIENCIA_GENERAL`,`fecha_creacion`,`EXPERIENCIA_ESPECIFICA`,`COMPETENCIA`,`ID_GRADO_ACADEMICO`,`ID_FORMACION_ACADEMICA`,`CURSOS`,`CONOCIMIENTO`) values (1,'PROGRAMADOR','PROGRMACION AVANZADA',NULL,'2018-11-13 08:42:44',NULL,NULL,NULL,NULL,NULL,NULL),(2,'JAVA','JAVA AVANZADO',NULL,'2018-11-13 08:42:44',NULL,NULL,NULL,NULL,NULL,NULL),(3,'MYSQL','Que sepa mysql','Personal capacitada','2018-11-13 08:42:44',NULL,NULL,NULL,NULL,NULL,NULL),(4,'Oscar Bazan','Especialista en Java','Dominio de POO','2018-11-16 20:20:13','Primefaces, HTML, CSS, js.','Analitico',43,40,'Java, orable, sql server.','Conocimiento en metodologia Scrum'),(5,'Juan Perez','Experto en Excel','Experiencia general 1\r\nExperiencia general 2','2018-11-16 20:15:51','Experiencia especifico 1\r\nExperiencia especifico 2','Habilidades Blandas',43,39,'Curso 1, Curso 2','Dominio de excel nivel avanzado.'),(6,'Karina Sanchez','Especialista en tributacion.','Experiencia general 1\r\nExperiencia general 2','2018-11-16 20:18:03','Experiencia especifico 1\r\nExperiencia especifico 2','Leyes tributarias',44,39,'Curso 1, Curso 2, Curso 3','Experto en leyes tributarias.'),(7,'Carlos Valdez','Bachicher en telematica','2 años de trabajo','2018-11-16 21:53:41','Redes\r\nComunicaciones\r\nTelematica','Habilidades blandas',43,39,'Curso 1, Curso 2, Curso 3, Curso 4, Curso 5, Curso 6','Desarrollo de telematica');

/*Table structure for table `ca_plan_capacitacion` */

DROP TABLE IF EXISTS `ca_plan_capacitacion`;

CREATE TABLE `ca_plan_capacitacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERIODO` int(11) NOT NULL,
  `FECHA_ELABORACION` date NOT NULL,
  `FECHA_EJECUCION` date DEFAULT NULL,
  `ESTADO` int(11) NOT NULL,
  `FECHA_APROBACION` date DEFAULT NULL,
  `OBSERVACION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PLA_CAPA_ESTADO` (`ESTADO`),
  CONSTRAINT `FK_PLA_CAPA_ESTADO` FOREIGN KEY (`ESTADO`) REFERENCES `ca_parametros` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `ca_plan_capacitacion` */

/*Table structure for table `ca_recurso_capacitacion` */

DROP TABLE IF EXISTS `ca_recurso_capacitacion`;

CREATE TABLE `ca_recurso_capacitacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PLANIFICACION` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `ID_TIPO_RECURSO` int(11) DEFAULT NULL,
  `valor` int(11) DEFAULT NULL,
  `ID_OBJETO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REC_CAPA_TIP_REC` (`ID_TIPO_RECURSO`),
  KEY `FK_REC_CAPA_ID_PLAN_CAPA` (`ID_PLANIFICACION`),
  CONSTRAINT `FK_REC_CAPA_ID_PLAN_CAPA` FOREIGN KEY (`ID_PLANIFICACION`) REFERENCES `ca_plan_capacitacion` (`ID`),
  CONSTRAINT `FK_REC_CAPA_TIP_REC` FOREIGN KEY (`ID_TIPO_RECURSO`) REFERENCES `ca_parametros` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8;

/*Data for the table `ca_recurso_capacitacion` */

/*Table structure for table `ca_solicitud_capacitacion` */

DROP TABLE IF EXISTS `ca_solicitud_capacitacion`;

CREATE TABLE `ca_solicitud_capacitacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FECHA_DOCUMENTO` date DEFAULT NULL,
  `ID_ESTADO` int(11) DEFAULT NULL,
  `FECHA_ATENCION` date DEFAULT NULL,
  `OBSERVACION` varchar(100) DEFAULT NULL,
  `ID_AREA` int(11) NOT NULL,
  `PERIODO` int(11) DEFAULT NULL,
  `ID_TIPO_MODALIDAD` int(11) DEFAULT NULL,
  `CANT_DET_SOL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SOL_CAP_ESTADO` (`ID_ESTADO`),
  KEY `FK_SOL_ID_AREA` (`ID_AREA`),
  KEY `FK_ID_TIPO_MODALIDAD` (`ID_TIPO_MODALIDAD`),
  CONSTRAINT `FK_ID_TIPO_MODALIDAD` FOREIGN KEY (`ID_TIPO_MODALIDAD`) REFERENCES `ca_parametros` (`ID`),
  CONSTRAINT `FK_SOL_CAP_ESTADO` FOREIGN KEY (`ID_ESTADO`) REFERENCES `ca_parametros` (`ID`),
  CONSTRAINT `FK_SOL_ID_AREA` FOREIGN KEY (`ID_AREA`) REFERENCES `ss_area` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=177 DEFAULT CHARSET=utf8;

/*Data for the table `ca_solicitud_capacitacion` */

/*Table structure for table `ca_tipo_material` */

DROP TABLE IF EXISTS `ca_tipo_material`;

CREATE TABLE `ca_tipo_material` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `ca_tipo_material` */

insert  into `ca_tipo_material`(`ID`,`NOMBRE`) values (1,'HOJA BOND'),(2,'PIZARRA'),(3,'PAPELOGRAFOS'),(4,'BULKY'),(5,'PLUMONES'),(6,'BORRADOR'),(7,'LIQUIDO CORRECTOR'),(8,'REGLA'),(9,'BLOCK');

/*Table structure for table `gri_marca` */

DROP TABLE IF EXISTS `gri_marca`;

CREATE TABLE `gri_marca` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `gri_marca` */

insert  into `gri_marca`(`ID`,`NOMBRE`) values (1,'HP'),(2,'Marca 2'),(3,'Marca 3'),(4,'Marca 4'),(5,'Marca 5'),(6,'SONY'),(7,'EPSON'),(8,'OPTOMA'),(9,'VUEWSONIC'),(10,'ACER'),(11,'3M'),(12,'BELINS'),(13,'APEX'),(14,'ALPINO'),(15,'REXEL');

/*Table structure for table `gri_recurso` */

DROP TABLE IF EXISTS `gri_recurso`;

CREATE TABLE `gri_recurso` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(100) NOT NULL,
  `CANTIDAD` int(11) DEFAULT NULL,
  `SO` varchar(100) NOT NULL,
  `PROCESADOR` varchar(100) NOT NULL,
  `MEMORIA_RAM` varchar(100) NOT NULL,
  `DISCO_DURO` varchar(100) NOT NULL,
  `PULGADAS` varchar(100) NOT NULL,
  `CAPACIDAD` varchar(100) NOT NULL,
  `SERIE` varchar(100) NOT NULL,
  `TIPO_EXPIRACION` varchar(100) NOT NULL,
  `FECHA_EXPIRACION` date NOT NULL,
  `ID_GRI_MARCA` int(11) NOT NULL,
  `ID_GRI_TIPO_RECURSO` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_GRI_RECURSO_ID_MARCA` (`ID_GRI_MARCA`),
  KEY `FK_GRI_RECURSO_TIPO_RECURSO` (`ID_GRI_TIPO_RECURSO`),
  CONSTRAINT `FK_GRI_RECURSO_ID_MARCA` FOREIGN KEY (`ID_GRI_MARCA`) REFERENCES `gri_marca` (`ID`),
  CONSTRAINT `FK_GRI_RECURSO_TIPO_RECURSO` FOREIGN KEY (`ID_GRI_TIPO_RECURSO`) REFERENCES `gri_tipo_recurso` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `gri_recurso` */

insert  into `gri_recurso`(`ID`,`DESCRIPCION`,`CANTIDAD`,`SO`,`PROCESADOR`,`MEMORIA_RAM`,`DISCO_DURO`,`PULGADAS`,`CAPACIDAD`,`SERIE`,`TIPO_EXPIRACION`,`FECHA_EXPIRACION`,`ID_GRI_MARCA`,`ID_GRI_TIPO_RECURSO`) values (1,'Mouse',5,'asdsad','-','-','-','fdsfdsfdsf','fdfsfsdf','fdsfsdfdsf','dsffdfsfsdf','2018-05-04',1,1),(2,'Teclado',5,'ffsf','-','-','-','dfdsdff','vdvdsdfs','vfdfdffgd','dfdggdffg','2018-05-05',1,1),(3,'Monitor',8,'fffsdfsfds','-','-','-','qfdgdfgdfg','vddfdgfdg','dfdgdfgdf','qdgdfgfdg','2018-05-04',1,1),(4,'Camara Web',10,'jk','-','-','-','m','j','u','h','2018-11-16',1,1),(5,'Office 2014',20,'k','-','-','-','k','k','k','k','2018-11-16',1,1),(6,'Cargador',10,'k','-','-','-','k','k','k','k','2018-11-16',1,1),(7,'CPU',15,'K','-','-','-','K','KK','K','K','2018-11-16',1,1),(8,'UPS',15,'F','-','-','-','F','F','F','F','2018-11-16',1,1),(9,'LAPTOP',20,'J','-','-','-','J','J','J','J','2018-11-16',1,1),(10,'PROYECTOS',5,'H','-','-','-','H','H','H','H','2018-11-16',1,1),(11,'ECRAN',5,'H','-','-','-','H','H','H','H','2018-11-16',1,1),(12,'TV SMART',5,'H','-','-','-','H','H','H','H','2018-11-16',1,1),(13,'TABLET',20,'H','-','-','-','H','H','H','H','2018-11-16',1,1);

/*Table structure for table `gri_tipo_recurso` */

DROP TABLE IF EXISTS `gri_tipo_recurso`;

CREATE TABLE `gri_tipo_recurso` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO` varchar(20) NOT NULL,
  `NOMBRE` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `gri_tipo_recurso` */

insert  into `gri_tipo_recurso`(`ID`,`CODIGO`,`NOMBRE`) values (1,'C01','Recurso informaticos'),(2,'C02','Tipo 2'),(3,'C03','Tipo 3'),(4,'C04','Tipo 4'),(5,'C05','Tipo 5');

/*Table structure for table `ss_area` */

DROP TABLE IF EXISTS `ss_area`;

CREATE TABLE `ss_area` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  `ESTADO` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `ss_area` */

insert  into `ss_area`(`ID`,`NOMBRE`,`ESTADO`) values (1,'CONTABILIDAD',NULL),(2,'SISTEMAS',NULL),(3,'FINANZAS',NULL),(4,'MARKETING',NULL),(6,'GERENCIA',NULL),(7,'OPERACIONES',NULL),(8,'FORMACION',NULL),(10,'RECURSOS HUMANOS',NULL),(11,'PROCESOS',NULL),(12,'LOGISTICA',NULL),(13,'ADMINISTRACION',NULL),(14,'PRESUPUESTOS','1'),(15,'AREA 500','1');

/*Table structure for table `ss_rol` */

DROP TABLE IF EXISTS `ss_rol`;

CREATE TABLE `ss_rol` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(50) NOT NULL,
  `ID_AREA` int(11) DEFAULT NULL,
  `NIVEL` int(11) DEFAULT NULL,
  `ID_USUARIO_CREACION` int(11) DEFAULT NULL,
  `ID_USUARIO_MODIFICACION` int(11) DEFAULT NULL,
  `FECHA_CREACION` date DEFAULT NULL,
  `FECHA_CREACION_HORA` date DEFAULT NULL,
  `FECHA_MODIFICACION` date DEFAULT NULL,
  `FECHA_MODIFICACION_HORA` date DEFAULT NULL,
  `PC_CREACION` varchar(100) DEFAULT NULL,
  `PC_MODIFICACION` varchar(100) DEFAULT NULL,
  `ID_SEDE` int(11) DEFAULT NULL,
  `ESTADO` char(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_AREA` (`ID_AREA`),
  CONSTRAINT `ss_rol_ibfk_1` FOREIGN KEY (`ID_AREA`) REFERENCES `ss_area` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `ss_rol` */

insert  into `ss_rol`(`ID`,`NOMBRE`,`ID_AREA`,`NIVEL`,`ID_USUARIO_CREACION`,`ID_USUARIO_MODIFICACION`,`FECHA_CREACION`,`FECHA_CREACION_HORA`,`FECHA_MODIFICACION`,`FECHA_MODIFICACION_HORA`,`PC_CREACION`,`PC_MODIFICACION`,`ID_SEDE`,`ESTADO`) values (1,'Analista/programador',2,3,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(2,'Contador',1,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(3,'Diseñador',2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'Tester',2,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'Administrador',13,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(6,'Repartidor',7,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(7,'Chofer',7,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'Analista de procesos',11,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(9,'Jefe de presupuestos',14,4,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,'1'),(10,'Jefe de formación',10,2,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(11,'Asistente de formación',10,7,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(12,'Asistente Soporte técnico',2,6,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(13,'Asistente de operaciones',7,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(14,'Asistente de contabilidad',1,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(15,'Jefe de procesos',11,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(16,'Asistente de administración',13,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(17,'Asistente de Sistemas',2,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(18,'Asistente de marketing',4,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL),(19,'Marketing',4,1,NULL,NULL,NULL,NULL,NULL,NULL,'','',NULL,NULL);

/*Table structure for table `ss_usuario` */

DROP TABLE IF EXISTS `ss_usuario`;

CREATE TABLE `ss_usuario` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_EMPLEADO` int(11) DEFAULT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL,
  `CLAVE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_EMPLEADO` (`ID_EMPLEADO`),
  CONSTRAINT `ss_usuario_ibfk_1` FOREIGN KEY (`ID_EMPLEADO`) REFERENCES `ca_empleado` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `ss_usuario` */

insert  into `ss_usuario`(`ID`,`ID_EMPLEADO`,`NOMBRE`,`CLAVE`) values (1,1,'RVALENCIA','12345678'),(2,38,'AVAZQUEZ','12345678'),(3,3,'IPORRAS','12345678'),(4,8,'CTELLO','12345678'),(5,4,'GARCE','12345678'),(6,28,'LCAYCAY','12345678'),(7,5,'ERAMIREZ','12345678'),(8,39,'GQUIROZ','12345678'),(9,40,'KBOCANEGRA','12345678'),(10,41,'RLEON','12345678'),(11,62,'RBARONA','12345678'),(12,63,'YVAZQUEZ','12345678');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


-- Agregar columnas
ALTER TABLE CA_EMPLEADO
ADD FLG_CAPACITACION CHAR(1);

ALTER TABLE ca_formacion
ADD ID_PERFIL_CAPACITADOR CHAR(1)