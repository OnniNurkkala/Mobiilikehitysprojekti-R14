package com.example.recipeapp

import android.os.Parcel
import android.os.Parcelable

class RecipeClass(
    val recipe_id: Int,
    val recipeName: String?,
    val recipe_ing: String?,
    val recipe_inst: String?
) : Parcelable {
    // TODO: Implement Parcelable methods
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(recipe_id)
        parcel.writeString(recipeName)
        parcel.writeString(recipe_ing)
        parcel.writeString(recipe_inst)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeClass> {
        override fun createFromParcel(parcel: Parcel): RecipeClass {
            return RecipeClass(parcel)
        }

        override fun newArray(size: Int): Array<RecipeClass?> {
            return arrayOfNulls(size)
        }
    }
}