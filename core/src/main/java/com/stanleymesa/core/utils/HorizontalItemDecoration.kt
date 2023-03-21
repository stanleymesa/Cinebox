package com.stanleymesa.core.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecoration(private val margin: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        with(outRect) {

            if (parent.getChildAdapterPosition(view) == 0) {
                right = margin / 2
                return@with
            }
            left = margin / 2
            right = margin / 2

        }

    }

}