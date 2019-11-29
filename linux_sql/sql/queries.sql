--Return table of host computer information, ordered by total memory, highest to lowest.

SELECT
  host_info.cpu_number,
  host_info.id,
  host_info.total_mem
FROM
  PUBLIC.host_info
ORDER BY
  host_info.total_mem DESC;

--Joins host_info and host_usage tables based on host_id, then return a table
--containing host machine information and the 5 minutes average percent memeory usage, in descending order

SELECT
  host_usage.host_id AS id_host,
  host_usage.host_name AS host,
  host_info.total_mem AS mem_total,
  AVG(
    100 * (
      (mem_total - host_usage.memory_free)/mem_total
    )
  ) OVER(
    PARTITION BY id_host
    ORDER BY DATE_TRUNC('hour', host_usage.timestamp) + (INTERVAL '5 minutes' * ROUND(DATE_PART('minutes', host_usage.timestamp) / 5)
  ) AS used_memory_percentage
FROM
  PUBLIC.host_usage
  INNER JOIN PUBLIC.host_info USING (host_id)
ORDER BY
  used_memory_percentage DESC;
