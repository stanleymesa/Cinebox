package com.example.cinebox.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HomeItemDecoration(private val margin: Int): RecyclerView.ItemDecoration() {

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
                return
            }
            if (parent.getChildAdapterPosition(view) == parent.childCount - 1) {
                left = margin / 2
                return
            }
            left = margin / 2
            right = margin / 2

        }

    }

}