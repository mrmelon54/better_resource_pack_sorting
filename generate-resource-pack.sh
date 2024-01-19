#!/bin/bash
function mkcp() {
  echo "Copying $1 to $2"
  mkdir -p -- "`dirname "$2"`"
  cp "$1" "$2"
}

for platform in fabric forge quilt; do
  mkcp "test-resource-pack.zip" "$platform/run/resourcepacks/test-resource-pack.zip"
done
