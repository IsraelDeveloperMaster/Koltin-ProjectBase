/*
 * *
 *  * Created by rael on 20/02/2025 21:50
 *  * Copyright (c) 2025 . All rights reserved.
 *  * Last modified 20/02/2025 21:00
 *
 */

package net.developermaster.projectbase.estudoUI.estudoUiShop.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class ModelUiScreenShopItems(
    var title: String = "",
    var description: String = "",
    var picUrl: ArrayList<String> = ArrayList(),
    var model: ArrayList<String> = ArrayList(),
    var price: Double = 0.0,
    var rating: Double = 0.0,
    var numbeInCarousel: Int = 0,
    var showRecomended: Boolean = false,
    var category: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!! as ArrayList<String>,
        parcel.createStringArrayList()!! as ArrayList<String>,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeStringList(picUrl)
        parcel.writeStringList(model)
        parcel.writeDouble(price)
        parcel.writeDouble(rating)
        parcel.writeInt(numbeInCarousel)
        parcel.writeByte(if (showRecomended) 1 else 0)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelUiScreenShopItems> {
        override fun createFromParcel(parcel: Parcel): ModelUiScreenShopItems {
            return ModelUiScreenShopItems(parcel)
        }

        override fun newArray(size: Int): Array<ModelUiScreenShopItems?> {
            return arrayOfNulls(size)
        }
    }
}
