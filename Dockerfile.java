# rebased/repackaged base image that only updates existing packages
FROM mbentley/alpine:latest
LABEL maintainer="Matt Bentley <mbentley@mbentley.net>"

ARG MC_JAVA_URL

RUN if [ -z "${MC_JAVA_URL}" ]; then echo "ERROR: no URL to the minecraft java release was passed via MC_JAVA_URL"; exit 1; fi &&\
  apk --no-cache add openjdk17 &&\
  mkdir /opt/minecraft /opt/minecraft/data &&\
  cd /opt/minecraft &&\
  wget -nv "${MC_JAVA_URL}" -O "minecraft_server.jar" &&\
  addgroup -g 511 mc &&\
  adduser -D -u 511 -G mc -s /sbin/nologin -H -h /opt/minecraft mc &&\
  chown -R mc:mc /opt/minecraft

COPY entrypoint-java.sh /entrypoint.sh

USER mc
WORKDIR /opt/minecraft/data
VOLUME ["/opt/minecraft/data"]
ENTRYPOINT ["/entrypoint.sh"]
CMD ["java","-Xmx4G","-Xms1G","-XX:SoftMaxHeapSize=3G","-XX:+UnlockExperimentalVMOptions","-XX:+UseZGC","-jar","/opt/minecraft/minecraft_server.jar","nogui"]
