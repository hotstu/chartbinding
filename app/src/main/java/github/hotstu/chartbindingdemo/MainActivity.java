package github.hotstu.chartbindingdemo;

import android.os.Bundle;

import com.github.mikephil.charting.utils.Utils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import github.hotstu.chartbindingdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.init(this);//MPAndroidChart ;
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TypedRecyclerAdapter adapter = new TypedRecyclerAdapter();
        binding.recyclerView.setAdapter(adapter);
        ChartViewModel chartViewModel = ViewModelProviders.of(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new ChartViewModel(getApplication());
            }
        }).get(ChartViewModel.class);
        chartViewModel.lists.observe(this, seriesDescriptions -> {
            adapter.setDataSet(seriesDescriptions);
        });
    }
}
