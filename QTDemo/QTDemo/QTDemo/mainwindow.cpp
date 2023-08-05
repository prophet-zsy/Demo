#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "stdio.h"
#include "iostream"
using namespace std;

#include "QProcess"
#include "QMessageBox"
#include <QLabel>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    //工具栏中创建打开文件
    QAction *actOpen = new QAction(QIcon(":/img/icons/open.bmp"), tr("打开文件(&O)"), this);
    ui->toolBar->addAction(actOpen);
    QAction *actOpen2 = new QAction(QIcon(":/img/icons/open.bmp"), tr("打开文件(&O)"), this);
    ui->toolBar->addAction(actOpen2);
    ui->toolBar->setToolButtonStyle(Qt::ToolButtonTextUnderIcon);//设置文字图标下
    QLabel *infoLabel = new QLabel;


    /// 信号与槽函数 的 三种绑定方式
    connect(ui->cmdlineEdit, SIGNAL(returnPressed()), this, SLOT(on_pushButton_3_clicked()));  //敲回车等同于按确定按钮
    connect(ui->pushButton_6, &QPushButton::clicked, this, &MainWindow::on_cancel_button_clicked);  // 取消 推出弹窗
    connect(ui->pushButton_8, &QPushButton::clicked, [this](){                // 如果槽函数内容比较简单，则可以通过lambda 表达式直接绑定
       QMessageBox::information(this, "弹窗标题", "点击浏览弹出的");
    });
}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_pushButton_3_clicked()
{
    cout << "MainWindow::on_pushButton_3_clicked" << endl;

//    QString program = ui->textEdit->toPlainText();  // 界面上第二个输入框，但没有returnPressed信号
    QString program = ui->cmdlineEdit->text();
    QProcess* process = new QProcess();
    process->start(program);
}

void MainWindow::on_cancel_button_clicked()
{
    cout << "MainWindow::on_cancel_button_clicked" << endl;
    this->close();
}
