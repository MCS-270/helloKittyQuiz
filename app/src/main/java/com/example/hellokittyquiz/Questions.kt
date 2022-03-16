import androidx.annotation.StringRes
import androidx.annotation.DrawableRes
data class Questions(@DrawableRes val imageResID: Int, @StringRes val textResID: Int, val answer: Boolean)

