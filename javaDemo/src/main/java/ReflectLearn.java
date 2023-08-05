import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

public class ReflectLearn {

    static {
        System.out.println("Main类被加载");
    }

    private Person person;
    public ReflectLearn(Person p) {
        this.person = p;
    }
    public void visit(String methonName, Object[] args) {

    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, InvocationTargetException {
//        ReflectLearn test = new ReflectLearn(new Person());
////        test.send();
////        test.receive();
//
//        int la = 214736;
//        int lb = 100;
//        int res = 0;
//        while (lb > 0) {
//            if ((lb & (int) 1) == 1) {res += la; lb --;}
//            else {la = la << 1;
//            lb = lb >> 1;}
//        }
//        System.out.println(res);
//
//        String[] dictionary = {"looked","just","like","her","brother"};
//        String sentence = "jesslookedjustliketimherbrother";
//        Queue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2 - o1;
//            }
//        });
//        for (int i = 0; i < 5; i ++) {
//            if (i > 2) continue;
//            System.out.println(i);
//        }

//        int[] big = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
//        int[] small  = {1, 5, 9};
//
//        int[] rrres = new Solution().shortestSeq(big, small);

//        String[] input = {"lcauj", "mdlby", "myulp", "yvkqn", "usajk", "rpj", "bojvf", "ukmkb", "afqbhs", "j", "ebe", "yacov", "wsaep", "zdk", "wziqrdd", "pcjfn", "nlrehaq", "dasrc", "lruvq", "dvca"};
//        String[] output = new Solution1().maxRectangle(input);
//        for (String item: output
//             ) {
//            System.out.print(item + " ");
//        }

//        System.out.println(new Solution5().maxProfit(2, new int[]{2,1,2,0,1}));

//            System.out.println("asd".compareTo("as"));
////        int[] aaa = new int[Integer.MAX_VALUE];
//        Math.pow(2, 0.5);
//            Map<Integer, Integer> h = new HashMap<>();

        System.out.println(new Solution8().maxSubarraySumCircular(new int[]{-5,-2,5,6,-2,-7,0,2,8}));
        new ArrayList<>(5);

        Person1 person1 = new Stu("Asfsd");
        Class aClass = person1.getClass();
        Class aClass1 = Class.forName("Stu");
        Class stuClass = Stu.class;
        Class type = Integer.TYPE;
        Class superclass = aClass.getSuperclass();
        System.out.println(aClass);
        System.out.println(aClass1);
        System.out.println(stuClass);
        System.out.println(type);
        System.out.println(superclass);

        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = Override.class;
        Class c6 = ElementType.class;
        Class c7 = Integer.class;
        Class c8 = void.class;
        Class c9 = Class.class;
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);
        int[] aa = new int[10];
        int[] bb = new int[10];
        System.out.println(aa.getClass().hashCode());
        System.out.println(bb.getClass().hashCode());

//        new AA();
        System.out.println(AA.m);

//        new Son();  // new，主动引用
//        Class.forName("Son");   // 反射，主动引用
//        System.out.println(Son.b);  // Son不会被加载，Father会被加载，因为b声明在Father类中
//        System.out.println(Son.m);  // 静态变量只有声明其的作用域的类才会被加载
//        Son[] arr = new Son[5];  // 不会引起对应类加载
//        System.out.println(Son.M); // 类内静态常量在链接阶段就存在常量池中

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(parent);
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);


