package com.duodevloopers.hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    /* field injection, injecting this into main activity as dependency
    * hilt instantiate this class in background and hold it in memory
    * make this object compile time and make this available at run time
    *
    * */
    @Inject
    lateinit var class1: Class1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(class1.doAThing())
        println(class1.doOtherThing())
    }
}

@AndroidEntryPoint
class MyFragment : Fragment() {

    @Inject
    lateinit var class1: Class1
}

// @Singleton indicates this class will be alive as long as the app is alive
/*
* this class is a dependency of main activity
* so this class should be annotated higher tier scopes
* */
@Singleton
class Class1
/* constructor injection
* at first class2 will be made
* then object will be passed on to this class as argument
* */
@Inject
constructor(
    private val class2: Class2
) {

    fun doAThing(): String {
        return "Something"
    }

    fun doOtherThing(): String {
        return class2.doOtherThingFromOtherClass()
    }
}

class Class2
// indicating as this class is needed somewhere to inject
@Inject
constructor() {
    fun doOtherThingFromOtherClass(): String {
        return "Other thing"
    }
}