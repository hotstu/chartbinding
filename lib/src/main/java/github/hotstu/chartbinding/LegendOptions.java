package github.hotstu.chartbinding;

import com.github.mikephil.charting.components.Legend;

/**
 * @author hglf
 * @since 2018/10/15
 */
public class LegendOptions {
    public boolean enabled = true;
    public FontOptions font = new FontOptions();
    public Legend.LegendForm form = Legend.LegendForm.CIRCLE;
    public LegendPosition position = new LegendPosition();

    public void apply(Legend legend) {
        if (font != null) {
            font.apply(legend);
        }
        if (form != null) {
            legend.setForm(form);
        }
        if (position != null) {
            position.apply(legend);
        }
        legend.setEnabled(enabled);
    }

    public LegendOptions copy() {
        LegendOptions ret = new LegendOptions();
        ret.enabled = enabled;
        ret.form = form;
        ret.font = font;
        ret.position = position;
        return ret;
    }
}
