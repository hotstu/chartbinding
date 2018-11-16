package github.hotstu.chartbindingdemo;

import android.app.Application;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.SystemClock;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import github.hotstu.chartbinding.FontOptions;
import github.hotstu.chartbinding.GlobalOptions;
import github.hotstu.chartbinding.LegendOptions;
import github.hotstu.chartbinding.SeriesDescription;
import github.hotstu.chartbinding.XAxisOptions;
import github.hotstu.chartbinding.YAxisOptions;

/**
 * @author hglf
 * @since 2018/10/24
 */
public class ChartViewModel extends AndroidViewModel {
    private final MutableLiveData<Object> mQuery;
    private final int[] colors;
    private final XAxisOptions xAxisOptions;
    private final YAxisOptions disabled;
    private final GlobalOptions globalOptions;
    private final YAxisOptions leftAxisOptions;
    private final LegendOptions legendOptions;
    public final LiveData<List<SeriesDescription>> lists;
    private final MediatorLiveData<List<ChartMapping>> raw;

    public ChartViewModel(Application application) {
        super(application);
        mQuery = new MutableLiveData<>();

        raw = new MediatorLiveData<>();

        TypedArray a = application.getTheme().obtainStyledAttributes(R.style.defaultChartBindingStyle, R.styleable.ChartBinding);

        colors = new int[]{
                a.getColor(R.styleable.ChartBinding_chart_item_a, Color.BLACK),
                a.getColor(R.styleable.ChartBinding_chart_item_b, Color.BLACK),
                a.getColor(R.styleable.ChartBinding_chart_item_c, Color.BLACK),
                a.getColor(R.styleable.ChartBinding_chart_item_d, Color.BLACK),
                a.getColor(R.styleable.ChartBinding_chart_item_e, Color.BLACK),
                a.getColor(R.styleable.ChartBinding_chart_item_f, Color.BLACK),
                a.getColor(R.styleable.ChartBinding_chart_item_g, Color.BLACK),
                a.getColor(R.styleable.ChartBinding_chart_item_h, Color.BLACK),
                a.getColor(R.styleable.ChartBinding_chart_item_i, Color.BLACK),
                a.getColor(R.styleable.ChartBinding_chart_item_j, Color.BLACK)
        };

        int colorText = a.getColor(R.styleable.ChartBinding_chart_text, Color.BLACK);
        int colorSubText = a.getColor(R.styleable.ChartBinding_chart_subtext, Color.BLACK);
        int colorBackGround = a.getColor(R.styleable.ChartBinding_chart_background, Color.WHITE);
        a.recycle();


        FontOptions globalTextStyle = new FontOptions();
        globalTextStyle.color = colorText;
        xAxisOptions = new XAxisOptions();
        xAxisOptions.font = globalTextStyle;
        disabled = new YAxisOptions();
        disabled.enabled = false;
        globalOptions = new GlobalOptions();
        leftAxisOptions = new YAxisOptions();
        legendOptions = new LegendOptions();

        lists = Transformations.map(raw, input -> {
            List<SeriesDescription> ret = new ArrayList<>();
            for (int i = 0; i < input.size(); i++) {
                ChartMapping mapping = input.get(i);
                if (mapping.data instanceof ChartMapping.PieData) {
                    ret.add(buildPieData(mapping.label, (ChartMapping.PieData) mapping.data));
                }
                if (mapping.data instanceof ChartMapping.BarData) {
                    ret.add(buildBarData(mapping.label, (ChartMapping.BarData) mapping.data));
                }
                if (mapping.data instanceof ChartMapping.LineData) {
                    ret.add(buildLineData(mapping.label, (ChartMapping.LineData) mapping.data));
                }
                //TODO other type
            }
            return ret;
        });
        initData();
    }


    private void initData() {
        Random r = new Random(SystemClock.elapsedRealtimeNanos());
        List<ChartMapping> ret = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ChartMapping c = new ChartMapping();
            int typeInt = r.nextInt(3);
            String type = "line";
            switch (typeInt) {
                case 0:
                    type = "pie";
                    c.data = new ChartMapping.PieData();
                    ((ChartMapping.PieData) c.data).legend = new String[10];
                    ((ChartMapping.PieData) c.data).value = new float[10];
                    for (int j = 0; j < 10; j++) {
                        int value = r.nextInt(100);
                        ((ChartMapping.PieData) c.data).legend[j] = j+ "";
                        ((ChartMapping.PieData) c.data).value[j] = value;
                    }
                    break;
                case 1:
                    type = "bar";
                    c.data = new ChartMapping.BarData();
                    ((ChartMapping.BarData) c.data).legend = new String[10];
                    ((ChartMapping.BarData) c.data).value = new float[10];
                    for (int j = 0; j < 10; j++) {
                        int value = r.nextInt(100);
                        ((ChartMapping.BarData) c.data).legend[j] = j+ "";
                        ((ChartMapping.BarData) c.data).value[j] = value;
                    }
                    break;
                case 2:
                    type = "line";
                    c.data = new ChartMapping.LineData();
                    ((ChartMapping.LineData) c.data).legend = new String[10];
                    ((ChartMapping.LineData) c.data).value = new float[10];
                    for (int j = 0; j < 10; j++) {
                        int value = r.nextInt(100);
                        ((ChartMapping.LineData) c.data).legend[j] = j+ "";
                        ((ChartMapping.LineData) c.data).value[j] = value;
                    }
                    break;
            }
            String label = type + "" + i;
            c.type = type;
            c.label = label;
            ret.add(c);
        }

