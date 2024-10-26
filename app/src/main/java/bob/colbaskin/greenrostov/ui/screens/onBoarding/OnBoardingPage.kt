package bob.colbaskin.greenrostov.ui.screens.onBoarding

import androidx.annotation.DrawableRes
import bob.colbaskin.greenrostov.R

/**
 * @author bybuss
 */
sealed class OnBoardingPage(
    @DrawableRes val image: Int
) {
    object First : OnBoardingPage(
        image = R.drawable.first
    )

    object Second : OnBoardingPage(
        image = R.drawable.second,
    )

    object Third : OnBoardingPage(
        image = R.drawable.third,
    )
}