import java.lang.Exception

/**
 * 【Kotlin：数据类、密封类】 https://juejin.cn/post/6844903543455891463
 */

//  kotlin中， 给类加上‘data’关键字，即声明其为数据类；
//  kotlin中，编译器会为其生成一些有用的方法（equals() hashCode() toString() componentN() copy()）
data class Person(val name: String, val age: Int)

val tom = Person("Tom", 18)
val jack = Person("Jack", 19)

fun testDataClass() {
    println(tom.equals(jack))
    println(tom.hashCode())
    println(tom.toString())

    val (name, age) = tom
    println("name is $name, age is $age")

    val mike = tom.copy(name = "Mike")
    println(mike)
}


enum class Human {
    MAN, WOMAN
}
fun isMan(data: Human) = when(data) {
    Human.MAN -> true
    Human.WOMAN -> false
}
//   使用‘sealed’关键字定义密封类，常使用密封类对数据类进行封装，
//   密封类不能被实例化，
sealed class Result<out R> {
    data class Success<out T>(val data: T, val message: String = "") : Result<T> ()
    data class Error(val exception: Exception) : Result<Nothing> ()
    data class Loading(val time: Long = System.currentTimeMillis()) : Result<Nothing> ()
}
fun display(data: Result<Int>) : String = when(data) {
    is Result.Success -> displaySuccessUI()
    is Result.Error -> showErrorMsg()
    is Result.Loading -> showLoading()
}
fun displaySuccessUI(): String {
    return "success"
}
fun showErrorMsg(): String {
    return "error"
}
fun showLoading(): String {
    return "loading"
}


fun testSealedClass() {
    println(isMan(Human.MAN))
    println(display(Result.Success<Int>(56)))
}


fun main() {
    testDataClass()
    println()
    testSealedClass()
}





