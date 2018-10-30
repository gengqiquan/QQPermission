package com.gengqiquan.permission;

import java.util.List;

/**
 * Created by gengqiquan on 2017/7/4.
 */

 class QQResult implements IResult {
    Func1 func1;
    Func2 func2;

    public QQResult(Func1 func1, Func2 func2) {
        this.func1 = func1;
        this.func2 = func2;
    }

    /**
     * 许可权限
     *
     * @author gengqiquan
     * @date 2018/10/11 下午2:24
     */
    @Override
    public void permit() {
        if (func1 != null) {
            func1.permit();
        }
    }

    /**
     * 拒绝权限
     *
     * @author gengqiquan
     * @date 2018/10/11 下午2:24
     */
    @Override
    public void refuse(List<String> list) {
        if (func2 != null)
            func2.refuse(list);
    }
}