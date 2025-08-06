# mbentley/minecraft

docker image for Minecraft Bedrock & Java editions
based off of ubuntu:24.04 (Bedrock) & alpine:latest (Java)

To pull this image:
`docker pull mbentley/minecraft:bedrock-latest` or `docker pull mbentley/minecraft:java-latest`

## Tags

* `bedrock` - the latest server version for Bedrock
  * `latest` - the `latest` tag points to the latest Bedrock tag for backwards compatibility since this was originally just a repository for Bedrock
* `java` - the latest server version for Java
* There are also tags for each version of minecraft which can be viewed by checking the [tag listing on Docker Hub](https://hub.docker.com/r/mbentley/minecraft/tags).

## Example Usage

### Bedrock

```
docker run -it --rm --init --name mc -p 19132:19132/udp mbentley/minecraft:bedrock-latest
```

By default, this just runs a default Minecraft Bedrock server that listens on port 19132/udp.

Minecraft Bedrock runs in `screen` so you can send commands to the console like so, assuming that your container is named `mc`:

```
docker exec -t mc screen -S minecraft -X stuff "stop\n"
```

Data can be persisted by mapping `/opt/minecraft/worlds` from the container to a volume or a bind mount on the host.

### Java

```
docker run -it --rm --init --name mc -p 25565:25565 mbentley/minecraft:java-latest
```

By default, this just runs a default Minecraft Java server that listens on port 25565.

Data can be persisted by mapping `/opt/minecraft/data` from the container to a volume or a bind mount on the host.

## Building this Image

### Bedrock
In order to build this image, you must pass the `MC_BEDROCK_URL` build arg with the minecraft zip file URL. This can be obtained manually or by using `wget` or `curl`:

```
...
--build-arg MC_BEDROCK_URL="$(wget -q -O - "https://net-secondary.web.minecraft-services.net/api/v1.0/download/links" | jq -r '.result.links|.[]' | jq -r 'select(.downloadType == "serverBedrockLinux") | .downloadUrl')"
...
```

### Java
In order to build this image, you must pass the `MC_JAVA_URL` build arg with the minecraft jar file URL. This can be obtained manually or by using `lynx`:

```
...
--build-arg MC_JAVA_URL="$(wget -q -O - "https://net-secondary.web.minecraft-services.net/api/v1.0/download/links" | jq -r '.result.links|.[]' | jq -r 'select(.downloadType == "serverJar") | .downloadUrl')"
...
```
