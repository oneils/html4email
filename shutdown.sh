#!/bin/bash

# Read the Aplictation PID from the file. If PID is specified, application will be terminated.

if [ ! -f idgst.pid ]; then
   echo "Server is not running... ";
   exit 0
fi

PID=$(cat idgst.pid)

echo "Stopping the server";
kill -9 $PID

rm idgst.pid