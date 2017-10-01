# spring-eureka-standalone-server

Simple spring cloud eureka standalone server for cloud micro-services


Simple spring-cloud eureka standalone server that can run on both windows and other OS without additional setup

# Feature

* Standalone eureka server that can run without additional installation on Windows, Linux and MacOS.
* Allow shut down of the eureka server remotely via web api call

# Usage
copy the spring-eureka-server.jar to your directory and run the following command:

```bash
java -jar spring-eureka-server.jar
```

This will start the eureka server at port 8761.

To check whether the eureka server is alive, call the following url:

http://localhost:8761/ping

To kill the eureka server remotely, just call the following url:

http://localhost:8761/kill

# Note
In case you want to modify the behavior the eureka server and want to rebuild, you can run the make.ps1 on Windows
or make.sh on Linux or Mac.

