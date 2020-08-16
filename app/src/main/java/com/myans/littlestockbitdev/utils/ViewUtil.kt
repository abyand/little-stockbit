package com.myans.littlestockbitdev.utils

import androidx.core.widget.TextViewCompat
import com.google.android.material.button.MaterialButton

fun MaterialButton.setIconRight() {
    TextViewCompat.setCompoundDrawablesRelative(this, null, null, this.icon, null)
}

