package JDBC;


// Bean类，用来和sql表中的一行数据进行映射
// 因为mysql中的所有类型都有可能为null，而java中只有引用数据类型才可以为null值，所以Bean类中的属性只能使用包装类，不能使用基本数据类型
public class Student {
    int id;
    String name;
    int chinese;
    int math;
    int english;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", chinese=" + chinese +
                ", math=" + math +
                ", english=" + english +
                "}\n";
    }
}
