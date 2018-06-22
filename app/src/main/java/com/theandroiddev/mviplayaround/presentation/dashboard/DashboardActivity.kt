package com.theandroiddev.mviplayaround.presentation.dashboard

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.theandroiddev.mviplayaround.R
import com.theandroiddev.mviplayaround.extension.inTransaction
import com.theandroiddev.mviplayaround.mvi.MviDaggerAppCompatActivity
import com.theandroiddev.mviplayaround.presentation.sign_in.SignInFragment

class DashboardActivity : MviDaggerAppCompatActivity<DashboardView, DashboardPresenter>(),
        DashboardView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val toolbar = findViewById<Toolbar>(R.id.dashboard_toolbar)
        setSupportActionBar(toolbar)

        supportFragmentManager.inTransaction {
            add(R.id.dashboard_frame_layout, SignInFragment())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.dashboard_action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }

}
