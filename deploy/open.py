import yaml
import os
import sys
from shutil import copyfile
def my(id):
    mkdir(id)#创建数据库的准备工作
    port1 = str(10000 + (int(id) - 1) * 8 + 0)
    port2 = str(10000 + (int(id) - 1) * 8 + 1)
    port3 = str(10000 + (int(id) - 1) * 8 + 2)
    port4 = str(10000 + (int(id) - 1) * 8 + 3)
    port5 = str(10000 + (int(id) - 1) * 8 + 4)
    port6 = str(10000 + (int(id) - 1) * 8 + 5)
    port7 = str(10000 + (int(id) - 1) * 8 + 6)
    port8 = str(10000 + (int(id) - 1) * 8 + 7)
    f=open("docker-compose.yml", "w")
    line1 = "version: '3'\n"
    line2 = "services:\n"
    line3 = "  eureka"+ id+":\n"
    line4 = "    container_name: eureka"+id+"\n"
    line5 = "    image: eureka:latest\n"
    line6 = "    ports:\n"
    line7 = "      - "+port1+":10000\n"
    line8 = "    networks:\n"
    line9 = "      - netName"+id+"\n"


    line10 = "  zuul"+id+":\n"
    line11 = "    container_name: zuul"+id+"\n"
    line12 = "    image: zuul:latest\n"
    line13 = "    ports:\n"
    line14 = "      - "+port2+":10001\n"
    line15 = "    networks:\n"
    line16 = "      - netName"+id+"\n"
    line17 = "    depends_on:\n"
    line18 = "      - eureka"+id+"\n"
    line19 = "    links:\n"
    line20 = "      - eureka"+id+":eureka-server\n"


    line21 = "  user"+id+":\n"
    line22 = "    container_name: user"+id+"\n"
    line23 = "    image: user:latest\n"
    line24 = "    ports:\n"
    line25 = "      - "+port3+":10002\n"
    line26 = "    depends_on:\n"
    line27 = "      - eureka"+id+"\n"
    line28 = "    networks:\n"
    line29 = "      - netName"+id+"\n"
    line30 = "    links:\n"
    line31 = "      - eureka"+id+":eureka-server\n"
    line32 = "      - zuul"+id+":zuul\n"
    line33 = "      - mysql"+id+":mysql\n"

    line34 = "  device" + id + ":\n"
    line35 = "    container_name: device" + id + "\n"
    line36 = "    image: device:latest\n"
    line37 = "    ports:\n"
    line38 = "      - " + port4 + ":10003\n"
    line39 = "    depends_on:\n"
    line40 = "      - eureka" + id + "\n"
    line41 = "    networks:\n"
    line42 = "      - netName" + id + "\n"
    line43 = "    links:\n"
    line44 = "      - eureka" + id + ":eureka-server\n"
    line45 = "      - zuul" + id + ":zuul\n"
    line46 = "      - mysql"+id+":mysql\n"

    line47 = "  bracelet" + id + ":\n"
    line48 = "    container_name: bracelet" + id + "\n"
    line49 = "    image: bracelet:latest\n"
    line50 = "    ports:\n"
    line51 = "      - " + port5 + ":10004\n"
    line52 = "    depends_on:\n"
    line53 = "      - eureka" + id + "\n"
    line54 = "    networks:\n"
    line55 = "      - netName" + id + "\n"
    line56 = "    links:\n"
    line57 = "      - eureka" + id + ":eureka-server\n"
    line58 = "      - zuul" + id + ":zuul\n"
    line59 = "      - mysql" + id + ":mysql\n"

    line60 = "  prisoner" + id + ":\n"
    line61 = "    container_name: prisoner" + id + "\n"
    line62 = "    image: prisoner:latest\n"
    line63 = "    ports:\n"
    line64 = "      - " + port6 + ":10005\n"
    line65 = "    depends_on:\n"
    line66 = "      - eureka" + id + "\n"
    line67 = "    networks:\n"
    line68 = "      - netName" + id + "\n"
    line69 = "    links:\n"
    line70 = "      - eureka" + id + ":eureka-server\n"
    line71 = "      - zuul" + id + ":zuul\n"
    line72 = "      - mysql" + id + ":mysql\n"

    line73 = "  task" + id + ":\n"
    line74 = "    container_name: task" + id + "\n"
    line75 = "    image: task:latest\n"
    line76 = "    ports:\n"
    line77 = "      - " + port7 + ":10006\n"
    line78 = "    depends_on:\n"
    line79 = "      - eureka" + id + "\n"
    line80 = "    networks:\n"
    line81 = "      - netName" + id + "\n"
    line82 = "    links:\n"
    line83 = "      - eureka" + id + ":eureka-server\n"
    line84 = "      - zuul" + id + ":zuul\n"
    line85 = "      - mysql" + id + ":mysql\n"

    line86 = "  mysql" + id + ":\n"
    line87 = "    image: mysql:latest\n"
    line88 = "    container_name: mysql" + id + "\n"
    #line89 = "    restart: always\n"
    line89 = "    environment:\n"
    line90 = "      MYSQL_ROOT_PASSWORD: a128263\n"
    line91 = "    ports:\n"
    line92 = "      - " + port8 + ":3306\n"
    line93 = "    volumes:\n"
    line94 = "      - './database/db"+id+"/db:/var/lib/mysql'\n"
    line95 = "      - './database/db"+id+"/conf/my.cnf:/etc/my.cnf'\n"
    line96 = "      - './database/db"+id+"/init:/docker-entrypoint-initdb.d/'\n"
    line97 = "    networks:\n"
    line98 = "      - netName"+id+"\n"

    line99 = "networks:\n"
    line100 = "  netName"+id+":\n"
    line101 = "    driver: bridge"
    str1=line1+line2+line3+line4+line5+line6+line7+line8+line9+line10+line11+line12+line13+line14+line15+line16+line17+line18+line19+line20
    str1+=line21+line22+line23+line24+line25+line26+line27+line28+line29+line30+line31+line32+line33+line34+line35+line36+line37+line38
    str1+=line39+line40+line41+line42+line43+line44+line45+line46+line47+line48+line49+line50+line51+line52+line53
    str1+=line54+line55+line56+line57+line58+line59+line60+line61+line62+line63+line64+line65+line66+line67+line68+line69+line70+line71
    str1+=line72+line73+line74+line75+line76+line77+line78+line79+line80+line81+line82+line83+line84+line85+line86+line87+line88+line89
    str1+=line90+line91+line92+line93+line94+line95+line96+line97+line98+line99+line100+line101
    f.write(str1)
    f.close()
    print(str1)
    cmd = 'docker-compose up -d'
    res = os.popen(cmd)
    output_str = res.read()   # 获得输出字符串
    print(output_str)

def mkdir(id):
    path = './database/db'+id

    # 判断路径是否存在
    # 存在     True
    # 不存在   False
    isExists = os.path.exists(path)

    # 判断结果
    if not isExists:
        # 如果不存在则创建目录
        os.makedirs(path)
        path1 = './database/db'+id+'/init'
        os.makedirs(path1)
        path2 = './database/db'+id+'/conf'
        os.makedirs(path2)
        path3 = './database/db'+id+'/db'
        os.makedirs(path3)
        src = './init.sql'
        dst = path1+'/init.sql'
        copyfile(src,dst)
        src = './my.cnf'
        dst = path2+'/my.cnf'
        copyfile(src,dst)
        print(path + ' 创建成功')
        return True
    else:
        # 如果目录存在则不创建，并提示目录已存在
        print(path + ' 目录已存在')
        return False
def main(argv):
    my(argv[1])#argv[1]是id


if __name__ == "__main__":
    main(sys.argv)
