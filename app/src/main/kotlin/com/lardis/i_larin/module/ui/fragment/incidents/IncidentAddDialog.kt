package com.lardis.i_larin.module.ui.fragment.incidents

import android.app.Dialog
import android.location.Location
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.lardis.i_larin.module.R
import kotlinx.android.synthetic.main.incident_add_dialog.view.*


/**
 * Created by black-sony on 13.05.17.
 */


class IncidentAddDialog : MvpAppCompatDialogFragment(),
        GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback {


    private var mMap: GoogleMap? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context, R.style.MatchActivityDialog)
        val window = dialog.window

        window!!.requestFeature(
                Window.FEATURE_NO_TITLE)//new Dialog(getContext(),R.style.FullScreenActivity);


        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (mView != null) {
            var parent: ViewGroup = mView?.getParent() as ViewGroup;
            if (parent != null)
                parent.removeView(mView);
        }
        try {
            mView = inflater.inflate(R.layout.incident_add_dialog, container, false);

        } catch (e: InflateException) {
            /* map is already there, just return view as it is */
        }
        clearAll()
        (activity.getSupportFragmentManager().findFragmentById(R.id.map) as SupportMapFragment).getMapAsync(this)

        mView?.let {
            with(it)
            {

                incident_add_dialog_swith_show_map.setOnCheckedChangeListener { buttonView, isChecked ->
                    incident_add_dialog_map_container.visibility = (if (isChecked) View.VISIBLE else View.GONE)
                    incident_add_dialog_text_view_map_info.visibility = (if (isChecked) View.VISIBLE else View.GONE)
                }






                incident_add_dialog_button_save.setOnClickListener {
                    var myLocation: Location? = if (!incident_add_dialog_swith_show_map.isChecked) null else mMap?.myLocation
                    var incidentAddModel = IncidentAddModel(
                            incident_add_dialog_edit_text_intident.text.toString(),
                            incident_add_dialog_edit_text_car.text.toString(),
                            incident_add_dialog_edit_text_phone_number.text.toString(),
                            incident_add_dialog_edit_text_location_text.text.toString(),
                            myLocation?.latitude,
                            myLocation?.longitude)


                    if (  incidentAddModel.intident.length > 3) {

                        callback.invoke(incidentAddModel)
                        dismiss()
                    } else Toast.makeText(context, "", Toast.LENGTH_SHORT).show()

                }

            }
        }

        return mView
    }

    var callback: (IncidentAddModel) -> Unit = {}

    fun clearAll() {
        mView?.let {
            with(it)
            {
                incident_add_dialog_edit_text_intident.text.clear()
                incident_add_dialog_edit_text_car.text.clear()
                incident_add_dialog_edit_text_phone_number.text.clear()
                incident_add_dialog_edit_text_location_text.text.clear()
            }
        }
    }

    override fun onStart() {
        dialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT)
        dialog.window!!.setWindowAnimations(R.style.MatchActivityDialog)
        super.onStart()
    }


    companion object {
        private var mView: View? = null

        fun newInstance(): IncidentAddDialog = IncidentAddDialog()


        private val TAG = "SplashActivity"
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(context, "MyLocation button clicked", Toast.LENGTH_SHORT).show()

        return false

    }

    override fun onMapReady(p0: GoogleMap?) {

        mMap = p0

        mMap?.setOnMyLocationButtonClickListener(this)
        mMap?.setMyLocationEnabled(true)

    }

}