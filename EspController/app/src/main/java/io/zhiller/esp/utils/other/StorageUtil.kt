package io.zhiller.esp.utils.other

import android.annotation.SuppressLint
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar

object StorageUtil {
  @SuppressLint("SimpleDateFormat")
  private val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")

  /*生成图片文件名*/
  private fun genFileName(): String {
    val currentTimeMillis = Calendar.getInstance().time
    val currentDateTime = dateTimeFormat.format(currentTimeMillis)
    val currentDate = currentDateTime.split("_").first()
    val currentTime = currentDateTime.split("_").last()

    return "PureBoard_${currentDate}_$currentTime"
  }

  /*获取下载目录*/
  fun getDownloadPath(format: String): File = File(
    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
    "${genFileName()}.$format"
  )

  /*获取相册目录*/
//  fun getPhotoAlbumPath(format: String): File {
//
//  }

  /*获取软件本地存储目录*/
//  fun getApplicationPath(format: String):File
}