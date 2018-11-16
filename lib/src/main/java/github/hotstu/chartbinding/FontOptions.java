package github.hotstu.chartbinding;

import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.components.ComponentBase;

/**
 * @author hglf
 * @since 2018/10/25
 */
public class FontOptions {
    public int color = Color.BLACK;
    public float size = 14f;
    public Typeface typeface = null;

    public void apply(ComponentBase component) {
        if (component == null) {
            return;
        }
        component.setTextColor(color);
        component.setTextSize(size);
        if (typeface != null) {
            component.setTypeface(typeface);
        }
    }

    public FontOptions copy() {
        FontOptions ret = new FontOptions();
        ret.typeface = typeface;
        ret.color = color;
        ret.size = size;
        return ret;
    }
}
