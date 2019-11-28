# Linux Cluster Monitoring Agent

In order to manage a cluster of machines, the manager requires a way to glance at each cluster node's specific hardware specifications as well as up to date usage data. This project creates a method to monitor cluster nodes individually and query a central database, where the manager can glean valuable information regarding the cluster as a whole.



![Architecture Diagram](assets/ArchitectureDiagram.svg)



#### host_info.sh

This script will insert the host machine specifications directly into the *host_info* table within the *host_agent* database. After running once, this script is not run again as it is assumed physical parameters will not change.

```
[Usage] host_info.sh psql_host psql_port db_name psql_user psql_password
```



#### host_usage.sh

Running only after *host_info.sh*, *host_usage.sh* collects usage data from the host and inserts it into the *host_usage* table within the *host_agent* database. This script will be run once every minute, as dictated by its *crontab* job.

```
[Usage] host_usage.sh psql_host psql_port db_name psql_user psql_password
```



#### psql_docker.sh

Executing this script calls on docker (starts  service if its not already) to create the *jrvs-psql* container and providing it with a *pgdata* volume where all PostgreSQL databases will be stored. The script will skip creation of the container and volume if they already, and exit if the container is already running.

```
[Usage] psql_docker.sh start|stop [db_password]
```



#### ddl.sql

A SQL file used to create the host agent database. The file then populates the database with tables for host machine specifications and usage data.

##### *host_info*

| id   | host_name | cpu_number | cpu_architecture | cpu_model | cpu_mhz | l2_cache | total_mem | timestamp |
| ---- | --------- | ---------- | ---------------- | --------- | ------- | -------- | --------- | --------- |

##### *host_usage*

| timestamp | host_id | host_name | memory_free | cpu_idle | cpu_kernel | disk_io | disk_available |
| --------- | ------- | --------- | ----------- | -------- | ---------- | ------- | -------------- |



#### queries.sql

A set of practical PostgreSQL queries that produce results which would be useful for managing a cluster of machines. 

``` 
#Sort machines based on number of CPUs and memory and list in order of largest to #smallest memory.
#What was the average free memory for all machines over the last 5 minutes?
#etc.
```