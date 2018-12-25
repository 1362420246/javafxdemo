#!/bin/bash
#启动脚本
basepath=$(cd `dirname $0`; pwd)

if [ ! -d "$basepath/jdk1.8.0_191" ]
then
 tar -xzf "$basepath/jdk-8u191-linux-x64.tar.gz"
fi
nohup $basepath/jdk1.8.0_191/bin/java -jar airman-ams-0.0.1-SNAPSHOT.jar >temp.log &

