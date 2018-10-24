package com.gengqiquan.permission;

import java.util.List;

public interface IResult {
    void permit();

    /**
     * 拒绝权限
     *
     * @author gengqiquan
     * @date 2018/10/11 下午2:24
     */
    void refuse(List<String> list);
}
