# XTabLayoutDemo
XTabLayout的简单使用，以及沉浸式标题栏和隐藏底部虚拟键
</br>
![image](https://github.com/TankSao/XTabLayoutDemo/blob/master/image/QQ%E5%9B%BE%E7%89%8720181030143447.png)
```
    /**
     * 隐藏虚拟按键，并且全屏
     */
    public void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    /**
     * 沉浸式导航栏
     */
    public void hideTopUIMenu() {
        StatusBarUtils.with(this)
                .init();
    }
```
</br>
