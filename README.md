[![](https://jitpack.io/v/zlisa/Camera2Utils.svg)](https://jitpack.io/#zlisa/Camera2Utils)

# Camera2工具
基于Google Video Recording Demo，简单封装Camera2视频录制工具类

<center>
<img src="https://github.com/zlisa/Camera2Utils/raw/master/image/QQ%E5%9B%BE%E7%89%8720180807144803.png" width="25%" height="25%" />
录制前
</center>

![录制前](https://github.com/zlisa/Camera2Utils/blob/master/image/QQ%E5%9B%BE%E7%89%8720180807144803.png)
![录制中](https://github.com/zlisa/Camera2Utils/blob/master/image/QQ%E5%9B%BE%E7%89%8720180807144810.png)
![录制后](https://github.com/zlisa/Camera2Utils/blob/master/image/QQ%E5%9B%BE%E7%89%8720180807144815.png)

repositories
```
maven { url "https://jitpack.io" }
```
dependencies
```
implementation 'com.github.zlisa:Camera2Utils:0.0.1-alpha2'
```

必须实现以下方法
```
    override fun onResume() {
        super.onResume()
        mVideoRecordingUtils.onResume()
    }

    override fun onPause() {
        mVideoRecordingUtils.onPause()
        super.onPause()
    }
```
设置最大录制时间
```
/**
 * 最大录制时间
 * 
 * @param 
 */
fun setMaxTime(max: Long)
```
绑定监听器
```
interface OnRecordingListener {
    // 开始录制时
    fun onStart()
    // 进度更新时
    fun progress(progress: Int)
    // 结束录制时
    fun onStop()
}
```
