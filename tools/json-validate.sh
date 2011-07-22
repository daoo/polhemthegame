#!/usr/bin/env bash

validate() {
  cat "$1" | jsawk > /dev/null
}

for file in $(find data -not -type d -and -iname "*.js" -print); do
  validate $file
done

