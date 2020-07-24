from docker import client
import yaml
import os
import sys

def getContainer():
    mydocker = client.from_env()

    container = mydocker.containers.get("eureka")
    print(container.status)

def main(argv):
    getContainer()

if __name__ == "__main__":
    main(sys.argv)