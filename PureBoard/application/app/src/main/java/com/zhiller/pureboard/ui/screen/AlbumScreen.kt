import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zhiller.pureboard.R
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.data.viewmodel.LayoutViewModel
import com.zhiller.pureboard.ui.navigation.NavActions
import com.zhiller.pureboard.ui.view.album.AlbumBottomToolbar
import com.zhiller.pureboard.ui.view.album.AlbumFileList

@Composable
fun AlbumScreen(layoutVM: LayoutViewModel, canvasVM: CanvasViewModel, navActions: NavActions) {
  Surface(
    modifier = Modifier
      .fillMaxSize()
      .windowInsetsPadding(WindowInsets.statusBars)
  ) {
    Column(Modifier.fillMaxSize()) {
      Column(
        modifier = Modifier
          .padding(16.dp)
          .fillMaxSize()
          .weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Text(
          text = stringResource(id = R.string.album_title),
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )

        // 相册显示主体
        AlbumFileList()
      }

      // 底部工具栏
      AlbumBottomToolbar(navActions = navActions)
    }


  }
}