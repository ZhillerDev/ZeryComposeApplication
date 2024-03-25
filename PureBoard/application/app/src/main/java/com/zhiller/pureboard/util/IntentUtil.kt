package com.zhiller.pureboard.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.runtime.Composable

object IntentUtil {
  @Composable
  @SuppressLint("QueryPermissionsNeeded")
  fun OpenGallery(context: Context){
    val intent =  Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    if (intent.resolveActivity(context.packageManager) != null) {
      context.startActivity(intent)
    } else {
      // 如果没有应用能够处理这个意图，则可以显示错误提示或进行其他操作
    }
  }

  @Composable
  @SuppressLint("QueryPermissionsNeeded")
  fun OpenDownload(context: Context){
    val intent = Intent(Intent.ACTION_VIEW)
    val downloadsUri = Uri.parse("content://com.android.providers.downloads.documents/tree/downloads")
    intent.setDataAndType(downloadsUri, "vnd.android.document/directory")
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    if (intent.resolveActivity(context.packageManager) != null) {
      context.startActivity(intent)
    } else {
      MessageUtil.Toast(title = "您的设备不支持直接打开文件管理器")
    }
  }
}