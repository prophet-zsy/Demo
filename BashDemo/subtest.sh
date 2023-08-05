#! /bin/bash


#  ./subtest.sh a b c dd eee

#  a b c dd eee
echo $@
echo $*

echo "$@"
echo "$*"


for var in "$*"
do
    echo "${var}"
done
#  a b c dd eee
    
for var in "$@"
do
    echo "${var}"
done
# a
# b
# c
# dd
# eee
