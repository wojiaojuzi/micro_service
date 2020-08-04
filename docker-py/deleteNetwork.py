from docker import client
import os
import sys

def createNetwork(ip):
    mydocker = client.DockerClient(base_url=ip + ":2375")
    networks = mydocker.networks.list()
    name_list = []
    for network in networks:
        name_list.append(network.name)
    network = None
    if "zhuanyong" in name_list:
        network = mydocker.networks.get("zhuanyong")
        network.remove()
    if "zhuanyong" in name_list:
        print("delete failure")
    else:
        print("delete success")

def main(argv):
    createNetwork(argv[1])

if __name__ == "__main__":
    main(sys.argv)