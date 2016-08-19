package com.xiaozhi.bottomdialog.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.xiaozhi.bottomdialog.R;
import com.xiaozhi.bottomdialog.bean.ShareBean;

/**
 * Created by liudezhi on 16/8/19.
 */
public class ShareBottomDialog extends Dialog implements View.OnClickListener {

    private Activity mActivity;
    private ShareBean shareBean;
    private UMShareListener shareListener;

    public ShareBottomDialog(Activity mActivity, ShareBean shareBean) {
        super(mActivity, R.style.dialog_bottom);
        this.mActivity = mActivity;
        this.shareBean = shareBean;
        initShare();
    }

    public ShareBottomDialog(Fragment mFragment, ShareBean shareBean) {
        super(mFragment.getActivity(), R.style.dialog_bottom);
        this.mActivity = mFragment.getActivity();
        this.shareBean = shareBean;
        initShare();
    }

    private void initShare(){
        shareListener = new UMShareListener() {
            @Override
            public void onResult(SHARE_MEDIA platform) {
                //Toast.makeText(mActivity, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                //Toast.makeText(mActivity, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                //Toast.makeText(mActivity, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom);
        initLocation();
        initView();
    }

    private void initLocation(){
        Window Window = this.getWindow();
        Window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = Window.getAttributes();
        params.y = 20;
        Window.setAttributes(params);
    }

    private void initView(){
        findViewById(R.id.layout_share_friend).setOnClickListener(this);
        findViewById(R.id.layout_share_qq).setOnClickListener(this);
        findViewById(R.id.layout_share_qzone).setOnClickListener(this);
        findViewById(R.id.layout_share_wechat).setOnClickListener(this);
        findViewById(R.id.layout_logout).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        UMImage image = null;
        SHARE_MEDIA media = null;
        switch (view.getId()) {
            case R.id.layout_share_wechat:
                media= SHARE_MEDIA.WEIXIN;
                break;
            case R.id.layout_share_friend:
                media= SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case R.id.layout_share_qq:
                media= SHARE_MEDIA.QQ;
                break;
            case R.id.layout_share_qzone:
                media= SHARE_MEDIA.QZONE;
                break;
        }

        if(!TextUtils.isEmpty(shareBean.getImgUrl())){
            image = new UMImage(mActivity, shareBean.getImgUrl());
        }else{
            image = new UMImage(mActivity, BitmapFactory.decodeResource(mActivity.getResources(), shareBean.getImgRes()));
        }
        new ShareAction(mActivity)
                .setPlatform(media)
                .setCallback(shareListener)
                .withTitle(shareBean.getTitle())
                .withText(shareBean.getContent())
                .withTargetUrl(shareBean.getArticleUrl())
                .withMedia(image)
                .share();
        this.dismiss();
    }
}
