package com.ahmrh.newsapp.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun ErrorDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    errorMessage: String,
) {

    var errorDialogOpen by remember { mutableStateOf(true) }

    when {
        errorDialogOpen -> {
            AlertDialog(
                icon = {
                    Icon(
                        Icons.Default.Info, contentDescription = null
                    )
                },
                text = {
                    Text(errorMessage)
                },
                onDismissRequest = {
                    onDismiss()

                    errorDialogOpen = false
                }, confirmButton = {
                    onConfirm()

                    errorDialogOpen = false
                }
            )

        }
    }

}