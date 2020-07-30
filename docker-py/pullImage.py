from docker import client
import os
import sys


def pullImage(repository, tag, ip):
    mydocker = client.from_env()

    image = mydocker.images.pull(repository=repository,tag=tag)
    #nowimage = mydocker.images.pull(repository="registry.cn-hangzhou.aliyuncs.com/edge_node/eureka", tag="latest")
    images = mydocker.images.list()
    image = mydocker.images.get(repository+":"+tag)
    if image in images:
        print("pull success")
        print(image.id+" "+image.short_id)
    else:
        print("pull failure")


def main(argv):
    pullImage(argv[1],argv[2],argv[3])


if __name__ == "__main__":
    main(sys.argv)