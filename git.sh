#!/bin/bash
coment=$1
if [ $# -lt 1 ];
then
	comment="hi"
fi
echo "add files..."
git add*
echo "commint files..."$comment
git commit -m $comment
echo "git push"
git push

