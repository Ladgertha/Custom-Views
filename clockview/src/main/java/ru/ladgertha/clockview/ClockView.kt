package ru.ladgertha.clockview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

private const val RADIUS_OFFSET_INDICATOR = -35

class DialView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var radius = 0.0f
    private var currentHour = Hour.TWELVE
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }


    init {
        isClickable = true
        Hour.setColor(context, attrs)
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        radius = (width.coerceAtMost(height) / 2.0 * 0.8).toFloat()
    }

    private fun PointF.computeXYForSpeed(position: Hour, radius: Float) {
        val startAngle = 3 * Math.PI / 2.0
        val angle = startAngle + position.ordinal * (Math.PI / 6)
        x = (radius * kotlin.math.cos(angle)).toFloat() + width / 2
        y = (radius * kotlin.math.sin(angle)).toFloat() + height / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBigCircle(canvas)
        drawCenterCircle(canvas)
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        drawHourLine(markerRadius, canvas)
        drawMinutesLine(markerRadius, canvas)
        drawNumbers(markerRadius, canvas)
    }

    private fun drawBigCircle(canvas: Canvas) {
        paint.color = currentHour.color
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
    }

    private fun drawCenterCircle(canvas: Canvas) {
        paint.color = Color.BLACK
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius / 12, paint)
    }

    private fun drawHourLine(markerRadius: Float, canvas: Canvas) {
        paint.strokeWidth = 10.5f
        pointPosition.computeXYForSpeed(Hour.TWELVE, markerRadius - 60)
        canvas.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            pointPosition.x,
            pointPosition.y,
            paint
        )
    }

    private fun drawMinutesLine(markerRadius: Float, canvas: Canvas) {
        pointPosition.computeXYForSpeed(currentHour, markerRadius - 35)
        paint.strokeWidth = 8.5f
        canvas.drawLine(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            pointPosition.x,
            pointPosition.y,
            paint
        )
    }

    private fun drawNumbers(markerRadius: Float, canvas: Canvas) {
        for (hour in Hour.values()) {
            pointPosition.computeXYForSpeed(hour, markerRadius)
            val label = hour.ordinal + 1
            canvas.drawText(label.toString(), pointPosition.x, pointPosition.y + 20, paint)
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        currentHour = if (currentHour.ordinal == Hour.values().size - 1) {
            Hour.TWELVE
        } else {
            Hour.values()[currentHour.ordinal + 1]
        }
        contentDescription = (currentHour.ordinal + 1).toString()

        invalidate()
        return true
    }
}