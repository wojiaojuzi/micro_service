from docker import client
import yaml
import os
import sys

def runContainer(repository, tag ,ip , port, name):
    mydocker = client.from_env()

    container = mydocker.containers.run(repository+":"+tag,
                                detach=True, ports={port+'/tcp': [port]}, name=name, network="zhuanyong")
    container_list = mydocker.containers.list();

    if container in container_list:
        print("create success")
    else:
        print("create failure")

def main(argv):
    runContainer(argv[1],argv[2],argv[3],argv[4],argv[5])

if __name__ == "__main__":
    main(sys.argv)