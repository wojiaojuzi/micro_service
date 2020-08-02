from docker import client

mydocker = client.DockerClient(base_url="192.168.194.128:2375")
print(mydocker)