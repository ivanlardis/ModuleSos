package com.lardis.i_larin.module.ui.fragment.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.presentation.presenter.setting.SettingPresenter
import com.lardis.i_larin.module.presentation.view.setting.SettingView


class AboutFragment : MvpAppCompatFragment(), SettingView {

    @InjectPresenter
     lateinit var mSettingPresenter: SettingPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.about_fragment, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        val TAG = "MesLoaderFragment"


        fun newInstance(): AboutFragment {
            val fragment = AboutFragment()

            val args = Bundle()
            fragment.arguments = args

            return fragment
        }
    }
}
