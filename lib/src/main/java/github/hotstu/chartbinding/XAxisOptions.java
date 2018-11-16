package github.hotstu.chartbinding;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * @author hglf
 * @since 2018/10/24
 */
public class XAxisOptions {
    public XAxis.XAxisPosition position = XAxis.XAxisPosition.BOTTOM;
    public boolean enabled  = true;
    public boolean drawAxisLine = true;
    public boolean drawGridLine = false;
    public float granularity = 1;
    public float labelRotationAngle = 0;
    public IAxisValueFormatter valueFormatter = null;
    public FontOptions font = new FontOptions();
    public AxisLineOptions lineOptions = new AxisLineOptions();


    public void apply(XAxis xAxis) {
        if (xAxis == null) {
            return;
        }
        if (valueFormatter != null) {
            xAxis.setValueFormatter(valueFormatter);
        }
        xAxis.setEnabled(enabled);
        xAxis.setPosition(position);
        xAxis.setDrawAxisLine(drawAxisLine);
        xAxis.setDrawGridLines(drawGridLine);
        xAxis.setGranularity(granularity);
        xAxis.setLabelRotationAngle(labelRotationAngle);
        if (font != null) {
            font.apply(xAxis);
        }
        if (lineOptions != null) {
            lineOptions.apply(xAxis);
        }
    }

    public XAxisOptions copy() {
        XAxisOptions ret = new XAxisOptions();
        ret.position = position;
        ret.font = font;
        ret.drawAxisLine = drawAxisLine;
        ret.drawGridLine = drawGridLine;
        ret.valueFormatter = valueFormatter;
        ret.granularity = granularity;
        return ret;
    }
}
