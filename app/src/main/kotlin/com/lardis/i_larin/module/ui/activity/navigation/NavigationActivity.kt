package com.lardis.i_larin.module.ui.activity.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.lardis.i_larin.module.App
import com.lardis.i_larin.module.R
import com.lardis.i_larin.module.presentation.presenter.navigation.NavigationPresenter
import com.lardis.i_larin.module.presentation.view.navigation.NavigationsView
import com.lardis.i_larin.module.ui.fragment.setting.AboutFragment
import com.lardis.i_larin.module.ui.fragment.setting.DialogsFragment
import com.lardis.i_larin.module.ui.fragment.setting.SettingFragment
import kotlinx.android.synthetic.main.navigation_activity.*
import kotlinx.android.synthetic.main.navigation_activity_layout_content.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command


class NavigationActivity : MvpAppCompatActivity(), NavigationsView, NavigationView.OnNavigationItemSelectedListener {

    private var toggle: ActionBarDrawerToggle? = null
    private val MENU_DIALOGS = 0

    val EMAIL = "EMAIL"
    val PASSWORD = "PASSWORD"

    @InjectPresenter
    lateinit var mNavigationPresenter: NavigationPresenter

    var router: Router
    var navigatorHolder: NavigatorHolder

    init {
        router = App.INSTANCE.getRouter()
        navigatorHolder = App.INSTANCE.getNavigatorHolder()
    }

    @ProvidePresenter
    fun provideRepositoryPresenter(): NavigationPresenter {
        val repositoryPresenter = NavigationPresenter(router)
        return repositoryPresenter
    }

    companion object {
        const val TAG = "NavigationActivity"
        fun getIntent(context: Context): Intent = Intent(context, NavigationActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)
        initView(savedInstanceState)
    }

    private fun initView(savedInstanceState: Bundle?) {

        setSupportActionBar(navigation_activity_layout_content_toolbar)

        toggle = ActionBarDrawerToggle(this, navigation_activity_drawer_layout,
                navigation_activity_layout_content_toolbar, 0, 0)
        toggle?.let { navigation_activity_drawer_layout.addDrawerListener(it) }

        navigation_activity_navigation_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            navigation_activity_navigation_view.menu.getItem(MENU_DIALOGS).isChecked = true
            onNavigationItemSelected(navigation_activity_navigation_view.menu.getItem(MENU_DIALOGS))
        }
        if (intent != null) {
            val email = intent.getStringExtra(EMAIL)

            (navigation_activity_navigation_view.getHeaderView(0)
                    .findViewById(R.id.header_login) as TextView).text = email
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private val navigator = object : SupportFragmentNavigator(supportFragmentManager, R.id.navigation_activity_layout_content_container) {
        override fun createFragment(screenKey: String?, data: Any?): Fragment = when (screenKey) {
            NavigationScreens.ABOUT -> AboutFragment.newInstance()
            NavigationScreens.DIALOG -> DialogsFragment.newInstance()
            NavigationScreens.SETTING -> SettingFragment.newInstance()
            else -> SettingFragment.newInstance()
        }


        override fun showSystemMessage(message: String) =
                Toast.makeText(this@NavigationActivity, message, Toast.LENGTH_SHORT).show()

        override fun exit() = finish()


        override fun applyCommand(command: Command) = super.applyCommand(command)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_dialogs -> mNavigationPresenter.onNewRootCommandClick(NavigationScreens.DIALOG)
            R.id.nav_settings -> mNavigationPresenter.onNewRootCommandClick(NavigationScreens.SETTING)
            R.id.nav_about -> mNavigationPresenter.onNewRootCommandClick(NavigationScreens.ABOUT)
            R.id.nav_exit -> mNavigationPresenter.onBackCommandClick()
        }
        navigation_activity_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle?.syncState()
    }

}
