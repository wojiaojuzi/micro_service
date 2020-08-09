from docker import client
import sys

def getContainer(containerName,ip):
    mydocker = client.DockerClient(base_url=ip + ":2375")
    containerNames = []
    containers = mydocker.containers.list()
    for container in containers:
        containerNames.append(container.name)
    if containerName in containerNames:
        container = mydocker.containers.get(containerName)
        print(container.status)
    else:
        print("error")

def main(argv):
    getContainer(argv[1],argv[2])

if __name__ == "__main__":
    main(sys.argv)