import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread

fun main(args: Array<String>) {
//    todo 空安全 1
    var a : Int = 23
//    a = null  // 这里非法，因为kotlin中变量默认非空
    var b : Int ?= 23
    b = null  // 这里合法，主动声明变量可能为空，则可以对变量赋空值

//    a = b  // 可能为空的，不能赋值为非空的变量
    b = a  //  非空的，可以赋值为可能为空的变量

//    todo 空安全 2
    var text : String ?= "null"
    println(text?.length ?:0)  // ?.  空返空 不空返函数调用， ?: 空返 : 后 不空返 其本身

//    todo 空安全 3
    val myList : ArrayList<String> ?= null
    println(myList!!.size)  // !! 相当于断言 不为空， 为空则抛异常
}







class Main{
//    todo kotlin中的半生对象
//   【[kotlin]kotlin中的伴生对象（companion object）到底是个什么东西？】https://blog.csdn.net/qq_34676644/article/details/119178392
    companion object {
//     kotlin相对于java取消了static关键字，因此引入伴生对象来弥补没有静态成员的不足
//    【Kotlin 中的伴生对象和静态成员】https://blog.csdn.net/sinat_14849739/article/details/80552111
        @JvmStatic
        fun main(args: Array<String>) {
            println("主入口方法")
//            Main main = Main()
        }
    }

    fun testThread() {
        // 执行时间 1min+
        val c = AtomicLong()
        for (i in 1..1_000_000L)
            thread(start = true) {
                c.addAndGet(i)
            }
        println(c.get())
    }

}