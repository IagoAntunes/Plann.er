package com.iagoaf.plannerjetpack.src.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iagoaf.plannerjetpack.R
import com.iagoaf.plannerjetpack.core.ui.theme.zinc100
import com.iagoaf.plannerjetpack.core.ui.theme.zinc400
import com.iagoaf.plannerjetpack.core.ui.theme.zinc900
import com.iagoaf.plannerjetpack.src.home.domain.models.ActivityModel

@Composable
fun ItemListActivities(activity: ActivityModel) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(zinc900)
            .padding(8.dp)
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(if (activity.isDone) R.drawable.img_circle_check else R.drawable.img_circle_dashed),
                    contentDescription = "isDone",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    activity.name,
                    style = AppTypography.bodyMd.copy(
                        color = zinc100
                    )
                )
            }
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    activity.date.substring(0, 5),
                    style = AppTypography.bodySm.copy(
                        color = zinc400
                    )
                )
                Text(
                    activity.time,
                    style = AppTypography.bodySm.copy(
                        color = zinc400
                    )
                )
            }
        }
    }
}