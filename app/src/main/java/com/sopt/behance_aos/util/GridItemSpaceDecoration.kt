package com.sopt.behance_aos.util

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemSpaceDecoration(private val verticalSpace: Int, private val horizontalSpace:Int) :
        RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val lastPosition = parent.adapter?.itemCount?.minus(1) // 마지막 아이템 가져오기
        outRect.bottom = verticalSpace
        outRect.right = horizontalSpace
        outRect.left = horizontalSpace

        for (i in 0..lastPosition!! step 4){ // 왼쪽 가장자리 여백 제거
           if(position == i) outRect.left = 0
        }

        for (j in 3..lastPosition step 4){ // 오른쪽 가장자리 여백 제거
           if (position == j) outRect.right = 0
        }

    }
}