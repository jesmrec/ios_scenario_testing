#!/bin/bash
set -ex
npm install -g appium@latest
appium -v
appium driver install xcuitest
appium --allow-insecure=adb_shell --allow-cors --log-level debug  > appium.log 2>&1 &
