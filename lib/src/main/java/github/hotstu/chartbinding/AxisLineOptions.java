package github.hotstu.chartbinding;

import com.github.mikephil.charting.components.AxisBase;

/**
 * @author hglf
 * @since 2018/10/25
 */
public class AxisLineOptions extends BaseLineOptions<AxisBase> {
    public void apply(AxisBase axisBase) {
        if (axisBase == null) {
            return;
        }
        axisBase.setAxisLineColor(color);
        axisBase.setAxisLineWidth(width);
        axisBase.setAxisMinimum(axisMinimum);
        if (axisMaximum != -0.01f) {
            axisBase.setAxisMaximum(axisMaximum);
        }
        if (dashPathEffect != null) {
            axisBase.setAxisLineDashedLine(dashPathEffect);
        }
    }
}
