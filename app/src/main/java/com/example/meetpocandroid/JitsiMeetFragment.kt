//package com.example.meetpocandroid
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [JitsiMeetFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class JitsiMeetFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_jitsi_meet, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment JitsiMeetFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            JitsiMeetFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}

package com.example.meetpocandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.facebook.react.modules.core.PermissionListener
import org.jitsi.meet.sdk.JitsiMeetActivityInterface
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions
import org.jitsi.meet.sdk.JitsiMeetView
import java.net.URL

class JitsiMeetFragment : Fragment(), JitsiMeetActivityInterface {

    private var jitsiMeetView: JitsiMeetView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflating layout for the fragment
        return inflater.inflate(R.layout.fragment_jitsi_meet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jitsiMeetView = JitsiMeetView(requireContext())
        val options = JitsiMeetConferenceOptions.Builder()
            .setServerURL(URL("https://meet.minhacademia.com.br/"))
            .setRoom("poctestesisrael")
            .setAudioMuted(false)
            .setVideoMuted(false)
            .setAudioOnly(false)
            .setConfigOverride("requireDisplayName", true)
            .build()
        jitsiMeetView?.join(options)

        // Add JitsiMeetView to the layout
        val jitsiMeetContainer: FrameLayout = view.findViewById(R.id.jitsi_meet_container)
        jitsiMeetContainer.addView(jitsiMeetView)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Dispose of JitsiMeetView
        jitsiMeetView?.dispose()
        jitsiMeetView = null
    }

    override fun checkPermission(p0: String?, p1: Int, p2: Int): Int {
        TODO("Not yet implemented")
    }

    override fun checkSelfPermission(p0: String?): Int {
        TODO("Not yet implemented")
    }

    override fun requestPermissions(p0: Array<out String>?, p1: Int, p2: PermissionListener?) {
        TODO("Not yet implemented")
    }
}
