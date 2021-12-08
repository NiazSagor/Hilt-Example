package com.duodevloopers.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Qualifier

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
    @AStringDependency1
    @ActivityScoped
    @Provides
    fun provideSomeStringDependency(): String {
        return "a string dependency"
    }

    /*
    * There are 2 provider methods that return same data type
    * So these provider methods are annotated with different annotation in order to HILT to distinguish
    * */
    @AStringDependency2
    @ActivityScoped
    @Provides
    fun provideSomeOtherStringDependency(): String {
        return "another string dependency"
    }

}

interface SomeOtherInterface {
    fun doSomething(): String
}

// this is a class that implements an interface, also has a constructor dependency
class SomeClassThatImplementsAnInterface : SomeOtherInterface {

    private lateinit var someDependency: String

    /*
    * @Inject indicating that we need a dependency coming from HILT to generate this class
    * the constructor argument is annotated with a custom specific annotation
    * that we made to indicate HILT
    * which of the provider methods to use
    * as here, the constructor argument is annotated with @AStringDependency1
    * HILT knows that it has to get the string dependency from a provider method
    * which is annotated with @AStringDependency1
    * */
    @Inject
    constructor(@AStringDependency1 someDependency: String) {
        this.someDependency = someDependency
    }

    override fun doSomething(): String {
        return "A thing . ${someDependency}"
    }

}

/*
* These are custom annotation class
* To mark provider methods which return same data type
* These should be done for same data type also for same classes or same interfaces
* */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AStringDependency1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AStringDependency2