        raw.postValue(ret);
    }


    private SeriesDescription<IPieDataSet> buildPieData(String label, ChartMapping.PieData data) {
        List<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < data.legend.length && i < data.value.length; i++) {
            entries.add(new PieEntry(data.value[i], data.legend[i] + ""));
        }
        PieDataSet dataSet = new PieDataSet(entries, label);
        stylePieDataSet(dataSet);
        SeriesDescription<IPieDataSet> ds = new SeriesDescription<>(SeriesDescription.TYPE_PIE, dataSet);
        ds.globalOptions = globalOptions;
        ds.leftAxisOptions = leftAxisOptions;
        ds.xAxisOptions = xAxisOptions;
        ds.rightAxisOptions = disabled;
        LegendOptions legendOptions = this.legendOptions.copy();
        legendOptions.enabled = false;
        ds.legendOptions = legendOptions;
        ds.customOptions = chart -> {
            if (chart instanceof PieChart) {
                PieChart mChart = (PieChart) chart;
                mChart.setEntryLabelColor(Color.BLACK);
                mChart.setDrawCenterText(true);
                mChart.setExtraOffsets(20.f, 0.f, 20.f, 0.f);
                mChart.setCenterText(label);
            }
        };
        return ds;
    }

    private SeriesDescription<IBarDataSet> buildBarData(String label, ChartMapping.BarData data) {
        List<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < data.legend.length && i < data.value.length; i++) {
            entries.add(new BarEntry(i, data.value[i]));
        }
        IAxisValueFormatter formatter = (value, axis) -> data.legend[((int) value)] + "";

        BarDataSet dataSet = new BarDataSet(entries, label);
        styleBarDataSet(dataSet);
        SeriesDescription<IBarDataSet> ds = new SeriesDescription<>(SeriesDescription.TYPE_BAR, dataSet);
        ds.globalOptions = globalOptions;
        ds.leftAxisOptions = leftAxisOptions;
        XAxisOptions copy = xAxisOptions.copy();
        copy.valueFormatter = formatter;
        ds.xAxisOptions = copy;
        ds.rightAxisOptions = disabled;
        LegendOptions copy1 = legendOptions.copy();
        copy1.position.drawInside = true;
        ds.legendOptions = copy1;
        return ds;
    }

    private SeriesDescription<ILineDataSet> buildLineData(String label, ChartMapping.LineData data) {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < data.legend.length && i < data.value.length; i++) {
            entries.add(new Entry(i, data.value[i]));
        }
        IAxisValueFormatter formatter = (value, axis) -> data.legend[((int) value)] + "";
        LineDataSet dataSet = new LineDataSet(entries, label);
        styleLineDataSet(dataSet);
        SeriesDescription<ILineDataSet> ds = new SeriesDescription<>(SeriesDescription.TYPE_LINE, dataSet);
        ds.globalOptions = globalOptions;
        ds.leftAxisOptions = leftAxisOptions;
        XAxisOptions copy = xAxisOptions.copy();
        copy.valueFormatter = formatter;
        ds.xAxisOptions = copy;
        ds.rightAxisOptions = disabled;
        LegendOptions copy1 = legendOptions.copy();
        copy1.position.drawInside = true;
        ds.legendOptions = copy1;
        return ds;
    }

    private void styleLineDataSet(LineDataSet dataSet) {
        int color = colors[0];
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12);
        dataSet.setCircleRadius(5);
        dataSet.setCircleHoleRadius(4);
        dataSet.setCircleColor(color);
        dataSet.disableDashedLine();
        dataSet.disableDashedHighlightLine();
        dataSet.setColor(color);
        dataSet.setDrawValues(false);
        dataSet.setLineWidth(2);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
    }

    private void styleBarDataSet(BarDataSet dataSet) {
        dataSet.setColors(colors);
        dataSet.setValueTextSize(12);
        dataSet.setColor(colors[0]);
        dataSet.setDrawValues(false);
    }

    private void stylePieDataSet(PieDataSet dataSet) {
        dataSet.setColors(colors);
        dataSet.setSelectionShift(15);
        dataSet.setDrawValues(true);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueTextSize(12f);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueLineColor(Color.BLACK);
    }
}
