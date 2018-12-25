#!/bin/bash 
#描述 ：通过交互式将动态获取的ip地址静态化

ip=$1
netmask=$2
gw=$3
name=$4
ping -c 1 $ip  > /dev/null 2>&1

if [ $? -eq 0 ]
then 
    echo "当前ip已经存在！请重新设置！"
    exit 1
fi

cat > /etc/sysconfig/network-scripts/ifcfg-$NAME <<EOF
TYPE=Ethernet
NAME=$name
DEVICE=$name
ONBOOT=yes
BOOTPROTO=static
IPADDR=$ip
NETMASK=$netmask
GATEWAY=$gw
DNS1=202.96.128.166
EOF

systemctl restart network
