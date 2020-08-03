from docker import client
import sys


def deleteImage(repository, tag, ip):
    mydocker = client.DockerClient(base_url=ip + ":2375")
    targetimage = mydocker.images.get(repository+":"+tag)
    image_list = mydocker.images.list()
    if targetimage in image_list:
        mydocker.images.remove(repository+":"+tag)
        image_list = mydocker.images.list()
        if targetimage not in image_list:
            print("delete success")
        else:
            print("delete failure")
    else:
        print("delete failure")

def main(argv):
    deleteImage(argv[1],argv[2],argv[3])


if __name__ == "__main__":
    main(sys.argv)