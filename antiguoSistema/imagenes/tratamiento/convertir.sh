#!/bin/bash
for image in *.jpeg; do 
    cwebp $image -o `basename ${image%.jpeg}`.webp; 
done