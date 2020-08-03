from docker import client
import sys


def pullImage(repository, tag, ip):
    mydocker = client.DockerClient(base_url=ip+":2375")

    image = mydocker.images.pull(repository=repository,tag=tag)
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