/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.12.6
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTextEdit>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QAction *actDelete;
    QWidget *centralwidget;
    QPushButton *pushButton_3;
    QPushButton *pushButton_6;
    QPushButton *pushButton_8;
    QLabel *label;
    QTextEdit *textEdit;
    QLabel *label_2;
    QLineEdit *cmdlineEdit;
    QStatusBar *statusbar;
    QToolBar *toolBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(800, 600);
        actDelete = new QAction(MainWindow);
        actDelete->setObjectName(QString::fromUtf8("actDelete"));
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        pushButton_3 = new QPushButton(centralwidget);
        pushButton_3->setObjectName(QString::fromUtf8("pushButton_3"));
        pushButton_3->setGeometry(QRect(230, 430, 89, 28));
        QFont font;
        font.setPointSize(10);
        pushButton_3->setFont(font);
        pushButton_3->setFocusPolicy(Qt::StrongFocus);
        pushButton_3->setLayoutDirection(Qt::LeftToRight);
        pushButton_6 = new QPushButton(centralwidget);
        pushButton_6->setObjectName(QString::fromUtf8("pushButton_6"));
        pushButton_6->setGeometry(QRect(340, 430, 88, 28));
        pushButton_8 = new QPushButton(centralwidget);
        pushButton_8->setObjectName(QString::fromUtf8("pushButton_8"));
        pushButton_8->setGeometry(QRect(440, 430, 88, 28));
        label = new QLabel(centralwidget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setGeometry(QRect(220, 350, 81, 31));
        QFont font1;
        font1.setPointSize(12);
        label->setFont(font1);
        textEdit = new QTextEdit(centralwidget);
        textEdit->setObjectName(QString::fromUtf8("textEdit"));
        textEdit->setGeometry(QRect(560, 350, 221, 31));
        QFont font2;
        font2.setFamily(QString::fromUtf8("72"));
        textEdit->setFont(font2);
        textEdit->setTabStopWidth(78);
        textEdit->setAcceptRichText(true);
        label_2 = new QLabel(centralwidget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setGeometry(QRect(310, 260, 141, 31));
        QFont font3;
        font3.setPointSize(15);
        label_2->setFont(font3);
        label_2->setMidLineWidth(0);
        cmdlineEdit = new QLineEdit(centralwidget);
        cmdlineEdit->setObjectName(QString::fromUtf8("cmdlineEdit"));
        cmdlineEdit->setGeometry(QRect(300, 350, 211, 31));
        QFont font4;
        font4.setFamily(QString::fromUtf8("72"));
        font4.setStyleStrategy(QFont::NoAntialias);
        cmdlineEdit->setFont(font4);
        cmdlineEdit->setMouseTracking(true);
        MainWindow->setCentralWidget(centralwidget);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        MainWindow->setStatusBar(statusbar);
        toolBar = new QToolBar(MainWindow);
        toolBar->setObjectName(QString::fromUtf8("toolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, toolBar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", nullptr));
        actDelete->setText(QApplication::translate("MainWindow", "\345\210\240\351\231\244\350\241\214", nullptr));
#ifndef QT_NO_TOOLTIP
        actDelete->setToolTip(QApplication::translate("MainWindow", "\345\210\240\351\231\244\350\241\214", nullptr));
#endif // QT_NO_TOOLTIP
#ifndef QT_NO_SHORTCUT
        actDelete->setShortcut(QApplication::translate("MainWindow", "Ctrl+D", nullptr));
#endif // QT_NO_SHORTCUT
        pushButton_3->setText(QApplication::translate("MainWindow", "\347\241\256\345\256\232", nullptr));
        pushButton_6->setText(QApplication::translate("MainWindow", "\345\217\226\346\266\210", nullptr));
        pushButton_8->setText(QApplication::translate("MainWindow", "\346\265\217\350\247\210", nullptr));
        label->setText(QApplication::translate("MainWindow", "\346\211\223\345\274\200\357\274\232", nullptr));
        textEdit->setPlaceholderText(QApplication::translate("MainWindow", "notepad", nullptr));
        label_2->setText(QApplication::translate("MainWindow", "\350\257\267\350\276\223\345\205\245\345\221\275\344\273\244", nullptr));
        cmdlineEdit->setPlaceholderText(QApplication::translate("MainWindow", "notepad", nullptr));
        toolBar->setWindowTitle(QApplication::translate("MainWindow", "toolBar", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
