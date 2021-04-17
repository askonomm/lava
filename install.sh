#!/usr/bin/env bash

if [ "$(uname)" = "Darwin" ]; then
  echo "Downloading ..."
  wget -q --show-progress https://github.com/soynomm/bloggo/releases/latest/download/bloggo-mac \
  && chmod +x bloggo-mac \
  && mv bloggo-mac bloggo \
  && mv bloggo /usr/local/bin
  echo "All done."
fi

