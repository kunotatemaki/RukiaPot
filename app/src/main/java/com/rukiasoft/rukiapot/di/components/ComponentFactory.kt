package com.rukiasoft.rukiapot.di.components

import com.rukiasoft.rukiapot.ApplicationBase


/* Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 */

class ComponentFactory {
    companion object {

        fun component(context: ApplicationBase): RukiaPotComponent {
            return DaggerRukiaPotComponent.builder()
                    .application(context)
                    .build()
        }

    }
}