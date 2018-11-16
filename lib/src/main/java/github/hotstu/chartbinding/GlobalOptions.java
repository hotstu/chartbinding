package github.hotstu.chartbinding;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Description;

/**
 * @author hglf
 * @since 2018/10/15
 */
public class GlobalOptions {

    public Description description;
    public String noDataText = "没有数据";


    public void apply(Chart chart) {
        if (chart == null) {
            return;
        }
        if (description != null) {
            chart.setDescription(description);
        } else {
            Description disabled = new Description();
            disabled.setEnabled(false);
            chart.setDescription(disabled);
        }
        chart.setNoDataText(noDataText);
    }
}
