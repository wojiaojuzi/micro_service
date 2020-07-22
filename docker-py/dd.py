from docker import client

print(client)
mydocker = client.DockerClient(base_url='tcp://127.0.0.1:2375')
#print(mydocker.info())
#mydocker.containers.run('67417b63d2b6',detach=True)
#print(mydocker.containers.list())
id = mydocker.containers.list()[0].short_id
print(id)
mycontainer = mydocker.containers.get("86463dd2cb")
print(mycontainer.id+" "+mycontainer.short_id+" "+" "+mycontainer.status)
