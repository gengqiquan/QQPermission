package com.gengqiquan.permission;

import android.app.Dialog;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by 耿 on 2016/7/11.
 */
class DialogBuilder {
    private Context context;
    private View mLayout;
    private boolean mCancelable = false;
    private boolean justMessage = false;
    private boolean darkBack = false;
    Dialog dialog;

    public DialogBuilder(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = inflater.inflate(R.layout.qq_permission_dialog_layout_qqg, null);


    }
//
//    //能否返回键取消
//    public DialogBuilder setCancelable(Boolean boolean1) {
//        this.mCancelable = boolean1;
//        return this;
//    }

//    public DialogBuilder title(int title) {
//        ((TextView) mLayout.findViewById(R.id.title)).setText(title);
//        return this;
//    }
//
//    public DialogBuilder title(String title) {
//        ((TextView) mLayout.findViewById(R.id.title)).setText(title);
//        return this;
//    }
//
//    public DialogBuilder titleVisble(int v) {
//        ((TextView) mLayout.findViewById(R.id.title)).setVisibility(v);
//        return this;
//    }
//
//    public DialogBuilder titleColor(int color) {
//        ((TextView) mLayout.findViewById(R.id.title)).setTextColor(context.getResources().getColor(color));
//
//        return this;
//    }
//
//    /**
//     * 弹窗标题字体大小修改
//     *
//     * @author hsh
//     * @time 2017/5/23 023 上午 10:24
//     * @version 1.7.6
//     */
//    public DialogBuilder titleSize(float size) {
//        ((TextView) mLayout.findViewById(R.id.title)).setTextSize(size);
//        return this;
//    }
//
//    public DialogBuilder titleBackColor(int color) {
//        mLayout.findViewById(R.id.title_back).setBackgroundColor(context.getResources().getColor(color));
//        return this;
//    }

    public DialogBuilder setSureOnClickListener(View.OnClickListener listener) {
        ((TextView) mLayout.findViewById(R.id.tv_sure)).setOnClickListener(listener);
        return this;
    }

    public DialogBuilder setCancelOnClickListener(View.OnClickListener listener) {
        ((TextView) mLayout.findViewById(R.id.tv_cancel)).setOnClickListener(listener);
        return this;
    }

    public DialogBuilder setSettingOnClickListener(View.OnClickListener listener) {
        ((TextView) mLayout.findViewById(R.id.tv_setting)).setOnClickListener(listener);
        return this;
    }

    public DialogBuilder setApplyOnClickListener(View.OnClickListener listener) {
        ((TextView) mLayout.findViewById(R.id.tv_apply)).setOnClickListener(listener);
        return this;
    }

    public DialogBuilder message(String message) {
        ((TextView) mLayout.findViewById(R.id.tv_message)).setText(message);
        return this;
    }
//
//    public DialogBuilder darkBack() {
//        darkBack = true;
//        return this;
//    }

    public DialogBuilder message(Spanned message) {
        ((TextView) mLayout.findViewById(R.id.tv_message)).setText(message);
        return this;
    }

//    public DialogBuilder messageGravit(int gravity) {
//        ((LinearLayout) mLayout.findViewById(R.id.tv_message)).setGravity(gravity);
//        return this;
//    }

    public DialogBuilder message(SpannableString message) {
        ((TextView) mLayout.findViewById(R.id.tv_message)).setText(message);
        return this;
    }

    public DialogBuilder showApply() {
        ((TextView) mLayout.findViewById(R.id.tv_apply)).setVisibility(View.VISIBLE);
        ((TextView) mLayout.findViewById(R.id.tv_setting)).setVisibility(View.GONE);
        ((TextView) mLayout.findViewById(R.id.tv_sure)).setVisibility(View.GONE);
        return this;
    }

    public DialogBuilder showSure() {
        ((TextView) mLayout.findViewById(R.id.tv_apply)).setVisibility(View.GONE);
        ((TextView) mLayout.findViewById(R.id.tv_setting)).setVisibility(View.VISIBLE);
        ((TextView) mLayout.findViewById(R.id.tv_sure)).setVisibility(View.VISIBLE);
        return this;
    }

