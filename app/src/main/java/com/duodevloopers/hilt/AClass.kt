package com.duodevloopers.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module
abstract class AClass {

    /*
    * ActivityComponent Suggests this would be only live as long as an activity
    * But Singleton lives as long as the app lives
    * */
    //@Singleton wrong
    @ActivityScoped // right
    @Binds
    abstract fun bindSomeDependency(
        someInterfaceImpl: SomeInterfaceImpl
    ): SomeInterface

}

interface SomeInterface {
    fun getAThing(): String
}

class SomeInterfaceImpl
constructor() : SomeInterface {
    override fun getAThing(): String {
        return "A thing"
    }

}