        System.out.println(Class.forName("ReflectLearn").getClassLoader());
        System.out.println(Class.forName("java.lang.Object").getClassLoader());
        System.out.println(System.getProperty("java.class.path"));
//        "D:\JDK8u301\jre\lib\charsets.jar;
//        D:\JDK8u301\jre\lib\deploy.jar;
//        D:\JDK8u301\jre\lib\ext\access-bridge-64.jar;
//        D:\JDK8u301\jre\lib\ext\cldrdata.jar;
//        D:\JDK8u301\jre\lib\ext\dnsns.jar;
//        D:\JDK8u301\jre\lib\ext\jaccess.jar;
//        D:\JDK8u301\jre\lib\ext\jfxrt.jar;
//        D:\JDK8u301\jre\lib\ext\localedata.jar;
//        D:\JDK8u301\jre\lib\ext\nashorn.jar;
//        D:\JDK8u301\jre\lib\ext\sunec.jar;
//        D:\JDK8u301\jre\lib\ext\sunjce_provider.jar;
//        D:\JDK8u301\jre\lib\ext\sunmscapi.jar;
//        D:\JDK8u301\jre\lib\ext\sunpkcs11.jar;
//        D:\JDK8u301\jre\lib\ext\zipfs.jar;
//        D:\JDK8u301\jre\lib\javaws.jar;
//        D:\JDK8u301\jre\lib\jce.jar;
//        D:\JDK8u301\jre\lib\jfr.jar;
//        D:\JDK8u301\jre\lib\jfxswt.jar;
//        D:\JDK8u301\jre\lib\jsse.jar;
//        D:\JDK8u301\jre\lib\management-agent.jar;
//        D:\JDK8u301\jre\lib\plugin.jar;
//        D:\JDK8u301\jre\lib\resources.jar;
//        D:\JDK8u301\jre\lib\rt.jar;
//        C:\Users\lenovo\Desktop\demo\out\production\demo;
//        D:\InetlliJ_IDEA\IntelliJ IDEA Community Edition 2021.2.2\lib\idea_rt.jar "

        Class aClass2 = Class.forName("Son");
        Son rew = (Son) aClass2.newInstance();

        Field[] fields = aClass2.getFields();
//        Field[] fields = aClass2.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Method[] methods = aClass2.getDeclaredMethods();
//        Method[] methods = aClass2.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println(aClass2.getDeclaredField("ff"));
        System.out.println(aClass2.getMethod("setF", int.class));
        System.out.println(aClass2.getMethod("getB"));

        Constructor[] constructors = aClass2.getDeclaredConstructors();
        for (Constructor constructor: constructors) {
            System.out.println(constructor);
        }

        Constructor constructor = aClass2.getDeclaredConstructor(int.class, int.class);
        constructor.setAccessible(true);
        Son son = (Son) constructor.newInstance(2, 3);
        System.out.println(son);

        Method set = aClass2.getMethod("setFf", int.class);
        set.invoke(son, 6);
        System.out.println(son.getFf());

        Field ff = aClass2.getDeclaredField("ff");
        ff.setAccessible(true);
        ff.set(son, 7);
        System.out.println(son.getFf());

//        testTime(son);

        Class<?> reflectLearn = Class.forName("ReflectLearn");
        Method test01 = reflectLearn.getDeclaredMethod("test01", Map.class, List.class);
        Method test02 = reflectLearn.getDeclaredMethod("test02");
        for (Type type1 : test01.getGenericParameterTypes()) {
            System.out.println(type1);
            if (type1 instanceof ParameterizedType) {
                for (Type type2 : ((ParameterizedType) type1).getActualTypeArguments()) {
                    System.out.println(type2);
                }
            }
        }
        System.out.println(test02.getGenericReturnType());
        if (test02.getGenericReturnType() instanceof ParameterizedType) {
            for (Type type2 : ((ParameterizedType) test02.getGenericReturnType()).getActualTypeArguments()) {
                System.out.println(type2);
            }
        }

        Class<?> student = Class.forName("Student");
        for (Annotation annotation : student.getAnnotations()) {
            System.out.println(annotation);
            System.out.println(((StuTable) annotation).value());
        }
        Annotation annotation = student.getAnnotation(StuTable.class);
        System.out.println(((StuTable) annotation).value());

