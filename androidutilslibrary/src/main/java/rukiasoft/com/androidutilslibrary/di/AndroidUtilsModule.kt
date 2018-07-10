package rukiasoft.com.androidutilslibrary.di

import dagger.Module
import dagger.Provides
import rukiasoft.com.androidutilslibrary.resources.ResourcesManager
import rukiasoft.com.androidutilslibrary.resources.ResourcesManagerImpl


/**
Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 *
 * Module for injecting dependencies from the library
 */

@Module
class AndroidUtilsModule {

    @Provides
    fun providesResourcesManager(resources: ResourcesManagerImpl): ResourcesManager {
        return resources
    }

}