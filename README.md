# mbentley/minecraft

docker image for Minecraft
based off of ubuntu:18.04

To pull this image:
`docker pull mbentley/minecraft`

Example usage:
`docker run -t --rm --name mc -p 19132:19132/udp mbentley/minecraft:bedrock`

By default, this just runs a default Minecraft Bedrock server that listens on port 19132/udp.

Minecraft runs in `screen` so you can send commands to the console like so, assuming that your container is named `mc`:

```
docker exec -t mc screen -S minecraft -X stuff "stop\n"
```

Data can be persisted by mapping `/opt/minecraft/worlds` from the container to a volume or a bind mount on the host.
