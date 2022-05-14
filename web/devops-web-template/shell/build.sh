#!/bin/bash
set -m

# 项目名称（jar包运行位置）
project_name=devops
# 代码目录
code_dir=/data/code/devops/java/
# 模块目录
submodule_dir=devops-web-template
# 服务端口
server_port=50001
# jar包名称
jar_name=devops-web-template
# profile
active_profile=dev

source /etc/profile
export M2_HOME=/data/services/apache-maven-3.0.3
export M2=$M2_HOME/bin
export MAVEN_OPTS="-Xms256m -Xmx512m"
PATH=$M2:$PATH:


cd ${code_dir}
git pull

git submodule foreach git pull

mvn clean install -Dmaven.test.skip=true

kill -9 `netstat -nlp | grep ${server_port} | awk '{print $7}'|awk -F/ '{print $1}'`

sleep 2

mkdir -p /data/webapps/${project_name}/

rm -rf /data/webapps/${project_name}/${jar_name}.jar

cp -rf ${code_dir}/${submodule_dir}/target/${jar_name}.jar /data/webapps/${project_name}/

nohup java -jar /data/webapps/${project_name}/${jar_name}.jar\
  --spring.profiles.active="${active_profile}"\
  --spring.main.allow-bean-definition-overriding=true\
  > /data/logs/${jar_name}.log 2>&1 &

tail -f /data/logs/${jar_name}.log