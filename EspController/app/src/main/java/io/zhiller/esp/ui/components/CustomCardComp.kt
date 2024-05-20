package io.zhiller.esp.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

// 自动填充全部宽度的card
@Composable
fun PlainCard(
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit
) {
  Column(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .background(MaterialTheme.colorScheme.inverseOnSurface)
      .fillMaxWidth()
      .animateContentSize()
  ) {
    Column(
      modifier = Modifier
        .padding(16.dp)
        .animateContentSize(),
      verticalArrangement = Arrangement.spacedBy(4.dp),
      content = content
    )
  }
}

// 宽度根据内容自适应card
@Composable
fun PlainWrapCard(
  modifier: Modifier = Modifier,
  content: @Composable ColumnScope.() -> Unit
) {
  Column(
    modifier = modifier
      .clip(RoundedCornerShape(12.dp))
      .background(MaterialTheme.colorScheme.inverseOnSurface)
      .animateContentSize()
  ) {
    Column(
      modifier = Modifier
        .padding(16.dp)
        .animateContentSize(),
      verticalArrangement = Arrangement.spacedBy(4.dp),
      content = content
    )
  }
}