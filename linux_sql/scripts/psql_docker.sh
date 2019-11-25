#! /bin/bash
### script usage
#./scripts/psql_docker.sh start|stop [db_password]

operation=$1

if [[ -z "$1" ]]; then
	echo "Usage: psql_docker.sh start|stop [db_password]"
	exit 1
fi

if [[ -z "$2" ]]; then
	break	
else
	db_password=$2
	export PGPASSWORD="$db_password"
fi

case "$operation" in
	start)
		docker run --rm --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
		psql -h localhost -U postgres
		;;	
	stop)
		docker stop jrvs-psql
		;;
	*) 
		echo "Usage: psql_docker.sh start|stop [db_password]"
		exit 1
esac

exit 0
