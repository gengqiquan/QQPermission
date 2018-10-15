package com.gengqiquan.permission;

import android.content.pm.PermissionGroupInfo;

import java.util.Set;

/**
 * Created by gengqiquan on 2018/10/12.
 */

public interface TipsProxy {
    String makeText(Set<PermissionGroupInfo> groupInfos);
}
