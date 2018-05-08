package com.example.app.expensetracker;

import android.app.Activity;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Utils {


        public static void Toast(final Activity activity, String title, String msg, int dialogType) {
            if (activity == null) return;
            new SweetAlertDialog(activity, dialogType).setTitleText(title).setConfirmText(activity.getString(R.string.dialog_ok)).setContentText(msg).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    sweetAlertDialog.dismissWithAnimation();
                }
            }).show();

        }

    public static void Toast(final Activity activity, final Runnable run, String title, String msg, int dialogType) {

        new SweetAlertDialog(activity, dialogType).setTitleText(title).setContentText(msg).setConfirmText(activity.getResources().getString(R.string.yes)).setCancelText(activity.getResources().getString(R.string.cancel)).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                run.run();
            }
        })

                .show();
    }
}
