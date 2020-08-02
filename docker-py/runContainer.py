from docker import client
import os
import sys

def runContainer(repository, tag ,ip , port, name):
    #mydocker = client.from_env()
    mydocker = client.DockerClient(base_url=ip + ":2375")
    container = mydocker.containers.run(repository+":"+tag,
                                detach=True, ports={port+'/tcp': [port]}, name=name, network="zhuanyong")
    if container.status == "created":
        container_list = mydocker.containers.list();
        container = mydocker.containers.get(name)
        if container in container_list:
            if container.status == "running":
                print("create success")
                print(container.name + " " + container.id + " " + container.short_id + " " + container.status)
            else:
                print("create failure")
    else:
        print("create failure")

def main(argv):
    runContainer(argv[1],argv[2],argv[3],argv[4],argv[5])

if __name__ == "__main__":
    main(sys.argv)