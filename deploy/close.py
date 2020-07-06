import yaml
import os
import sys

def my(id):
    name1 = "eureka"+id
    name2 = "zuul"+id
    name3 = "user"+id
    name4 = "device"+id
    name5 = "bracelet"+id
    name6 = "prisoner"+id
    name7 = "task"+id
    name8 = "mysql"+id

    cmd1 = "docker stop "+name1
    cmd2 = "docker stop "+name2
    cmd3 = "docker stop "+name3
    cmd4 = "docker stop "+name4
    cmd5 = "docker stop "+name5
    cmd6 = "docker stop "+name6
    cmd7 = "docker stop "+name7
    cmd8 = "docker stop "+name8
    res = os.popen(cmd1)
    output_str = res.read()   # 获得输出字符串
    print(output_str)
    res = os.popen(cmd2)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd3)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd4)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd5)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd6)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd7)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd8)
    output_str = res.read()  # 获得输出字符串
    print(output_str)

    cmd1 = "docker rm " + name1
    cmd2 = "docker rm " + name2
    cmd3 = "docker rm " + name3
    cmd4 = "docker rm " + name4
    cmd5 = "docker rm " + name5
    cmd6 = "docker rm " + name6
    cmd7 = "docker rm " + name7
    cmd8 = "docker rm " + name8

    res = os.popen(cmd1)
    output_str = res.read()   # 获得输出字符串
    print(output_str)
    res = os.popen(cmd2)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd3)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd4)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd5)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd6)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd7)
    output_str = res.read()  # 获得输出字符串
    print(output_str)
    res = os.popen(cmd8)
    output_str = res.read()  # 获得输出字符串
    print(output_str)


def main(argv):
    my(argv[1])#argv[1]是id


if __name__ == "__main__":
    main(sys.argv)