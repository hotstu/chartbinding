package github.hotstu.chartbindingdemo;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private T binding;
    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public T getBinding() {
        return  binding;
    }
}
