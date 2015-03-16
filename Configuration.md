# Configuration #

## Database ##
  * MySQL 5.1
  * Database는 UTF-8 인코딩을 사용하도록 한다.
  * Tomcat 6.0 이상
  * server.xml DB Connection Pool 설정
```
<GlobalNamingResources>
  <Resource
      name="jdbc/BookTogetherDataSource"
      auth="Container"
      driverClassName="com.mysql.jdbc.Driver"
      maxActive="40"
      maxIdle="20"
      maxWait="10000"
      removeAbandoned="true"
      removeAbandonedTimeout="60"
      testWhileIdle="true"
      timeBetweenEvictionRunsMillis="60000"
      type="javax.sql.DataSource"
      url="jdbc:mysql://localhost:3306/#{DB이름}?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8"
      username="root"
      password="#{비밀번호}"
      validationQUery="select 1"
  />
</GlobalNamingResources>
```
  * META-INF/context.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<Context path="" reloadable="false">
	<ResourceLink global="jdbc/BookTogetherDataSource" name="jdbc/BookTogetherDataSource"
		type="javax.sql.DataSource" />
</Context>
```
  * Spring 2.5 ApplicationContext
```
	<jee:jndi-lookup id="dataSource" jndi-name="jdbc/BookTogetherDataSource"
		resource-ref="true" />
```
  * DB 생성 방법
```
Database:

(CREATE | ALTER) DATABASE booktogether DEFAULT CHARACTER SET utf8

Table:

(CREATE | ALTER) TABLE user  DEFAULT CHARACTER SET utf8

```

# 관련 자료 #
  * [Tomcat 6 JNDI 설정](http://tomcat.apache.org/tomcat-6.0-doc/jndi-resources-howto.html)
  * [Tomcat 6 DataSource 설정](http://tomcat.apache.org/tomcat-6.0-doc/jndi-datasource-examples-howto.html)