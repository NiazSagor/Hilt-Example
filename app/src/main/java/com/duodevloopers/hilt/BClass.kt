package com.duodevloopers.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/*
* Module is where we declare the dependency to build objects that needs interface or third party libraries
* */
@InstallIn(ActivityComponent::class)
@Module
class BClass {

    /*
    * SomeInterfaceImpl class does not have any requirements for the constructor
    * That's why provideSomeInterface() does not have any parameters inside the bracket
    * means HILT does not have to pass anything into the method
    * */
    @ActivityScoped
    @Provides
    fun provideSomeInterface(): SomeInterface {
        return SomeInterfaceImpl()
    }

    /*
    * SomeClassThatImplementsAnInterface class does have a constructor requirement
    * That's why provideSomeOtherInterface() does have a parameter inside the bracket
    * means HILT has to pass something into the method
    * */
    @ActivityScoped
    @Provides
    fun provideSomeOtherInterface(someDependency: String): SomeOtherInterface {
        return SomeClassThatImplementsAnInterface(someDependency)
    }

    /*
    * A string dependency that HILT will use where a string dependency is needed
    * Like the above provider method needs a string dependency
    * */
    @ActivityScoped
    @Provides
    fun provideSomeStringDependency(): String {
        return "a string dependency"
    }

}

interface SomeOtherInterface {
    fun doSomething(): String
}

// this is a class that implements an interface, also has a constructor dependency
class SomeClassThatImplementsAnInterface : SomeOtherInterface {

    private lateinit var someDependency: String

    // indicating that we need a dependency coming from HILT to generate this class
    @Inject
    constructor(someDependency: String) {
        this.someDependency = someDependency
    }

    override fun doSomething(): String {
        return "A thing . ${someDependency}"
    }

}