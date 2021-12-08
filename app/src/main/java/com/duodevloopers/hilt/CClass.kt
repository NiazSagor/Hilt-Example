package com.duodevloopers.hilt

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class CClass {


}

class ClassImplementingSameInterface1 : SomeOtherInterface {
    override fun doSomething(): String {
        TODO("Not yet implemented")
    }

}

class ClassImplementingSameInterface2 : SomeOtherInterface {
    override fun doSomething(): String {
        TODO("Not yet implemented")
    }

}