#! /bin/bash
# Script usage
#./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
# Example
#./scripts/host_info.sh "localhost" 5432 "host_agent" "postgres" "mypassword"

#Parse script arguments and place in appropriate variables
psql_host=$1
psql_port=$2
db_name=$3
host_user=$4
db_pass=$5

#Verify number of arguments is sufficient
if [ $# -ne 5 ]; then
	echo "Invalid number of arguments, 5 are required" >&2
        echo "[Usage] ./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password" >&2
        exit 1
fi

export PGPASSWORD=$db_pass #Set environmental variable for psql db password

lscpu_out=$(lscpu && vmstat --stats) #Appends command outputs to variable

#Parse lscpu_out and find relevant information
host_nm=$(hostname -f)
cpu_num=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}')
cpu_arch=$(echo "$lscpu_out" |  egrep "Architecture" | awk '{print $2}')
cpu_model=$(echo "$lscpu_out" | egrep "Model name" | awk '{output=""; gap=" "; for(i=3; i<=NF;i++) output = output $i gap; print output}')
cpu_mhz=$(echo "$lscpu_out" | egrep "CPU MHz:" | awk '{print $3}')
L2_cache=$(echo "$lscpu_out" | egrep "L2 cache:" | awk '{print substr($3, 0, length($3)-1)}')
total_mem=$(echo "$lscpu_out" | egrep "free memory" | awk '{print $1}')
current_time=$(echo "$(vmstat -t)" | tail -n 1 | awk '{printf("%s %s",  $(NF-1), $NF)}')

#Form INSERT psql query
insert_query=$(echo "INSERT INTO host_info(id, host_name, cpu_number,"\
" cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, \"timestamp\")"\
"VALUES(DEFAULT, '$host_nm', $cpu_num, '$cpu_arch', '$cpu_model', $cpu_mhz, $L2_cache, $total_mem,"\
"'$current_time');")

#Connect to host_agent database with user postgres (using env var for password)
#on psql port; execute and echo the insert query
psql -h $psql_host -U $host_user -d $db_name -e -c "$insert_query"

exit 0