    public DialogBuilder showSetting() {
        ((TextView) mLayout.findViewById(R.id.tv_apply)).setVisibility(View.GONE);
        ((TextView) mLayout.findViewById(R.id.tv_setting)).setVisibility(View.VISIBLE);
        ((TextView) mLayout.findViewById(R.id.tv_sure)).setVisibility(View.GONE);
        return this;
    }

    public void shake() {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.qq_permission_shake_anim);
        mLayout.findViewById(R.id.tv_message).startAnimation(anim);
    }
//
//    public DialogBuilder message(int message) {
//        ((TextView) mLayout.findViewById(R.id.message)).setText(message);
//        return this;
//    }
//
//    public DialogBuilder messageColor(int color) {
//        ((TextView) mLayout.findViewById(R.id.message)).setTextColor(context.getResources().getColor(color));
//        return this;
//    }

//    public DialogBuilder sureTextColor(int color) {
//        ((TextView) mLayout.findViewById(R.id.sure)).setTextColor(context.getResources().getColor(color));
//        return this;
//    }
//
//    public DialogBuilder cancelTextColor(int color) {
//        ((TextView) mLayout.findViewById(R.id.cancel)).setTextColor(context.getResources().getColor(color));
//        return this;
//    }
//
//    public DialogBuilder cancelText(String str) {
//        ((TextView) mLayout.findViewById(R.id.cancel)).setText(str);
//        return this;
//    }
//
//    //切换内容view
//    public DialogBuilder setView(View v) {
//        ((LinearLayout) mLayout.findViewById(R.id.content)).removeAllViews();
//        ((LinearLayout) mLayout.findViewById(R.id.content)).addView(v);
//        return this;
//    }

//
//    public DialogBuilder setContentMiniheight(int height) {
//        mLayout.findViewById(R.id.content).setMinimumHeight(height);
//        return this;
//    }
//
//    //确定按钮文本
//    @Deprecated
//    public DialogBuilder SureText(String str) {
//        sureText(str);
//        return this;
//    }
//
//    public DialogBuilder sureText(String str) {
//        ((TextView) mLayout.findViewById(R.id.sure)).setText(str);
//        return this;
//    }
//
//    public DialogBuilder setView(View v, LinearLayout.LayoutParams params) {
//        ((LinearLayout) mLayout.findViewById(R.id.content)).removeAllViews();
//        ((LinearLayout) mLayout.findViewById(R.id.content)).addView(v, params);
//        return this;
//    }
//
//    public DialogBuilder setView(int res) {
//        ((LinearLayout) mLayout.findViewById(R.id.content)).removeAllViews();
//        View v = mLayout.inflate(context, res, (LinearLayout) mLayout.findViewById(R.id.content));
//        return this;
//    }
//
//    public DialogBuilder showCancelButton(boolean isShow) {
//        if (!isShow)
//            mLayout.findViewById(R.id.cancel).setVisibility(View.GONE);
//        return this;
//    }
//
//    public DialogBuilder noTitle() {
//        mLayout.findViewById(R.id.title_back).setVisibility(View.GONE);
//        return this;
//    }
//
//    public DialogBuilder messageSize(float size) {
//        ((TextView) mLayout.findViewById(R.id.message)).setTextSize(size);
//        return this;
//    }
//
//    public DialogBuilder justMessageDialog() {
//        justMessage = true;
//        return this;
//    }


    public Dialog builder() {
        dialog = new Dialog(context, R.style.qq_permission_dialog_style);
        dialog.setCancelable(mCancelable);
//        if (darkBack) {
//            dialog.getWindow().getAttributes().dimAmount = 0.5f;
//        } else {
//        dialog.getWindow().getAttributes().dimAmount = 1f;
//        }

        dialog.setContentView(mLayout);
        return dialog;
    }
}
