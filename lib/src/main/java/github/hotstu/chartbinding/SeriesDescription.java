package github.hotstu.chartbinding;

import com.github.mikephil.charting.interfaces.datasets.IDataSet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.IntDef;

/**
 * @author hglf
 * @since 2018/10/15
 */
public class SeriesDescription<T extends IDataSet> {
    public static final int TYPE_NONE = 0;
    public static final int TYPE_LINE = 1;
    public static final int TYPE_BAR = 2;
    public static final int TYPE_PIE = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_NONE, TYPE_LINE, TYPE_BAR, TYPE_PIE})
    public @interface chartType { }

    final @chartType  int  type;

    final T[] dataSet;

    public GlobalOptions globalOptions;
    public XAxisOptions xAxisOptions;
    public YAxisOptions leftAxisOptions;
    public YAxisOptions rightAxisOptions;
    public LegendOptions legendOptions;
    public CustomOptions customOptions;
    public boolean animateX = true;
    public boolean animateY = true;

    public SeriesDescription(int type, T... dataSet) {
        this.type = type;
        this.dataSet = dataSet;
    }

    public T[] getDataSet() {
        return dataSet;
    }
}
