from docker import client
import yaml
import os
import sys


def deleteImage(repository, tag, ip):
    mydocker = client.from_env()
    targetimage = mydocker.images.get("registry.cn-hangzhou.aliyuncs.com/edge_node/eureka"+":"+"latest")
    mydocker.images.remove("registry.cn-hangzhou.aliyuncs.com/edge_node/eureka"+":"+"latest")
    image_list = mydocker.images.list()

    if targetimage not in image_list:
        print(True)
    else:
        print(False)


def main(argv):
    deleteImage("","","127.0.0.1")


if __name__ == "__main__":
    main(sys.argv)