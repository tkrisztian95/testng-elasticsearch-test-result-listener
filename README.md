# testng-elasticsearch-test-result-listener
Simple Java library to extend your TestNG test suite execution with sending test results to Elasticsearch.

Based on: https://www.vinsguru.com/selenium-webdriver-real-time-test-execution-results-using-elasticsearch-kibana/

## How to build
TODO!

## How to use in test projects
### Add as a maven dependency
### Configurations and properties
To configure your Elasticsearch server's host url try using the Maven Surefire plugin to specify property `testng.elasticsearch.url`

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
in your project's test resources directory in the following directory structure `test\resources\META-INF\services`
and the file must contain the listener class's package path:

```
com.ktoth.testng.elasticsearch.ElasticsearchTestResultListener
```