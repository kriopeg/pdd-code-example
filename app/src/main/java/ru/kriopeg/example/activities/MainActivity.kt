package ru.kriopeg.example.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import ru.kriopeg.example.R
import ru.kriopeg.example.helpers.Titled
import ru.kriopeg.example.toggleVisibility

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Titled {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarSetup()
    }

    private fun toolbarSetup() {
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbar.title = ""
            supportActionBar?.title = ""

            destination.label?.let {
                setTBTitle(it.toString())
            }
        }
    }

    override fun setTBTitle(title: String) {
        toolbar_title?.text = title
    }

    override fun setTBSubtitle(subTitle: String) {
        toolbar_sub_title?.text = subTitle
    }

    override fun showSubtitle(show: Boolean) {
        toolbar_sub_title.toggleVisibility(show)
    }

}