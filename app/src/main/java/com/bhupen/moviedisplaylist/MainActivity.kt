package com.bhupen.moviedisplaylist


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bhupen.moviedisplaylist.app.di.entry.MovieDbFragmentFactoryEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        EntryPointAccessors.fromActivity(this, MovieDbFragmentFactoryEntryPoint::class.java)
            .getFragmentFactory().also {
                supportFragmentManager.fragmentFactory = it
            }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}