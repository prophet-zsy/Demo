package com.example.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Flow;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;


/**
 *  rxJava是一个基于事件的异步框架，主要原理为观察者模式
 *  https://www.jianshu.com/p/a406b94f3188【Carson带你学Android：这是一篇清晰易懂的Rxjava入门教程】
 *  以下是使用示例
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      示例1
        Observable.just("hello world  1 !")
                .subscribe(s -> System.out.println(s));

//      示例2
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                e.onNext("hello world  2 !");  // 通过ObservableEmitter来发射内容
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("Consumer accept", (String) o);
            }
        });

//      示例3
        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> e) throws Exception {
                e.onNext("hello world  3 !");
            }
        }, BackpressureStrategy.DROP)
            .subscribe(new Subscriber<Object>() {
                @Override
                public void onSubscribe(Subscription s) {
                    s.request(Long.MAX_VALUE);  //观察者设置接收事件的数量,如果不设置接收不到事件
                }

                @Override
                public void onNext(Object o) {
                    Log.i("Subscriber onNext", (String) o);
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {

                }
            });

//      示例4
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello World  4 !");
            }
        }).observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io())
          .subscribe(new Observer<String>() {
              @Override
              public void onSubscribe(Disposable d) {

              }

              @Override
              public void onNext(String s) {
                  Log.i("Observer onNext", s);
              }

              @Override
              public void onError(Throwable e) {

              }

              @Override
              public void onComplete() {

              }
          });
    }
}
