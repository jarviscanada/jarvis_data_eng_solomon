DROP DATABASE IF EXISTS host_agent;
CREATE DATABASE host_agent;
\c host_agent;

DROP TABLE IF EXISTS PUBLIC.host_info;
CREATE TABLE PUBLIC.host_info
(
	id               SERIAL UNIQUE  NOT NULL, 
	host_name        VARCHAR NOT NULL UNIQUE, 
	cpu_number       INT NOT NULL,
	cpu_architecture VARCHAR(6) NOT NULL,
	cpu_model        VARCHAR NOT NULL,
	cpu_mhz          REAL NOT NULL,
	L2_cache         INT NOT NULL,
	total_mem        INT NOT NULL,
	"timestamp"      TIMESTAMP NOT NULL,
	PRIMARY KEY(id)
);

DROP TABLE IF EXISTS PUBLIC.host_usage;
CREATE TABLE PUBLIC.host_usage 
( 
	"timestamp"    TIMESTAMP NOT NULL, 
	host_id  SERIAL UNIQUE NOT NULL REFERENCES host_info(id),
	memory_free    INT NOT NULL,
	cpu_idel      REAL NOT NULL,
	cpu_kernel     REAL NOT NULL,
	disk_io        INT NOT NULL,
	disk_available INT NOT NULL, 
	PRIMARY KEY("timestamp", host_id)
); 
