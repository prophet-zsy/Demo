public class StringDemo {

    public static void main(String[] args) {
//////        字符串比对的写法  ///////
        String test = null;
//      字符串通过equals方法进行比较时，一般将字符串常量放到前面，这样如果比对的变量为null，会正常返回false，而不是报空指针的错误
        if ("null".equals(test)) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
//      但如果按照 var.equals() 的调用方式，则需要考虑到var为空的情况，前面需要加一个判空的逻辑
        if (test != null && test.equals("null")) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
    }



}
