#! /bin/bash
# Script usage
#bash scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
# Example
#bash scripts/host_usage.sh localhost 5432 host_agent postgres password

#Parse script arguments and place in appropriate variables
psql_host=$1
psql_port=$2
db_name=$3
host_user=$4
host_password=$5

#Verify number of arguments is sufficient
if [ $# -ne 5 ]; then
	echo "Invalid number of arguments, 5 are required" >&2
        echo "[Usage] ./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password" >&2
        exit 1
fi
export PGPASSWORD=$host_password #Set environmental variable for psql db password

vmstat_out=$(vmstat --stats --unit M && vmstat --disk-sum --unit M && vmstat -wt) #Appends command outputs to variable

#Parse vmstat_out and find relevant information
host_name=$(hostname -f)
memory_free=$(echo "$vmstat_out" | egrep "free memory" | awk '{print$1}')
cpu_idle=$(echo "$vmstat_out" | tail -n 1 | awk '{print $(NF-2)}')
cpu_kernel=$(uname -s)
disk_io=$(echo "$vmstat_out" | egrep "inprogress" | awk '{print $1 * 1000}')
disk_available=$(echo "$(df -BM /)" | tail -n 1 | awk '{print substr($4, 0, length($4)-1)}')
current_time=$(echo "$vmstat_out" | tail -n 1 | awk '{printf("%s %s",  $(NF-1), $NF)}')

#Form INSERT psql query
insert_query=$(echo "INSERT INTO host_usage(timestamp, host_id, "'host_name'", memory_free, cpu_idle,"\
" "'cpu_kernel'","' disk_io'", "'disk_available'")VALUES('$current_time', "\
"(SELECT id FROM host_info WHERE host_name = '$host_name')"\
", '$host_name', $memory_free, $cpu_idle, '$cpu_kernel', $disk_io, $disk_available);")

#Connect to host_agent database with user postgres (using env var for password)
#on psql port; execute and echo the insert query
psql -h $psql_host -p $psql_port -U $host_user -w -d $db_name -c "$insert_query" -e
echo "[$current_time] Inserted usage data"

exit 0
