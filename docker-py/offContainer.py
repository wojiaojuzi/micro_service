from docker import client
import yaml
import os
import sys

def offContainer(containerName,ip):
    mydocker = client.from_env()

    container = mydocker.containers.get(containerName)
    container.stop();
    container.remove();

    container_list = mydocker.containers.list();

    if container in container_list:
        print("close failure")
    else:
        print("close success")



def main(argv):
    offContainer(argv[1],"127.0.0.1")

if __name__ == "__main__":
    main(sys.argv)
