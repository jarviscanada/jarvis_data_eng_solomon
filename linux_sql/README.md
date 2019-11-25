# Linux Cluster Monitoring Agent


![Architecture Diagram](assets/ArchitectureDiagram.svg)



- **host_info.sh** : Collects the host hardware info and insert it into the database. It will be run only once at the installation time.
- **host_usage.sh** : Collects the current host usage (CPU and Memory) and then insert into the database. It will be triggered by the crontab job every minute.
- **psql_docker.sh** : Using docker, the script sets up the postgreSQL dataabase on the JRD.
- **ddl.sql** : Automates postgreSQL database initialization.
- **queries.sql** : A set of SQL queries determined by user story.
