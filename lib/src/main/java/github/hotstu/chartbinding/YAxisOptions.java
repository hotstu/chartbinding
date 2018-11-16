package github.hotstu.chartbinding;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * 注意，YAxis 分为left和right，可以单独赋值
 * @author hglf
 * @since 2018/10/24
 */
public class YAxisOptions {
    public boolean drawLabels = true;
    public boolean drawAxisLine = true;
    public boolean drawGridLines = false; // no grid lines
    public boolean drawZeroLine = false; // draw a zero line
    public boolean enabled = true;
    public IAxisValueFormatter valueFormatter;
    public FontOptions font = new FontOptions();
    public AxisLineOptions lineOptions = new AxisLineOptions();

    public void apply(YAxis yAxis) {
        if (yAxis == null) {
            return;
        }
        yAxis.setDrawLabels(drawLabels);
        yAxis.setDrawAxisLine(drawAxisLine);
        yAxis.setDrawGridLines(drawGridLines);
        yAxis.setDrawZeroLine(drawZeroLine);
        if (valueFormatter != null) {
            yAxis.setValueFormatter(valueFormatter);
        }
        if (font != null) {
            font.apply(yAxis);
        }
        if (lineOptions != null) {
            lineOptions.apply(yAxis);
        }
        yAxis.setZeroLineWidth(1);
        yAxis.setEnabled(enabled);
    }
}
