package com.fukuhara.douglas.f1driverslineup.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.StringRes
import com.fukuhara.douglas.f1driverslineup.R
import com.fukuhara.douglas.f1driverslineup.databinding.CustomDriverAppbarBinding
import com.google.android.material.appbar.AppBarLayout

class CustomDriverToolbar : AppBarLayout {
    constructor(context: Context) : super(context, null) {
        init(context, null)
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private var binding: CustomDriverAppbarBinding? = null

    private fun init(context: Context, attrs: AttributeSet?) {
        binding = CustomDriverAppbarBinding.inflate(LayoutInflater.from(context), this, true)

        attrs?.let { attributeSet ->
            context.theme.obtainStyledAttributes(attributeSet, R.styleable.CustomDriverToolbar, 0, 0).apply {
                try {
                    val title = getString(R.styleable.CustomDriverToolbar_title) ?: ""
                    setText(title)
                } finally {
                    recycle()
                }
            }
        }
    }

    fun setText(@StringRes titleStringRes: Int) {
        binding?.customToolbar?.setTitle(titleStringRes)
    }

    fun setText(title: String) {
        binding?.customToolbar?.title = title
    }

    fun setUpBackButton(listener: (View) -> Unit) {
        binding?.customToolbar?.let { toolbar ->
            toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            toolbar.setOnClickListener(listener)
        }
    }
}
