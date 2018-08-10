package com.wall.demo

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.wall.camera2.VideoRecordingUtils
import kotlinx.android.synthetic.main.fragment_video_recording.*
import java.text.DecimalFormat

/**
 * =================================================================================================
 * |
 * |    what:    视频录制Demo
 * |
 * |    --------------------------------------------------------------------------------------------
 * |
 * |    who:     wall
 * |    when:    2018/8/6 17:20
 * |
 * =================================================================================================
 */
class VideoRecordingFragment : Fragment(), View.OnClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private val mDecimalFormat = DecimalFormat(".0")

    private val mVideoRecordingUtils: VideoRecordingUtils by lazy {
        VideoRecordingUtils(activity as AppCompatActivity, auto_fit_texture_view).apply {
            val max = 30 * 1000L
            recording_progress.max = max.toInt()
            setMaxTime(max)
            setOnRecordingListener(object : VideoRecordingUtils.OnRecordingListener {
                override fun onStart() {
                    this@apply.startAnimator()
                    btn_recording.setImageResource(R.drawable.shape_recording_pause)
                    visibleProgress(true)
                }

                override fun progress(progress: Int) {
                    text_progress.text = mDecimalFormat.format(progress.toFloat() / 1000).toString()
                    recording_progress.progress = progress
                }

                override fun onStop() {
                    btn_recording.setImageResource(R.drawable.shape_recording_video)
                    btn_delete.visibility = View.VISIBLE
                    btn_confirm.visibility = View.VISIBLE
                    visibleProgress(false)
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_video_recording, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_recording.setOnClickListener(this)
        btn_switch_face.setOnClickListener(this)
        btn_close.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        mVideoRecordingUtils.onResume()
    }

    override fun onPause() {
        mVideoRecordingUtils.onPause()
        super.onPause()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_close -> {
                if (mVideoRecordingUtils.isRecordingVideo()) {
                    mVideoRecordingUtils.stopRecordingVideo()
                }
                recording_progress.progress = 0
                btn_delete.visibility = View.GONE
                btn_confirm.visibility = View.GONE
            }
            R.id.btn_recording -> {
                if (mVideoRecordingUtils.isRecordingVideo()) {
                    mVideoRecordingUtils.stopRecordingVideo()
                } else {
                    mVideoRecordingUtils.startRecordingVideo()
                }
            }
            R.id.btn_switch_face -> {
                mVideoRecordingUtils.switchFace()
            }
            R.id.btn_confirm -> {
                Toast.makeText(context, mVideoRecordingUtils.getVideoPath(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun visibleProgress(visible: Boolean) {
        image_progress.visible(visible)
        text_progress.visible(visible)
        btn_close.visible(!visible)
        btn_switch_face.visible(!visible)
    }

    private fun View.visible(visible: Boolean) {
        if (visible) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        mVideoRecordingUtils.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        fun newInstance(): VideoRecordingFragment = VideoRecordingFragment()
    }
}