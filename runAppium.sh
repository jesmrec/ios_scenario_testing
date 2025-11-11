#!/bin/bash
set -ex
npm install -g appium@latest
appium -v
appium driver install xcuitest@latest
appium --allow-cors --log-level debug &>/dev/null &
