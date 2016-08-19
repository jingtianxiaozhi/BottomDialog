# BottomDialog
友盟分享的对话框(从底部弹出):友盟分享等相关API已经嵌入到了Dialog中，只需要在Activity中写少量的代码，即可完成分享。

#效果
<img src="https://github.com/jingtianxiaozhi/BottomDialog/blob/master/pic/bottom_dialog.jpg?raw=true" height="480" width="300"/>


#How To Use(Too Simple)
```java  
//1.在Activity中初始化配置
PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

//2.复写onActivityResult方法
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
}

//3.new一个ShareBean、new一个ShareDialog然后show出来
@OnClick(R.id.button)
public void onClick() {
    ShareBean shareBean = new ShareBean();
    shareBean.setTitle("不错不错");
    shareBean.setArticleUrl("https://www.baidu.com/img/bd_logo1.png");
    shareBean.setContent("我是文章内容，我是文章内容，我是文章内容，我是文章内容，我是文章内容，我是文章内容！");
    ShareBottomDialog shareDialog = new ShareBottomDialog(MainActivity.this, shareBean);
    shareDialog.show();
}
```

#Tanks for
https://www.umeng.com/
