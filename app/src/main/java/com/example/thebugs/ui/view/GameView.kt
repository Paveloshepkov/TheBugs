package com.example.thebugs.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.thebugs.R

@SuppressLint("ClickableViewAccessibility", "ViewConstructor")
class GameView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val bugGreen: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.greenbug)
    private val bugRed: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.redbug)
    private val bugBlue: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bluebug)
    private val bugPurple: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.purplebug)
    private val bugYellow: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.yellowbug)
    private val bugGrey: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.greybug)

    private var p: Float = 0.0f

    private var booleanGreen: Boolean = true
    private var booleanRed: Boolean = true
    private var booleanBlue: Boolean = true
    private var booleanPurple: Boolean = true
    private var booleanYellow: Boolean = true
    private var booleanGrey: Boolean = true

    var deathTime: Long = 1000

    @Override
    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)
        canvas.apply {
            redBug(this)
            greenBug(this)
            blueBug(this)
            purpleBug(this)
            yellowBug(this)
            greyBug(this)
        }
        p = if (p > 360) 0F else p + 6f
        invalidate()
    }

    private fun scaleRotate(matrix: Matrix) {
        matrix.postScale(3.0f, 3.0f)
        matrix.preRotate(p, 39 / 2.0f, 41 / 2.0f)
    }

    private fun redBug(canvas: Canvas) {
        val matrix = Matrix()
        scaleRotate(matrix)
        matrix.postTranslate(400.0f, 300.0f)
        if (booleanRed) {
            canvas.drawBitmap(bugRed, matrix, null)
        }
    }

    private fun blueBug(canvas: Canvas) {
        val matrix = Matrix()
        scaleRotate(matrix)
        matrix.postTranslate(100.0f, 100.0f)
        if (booleanBlue) {
            canvas.drawBitmap(bugBlue, matrix, null)
        }
    }

    private fun greenBug(canvas: Canvas) {
        val matrix = Matrix()
        scaleRotate(matrix)
        matrix.postTranslate(200.0f, 600.0f)
        if (booleanGreen) {
            canvas.drawBitmap(bugGreen, matrix, null)
        }
    }

    private fun yellowBug(canvas: Canvas) {
        val matrix = Matrix()
        scaleRotate(matrix)
        matrix.postTranslate(750.0f, 200.0f)
        if (booleanYellow) {
            canvas.drawBitmap(bugYellow, matrix, null)
        }
    }

    private fun purpleBug(canvas: Canvas) {
        val matrix = Matrix()
        scaleRotate(matrix)
        matrix.postTranslate(650.0f, 500.0f)
        if (booleanPurple) {
            canvas.drawBitmap(bugPurple, matrix, null)
        }
    }

    private fun greyBug(canvas: Canvas) {
        val matrix = Matrix()
        scaleRotate(matrix)
        matrix.postTranslate(800.0f, 800.0f)
        if (booleanGrey) {
            canvas.drawBitmap(bugGrey, matrix, null)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

                if (event.x > 400f && event.x < 500f && event.y > 300f && event.y < 400f && booleanRed) {
                    booleanRed = false
                    object : CountDownTimer(deathTime, 1000) {

                        override fun onTick(seconds: Long) {

                            seconds / 1000
                        }

                        override fun onFinish() {
                            booleanRed = true
                        }
                    }.start()

                }

                if (event.x > 100f && event.x < 200f && event.y > 100f && event.y < 200f && booleanBlue) {
                    booleanBlue = false
                    object : CountDownTimer(deathTime, 1000) {

                        override fun onTick(seconds: Long) {
                            seconds / 1000
                        }

                        override fun onFinish() {
                            booleanBlue = true
                        }
                    }.start()
                }

                if (event.x > 200f && event.x < 300f && event.y > 600f && event.y < 700f && booleanGreen) {
                    booleanGreen = false
                    object : CountDownTimer(deathTime, 1000) {

                        override fun onTick(seconds: Long) {
                            seconds / 1000
                        }

                        override fun onFinish() {
                            booleanGreen = true
                        }
                    }.start()
                }

                if (event.x > 750f && event.x < 850f && event.y > 200f && event.y < 300f && booleanYellow) {
                    booleanYellow = false
                    object : CountDownTimer(deathTime, 1000) {

                        override fun onTick(seconds: Long) {
                            seconds / 1000
                        }

                        override fun onFinish() {
                            booleanYellow = true
                        }
                    }.start()
                }

                if (event.x > 700f && event.x < 800f && event.y > 500f && event.y < 600f && booleanPurple) {
                    booleanPurple = false
                    object : CountDownTimer(deathTime, 1000) {

                        override fun onTick(seconds: Long) {
                            seconds / 1000
                        }

                        override fun onFinish() {
                            booleanPurple = true
                        }
                    }.start()
                }

                if (event.x > 800f && event.x < 900f && event.y > 800f && event.y < 900f && booleanGrey) {
                    booleanGrey = false
                    object : CountDownTimer(deathTime, 1000) {

                        override fun onTick(seconds: Long) {
                            seconds / 1000
                        }

                        override fun onFinish() {
                            booleanGrey = true
                        }
                    }.start()
                }
            }
        }
        return true
    }
}