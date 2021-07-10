#!/usr/bin/env bash

if [ "$(uname -s)" = "Darwin" ]; then
  echo "Downloading ..."
  wget -q --show-progress https://github.com/askonomm/lava/releases/latest/download/lava-mac \
  && chmod +x lava-mac \
  && mv lava-mac lava \
  && mv lava /usr/local/bin
  echo "All done."
fi

if [ "$(uname -s)" = "Linux" ]; then
  echo "Downloading ..."
  wget -q --show-progress https://github.com/askonomm/lava/releases/latest/download/lava-linux \
  && chmod +x lava-linux \
  && mv lava-linux lava \
  && mv lava /usr/local/bin
  echo "All done."
fi
