package com.example.newsapp.presentation.OnBoarding

import androidx.annotation.DrawableRes
import com.example.newsapp.R

data class Page(
    val title : String,
    val description : String,
    @DrawableRes val image : Int
)
val pages = listOf(
    Page(title = "Stay Informed, Instantly\n" ,
        description = "Get the latest breaking news from around the world—right when it happens.",
         image = R.drawable.onboarding1
    ),
    Page(title = "News That Matters to You\n",
        description = "Customize your feed to follow topics you care about—from tech to sports.",
        image = R.drawable.onboarding2),
    Page(title = "Fast, Clean & Offline Friendly\n",
        description = "Save articles for later and enjoy a smooth reading experience, even without internet.",
        image = R.drawable.onboarding3)
)