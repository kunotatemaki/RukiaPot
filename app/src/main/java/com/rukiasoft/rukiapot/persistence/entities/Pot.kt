package com.rukiasoft.rukiapot.persistence.entities

import androidx.room.Entity
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*


/**
 *  Copyright (C) Rukiasoft - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Raul <raulfeliz@gmail.com>, July 2018
 *
 *
 */

@Entity(tableName = "pot")
data class Pot constructor(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "description")
        val description: String? = null,
        @ColumnInfo(name = "owner")
        val owner: String,
        @ColumnInfo(name = "open")
        val closed: Boolean = true,
        @ColumnInfo(name = "has_pot")
        val hasPot: Boolean = true,
        @ColumnInfo(name = "created")
        val created: Date ,
        @ColumnInfo(name = "accessed")
        val accessed: Date )

