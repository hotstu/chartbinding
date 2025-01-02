package github.hotstu.chartbinding;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.Utils;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @author hglf
 * @since 2018/10/15
 */
public class BaseChartView extends FrameLayout {

    private ViewDataBinding mBinding;
    private SeriesDescription mSeriesDescrption;

    public BaseChartView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseChartView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseChartView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Utils.init(getContext());
    }

    private ChartData buildData(SeriesDescription series) {
        if (series == null || series.type == SeriesDescription.TYPE_NONE) {
            return null;
        } else if (series.type == SeriesDescription.TYPE_LINE) {
            SeriesDescription<ILineDataSet> realSeries = series;
            return new LineData(realSeries.getDataSet());
        } else if (series.type == SeriesDescription.TYPE_BAR) {
            SeriesDescription<IBarDataSet> realSeries = series;
            return new BarData(realSeries.getDataSet());
        } else if (series.type == SeriesDescription.TYPE_PIE) {
            SeriesDescription<IPieDataSet> realSeries = series;
            return new PieData(realSeries.getDataSet()[0]);
        } else {
            return null;
        }
    }

    private ViewDataBinding BuildChart(SeriesDescription series) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @LayoutRes int layoutId;
        if (series == null || series.type == SeriesDescription.TYPE_NONE) {
            layoutId = R.layout.binding_none_chart;
        } else if (series.type == SeriesDescription.TYPE_LINE) {
            layoutId = R.layout.binding_line_chart;
        } else if (series.type == SeriesDescription.TYPE_BAR) {
            layoutId = R.layout.binding_bar_chart;
        } else if (series.type == SeriesDescription.TYPE_PIE) {
            layoutId = R.layout.binding_pie_chart;
        } else {
            layoutId = R.layout.binding_none_chart;
        }
        return DataBindingUtil.inflate(inflater, layoutId, this, true);
    }

    public void setSeries(SeriesDescription series) {
        if (mSeriesDescrption != null && mSeriesDescrption.type == series.type && mBinding != null) {
            mSeriesDescrption = series;
            mBinding.setVariable(BR.globalOptions, mSeriesDescrption.globalOptions);
            mBinding.setVariable(BR.legendOptions, mSeriesDescrption.legendOptions);
            mBinding.setVariable(BR.xAxisOptions, mSeriesDescrption.xAxisOptions);
            mBinding.setVariable(BR.leftAxisOptions, mSeriesDescrption.leftAxisOptions);
            mBinding.setVariable(BR.rightAxisOptions, mSeriesDescrption.rightAxisOptions);
            mBinding.setVariable(BR.customOptions, mSeriesDescrption.customOptions);
            mBinding.setVariable(BR.animateX, mSeriesDescrption.animateX);
            mBinding.setVariable(BR.animateY, mSeriesDescrption.animateY);
            mBinding.setVariable(BR.data, buildData(mSeriesDescrption));
        } else {
            if (mBinding != null) {
                mBinding.unbind();
            }
            mSeriesDescrption = series;
            removeAllViews();
            if (mSeriesDescrption != null) {
                mBinding = BuildChart(mSeriesDescrption);
                mBinding.setVariable(BR.globalOptions, mSeriesDescrption.globalOptions);
                mBinding.setVariable(BR.legendOptions, mSeriesDescrption.legendOptions);
                mBinding.setVariable(BR.xAxisOptions, mSeriesDescrption.xAxisOptions);
                mBinding.setVariable(BR.leftAxisOptions, mSeriesDescrption.leftAxisOptions);
                mBinding.setVariable(BR.rightAxisOptions, mSeriesDescrption.rightAxisOptions);
                mBinding.setVariable(BR.customOptions, mSeriesDescrption.customOptions);
                mBinding.setVariable(BR.animateX, mSeriesDescrption.animateX);
                mBinding.setVariable(BR.animateY, mSeriesDescrption.animateY);
                mBinding.setVariable(BR.data, buildData(mSeriesDescrption));
            }

        }
        invalidate();
    }
}
