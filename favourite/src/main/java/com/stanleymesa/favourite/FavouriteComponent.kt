package com.stanleymesa.favourite

import android.content.Context
import com.stanleymesa.cinebox.di.FavouriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavouriteModuleDependencies::class])
interface FavouriteComponent {

    fun inject(activity: FavouriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favouriteModuleDependencies: FavouriteModuleDependencies): Builder
        fun build(): FavouriteComponent
    }

}