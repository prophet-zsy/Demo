fun main() {
    val user = User("zhangsan", 26)
    println(user.getUserName())
    println(user.getAge())
    println(user.helloUserName())

}

// 【一篇文章学会Kotlin中的构造函数】https://juejin.cn/post/6844903872016678919


class User constructor(userName :String, age :Int){  // 主构造器
    private val userName : String
    private var age : Int
    init {
        this.userName = userName
        this.age = age
    }

    constructor(userName :String) : this(userName, 26) // 次级构造器 （可以直接或间接调用主构造器）

    fun getUserName() : String {
        return userName
    }
    fun getAge() : Int {
        return age
    }
    fun helloUserName() : String {
        return "Hello $userName !"
    }

}


