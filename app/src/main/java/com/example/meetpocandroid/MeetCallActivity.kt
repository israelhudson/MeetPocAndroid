package com.example.meetpocandroid

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.facebook.react.modules.core.PermissionListener
import org.jitsi.meet.sdk.JitsiMeetActivityDelegate
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView

class MeetCallActivity : FragmentActivity(), JitsiMeetActivityInterface {
    private var view: JitsiMeetView? = null

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        JitsiMeetActivityDelegate.onActivityResult(
            this, requestCode, resultCode, data
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        JitsiMeetActivityDelegate.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = JitsiMeetView(this)
        val options = JitsiMeetConferenceOptions.Builder()
            .setRoom("https://meet.jit.si/test123")
            .build()
        view?.join(options)

        setContentView(view)
    }

    override fun onDestroy() {
        super.onDestroy()

        view?.dispose()
        view = null

        JitsiMeetActivityDelegate.onHostDestroy(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        JitsiMeetActivityDelegate.onNewIntent(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        JitsiMeetActivityDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun requestPermissions(p0: Array<out String>?, p1: Int, p2: PermissionListener?) {
        if (p0 != null) {
            super.requestPermissions(p0, p1)
        }
    }

    override fun onResume() {
        super.onResume()

        JitsiMeetActivityDelegate.onHostResume(this)
    }

    override fun onStop() {
        super.onStop()

        JitsiMeetActivityDelegate.onHostPause(this)
    }
}