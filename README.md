# Browscap4j
A simple to use Java library for the [Browscap project](http://browscap.org/).Uses the `browscap.csv` file , which can be found [here](http://browscap.org/stream?q=BrowsCapCSV) 

#Instructions

Provide the library a path the csv file ,like so :

```java
final File csvFile = new File(PATH_TO_BROWSCAP_CSV);
final Browscap browscap = new Browscap(csvFile);
```