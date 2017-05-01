package com.lardis.i_larin.module.ui.fragment.dialogs


import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.storage.entities.DialogModel
import kotlinx.android.synthetic.main.dialogs_fragment_dialog.*


class AddDiaDialog : MvpAppCompatDialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
            = Dialog(context, R.style.Theme_AppCompat_Dialog)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater!!.inflate(R.layout.dialogs_fragment_dialog, container)


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogs_fragment_dialog_edit_text_title.addTextChangedListener(textWather)
        dialogs_fragment_dialog_edit_text_description.addTextChangedListener(textWather)

        dialogs_fragment_dialog_button_add.setOnClickListener {

            var dialogModel = DialogModel(null, dialogs_fragment_dialog_edit_text_title.text.toString(),
                    dialogs_fragment_dialog_edit_text_description.text.toString(),
                    System.currentTimeMillis(),
                    "")
            dismiss()
            callback.invoke(dialogModel)

        }
    }


var callback:(DialogModel) -> Unit?={}

    var textWather: TextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                       after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                   count: Int) {
            dialogs_fragment_dialog_button_add.isEnabled =
                    (dialogs_fragment_dialog_edit_text_title.text.length > 2 &&
                            dialogs_fragment_dialog_edit_text_description.text.length > 2)
        }

    }

    companion object {

        fun newInstance(): AddDiaDialog {
            val dialog = AddDiaDialog()
            val args = Bundle()
            dialog.arguments = args
            return dialog
        }

        private val TAG = "SplashActivity"
    }

}
