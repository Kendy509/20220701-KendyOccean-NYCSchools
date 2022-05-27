package com.dynamicdevz.a20220516_kendyoccean_nycschools.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Schools(
    @SerializedName("city")
    val city: String?,
    @SerializedName("dbn")
    val dbn: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("school_email")
    val schoolEmail: String?,
    @SerializedName("school_name")
    val schoolName: String?,
    @SerializedName("website")
    val website: String?,
    @SerializedName("zip")
    val zip: String?
): Serializable