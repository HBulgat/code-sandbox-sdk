# code-sandbox java sdk
打包安装到本地
```shell
mvn install:install-file \
-Dmaven.repo.local= /usr/local/maven/apache-maven-3.9.6/repository \
-DgroupId=cn.bulgat \
-DartifactId=code-sandbox-sdk \
-Dversion=1.0 \ 
-Dpackaging=jar \
-Dfile=/home/bulgat/IdeaProjects/code-sandbox-sdk/target/code-sandbox-sdk-1.0.jar

#mvn install:install-file \ 
#-DgroupId=cn.bulgat \
#-DartifactId=code-sandbox-sdk \
#-Dversion=1.0 \
#-Dpackaging=jar \
#-Dfile=/home/bulgat/IdeaProjects/code-sandbox-sdk/target/code-sandbox-sdk-1.0.jar
```