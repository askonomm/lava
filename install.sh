#!/usr/bin/env bash

if [ "$(uname -s)" = "Darwin" ]; then
  echo "Downloading ..."
  wget -q --show-progress https://github.com/soynomm/bloggo/releases/latest/download/bloggo-mac \
  && chmod +x bloggo-mac \
  && mv bloggo-mac bloggo \
  && mv bloggo /usr/local/bin
  echo "All done."
fi

if [ "$(uname -s)" = "Linux" ]; then
  echo "Downloading ..."
  wget -q --show-progress https://github.com/soynomm/bloggo/releases/latest/download/bloggo-linux \
  && chmod +x bloggo-linux \
  && mv bloggo-linux bloggo \
  && mv bloggo /usr/local/bin
  echo "All done."
fi
