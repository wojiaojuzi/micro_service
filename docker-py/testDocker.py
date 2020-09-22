from docker import client
import sys
import requests
import urllib.request
import socket
def getContainer():
    mydocker = client.DockerClient(base_url="192.168.194.128:2375",timeout=1)
    try:
        mydocker.version()
    except Exception:
        print("error")
        return
    print("ok")

def main(argv):
    #getContainer(argv[1])
    getContainer()

if __name__ == "__main__":
    main(sys.argv)