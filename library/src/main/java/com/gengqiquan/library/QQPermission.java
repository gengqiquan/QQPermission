package com.gengqiquan.library;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArraySet;
import android.util.ArrayMap;
import android.view.View;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gengqiquan on 2018/10/10.
 */

public class QQPermission {
    static QQSubject subject = new QQSubject();

    public static Builder with(Activity t, String... p) {
        return new Builder(t, p);
    }

    public static class Builder {
        Activity activity;
        String[] permissions;
        QQResult result;
        TipsProxy tipsProxy;

        private Builder(Activity t, String[] p) {
            activity = t;
            permissions = p;
        }

        public void tipsProxy(TipsProxy tipsProxy) {
            this.tipsProxy = tipsProxy;
        }

        public void requestPermissions(Func1 func1) {
            requestPermissions(new QQResult(func1, null));
        }

        public void requestPermissions(Func1 func1, Func2 func2) {
            requestPermissions(new QQResult(func1, func2));
        }


        @SuppressLint("NewApi")
        public void requestPermissions(final QQResult r) {
            result = r;
            if (tipsProxy == null) {
                tipsProxy = new TipsProxy() {

                    String tips = "当前功能需要您允许：{0}\n请前往手机的\"设置-应用信息-权限\"中开启权限\n否则您将无法使用该功能";

                    @Override
                    public String makeText(Set<PermissionGroupInfo> groupInfos) {

                        PackageManager pm = activity.getPackageManager();
                        StringBuilder text = new StringBuilder();

                        for (PermissionGroupInfo groupInfo : groupInfos) {
                            CharSequence d = groupInfo.loadDescription(pm);
                            if (!text.toString().contains(d)) {
                                text.append("\n" + "-").append(d);
                            }
                        }
                        return MessageFormat.format(tips, text.toString());
                    }
                };
            }
            if (permissions == null) {
                throw new RuntimeException("permission can not be null");
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                result.permit();
                return;
            }
            subject.subscribe(new Observer() {
                @Override
                public void update(Map<String, Boolean> o) {
                    if (checkPermit(o)) {
                        subject.unSubscribe();
                        result.permit();
                    }
                }
            });
            boolean need = false;
            for (int i = 0, l = permissions.length; i < l; i++) {
                int p = ContextCompat.checkSelfPermission(activity, permissions[i]);
                if (p != PackageManager.PERMISSION_GRANTED) {
                    need = true;
                    break;
                }
            }
            if (!need) {
                Map<String, Boolean> map = new ArrayMap<>();
                for (int i = 0, l = permissions.length; i < l; i++) {
                    map.put(permissions[i], true);
                }
                subject.post(map);
            }

            Bundle b = new Bundle();
            b.putStringArray("permission", permissions);
            final QQFragment appFragment = new QQFragment();
            appFragment.setArguments(b);

            activity.getFragmentManager()
                    .beginTransaction().replace(android.R.id.content, appFragment)
                    .commitAllowingStateLoss();
        }

        Dialog dialog;

        @SuppressLint("NewApi")
        List<String> refuseList = new ArrayList<>();

        boolean checkPermit(Map<String, Boolean> map) {
            if (!map.containsValue(false)) {
                return true;
            }
            if (dialog == null) {
                createDialog();
            }
            if (!dialog.isShowing() && !activity.isFinishing()) {
                dialog.show();
            }

            if (!refuseList.isEmpty()) {
                refuseList.clear();
            }
            PackageManager pm = activity.getPackageManager();
            Set<PermissionGroupInfo> groupInfos = new ArraySet<>();

            for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                String key = entry.getKey();
                boolean value = entry.getValue();
                if (!value) {
                    refuseList.add(key);
                    try {
                        PermissionInfo n = pm.getPermissionInfo(key, 0);
                        PermissionGroupInfo g = pm.getPermissionGroupInfo(n.group, 0);
                        groupInfos.add(g);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            dialogBuilder.message(tipsProxy.makeText(groupInfos));
            return false;
        }

        DialogBuilder dialogBuilder;

        void createDialog() {
            dialogBuilder = new DialogBuilder(activity)
                    .setSureOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            requestPermissions(result);
                        }
                    })
                    .setCancelOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            subject.unSubscribe();
                            result.refuse(refuseList);
                        }
                    }).setSettingOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogBuilder.showSureButton();
                            new PermissionPageUtils(activity).jumpPermissionPage();
                        }
                    });
            dialog = dialogBuilder.builder();
        }
    }

    @SuppressLint("NewApi")
    protected static void post(@NonNull String[] permissions, @NonNull int[] grantResults) {
        Map<String, Boolean> map = new ArrayMap<>();
        for (int i = 0, l = permissions.length; i < l; i++) {
            map.put(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);
        }
        subject.post(map);
    }


}
