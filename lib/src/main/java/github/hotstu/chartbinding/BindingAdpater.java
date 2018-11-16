package github.hotstu.chartbinding;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.YAxis;

import androidx.databinding.BindingAdapter;

/**
 * @author hglf
 * @since 2018/10/15
 */
public class BindingAdpater {

    @BindingAdapter("bind:globalOptions")
    public static void bindGlobalOptions(Chart chart, GlobalOptions options) {
        if (options == null) {
            return;
        }
        options.apply(chart);
    }
    @BindingAdapter("bind:customOptions")
    public static void bindCustomOptions(Chart chart, CustomOptions options) {
        if (options == null) {
            return;
        }
        options.apply(chart);
    }

    @BindingAdapter("bind:legendOptions")
    public static void bindLegendOptions(Chart chart, LegendOptions options) {
        if (options != null) {
            options.apply(chart.getLegend());
        }
    }

    @BindingAdapter("bind:xAxisOptions")
    public static void bindXAxisOptions(Chart chart, XAxisOptions options) {
        if (options == null || chart instanceof PieChart) {
            //pieChart dont have XAxis
            return;
        }
        options.apply(chart.getXAxis());
    }

    @BindingAdapter("bind:leftAxisOptions")
    public static void bindLeftAxisOptions(Chart chart, YAxisOptions options) {
        if (options == null) {
            return;
        }
        YAxis yAxis = null;
        if (chart instanceof LineChart) {
            yAxis = ((LineChart) chart).getAxisLeft();
        } else if (chart instanceof BarChart) {
            yAxis = ((BarChart) chart).getAxisLeft();
        } else {
            //没有y轴
        }
        options.apply(yAxis);
    }

    @BindingAdapter("bind:rightAxisOptions")
    public static void bindRightAxisOptions(Chart chart, YAxisOptions options) {
        if (options == null) {
            return;
        }
        YAxis yAxis = null;
        if (chart instanceof LineChart) {
            yAxis = ((LineChart) chart).getAxisRight();
        } else if (chart instanceof BarChart) {
            yAxis = ((BarChart) chart).getAxisRight();
        } else {
            //没有y轴
        }
        options.apply(yAxis);
    }

    @BindingAdapter("bind:animateX")
    public static void bindAnimateX(Chart chart, boolean aniamte) {
        if (aniamte) {
            chart.animateX(1000);
        }
    }

    @BindingAdapter("bind:animateY")
    public static void bindAnimateY(Chart chart, boolean aniamte) {
        if (aniamte) {
            chart.animateY(1000);
        }
    }
}
