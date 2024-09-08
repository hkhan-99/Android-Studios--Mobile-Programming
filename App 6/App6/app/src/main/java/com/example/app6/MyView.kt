package com.example.app6

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.text.Typography.half

class MyView : View
{
    var paint = Paint()
    companion object
    {
        private var instance : MyView? = null
        public fun getInstance() : MyView
        {
            return instance!!
        }
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    {
        this.setWillNotDraw(false)
        instance = this
    }
    override fun onDraw(canvas: Canvas)
    {
        super.onDraw(canvas)
        paint.setColor(Color.LTGRAY)
        var width = getWidth()
        var height = getHeight()

        val upperPercentage = 0.25f // 25% for the upper half
        val lowerPercentage = 0.25f // 25% for the lower half

// Calculate the height for the upper and lower rectangles
        val upperRectHeight = height.toFloat() * upperPercentage
        val lowerRectHeight = height.toFloat() * lowerPercentage

// Calculate the y-coordinates for the upper and lower rectangles
        val upperRectTop = 0.0f
        val upperRectBottom = upperRectTop + upperRectHeight

        val lowerRectBottom = height.toFloat()
        val lowerRectTop = lowerRectBottom - lowerRectHeight

// Draw the upper rectangle
        canvas.drawRect(0.0f, upperRectTop, width.toFloat(), upperRectBottom, paint)

// Draw the lower rectangle
        canvas.drawRect(0.0f, lowerRectTop, width.toFloat(), lowerRectBottom, paint)
    }
}
