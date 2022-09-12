@file:OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)

package io.github.kiranshny.qrscanner

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import io.github.kiranshny.qrscanner.domain.BarCodeContract
import io.github.kiranshny.qrscanner.ui.theme.QRScannerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    @ExperimentalPermissionsApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val bottomSheetScaffoldState =
                rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val coroutineScope = rememberCoroutineScope()
            val barcodeLauncher = rememberLauncherForActivityResult(
                BarCodeContract()
            ) { result ->
                result.qrCodeContent?.let { scannedContent ->
                    //TODO: Handle Content
                }
            }
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

            QRScannerTheme {
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
                    MainScreen(
                        onHistoryClick = {

                        },
                        onScanClick = {
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
                    )
                }
            }
        }
    }
}
