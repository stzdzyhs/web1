rem deploy in tomcat

set TOMCAT_HOME=c:\tomcat7

rmdir /s /q %TOMCAT_HOME%\webapps\web1
del /q %TOMCAT_HOME%\webapps\web1.war
copy target\web1.war  %TOMCAT_HOME%\webapps\
