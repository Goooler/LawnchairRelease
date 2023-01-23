/*
 * Copyright (C) 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR condITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.lawnchair

import android.app.WallpaperColors
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.util.SparseIntArray
import android.view.View
import android.widget.RemoteViews
import androidx.annotation.Keep
import androidx.annotation.RequiresApi
import app.lawnchair.theme.ThemeProvider
import app.lawnchair.theme.ThemeProvider.ColorSchemeChangeListener
import app.lawnchair.theme.color.AndroidColor
import com.android.launcher3.widget.LocalColorExtractor
import dev.kdrag0n.colorkt.Color
import dev.kdrag0n.monet.theme.ColorScheme

@Suppress("unused") // See lawnchair/res/values-v31/config.xml#L3
@RequiresApi(api = Build.VERSION_CODES.S)
class AccentColorExtractor @Keep constructor(context: Context?) : LocalColorExtractor(),
    ColorSchemeChangeListener {
    private val themeProvider = ThemeProvider.INSTANCE[context]
    private var listener: Listener? = null

    override fun setListener(listener: Listener?) {
        this.listener = listener
        notifyListener()
    }

    override fun setWorkspaceLocation(pos: Rect, child: View, screenId: Int) = Unit

    override fun applyColorsOverride(base: Context, colors: WallpaperColors) {
        RemoteViews.ColorResources.create(base, generateColorsOverride(colors))?.apply(base)
    }

    override fun onColorSchemeChanged() {
        notifyListener()
    }

    private fun notifyListener() {
        listener?.onColorsChanged(generateColorsOverride(themeProvider.colorScheme))
    }

    private fun generateColorsOverride(colorScheme: ColorScheme): SparseIntArray {
        return SparseIntArray(5 * 13).apply {
            addColorsToArray(colorScheme.accent1, ACCENT1_RES)
            addColorsToArray(colorScheme.accent2, ACCENT2_RES)
            addColorsToArray(colorScheme.accent3, ACCENT3_RES)
            addColorsToArray(colorScheme.neutral1, NEUTRAL1_RES)
            addColorsToArray(colorScheme.neutral2, NEUTRAL2_RES)
        }
    }

    companion object {
        // Shade number -> color resource ID maps
        private val ACCENT1_RES = SparseIntArray(13)
        private val ACCENT2_RES = SparseIntArray(13)
        private val ACCENT3_RES = SparseIntArray(13)
        private val NEUTRAL1_RES = SparseIntArray(13)
        private val NEUTRAL2_RES = SparseIntArray(13)

        init {
            ACCENT1_RES.put(0, android.R.color.system_accent1_0)
            ACCENT1_RES.put(10, android.R.color.system_accent1_10)
            ACCENT1_RES.put(50, android.R.color.system_accent1_50)
            ACCENT1_RES.put(100, android.R.color.system_accent1_100)
            ACCENT1_RES.put(200, android.R.color.system_accent1_200)
            ACCENT1_RES.put(300, android.R.color.system_accent1_300)
            ACCENT1_RES.put(400, android.R.color.system_accent1_400)
            ACCENT1_RES.put(500, android.R.color.system_accent1_500)
            ACCENT1_RES.put(600, android.R.color.system_accent1_600)
            ACCENT1_RES.put(700, android.R.color.system_accent1_700)
            ACCENT1_RES.put(800, android.R.color.system_accent1_800)
            ACCENT1_RES.put(900, android.R.color.system_accent1_900)
            ACCENT1_RES.put(1000, android.R.color.system_accent1_1000)
            ACCENT2_RES.put(0, android.R.color.system_accent2_0)
            ACCENT2_RES.put(10, android.R.color.system_accent2_10)
            ACCENT2_RES.put(50, android.R.color.system_accent2_50)
            ACCENT2_RES.put(100, android.R.color.system_accent2_100)
            ACCENT2_RES.put(200, android.R.color.system_accent2_200)
            ACCENT2_RES.put(300, android.R.color.system_accent2_300)
            ACCENT2_RES.put(400, android.R.color.system_accent2_400)
            ACCENT2_RES.put(500, android.R.color.system_accent2_500)
            ACCENT2_RES.put(600, android.R.color.system_accent2_600)
            ACCENT2_RES.put(700, android.R.color.system_accent2_700)
            ACCENT2_RES.put(800, android.R.color.system_accent2_800)
            ACCENT2_RES.put(900, android.R.color.system_accent2_900)
            ACCENT2_RES.put(1000, android.R.color.system_accent2_1000)
            ACCENT3_RES.put(0, android.R.color.system_accent3_0)
            ACCENT3_RES.put(10, android.R.color.system_accent3_10)
            ACCENT3_RES.put(50, android.R.color.system_accent3_50)
            ACCENT3_RES.put(100, android.R.color.system_accent3_100)
            ACCENT3_RES.put(200, android.R.color.system_accent3_200)
            ACCENT3_RES.put(300, android.R.color.system_accent3_300)
            ACCENT3_RES.put(400, android.R.color.system_accent3_400)
            ACCENT3_RES.put(500, android.R.color.system_accent3_500)
            ACCENT3_RES.put(600, android.R.color.system_accent3_600)
            ACCENT3_RES.put(700, android.R.color.system_accent3_700)
            ACCENT3_RES.put(800, android.R.color.system_accent3_800)
            ACCENT3_RES.put(900, android.R.color.system_accent3_900)
            ACCENT3_RES.put(1000, android.R.color.system_accent3_1000)
            NEUTRAL1_RES.put(0, android.R.color.system_neutral1_0)
            NEUTRAL1_RES.put(10, android.R.color.system_neutral1_10)
            NEUTRAL1_RES.put(50, android.R.color.system_neutral1_50)
            NEUTRAL1_RES.put(100, android.R.color.system_neutral1_100)
            NEUTRAL1_RES.put(200, android.R.color.system_neutral1_200)
            NEUTRAL1_RES.put(300, android.R.color.system_neutral1_300)
            NEUTRAL1_RES.put(400, android.R.color.system_neutral1_400)
            NEUTRAL1_RES.put(500, android.R.color.system_neutral1_500)
            NEUTRAL1_RES.put(600, android.R.color.system_neutral1_600)
            NEUTRAL1_RES.put(700, android.R.color.system_neutral1_700)
            NEUTRAL1_RES.put(800, android.R.color.system_neutral1_800)
            NEUTRAL1_RES.put(900, android.R.color.system_neutral1_900)
            NEUTRAL1_RES.put(1000, android.R.color.system_neutral1_1000)
            NEUTRAL2_RES.put(0, android.R.color.system_neutral2_0)
            NEUTRAL2_RES.put(10, android.R.color.system_neutral2_10)
            NEUTRAL2_RES.put(50, android.R.color.system_neutral2_50)
            NEUTRAL2_RES.put(100, android.R.color.system_neutral2_100)
            NEUTRAL2_RES.put(200, android.R.color.system_neutral2_200)
            NEUTRAL2_RES.put(300, android.R.color.system_neutral2_300)
            NEUTRAL2_RES.put(400, android.R.color.system_neutral2_400)
            NEUTRAL2_RES.put(500, android.R.color.system_neutral2_500)
            NEUTRAL2_RES.put(600, android.R.color.system_neutral2_600)
            NEUTRAL2_RES.put(700, android.R.color.system_neutral2_700)
            NEUTRAL2_RES.put(800, android.R.color.system_neutral2_800)
            NEUTRAL2_RES.put(900, android.R.color.system_neutral2_900)
            NEUTRAL2_RES.put(1000, android.R.color.system_neutral2_1000)
        }

        private fun SparseIntArray.addColorsToArray(
            swatch: Map<Int, Color>,
            resMap: SparseIntArray
        ) {
            swatch.forEach { (shade, value) ->
                val resId = resMap[shade, -1]
                if (resId != -1) {
                    val (color1) = value as AndroidColor
                    put(resId, color1)
                }
            }
        }
    }
}
