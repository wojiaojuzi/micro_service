from docker import client
import sys

def getContainer(ip):
    mydocker = client.DockerClient(base_url=ip + ":2375")
    container = mydocker.containers.get("eureka")
    print(container.status)

def main(argv):
    getContainer(argv[1])

if __name__ == "__main__":
    main(sys.argv)