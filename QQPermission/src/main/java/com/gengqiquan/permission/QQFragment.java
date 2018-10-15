package com.gengqiquan.permission;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.support.annotation.NonNull;

/**
 * Created by gengqiquan on 2017/7/3.
 */

public class QQFragment extends Fragment {

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        QQPermission.post(permissions, grantResults);
        request = null;
        this.getActivity().getFragmentManager().beginTransaction().detach(this).commit();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    String[] request;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        request = getArguments().getStringArray("permission");
        if (request != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(request, 0);
            }
        }
    }

}
