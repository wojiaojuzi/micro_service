from docker import client
import yaml
import os
import sys
def get_image(ip):
    mydocker = client.from_env()
    myImage = mydocker.images.list()
    for image in myImage:
        str = image.tags.__getitem__(0).split(':')
        repository = str[0]
        tag = str[1]
        print(image.id+" "+image.short_id+" "+repository+" "+tag)

def main(argv):
    get_image("127.0.0.1")

if __name__ == "__main__":
    main(sys.argv)