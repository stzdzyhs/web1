rem deploy in tomcat


rmdir /s /q f:\tools\apache-tomcat-7.0.34\webapps\web1
del /q f:\tools\apache-tomcat-7.0.34\webapps\web1.war

copy target\web1.war  f:\tools\apache-tomcat-7.0.34\webapps\
