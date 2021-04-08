FROM ubuntu:18.04
MAINTAINER Matt Bentley <mbentley@mbentley.net>

RUN apt-get update &&\
  DEBIAN_FRONTEND=non-interactive apt-get install -y libcurl4 lynx screen unzip wget &&\
  MC_BEDROCK_URL="$(lynx -dump -listonly https://www.minecraft.net/en-us/download/server/bedrock | grep "bin-linux/bedrock-server-" | awk '{print $2}')" &&\
  MC_BEDROCK_VER="$(echo "${MC_BEDROCK_URL}" | awk -F "bin-linux/bedrock-server-" '{print $2}' | awk -F '.zip' '{print $1}')" &&\
  mkdir /opt/minecraft &&\
  cd /opt/minecraft &&\
  wget "${MC_BEDROCK_URL}" -O "bedrock-server-${MC_BEDROCK_VER}.zip" &&\
  unzip bedrock-server-${MC_BEDROCK_VER}.zip &&\
  rm bedrock-server-${MC_BEDROCK_VER}.zip &&\
  apt-get purge -y lynx &&\
  apt-get autoremove -y &&\
  rm -rf /var/lib/apt/lists/* &&\
  groupadd -g 511 mc &&\
  useradd -u 511 -g 511 -d /opt/minecraft mc &&\
  chown -R mc:mc /opt/minecraft &&\
  chmod +x /opt/minecraft/bedrock_server

USER mc
WORKDIR /opt/minecraft
ENV LD_LIBRARY_PATH="."
CMD screen -S minecraft -m -- ./bedrock_server
