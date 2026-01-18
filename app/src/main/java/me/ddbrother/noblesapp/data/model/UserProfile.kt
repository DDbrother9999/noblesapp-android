package me.ddbrother.noblesapp.data.model

import com.google.gson.annotations.SerializedName

data class UserProfile(
    @SerializedName("PersonType") val personType: String?,
    @SerializedName("FirstName") val firstName: String?,
    @SerializedName("LastName") val lastName: String?,
    @SerializedName("UNID") val unId: String?,
    @SerializedName("PeopleID") val peopleId: String?,
    @SerializedName("MACLockerNumber") val macLockerNumber: String?,
    @SerializedName("MACLockerCombo") val macLockerCombo: String?,
    @SerializedName("SchoolHouseLockerNumber") val schoolHouseLockerNumber: String?,
    @SerializedName("SchoolHouseLockerCombo") val schoolHouseLockerCombo: String?,
    @SerializedName("MSCubbyNo") val msCubbyNo: String?,
    @SerializedName("MSCubbyCombo") val msCubbyCombo: String?,
    @SerializedName("AssemblySeat") val assemblySeat: String?,
    @SerializedName("Pin") val pin: String? = null
)
