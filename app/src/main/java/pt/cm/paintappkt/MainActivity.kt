package pt.cm.paintappkt

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

//TODO
// Detect a double tap
// Detect a long press
//TODO
// when double tap is detected the app should enter in "erase mode"
// this mode changes the color of the paint to color of the background
//TODO
// when a long press is detect change the background color with a random one

class MainActivity : AppCompatActivity() {

    private val DEBUG_TAG = "Gestures"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val paintCanvas = SingleTouchEventView(applicationContext, null)
        setContentView(paintCanvas)// adds the created view to the screen
    }

    internal inner class SingleTouchEventView(context: Context, attrs: AttributeSet?) :
        View(context, attrs) {
        private val paint = Paint()
        private val path = Path()

        init {

            paint.isAntiAlias = true
            paint.strokeWidth = 20f
            paint.color = Color.BLACK
            paint.style = Paint.Style.STROKE
            paint.strokeJoin = Paint.Join.ROUND
        }


        override fun onDraw(canvas: Canvas) {
            canvas.drawPath(path, paint)// draws the path with the paint
        }

        override fun performClick(): Boolean {
            return super.performClick()
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            val eventX = event.x
            val eventY = event.y
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    path.moveTo(eventX, eventY)// updates the path initial point
                    return true
                }
                MotionEvent.ACTION_MOVE -> path.lineTo(
                    eventX,
                    eventY
                )// makes a line to the point each time this event is fired
                MotionEvent.ACTION_UP// when you lift your finger
                ->

                    performClick()
                else -> return false
            }

            // Schedules a repaint.
            invalidate()
            return true
        }
    }
}
