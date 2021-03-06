package com.reablace.masterquiz.di.component

import com.reablace.masterquiz.base.BaseActivity
import com.reablace.masterquiz.ui.MainActivity
import com.reablace.masterquiz.ui.detailevent.EventDetailFragment
import com.reablace.masterquiz.ui.home.EventDetailsMapFragment
import com.reablace.masterquiz.ui.listevent.EventListFragment
import com.reablace.masterquiz.ui.listevent.EventMapFragment
import com.reablace.masterquiz.ui.login.LoginActivity
import dagger.Subcomponent


/**
 * It is used to inject services into Activities and Fragments
 */
@Subcomponent(
    modules = [
        ControllerModule::class,
        ViewModelFactoryModule::class,
        SharedPrefsModule::class
    ]
)
interface ControllerSubcomponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(eventListFragment: EventListFragment)

    fun inject(mapFragment: EventMapFragment)

    fun inject(eventDetailFragment: EventDetailFragment)

    fun inject(eventDetailMaoFragment: EventDetailsMapFragment)

}
