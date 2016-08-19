package com.xiaozhi.bottomdialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.facebook.share.widget.ShareDialog;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.xiaozhi.bottomdialog.bean.ShareBean;
import com.xiaozhi.bottomdialog.dialog.ShareBottomDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.button)
    Button button;
    private ShareBottomDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.button)
    public void onClick() {
        ShareBean shareBean = new ShareBean();
        shareBean.setTitle("不错不错");
        shareBean.setArticleUrl("https://mp.weixin.qq.com/s?__biz=MzAwNDY1ODY2OQ==&mid=400785752&idx=1&sn=e1c166e7fad0892811c9ca9bca6d1540&scene=1&srcid=0815RGz8oTEbCypdSPr3njKB&key=305bc10ec50ec19bb0e3a9599e6297645cd5cedd8e10058e839d0fd6f382db4b0d24b6c0c41a38c29411352aaa04b43e&ascene=0&uin=ODE4NzczNTgx&devicetype=iMac+MacBookPro12%2C1+OSX+OSX+10.11.3+build(15D21)&version=11020201&pass_ticket=EmSKiCDiuH%2F8siGv2sncYwVbbKFNvm061pzgcg%2BiAJd1jBbJbOFjpK08ywlpW2kU");
        shareBean.setImgUrl("http://image.tianjimedia.com/uploadImages/2012/233/38/H439I0N71ARI.jpg");
        shareBean.setContent("我是文章内容，我是文章内容，我是文章内容，我是文章内容，我是文章内容，我是文章内容！");
        ShareBottomDialog shareDialog = new ShareBottomDialog(MainActivity.this, shareBean);
        shareDialog.show();
    }
}
