#!/bin/bash
echo "#!/bin/bash" >  automatic.sh
echo "#chkconfig: 2345 80 90" >>  automatic.sh
echo "#description:automatic.sh" >>  automatic.sh
echo $1 >> automatic.sh
echo "nohup java -jar springdemo-0.0.1-SNAPSHOT.jar >temp.log &"  >> automatic.sh
chmod +x  automatic.sh
mv automatic.sh  /etc/rc.d/init.d/automatic.sh
chkconfig --add automatic.sh
chkconfig automatic.sh on 

