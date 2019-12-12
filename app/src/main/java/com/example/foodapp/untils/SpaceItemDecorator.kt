package com.example.foodapp.untils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpaceItemDecorator(
    private val space:Int
):RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space
        outRect.bottom = space
        outRect.left = space
        outRect.right = space
    }
}