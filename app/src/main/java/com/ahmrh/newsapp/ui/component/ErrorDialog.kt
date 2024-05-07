package com.ahmrh.newsapp.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorDialog(
    onDismiss: () -> Unit,
    title: String,
    errorMessage: String,
    dismissText: String = "Dismiss"
) {

    AlertDialog(
        icon = {
            Icon(Icons.Default.Info, contentDescription = "Example Icon")
        },
        title = {
            Text(text = title, )
        },
        text = {
            Text(text = errorMessage, textAlign = TextAlign.Justify)
        },
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(dismissText)
            }
        }
    )

}