package com.zhiller.pureboard.ui.view.album

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlbumFileList() {
  val itemsList = (0..5).toList()
  val itemsIndexedList = listOf("A", "B", "C")

  val itemModifier = Modifier.border(1.dp, Color.Blue).wrapContentSize()

  LazyVerticalStaggeredGrid(
    columns = StaggeredGridCells.Fixed(3)
  ) {
    items(itemsList) {
      Text("Item is $it", itemModifier.height(80.dp))
    }
    item {
      Text("Single item", itemModifier.height(100.dp))
    }
    itemsIndexed(itemsIndexedList) { index, item ->
      Text("Item at index $index is $item", itemModifier.height(60.dp))
    }
  }
}