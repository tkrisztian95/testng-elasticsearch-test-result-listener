# Setup Elasticsearch with Kibana locally
1. Run command: `docker-compose up -d`
2. Navigate to [localhost:5601](http://localhost:5601)

## Checking published test results 

To check the test results in Elasticsearch navigate to your [local Kibana dashboard](http://localhost:5601). 

In the navigation menu try find the **Discover** menu item under the **Analytics** section. 

![Screenshot](images/discover_menu.png)

**Note:** *To visualize and explore data in Kibana, you must create an index pattern to retrieve data from Elasticsearch.
Click the `+ Create index Pattern button` to create a default one. 
In the first step set the Index pattern name to `*`. 
In the second step select the `executionDate` from the drop-down menu to be used as `Time field
`. Click the `Create Index Pattern` button.*

You should see the test result documents data on this view that have been sent to the Elasticsearch server during the test run.

![Screenshot](images/discover_test_results.png)

### Example API calls with the collected test run details 
#### Indexes & Documents
- testng-test-result
```
GET /testng-test-result/_search HTTP/1.1
Host: localhost:9200
{
    "took": 10,
    "timed_out": false,
    "_shards": {
        ...
    },
    "hits": {
        "total": {
            "value": 122,
            "relation": "eq"
        },
        "max_score": 1.0,
        "hits": [
            {
                "_index": "testng-test-result",
                "_type": "testng-test-result",
                "_id": "V1De6ncB-vd3i6QNJLWT",
                "_score": 1.0,
                "_source": {
                    "testRunId": "e30229d9-7e33-4f9c-a8d1-3c82c4dfa243",
                    "testClass": "com.example.GreetingAppTest",
                    "testMethodName": "testGetName",
                    "testMethodQualifiedName": "com.example.GreetingAppTest.testGetName",
                    "description": "Test get name from application run args",
                    "status": "SUCCESS",
                    "executionDate": "2021-02-28T23:59:22.285+0100",
                    "executionThreadId": "1",
                    "executionThreadName": "main",
                    "meta": null
                }
            },
            ...
```
- testng-test-run
```
GET /testng-test-run/_search HTTP/1.1
Host: localhost:9200
{
    "took": 16,
    "timed_out": false,
    "_shards": {
        ...
    },
    "hits": {
        "total": {
            "value": 6,
            "relation": "eq"
        },
        "max_score": 1.0,
        "hits": [
            {
                "_index": "testng-test-run",
                "_type": "testng-test-run",
                "_id": "gVDe6ncB-vd3i6QNNrW4",
                "_score": 1.0,
                "_source": {
                    "testRunId": "e30229d9-7e33-4f9c-a8d1-3c82c4dfa243",
                    "startDate": "2021-02-28T23:59:21.760+0100",
                    "finishDate": "2021-02-28T23:59:27.792+0100"
                }
            },
            ...
```

## Troubleshooting
### [Resolve issues on Windows] Unable to start Elasticsearch "max virtual memory areas vm.max_map_count [65530]  is too low, increase to at least [262144]"
See: https://stackoverflow.com/questions/42111566/elasticsearch-in-windows-docker-image-vm-max-map-count
```
// Run commands in CMD:
> wsl -d docker-desktop
> sysctl -w vm.max_map_count=262144
```
Then, try compose down with `docker-compose down` and up again!

