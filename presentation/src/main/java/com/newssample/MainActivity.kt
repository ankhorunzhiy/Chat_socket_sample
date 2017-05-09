package com.newssample

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import butterknife.ButterKnife
import com.android.newssample.R
import com.bluelinelabs.conductor.Conductor
import com.newssample.di.components.ApplicationComponent
import com.newssample.di.module.ActivityModule
import com.newssample.util.ActivityHolder
import io.techery.presenta.di.ScreenScope
import io.techery.presenta.mortar.DaggerService
import mortar.MortarScope
import mortar.bundler.BundleServiceRunner
import javax.inject.Inject
import com.bluelinelabs.conductor.Router
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import com.bluelinelabs.conductor.RouterTransaction
import com.newssample.util.StartController


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var activityHolder: ActivityHolder
    private var activityScope: MortarScope? = null
    lateinit var router: Router


    @ScreenScope(MainActivity::class)
    @dagger.Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
    interface Component : ApplicationComponent {
        fun inject(activity: MainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMortar(savedInstanceState)
        setSupportActionBar(toolbar)
        prepareUI()
        router = Conductor.attachRouter(this, controller_container, savedInstanceState)
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(StartController(null)))
        }
    }

    private fun prepareUI() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun initMortar(savedInstanceState: Bundle?) {
        val daggerComponent = DaggerService.getDaggerComponent<Any>(applicationContext)
        val component = DaggerService.createComponent(Component::class.java, daggerComponent)
        component.inject(this)
        val scopeName = localClassName + "-task-" + taskId
        val parentScope = MortarScope.getScope(application)
        activityScope = parentScope.findChild(scopeName)
        if (activityScope == null) {
            activityScope = parentScope.buildChild()
                    .withService(BundleServiceRunner.SERVICE_NAME, BundleServiceRunner())
                    .withService(DaggerService.SERVICE_NAME, component)
                    .build(scopeName)
        }
        BundleServiceRunner.getBundleServiceRunner(activityScope).onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun getSystemService(name: String): Any {
        if(activityScope != null && activityScope?.hasService(name) as Boolean)
            return activityScope?.getService<Any>(name) as Any
        else
            return super.getSystemService(name)
    }

    override fun onAttachedToWindow() {
        takeViews()
        super.onAttachedToWindow()
    }

    override fun onRestart() {
        super.onRestart()
        takeViews()
    }

    override fun onDetachedFromWindow() {
        dropViews()
        super.onDetachedFromWindow()
    }

    private fun takeViews() {
        activityHolder.takeView(this)
    }

    private fun dropViews() {
        activityHolder.dropView(this)
    }

}
