package kh.com.pheaktra.developer.basic.advance.android.weekend.util

import android.content.Context
import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.CameraController
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

object CameraUtil {

    fun captureImageToCache(
        context: Context,
        cameraController: CameraController,
        onSuccess: (Uri) -> Unit,
        onError: (ImageCaptureException) -> Unit,
    ) {
        // Create temporary file inside:
        // /data/user/0/your.package.name/cache/myImage
        val imageDir = File(context.cacheDir, "MyImage")
            .apply {
                if (!exists()) {
                    mkdirs()
                }
            }

        val photoFile = File.createTempFile(
            "camera_",
            ".jpg",
            imageDir
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            photoFile
        ).build()

        cameraController.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(
                    outputFileResults: ImageCapture.OutputFileResults
                ) {
                    println("=====> context.packageName:: ${context.packageName}")
                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.fileprovider",
                        photoFile
                    )

                    onSuccess(uri)
                }

                override fun onError(
                    exception: ImageCaptureException
                ) {
                    // Remove incomplete file
                    photoFile.delete()

                    onError(exception)
                }
            }
        )
    }
}