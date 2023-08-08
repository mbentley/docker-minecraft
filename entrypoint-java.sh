#!/bin/sh

# check eula
if [ "${ACCEPT_EULA}" = "true" ]
then
  # accept EULA
  echo "INFO: ACCEPT_EULA=true; setting 'eula=true' in eula.txt"
  echo "eula=true" > /opt/minecraft/data/eula.txt
else
  # don't auto-accept EULA
  echo "WARN: ACCEPT_EULA=true is NOT set; minecraft server may fail to start if existing acceptance not available"
fi

# run cmd
echo "INFO: executing ${*}"
exec "${@}"
