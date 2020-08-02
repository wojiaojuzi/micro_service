from docker import client
import sys

def offContainer(containerName,ip):
    #mydocker = client.from_env()
    #mydocker = client.DockerClient(base_url=ip + ":2375")
    mydocker = client.DockerClient(base_url="192.168.194.128:2375")
    containerNames=[]
    containers = mydocker.containers.list()
    for container in containers:
        containerNames.append(container.name)
    if containerName in containerNames:
        container = mydocker.containers.get(containerName)
        container.stop();
        container = mydocker.containers.get(containerName)
        if container.status == "exited":
            container.remove();
            container_list = mydocker.containers.list();
            if container not in container_list:
                print("close success")
            else:
                print("close failure")
        else:
            print("close failure")
    else:
        print("close failure")



def main(argv):
    #offContainer(argv[1],argv[2])
    offContainer("222","222")

if __name__ == "__main__":
    main(sys.argv)
