from docker import client
import yaml
import os
import sys


def pullImage(repository, tag, ip):
    mydocker = client.from_env()

    #nowimage = mydocker.images.pull(repository=repository,tag=tag)
    nowimage = mydocker.images.pull(repository="registry.cn-hangzhou.aliyuncs.com/edge_node/eureka", tag="latest")
    print(nowimage.tags[0])


def main(argv):
    pullImage("","","127.0.0.1")


if __name__ == "__main__":
    main(sys.argv)