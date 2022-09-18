@file:OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)

package io.github.kiranshny.qrscanner.home.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import io.github.kiranshny.qrscanner.home.domain.BarCodeContract
import io.github.kiranshny.qrscanner.home.domain.HomeViewModel
import io.github.kiranshny.qrscanner.home.theme.QRScannerTheme
import io.github.kiranshny.qrscanner.home.ui.components.ScanHistoryList
import io.github.kiranshny.qrscanner.home.ui.components.ScanQRCard
import io.github.kiranshny.qrscanner.home.ui.components.TitleBar
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val viewModel: HomeViewModel = viewModel()
    val bottomSheetScaffoldState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val barcodeLauncher = rememberLauncherForActivityResult(BarCodeContract()) { result ->
        result.qrCodeContent?.let { scannedContent ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(scannedContent))
            context.startActivity(intent)
            viewModel.saveContent(content = scannedContent)
        }
    }
    val homeState = viewModel.homeState

    val cameraPermissionState = rememberPermissionState(
        permission = Manifest.permission.CAMERA,
        onPermissionResult = { isGranted ->
            if (isGranted) {
                barcodeLauncher.launch(Unit)
            } else {
                coroutineScope.launch {
                    bottomSheetScaffoldState.show()
                }
            }
        }
    )

    viewModel.loadScanHistory()

    ModalBottomSheetLayout(
        sheetState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(32.dp, 32.dp),
        sheetElevation = 8.dp,
        sheetContent = {
            PermissionBottomSheet(
                title = "Camera Permission Required",
                subtitle = "Permission for camera required for scanning Barcode",
                buttonText = "Allow"
            ) {
                cameraPermissionState.launchPermissionRequest()
                coroutineScope.launch {
                    bottomSheetScaffoldState.hide()
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            TitleBar()
            ScanQRCard {
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                    ) -> {
                        barcodeLauncher.launch(Unit)
                    }
                    else -> {
                        cameraPermissionState.launchPermissionRequest()
                    }
                }
            }
            ScanHistoryList(homeState)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    QRScannerTheme {
        HomeScreen()
    }
}