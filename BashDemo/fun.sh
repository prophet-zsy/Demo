#! /bin/bash


function testFun() {  # 解释执行的脚本： 先定义，再执行
    echo "function testFun"
}
testFun2() {
    echo "function testFun2"
}
testFun  # 解释执行的脚本： 先定义，再执行
testFun2


####
# 数组作为函数入参
####
num=(1 2 3 4 5 6)
printArray(){
    local str=""
    local value=1
    for i in "$@"
    do
        let str="${str}${i}"  # 字符串拼接
        let value+=i  # 变量运算
    done
    echo "$str"
    echo ${value}
   
}
printArray ${num[@]}

####
# 按照位置传参
####



