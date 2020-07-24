from docker import client
import sys

def offContainer():
    mydocker = client.from_env()

    container = mydocker.containers.get("eureka")
    container.stop();
    container = mydocker.containers.get("eureka")
    print(container.status)
    if container.status == "exited":
        container.remove();

        container_list = mydocker.containers.list();

        if container in container_list:
            print("close failure")
        else:
            print("close success")
    else:
        print("close failure")



def main(argv):
    offContainer()

if __name__ == "__main__":
    main(sys.argv)