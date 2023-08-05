//
// Created by SM2887 on 2022/12/30.
//

#ifndef CPPMINGWDEMO_DEMOTEMPLATE_H
#define CPPMINGWDEMO_DEMOTEMPLATE_H


class CDemoTemplate {
public:
    CDemoTemplate();
    CDemoTemplate(int tmpVar);
    ~CDemoTemplate();

    void hello();

private:
    int var = 0;

};

template<class T>
class MyClass {
public:
    MyClass();
    MyClass(T tmpVar);

public:
    static int startVar;
};



#endif //CPPMINGWDEMO_DEMOTEMPLATE_H
