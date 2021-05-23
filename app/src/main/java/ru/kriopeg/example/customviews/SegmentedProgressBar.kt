package ru.kriopeg.example.customviews

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import androidx.core.os.bundleOf
import ru.kriopeg.example.R


class SegmentedProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val segmentPaint: Paint = Paint()

    private val defaultSegmentsCount = 10
    private val defaultSegmentCornerRadius = 0f
    private val defaultSegmentsSpacing = 0f
    private val defaultProgressColor = Color.rgb(0, 0, 0)
    private val defaultStartAlpha = 0
    private val defaultProgress = 0
    private val defaultMaxProgress = 100

    private var segmentsCount = defaultSegmentsCount
    private var segmentCornerRadius = defaultSegmentCornerRadius
    private var segmentsSpacing = defaultSegmentsSpacing
    private var progressColor = defaultProgressColor
    private var startAlpha = defaultStartAlpha
    private var progress = defaultProgress
    private var maxProgress = defaultMaxProgress

    private var minSize: Int
    private var progressPerSegment: Float
    private var alphaPerProgress: Float

    private val INSTANCE_STATE = "instance_state"
    private val INSTANCE_SEGMENTS_COUNT = "instance_segments_count"
    private val INSTANCE_SEGMENTS_SPACING = "instance_segments_spacing"
    private val INSTANCE_SEGMENT_CORNER_RADIUS = "instance_segment_corner_radius"
    private val INSTANCE_PROGRESS_COLOR = "instance_progress_color"
    private val INSTANCE_START_ALPHA = "instance_start_alpha"
    private val INSTANCE_PROGRESS = "instance_progress"
    private val INSTANCE_MAX_PROGRESS = "instance_max_progress"

    init {
        val attributes = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.krio,
            defStyleAttr,
            0
        )
        initByAttributes(attributes)
        attributes.recycle()

        segmentPaint.color = progressColor
        segmentPaint.style = Paint.Style.FILL
        segmentPaint.isAntiAlias = true

        minSize = dp2px(resources, 100f).toInt()
        progressPerSegment = (maxProgress / segmentsCount).toFloat()
        alphaPerProgress = (255 - startAlpha) / progressPerSegment
    }

    private fun initByAttributes(typedArray: TypedArray) {
        segmentsCount = typedArray.getInt(R.styleable.krio_segmentsCount, defaultSegmentsCount)
        progress = typedArray.getInt(R.styleable.krio_progress, defaultProgress)
        maxProgress = typedArray.getInt(R.styleable.krio_maxProgress, defaultMaxProgress)
        progressColor = typedArray.getColor(R.styleable.krio_progressColor, defaultProgressColor)
        segmentsSpacing = typedArray.getDimension(R.styleable.krio_segmentsSpacing, defaultSegmentsSpacing)
        segmentCornerRadius = typedArray.getDimension(
            R.styleable.krio_segmentCornerRadius,
            defaultSegmentCornerRadius
        )
        startAlpha = typedArray.getInt(R.styleable.krio_startAlpha, defaultStartAlpha)
    }

    fun setSegmentsCount(count: Int) {
        this.segmentsCount = count
        invalidate()
    }

    fun setSegmentsSpacing(spacing: Float) {
        this.segmentsSpacing = spacing
        invalidate()
    }

    fun setSegmentCornerRadius(radius: Float) {
        this.segmentCornerRadius = radius
        invalidate()
    }

    fun setProgressColor(color: Int) {
        this.progressColor = color
        invalidate()
    }

    fun setStartAlpha(alpha: Int) {
        this.startAlpha = alpha
        invalidate()
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }

    fun setMaxProgress(progress: Int) {
        this.maxProgress = progress
        invalidate()
    }

    fun getSegmentsCount() = segmentsCount

    fun getSegmentsSpacing() = segmentsSpacing

    fun getSegmentCornerRadius() = segmentCornerRadius

    fun setProgressColor() = progressColor

    fun setStartAlpha() = startAlpha

    fun setProgress() = progress

    fun setMaxProgress() = maxProgress

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val segmentWidth = (width - segmentsSpacing * (segmentsCount - 1)) / segmentsCount
        val segmentHeight = height

        for (index in 0 until segmentsCount) {
            if (progress >= (index + 1) * progressPerSegment) {
                segmentPaint.alpha = 255;
            } else if (progress >= index * progressPerSegment) {
                segmentPaint.alpha = (startAlpha + (progress - (index * progressPerSegment)) * alphaPerProgress).toInt()
            } else {
                segmentPaint.alpha = startAlpha;
            }

            canvas.drawRoundRect(index * (segmentWidth + segmentsSpacing),
                0f,
                segmentWidth * (index + 1) + index * segmentsSpacing,
                segmentHeight.toFloat(),
                segmentCornerRadius,
                segmentCornerRadius,
                segmentPaint)
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        return bundleOf(
            Pair(INSTANCE_STATE, super.onSaveInstanceState()),
            Pair(INSTANCE_MAX_PROGRESS, maxProgress),
            Pair(INSTANCE_PROGRESS, progress),
            Pair(INSTANCE_PROGRESS_COLOR, progressColor),
            Pair(INSTANCE_SEGMENTS_COUNT, segmentsCount),
            Pair(INSTANCE_SEGMENTS_SPACING, segmentsSpacing),
            Pair(INSTANCE_SEGMENT_CORNER_RADIUS, segmentCornerRadius),
            Pair(INSTANCE_START_ALPHA, startAlpha)
        )
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        (state as? Bundle)?.let {
            maxProgress = it.getInt(INSTANCE_MAX_PROGRESS, defaultMaxProgress)
            progress = it.getInt(INSTANCE_PROGRESS, defaultProgress)
            progressColor = it.getInt(INSTANCE_PROGRESS_COLOR, defaultProgressColor)
            segmentsCount = it.getInt(INSTANCE_SEGMENTS_COUNT, defaultSegmentsCount)
            segmentsSpacing = it.getFloat(INSTANCE_SEGMENTS_SPACING, defaultSegmentsSpacing)
            segmentCornerRadius = it.getFloat(
                INSTANCE_SEGMENT_CORNER_RADIUS,
                defaultSegmentCornerRadius
            )
            startAlpha = it.getInt(INSTANCE_START_ALPHA, defaultStartAlpha)
            super.onRestoreInstanceState(it.getParcelable(INSTANCE_STATE))
            return
        }
        super.onRestoreInstanceState(state)
    }

    private fun dp2px(resources: Resources, dp: Float): Float {
        val scale: Float = resources.displayMetrics.density
        return dp * scale + 0.5f
    }

    private fun sp2px(resources: Resources, sp: Float): Float {
        val scale: Float = resources.displayMetrics.scaledDensity
        return sp * scale
    }

}