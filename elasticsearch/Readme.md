# Start Elasticsearch cluster with Kibana
1. Run command in dir: `docker-compose up -d`
2. Navigate to [localhost:5601](http://localhost:5601)

## [Resolve on Windows] Unable to start Elasticsearch "max virtual memory areas vm.max_map_count [65530]  is too low, increase to at least [262144]"
See: https://stackoverflow.com/questions/42111566/elasticsearch-in-windows-docker-image-vm-max-map-count
```
Run commands in CMD:
$ wsl -d docker-desktop
$ sysctl -w vm.max_map_count=262144
```
Then, try compose down with `docker-compose down` and up again!
