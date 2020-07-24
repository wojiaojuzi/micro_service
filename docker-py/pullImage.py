from docker import client
import yaml
import os
import sys


def pullImage(repository, tag, ip):
    mydocker = client.from_env()

    image = mydocker.images.pull(repository=repository,tag=tag)
    #nowimage = mydocker.images.pull(repository="registry.cn-hangzhou.aliyuncs.com/edge_node/eureka", tag="latest")
    print(image.id+" "+image.short_id)


def main(argv):
    pullImage(argv[1],argv[2],argc[3])


if __name__ == "__main__":
    main(sys.argv)