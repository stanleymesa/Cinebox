package com.example.core.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecoration(private val margin: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        with(outRect) {

            if (parent.getChildAdapterPosition(view) == 0) {
                bottom = margin / 2
                return@with
            }
            bottom = margin / 2
            top = margin / 2

        }

    }

}