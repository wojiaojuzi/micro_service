import requests
import sys
import xmltodict
import json
from xml.etree import ElementTree
def checkService():
    url = "http://192.168.194.128:10000/eureka/apps"
    #url = "http://localhost:2998/eureka/apps"
    res = requests.get(url)
    res.encoding = 'utf-8'
    converteJson = xmltodict.parse(res.content)
    #jsonStr = json.dumps(converteJson,indent=4)
    apps = converteJson['applications']['application']
    print(type(apps))
    if isinstance(apps,dict):
        print(apps['name'].lower())
    else:
        for app in apps:
            print(app['name'].lower())
def main(argv):
    checkService()

if __name__ == "__main__":
    main(sys.argv)