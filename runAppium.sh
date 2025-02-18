#!/bin/bash
set -ex
npm install -g appium
appium -v
appium driver install xcuitest
appium &>/dev/null &
