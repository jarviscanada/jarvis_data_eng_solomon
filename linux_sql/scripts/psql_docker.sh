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
                if [[ pgrep docker >/dev/null ]]; then #Check if docker daemon is running
			echo "Starting docker"
			systemctl start docker
		else
			echo "Docker is running"
                fi

                if [[ echo $(docker ps -f name=jrvs-psql | wc -l) -eq 2 ]]; then #If container is already started, exit
			echo "The container is already running!"
                        exit 0
                fi

                if [[ -z echo $(docker volume ls | grep pgdata) ]]; then #If docker volume is not created, create it
                	echo "Volume does not exist. Creating volume"
			docker create pgdata
                fi

                if [[ echo $(docker ps -f name=jrvs-psql | wc -l) -ne 2 ]]; then #If container does not exist, create it
                        docker run --name jrvs-psql -e POSTGRES_PASSWORD=$PGPASSWORD -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
                fi

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
