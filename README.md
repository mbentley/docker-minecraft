# mbentley/minecraft

docker image for Minecraft Bedrock
based off of ubuntu:20.04

To pull this image:
`docker pull mbentley/minecraft`

## Example Usage

```
docker run -it --rm --init --name mc -p 19132:19132/udp mbentley/minecraft
```

By default, this just runs a default Minecraft Bedrock server that listens on port 19132/udp.

Minecraft runs in `screen` so you can send commands to the console like so, assuming that your container is named `mc`:

```
docker exec -t mc screen -S minecraft -X stuff "stop\n"
```

Data can be persisted by mapping `/opt/minecraft/worlds` from the container to a volume or a bind mount on the host.

## Building this Image

In order to build this image, you must pass the `MC_BEDROCK_URL` build arg with the minecraft zip file URL. This can be obtained manually or by using `lynx`:

```
...
--build-arg MC_BEDROCK_URL="$(lynx -dump -listonly -useragent="L_y_n_x/2.8.7dev9.1" https://www.minecraft.net/en-us/download/server/bedrock 2> /dev/null | grep "bin-linux/bedrock-server-" | awk '{print $2}')"
...
```
