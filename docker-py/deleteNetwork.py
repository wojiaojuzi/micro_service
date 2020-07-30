from docker import client
import os
import sys

def createNetwork(ip):
    mydocker = client.from_env()
    networks = mydocker.networks.list()

    network = mydocker.networks.get("zhuanyong")
    print(network)
    network.remove()
    networks = mydocker.networks.list()
    if network in networks:
        print("删除失败")
    else:
        print("删除成功")

def main(argv):
    createNetwork("127.0.0.1")

if __name__ == "__main__":
    main(sys.argv)