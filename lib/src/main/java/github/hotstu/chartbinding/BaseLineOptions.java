package github.hotstu.chartbinding;

import android.graphics.Color;
import android.graphics.DashPathEffect;

/**
 * @author hglf
 * @since 2018/10/25
 */
public abstract class BaseLineOptions<T> {
    public float width = 1;
    public int color = Color.BLACK;
    public float axisMinimum = 0f;
    public float axisMaximum = -0.01f;
    public DashPathEffect dashPathEffect;

    public abstract void apply(T taget);
}
