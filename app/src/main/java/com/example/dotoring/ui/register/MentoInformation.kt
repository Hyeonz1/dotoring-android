package com.example.dotoring.ui.register

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class MentoInformation(
    val company: String = "",
    val careerLevel: Int = 1,
    val job: String="",
    val major: String="",
    val employmentCertification: Uri? = null,
    val graduateCertification: Uri? = null,
    val nickname: String = "",
    val introduction: String = "",
    val loginId: String = "",
    val password: String = "",
    val email: String = ""
    ): Parcelable