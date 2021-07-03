package ru.ladgertha.clockview

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.withStyledAttributes

enum class Hour(var color: Int) {
    TWELVE(R.styleable.ClockView_clockColor12),
    ONE(R.styleable.ClockView_clockColor1),
    TWO(R.styleable.ClockView_clockColor2),
    THREE(R.styleable.ClockView_clockColor3),
    FOUR(R.styleable.ClockView_clockColor4),
    FIVE(R.styleable.ClockView_clockColor5),
    SIX(R.styleable.ClockView_clockColor6),
    SEVEN(R.styleable.ClockView_clockColor7),
    EIGHT(R.styleable.ClockView_clockColor8),
    NINE(R.styleable.ClockView_clockColor9),
    TEN(R.styleable.ClockView_clockColor10),
    ELEVEN(R.styleable.ClockView_clockColor11);

    companion object {
        fun setColor(context: Context, attrs: AttributeSet?) {
            context.withStyledAttributes(attrs, R.styleable.ClockView) {
                TWELVE.color = getColor(R.styleable.ClockView_clockColor12, 0)
                ONE.color = getColor(R.styleable.ClockView_clockColor1, 0)
                TWO.color = getColor(R.styleable.ClockView_clockColor2, 0)
                THREE.color = getColor(R.styleable.ClockView_clockColor3, 0)
                FOUR.color = getColor(R.styleable.ClockView_clockColor4, 0)
                FIVE.color = getColor(R.styleable.ClockView_clockColor5, 0)
                SIX.color = getColor(R.styleable.ClockView_clockColor6, 0)
                SEVEN.color = getColor(R.styleable.ClockView_clockColor7, 0)
                EIGHT.color = getColor(R.styleable.ClockView_clockColor8, 0)
                NINE.color = getColor(R.styleable.ClockView_clockColor9, 0)
                TEN.color = getColor(R.styleable.ClockView_clockColor10, 0)
                ELEVEN.color = getColor(R.styleable.ClockView_clockColor11, 0)
            }
        }
    }
}