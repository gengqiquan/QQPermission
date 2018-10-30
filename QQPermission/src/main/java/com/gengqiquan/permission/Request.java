package com.gengqiquan.permission;


import java.util.Map;

class Request {
    protected void post(Map<String, Boolean> result) {
        observer.update(result);
    }


    protected void subscribe(Observer observer) {
        this.observer = observer;
    }

    Observer observer;
    String[] permissions;

    public Request(String[] permissions) {
        this.permissions = permissions;
    }
}



