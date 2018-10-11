package com.gengqiquan.library;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

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

        private Builder(Activity t, String[] p) {
            activity = t;
            permissions = p;
        }

        public void requestPermissions(Func1 func1) {
            requestPermissions(new QQResult(func1, null));
        }

        public void requestPermissions(Func1 func1, Func2 func2) {
            requestPermissions(new QQResult(func1, func2));
        }

        public void requestPermissions(final QQResult r) {
            result = r;
            if (permissions == null) {
                throw new RuntimeException("permission can not be null");
            }
            subject.setObserver(new Observer() {
                @Override
                public void update(Map<String, Boolean> o) {
                    if (checkPermit(o)) {
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
                Map<String, Boolean> map = new HashMap<>();
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

        //        boolean checkEnable = false;
        Dialog dialog;
        String tips = "当前功能需要您允许：{0}\n请前往手机的\"设置-应用信息-权限\"中开启权限\n否则您将无法使用该功能";

        boolean checkPermit(Map<String, Boolean> map) {
            if (!map.containsValue(false)) {
                return true;
            }
            if (dialog == null) {
                showDialog();
            }
            String text = "";
            for (String key : map.keySet()) {
                if (!map.get(key)) {
                    try {
                        PackageManager pm = activity.getPackageManager();
                        PermissionInfo n = pm.getPermissionInfo(key, 0);
                        PermissionGroupInfo g = pm.getPermissionGroupInfo(n.group, 0);
                        text += "\n" + "-" + g.loadDescription(pm);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            dialogBuilder.message(MessageFormat.format(tips, text));
            return false;
        }

        DialogBuilder dialogBuilder;

        void showDialog() {
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
                            result.refuse();
                        }
                    }).setSettingOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogBuilder.showSureButton();
                            new PermissionPageUtils(activity).jumpPermissionPage();
                        }
                    });
            dialog = dialogBuilder.builder();
            dialog.show();
        }
    }

    protected static void post(@NonNull String[] permissions, @NonNull int[] grantResults) {
        Map<String, Boolean> map = new HashMap<>();
        for (int i = 0, l = permissions.length; i < l; i++) {
            map.put(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);
        }
        subject.post(map);
    }


}
