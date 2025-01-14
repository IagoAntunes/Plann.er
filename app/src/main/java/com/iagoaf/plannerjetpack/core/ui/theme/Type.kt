import AppTypography.Companion.Typography
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.iagoaf.plannerjetpack.core.ui.theme.interFontFamily

class AppTypography {
    companion object {


        val headingLg = TextStyle(
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = interFontFamily,
        )
        val headingSm = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = interFontFamily,
        )
        val bodyMd = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = interFontFamily,
        )
        val bodySm = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 16.8.sp,
            fontFamily = interFontFamily,
        )
        val button = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 19.6.sp,
            fontFamily = interFontFamily,
        )
        val Typography = Typography(
            bodyLarge = bodyMd,
            bodyMedium = bodySm,
            labelLarge = button,
            titleLarge = headingLg,
            titleMedium = headingSm
        )
    }
}