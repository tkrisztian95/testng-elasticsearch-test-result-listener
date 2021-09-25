# testng-elasticsearch-test-result-listener
Simple Java library to extend your TestNG test suite execution with sending test results to Elasticsearch. 

Based on: https://www.vinsguru.com/selenium-webdriver-real-time-test-execution-results-using-elasticsearch-kibana/

## How to build & run example
To take the compiled code and package it in its distributable format use the `mvn package` command:
```
> cd ./lib
> mvn package
```
The package with `.jar` extension should have created under the `/lib/target` directory.
The expected filename `testng-elasticsearch-extension-0.0.2-SNAPSHOT.jar`.

To build the `example-app` project and run the tests:
```
> cd ./lib
// install the package into the local repository, for use as a dependency in other projects locally
> mvn install
> cd ../example-app
// run the example-app tests 
> mvn test
```

## How to use

You can simply use the published Maven package of this project from the GitHub Maven packages. 

### Configurations and properties
To configure your Elasticsearch server's host url use the Maven Surefire plugin and specify the property `testng.elasticsearch.url`

Example `pom.xml` snippet:

```
...
    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.22.1</version>
                    <configuration>
                        <systemPropertyVariables>
                            <!--http://www.javabyexamples.com/set-system-property-for-tests-->
                            <testng.elasticsearch.url>http://localhost:9200</testng.elasticsearch.url>
                        </systemPropertyVariables>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
```
### Annotate test classes

Example:
```
@Listeners(ElasticsearchTestResultListener.class)
public class AppTest {
    ...
}
```
### Use ServiceLoader
To apply the TestNG listener globally you only need to create a file with name `org.testng.ITestNGListener` 
in your project's test resources directory. Following the directory structure `test\resources\META-INF\services`
and the file simply needs to contain the listener class's package path:

```
com.ktoth.testng.elasticsearch.ElasticsearchTestResultListener
```