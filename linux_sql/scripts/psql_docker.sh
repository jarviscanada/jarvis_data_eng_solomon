#!/bin/sh
su centos

#start docker
#understand why we use || in this case
systemctl status docker || systemctl start docker

#get psql docker image
docker pull postgres

#psql docker docs https://hub.docker.com/_/postgres
#set password for default user `postgres`
export PGPASSWORD='password'
#create a local volumn to persist data
docker volume create pgdata
#run psql
docker run --rm --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
#check running status
docker ps

#install psql CLI client
sudo yum install -y postgresql

#Connect to psql instance uing psql REPL (read?eval?print loop)
psql -h localhost -U postgres -W

#show all databases
postgres=# \l
