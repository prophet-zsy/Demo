#! /bin/bash

# 单引号字符串不能识别特殊语法
echo 'Hello World !'
# 双引号字符串能够识别一些特殊语法
echo "这是一个shell脚本"

# for循环
n=3
for((i=0;i<n;i++))
do
    echo "${i}"
done


./subtest.sh  a b c dd eee

./fun.sh


