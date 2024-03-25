package com.zhiller.pureboard.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun <viewModel : LifecycleObserver> viewModel.ObserveLifecycleEvents(lifecycle: Lifecycle) {
  DisposableEffect(lifecycle) {
    lifecycle.addObserver(this@ObserveLifecycleEvents)
    onDispose {
      lifecycle.removeObserver(this@ObserveLifecycleEvents)
    }
  }
}

@Composable
fun ComposableLifecycle(
  lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
  onEvent: (LifecycleOwner, Lifecycle.Event) -> Unit
) {
  DisposableEffect(lifecycleOwner) {
    val observer = LifecycleEventObserver { source, event ->
      onEvent(source, event)
    }
    lifecycleOwner.lifecycle.addObserver(observer)

    onDispose {
      lifecycleOwner.lifecycle.removeObserver(observer)
    }
  }
}