        for (Field field : student.getDeclaredFields()) {
            for (Annotation annotation1 : field.getAnnotations()) {
                System.out.println(annotation1);
                System.out.println(((StuField) annotation1).name());
                System.out.println(((StuField) annotation1).type());
                System.out.println(((StuField) annotation1).length());
            }
        }

    }

    private void test01 (Map<String, Son> map, List<Father> list) {
        System.out.println("test01");
    }

    private Map<String, Son> test02() {
        System.out.println("test02");
        return null;
    }


    private static void testTime(Son son) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //        普通调用、反射调用、反射关闭权限检测调用
        long startTime, endTime;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i ++) {
            son.getFf();
        }
        endTime = System.currentTimeMillis();
        System.out.println("普通调用用时：" + (endTime - startTime) + "ms");
        Class c = son.getClass();
        Method getFf = c.getMethod("getFf");
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i ++) {
            getFf.invoke(son);
        }
        endTime = System.currentTimeMillis();
        System.out.println("反射调用用时：" + (endTime - startTime) + "ms");
        getFf.setAccessible(true);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i ++) {
            getFf.invoke(son);
        }
        endTime = System.currentTimeMillis();
        System.out.println("反射关闭权限调用用时：" + (endTime - startTime) + "ms");
    }

}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface StuField {
    String name ();
    String type ();
    int length ();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface StuTable {
    String value();
}

@StuTable("db_stu")
class Student {
    @StuField(name = "db_id", type = "int", length = 10)
    int id;
    @StuField(name = "db_age", type = "int", length = 10)
    int age;
    @StuField(name = "db_name", type = "String", length = 10)
    String name;

    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



class Father {
    static int b = 2;
    static {
        System.out.println("父类被加载");
    }
    private int f;
    public int k;

    public static int getB() {
        return b;
    }

    public static void setB(int b) {
        Father.b = b;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public Father() {
    }

    public Father(int f, int k) {
        this.f = f;
        this.k = k;
    }
}
class Son extends Father {
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int m = 100;
    static final int M = 1;
    private int ff;

    @Override
    public String toString() {
        return "Son{" +
                "ff=" + ff +
                ", kk=" + kk +
                '}';
    }

    public static int getM() {
        return m;
    }

    public static void setM(int m) {
        Son.m = m;
    }

    public int getFf() {
        return ff;
    }

    public void setFf(int ff) {
        this.ff = ff;
    }

    public int getKk() {
        return kk;
    }

    public void setKk(int kk) {
        this.kk = kk;
    }

    public int kk;

    public Son() {
    }

    private Son(int ff, int kk) {
        this.ff = ff;
        this.kk = kk;
    }
}



class AA {
    static {
        System.out.println("静态代码块");
        m = 100;
    }
    static int m = 10;
    AA() {
        System.out.println("AA的构造函数");
    }
}


class Person1 {
    String name;

    public Person1(String name) {
        this.name = name;
    }

    public Person1() {
        this.name = "";
    }
}

class Stu extends Person1 {
    String name;

    public Stu(String name, String name1) {
        super(name);
        this.name = name1;
    }

    public Stu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

enum te {fasdf, fasd, sfawee, wer}
class asd {
    te t;
    public asd(te t) {
        this.t = t;
        t = te.fasdf;
    }
}


@Target(value = {ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@interface my {
    String[] value();
    String[] name() default "sadf";
}

@my(value = {"qwerty", "Sfas"}, name = "asdf")
class test {
    @my("wqr")
    private int a;
    private int b;
    @Override
    public String toString() {
        return super.toString();
    }
    @SuppressWarnings("all")
    public void ts () {
        List ls = new ArrayList();
    }
}


class Solution8 {
    public int maxSubarraySumCircular(int[] nums) {
        int len = nums.length;
        int res = Integer.MIN_VALUE;
        int curSum = 0;
        int maxLen = 0;
        for (int i = 0; i < 2*len; i ++) {
            if (maxLen == len) {
                int j = i % len;
                curSum -= nums[j];
                maxLen --;
                j = (j + 1) % len;
                if (nums[j] < 0) {
                    while (j < len && nums[j] < 0) {
                        curSum -= nums[j];
                        maxLen --;
                        j = (j + 1) % len;
                    }
                }
            }
            if (curSum > 0) {
                curSum += nums[i % len];
                maxLen ++;
            }else {
                maxLen = 1;
                curSum = nums[i % len];
            }
            System.out.print(curSum + "\t");
            res = Math.max(res, curSum);
        }
        System.out.println();
        for (int i = 0; i < len ; i ++) {
            System.out.print(nums[i] + "\t");
        }
        for (int i = 0; i < len ; i ++) {
            System.out.print(nums[i] + "\t");
        }
        return res;
    }
}





class Solution7 {
    private boolean valid(String input) {
        if (input.startsWith("0")) return false;
        int len = input.length();
        for (int i = 0; i < len; i ++) {
            if (input.charAt(i) > '9' && input.charAt(i) < '0')
                return false;
        }
        return true;
    }

    private void recur(String s, int idx, int dotNum, StringBuffer path, List<String> res) {
        if (dotNum >= 4 || idx >= s.length()) {
            if (dotNum >= 4 && idx >= s.length()) {
                String item = path.toString();
                res.add(item.substring(0, item.length()-1));
            }
            return ;
        }
        String tem;
        int bound;
        bound = Math.min(idx+1, s.length());
        tem = s.substring(idx, bound);
        if (valid(tem) && Integer.valueOf(tem) < 256 && Integer.valueOf(tem) >= 0) {
            path.append(tem + ".");
            recur(s, bound, dotNum+1, path, res);
            path.delete(idx, bound);
        }
        bound = Math.min(idx+2, s.length());
        tem = s.substring(idx, bound);
        if (valid(tem) && Integer.valueOf(tem) < 256 && Integer.valueOf(tem) >= 0) {
            path.append(tem + ".");
            recur(s, bound,dotNum+1, path, res);
            path.delete(idx, bound);
        }
        bound = Math.min(idx+3, s.length());
        tem = s.substring(idx, bound);
        if (valid(tem) && Integer.valueOf(tem) < 256 && Integer.valueOf(tem) >= 0) {
            path.append(tem + ".");
            recur(s, bound,dotNum+1, path, res);
            path.delete(idx, bound);
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() > 12 || s.length() < 4) return res;
        recur(s, 0,0, new StringBuffer(), res);
        return res;
    }
}




class Solution6 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length, len2 = v2.length;
        int i = 0, j = 0, a, b;
        while (i < len1 || j < len2) {
            if (i < len1) a = Integer.parseInt(v1[i]); else a = 0;
            if (i < len2) b = Integer.parseInt(v2[j]); else b = 0;
            if (a != b) return a - b;
            i ++; j ++;
        }
        return 0;
    }
}


class Solution5 {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        Queue<Integer> transcation = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int curMin = prices[0];
        int i = 1;
        while (i < len) {
            curMin = Math.min(curMin, prices[i]);
            if (i == len - 1 || prices[i] > prices[i+1]) {
                transcation.add(prices[i] - curMin);
                curMin = prices[i];
            }
            i ++;
        }
        int res = 0;
        for (int j = 0; j < Math.min(k, transcation.size()); j ++)
            res += transcation.poll();
        return res;
    }
}



class Solution4 {
    public int[][] generateMatrix(int n) {
        int[][] oriention = {{0,1},{1,0},{0,-1},{-1,0}};
        int[][] res = new int[n][n];
        int oriIdx = 0;
        int x = 0, y = 0;
        for (int i = 1; i <= n * n; i ++) {
            System.out.println(x + " " + y);
            res[x][y] = i;
            int newx = x + oriention[oriIdx][0];
            int newy = y + oriention[oriIdx][1];
            if (newx >= n || newy >= n || newx < 0 || newy < 0 || res[newx][newy] != 0) {
                oriIdx = (oriIdx + 1) % 4;
                x += oriention[oriIdx][0];
                y += oriention[oriIdx][1];
            } else {
                x = newx;
                y = newy;
            }
        }
        return res;
    }
}



class Solution3 {
    public int trap(int[] height) {
        if (height.length < 3) return 0;
        int i = 1, j = height.length - 2;
        int lmax = height[0], rmax = height[height.length-1];
        int res = 0;
        while (i < j) {
            if (lmax < rmax) {
                System.out.println(i + " " + height[i]);
                res += Math.max(0, lmax - height[i]);
                lmax = Math.max(lmax, height[i]);
                System.out.println(res);
                i ++;
            } else {
                System.out.println(j + " " + height[j]);
                res += Math.max(0, rmax - height[j]);
                rmax = Math.max(rmax, height[j]);
                System.out.println(res);
                j --;
            }
        }
        return res;
    }
}





class Solution1 {
    private int maxArea = 0;
    private List<String> res = new ArrayList<String>();

    private void updateRes(Triee[] ttem, List<String> path) {
        boolean valid = true;
        for (int i = 0; i < path.get(0).length(); i ++)
            if (! ttem[i].isEnd) valid = false;
        if (valid) {
            if (path.size()*path.get(0).length()>maxArea) {
                maxArea = path.size()*path.get(0).length();
                List<String> rres = new ArrayList<String>(path);
                res = rres;
            }
        }
    }

    private void recur(String[] words, Triee[] ttem, int a, int b, int idx, int len, List<String> path) {
        if (idx >= len) return ;
        System.out.print("path : ");
        for (String it : path)
            System.out.print(it + " ");
        System.out.println();
        for (int i = a; i < b; i ++) {
            System.out.println(words[i]);
            boolean have = true;
            Triee[] newttm = new Triee[ttem.length];
            for (int q = 0; q < ttem.length; q ++) newttm[q] = ttem[q];
            for (int k = 0; k < words[i].length(); k ++)
                if (newttm[k].next[words[i].charAt(k)-'a'] == null) have = false;
                else newttm[k] = newttm[k].next[words[i].charAt(k)-'a'];
            if (have) {
                path.add(words[i]);
//                for (String it : path)
//                    System.out.print(it + " ");
//                System.out.println();
                updateRes(newttm, path);
                recur(words, newttm, a, b, idx + 1, len, path);
                path.remove(path.size()-1);
            }
        }
    }

    public String[] maxRectangle(String[] words) {
        Map<Integer, List<String>> arr = new HashMap<>();
        int len = 0;
        for (String item : words) {
            List<String> a = arr.getOrDefault(item.length(), new ArrayList<String>());
            a.add(item);
            arr.put(item.length(), a);
            len = Math.max(len, item.length());
        }
        int idx = 0;
        for (Map.Entry<Integer, List<String>> en : arr.entrySet())
            for (String it : en.getValue())
                words[idx++] = it;
        Triee tem = new Triee();
        for (String word : words)
            tem.insert(word);
        int i = 0, j = 0;
        while (i < words.length) {
            while (j < words.length && words[i].length() == words[j].length()) j ++;
            List<String> path = new ArrayList<>();
            Triee[] ttem = new Triee[words[i].length()];
            for (int n = 0; n < words[i].length(); n ++) ttem[n] = tem;
            recur(words, ttem, i, j, 0, len, path);
            i = j;
        }
        String[] newRes = new String[res.size()];
        return res.toArray(newRes);
    }
}

class Triee {
    public Triee[] next;
    public boolean isEnd;
    public Triee() {
        next = new Triee[26];
        isEnd = false;
    }
    public void insert(String item) {
        int len = item.length();
        Triee iter = this;
        for (int i = 0; i < len; i ++) {
            char t = item.charAt(i);
            if (iter.next[t - 'a'] == null)
                iter.next[t - 'a'] = new Triee();
            iter = iter.next[t - 'a'];
        }
        iter.isEnd = true;
    }
}







class Solution {
    public int[] shortestSeq(int[] big, int[] small) {
        int i = 0, j = 0;
        Set<Integer> tem = new HashSet<>();
        for (int a = 0; a < small.length; a ++)
            tem.add(small[a]);
        while (j < big.length && tem.size() > 0) {
            if (tem.contains(big[j])) tem.remove(big[j]);
            j ++;
        }
        if (j == big.length) return new int[0];
        int res = j - i; int rres[] = new int[2]; rres[0] = i; rres[1] = j - 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a = 0; a < small.length; a ++) {
            int cnt = map.getOrDefault(small[a], 0);
            map.put(small[a], cnt + 1);
        }
        while (j < big.length) {
            System.out.println(i + " " + j + " : " + big[i] + " " + big[j]);

            if (map.containsKey(big[i]) && map.get(big[i]) == 1) {
                while (j < big.length && big[j] != big[i]) j ++;
                if (j == big.length) return rres;
                else {
                    i ++; j ++;
                    if (j-i > res) {
                        res = j - i;
                        rres[0] = i;
                        rres[1] = j - 1;
                    }
                }
            } else if (map.containsKey(big[i]) && map.get(big[i]) > 1) {
                map.put(big[i], map.get(big[i])-1);
                i ++;
                if (j-i > res) {
                    res = j - i;
                    rres[0] = i;
                    rres[1] = j - 1;
                }
            } else {
                i ++;
                if (j-i > res) {
                    res = j - i;
                    rres[0] = i;
                    rres[1] = j - 1;
                }
            }
        }
        return rres;
    }
}

