# buildTool

java -cp target/sample-1.0-SNAPSHOT.jar;"%HOME%/.m2/repository/commons-lang/commons-lang/2.4/commons-lang-2.4.jar" 패키지명.클래스명

## Ant




### Web deploy
- ${톰캣_설치_경로}/webapps 디렉토리에 war 파일을 추가
- ${톰캣_설치_경로}/conf/Catalina/localhost 디렉토리에 context 파일을 추가

#### add ${톰캣_설치_경로}\conf\tomcat-users.xml
```
<role rolename="manager"/>
<user username="${사용자_아이디}" password="${사용자_암호}" roles="manager"/>
```
```
# catalina home directory
catalina.home=C:\Tomcat2
catalina.ant.jar=${catalina.home}/server/lib/catalina-ant.jar
catalina.tasks.file=catalina.tasks3

# catalina manager configurations
catalina.manager.url=http://localhost:8080/manager
catalina.manager.user.name=test4
catalina.manager.user.password=test

# application configurations
application.path=/sample5
application.context=sample.xml6

```
```
# catalina tasks properties
deploy=org.apache.catalina.ant.DeployTask
undeploy=org.apache.catalina.ant.UndeployTask
start=org.apache.catalina.ant.StartTask
reload=org.apache.catalina.ant.ReloadTask
stop=org.apache.catalina.ant.StopTask
sessions=org.apache.catalina.ant.SessionsTask
list=org.apache.catalina.ant.ListTask
serverinfo=org.apache.catalina.ant.ServerinfoTask
roles=org.apache.catalina.ant.RolesTask
resources=org.apache.catalina.ant.ResourcesTask

```
```
<?xml version="1.0" encoding="UTF-8"?>
<Context path="/sample" docBase="C:\Sample7" debug="0" privileged="true" reloadable="true">
    <Logger className="org.apache.catalina.logger.FileLogger" prefix="localhost.sample." suffix=".txt" timestamp="true" />
</Context>

```
bulid
```
~
<taskdef file="catalina.tasks" classpath="${catalina.ant.jar}" />
~
<target name="catalina.deploy" description="Deploy web application">
    <deploy url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" path="${application.path}" config="${application.context}" update="true" />
</target>

<target name="catalina.undeploy" description="Undeploy web application">
    <undeploy url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" path="${application.path}" />
</target>

<target name="catalina.start" description="Start web application">
    <start url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" path="${application.path}" />
</target>

<target name="catalina.reload" description="Reload web application">
    <reload url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" path="${application.path}" />
</target>

<target name="catalina.stop" description="Stop web application">
    <stop url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" path="${application.path}" />
</target>

<target name="catalina.sessions" description="View server information">
    <sessions url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" path="${application.path}" />
</target>

<target name="catalina.list" description="List web applications">
    <list url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" />
</target>

<target name="catalina.serverinfo" description="View server information">
    <serverinfo url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" />
</target>

<target name="catalina.roles" description="View server information">
    <roles url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" />
</target>

<target name="catalina.resources" description="View server information">
    <resources url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" />
</target>
~
<deploy url="${catalina.manager.url}" username="${catalina.manager.user.name}" password="${catalina.manager.user.password}" path="${application.path}" config="${application.context}" update="true" />
~
```

## Maven

### 실행
```
mvn exec:java -Dexec.mainClass=패키지명.메인클래스명 -Dexec.args="인자"
```

### test 생략
```
-Dmaven.test.skip=true 
```
파라메터를 추가

### 이클립스 프로젝트
```
mvn eclipse:eclipse -Dwtpversion=1.5
```
### War 명
 ${finalName} = ${project.artifactId}-${project.version}.war
