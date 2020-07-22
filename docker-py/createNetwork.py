from docker import client
import yaml
import os
import sys

def createNetwork(ip):
    mydocker = client.from_env()
    networks = mydocker.networks.list()
    network = mydocker.networks.get("zhuanyong")

    if network in networks:
        print("create success")
    else:
        newNetwork = mydocker.networks.create(name="zhuanyong",driver="bridge")

        networks = mydocker.networks.list()
        if newNetwork in networks:
            print("create success")
        else:
            print("create failure")


def main(argv):
    createNetwork("127.0.0.1")

if __name__ == "__main__":
    main(sys.argv)