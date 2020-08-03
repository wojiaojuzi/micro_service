from docker import client
import sys

def createNetwork(ip):
    #mydocker = client.DockerClient(base_url=ip + ":2375")
    mydocker = client.DockerClient(base_url=ip+":2375")
    networks = mydocker.networks.list()
    name_list=[]
    for network in networks:
        name_list.append(network.name)

    if "zhuanyong" in name_list:
        print("create success")
    else:
        newNetwork = mydocker.networks.create(name="zhuanyong",driver="bridge")

        networks = mydocker.networks.list()
        if newNetwork in networks:
            print("create success")
        else:
            print("create failure")


def main(argv):
    createNetwork(argv[1])

if __name__ == "__main__":
    main(sys.argv)