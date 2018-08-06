package com.wall.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
 * =================================================================================================
 * |
 * |    what:    MainActivity.kt
 * |
 * |    --------------------------------------------------------------------------------------------
 * |
 * |    who:     wall
 * |    when:    2018/8/6 17:05
 * |
 * =================================================================================================
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager.beginTransaction()
                .replace(R.id.container, VideoRecordingFragment.newInstance())
                .commit()
    }
}
