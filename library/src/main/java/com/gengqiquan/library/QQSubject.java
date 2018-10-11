package com.gengqiquan.library;


import java.util.Map;

class QQSubject {
    public QQSubject() {
    }


    protected void post( Map<String, Boolean> result) {
        observer.update(result);
    }


    protected void setObserver(Observer observer) {
        this.observer = observer;
    }

    Observer observer;
}



