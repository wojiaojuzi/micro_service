from docker import client
import yaml
import os
import sys

mydocker = client.from_env()

net = mydocker.networks.get("host")
print(net.containers)