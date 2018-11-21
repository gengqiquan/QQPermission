package com.gengqiquan.permission;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by gengqiquan on 2017/7/3.
 */

public class QQFragment extends Fragment {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (request != null && request.hashCode() == requestCode) {
            Map<String, Boolean> map = new LinkedHashMap<>();
            for (int i = 0, l = permissions.length; i < l; i++) {
                map.put(permissions[i], grantResults[i] == PackageManager.PERMISSION_GRANTED);
            }
            request.post(map);
            request = null;
        }
        this.getActivity().getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    Request request;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (request != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(request.permissions, request.hashCode());
            }
        }
    }

    public void setRequest(Request request) {
        this.request = request;
    }

}
