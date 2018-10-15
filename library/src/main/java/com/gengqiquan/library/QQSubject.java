package com.gengqiquan.library;


import java.util.Map;

class QQSubject {
    public QQSubject() {
    }


    protected void post(Map<String, Boolean> result) {
        observer.update(result);
    }


    protected void subscribe(Observer observer) {
        this.observer = observer;
    }

    protected void unSubscribe() {
        this.observer = null;
    }

    Observer observer;
}



