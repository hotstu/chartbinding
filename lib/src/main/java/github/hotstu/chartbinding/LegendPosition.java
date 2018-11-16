package github.hotstu.chartbinding;

import com.github.mikephil.charting.components.Legend;

/**
 * @author hglf
 * @since 2018/10/26
 */
public class LegendPosition {
    public Legend.LegendHorizontalAlignment horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT;
    public Legend.LegendVerticalAlignment verticalAlignment = Legend.LegendVerticalAlignment.TOP;
    public Legend.LegendOrientation orientation = Legend.LegendOrientation.VERTICAL;
    public Legend.LegendDirection direction = Legend.LegendDirection.LEFT_TO_RIGHT;
    public boolean drawInside = false;

    public static LegendPosition topRight() {
        return new LegendPosition();
    }
    public static LegendPosition bottomLeft() {
        LegendPosition ret = new LegendPosition();
        ret.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT;
        ret.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM;
        ret.orientation = Legend.LegendOrientation.HORIZONTAL;
        return ret;
    }
    public void apply(Legend legend) {
        if (horizontalAlignment != null) {
            legend.setHorizontalAlignment(horizontalAlignment);
        }
        if (verticalAlignment != null) {
            legend.setVerticalAlignment(verticalAlignment);
        }
        if (orientation != null) {
            legend.setOrientation(orientation);
        }
        if (direction != null) {
            legend.setDirection(direction);
        }
        legend.setDrawInside(drawInside);
    }
}
