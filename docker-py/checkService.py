import requests
import sys
from xml.etree import ElementTree
def checkService(ip,port):
    url = "http://"+ip+":"+port+"/actuator/health"
    res = requests.get(url)
    print(res.json()['status'])

def main(argv):
    checkService(argv[1],argv[2])

if __name__ == "__main__":
    main(sys.argv)