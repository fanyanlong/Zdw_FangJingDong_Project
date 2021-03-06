package bawei.com.zhangduweijingdongproject.zdwview.zdwadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import bawei.com.zhangduweijingdongproject.R;
import bawei.com.zhangduweijingdongproject.zdwmodel.bean.ChildFenLeiBean;
import bawei.com.zhangduweijingdongproject.zdwview.zdwactivity.ProductListActivity;
import bawei.com.zhangduweijingdongproject.zdwview.zdwholder.FenLeiRecyclerOutHolder;
import bawei.com.zhangduweijingdongproject.zdwview.zdwiview.OnItemListner;

/**
 * Author:张杜伟
 * Email:58588471@qq.com
 * Demand:Shopping
 */


public class FenLeiRecyclerOutAdapter extends RecyclerView.Adapter<FenLeiRecyclerOutHolder>{
    private ChildFenLeiBean childFenLeiBean;
    private Context context;

    public FenLeiRecyclerOutAdapter(Context context, ChildFenLeiBean childFenLeiBean) {
        this.context = context;
        this.childFenLeiBean = childFenLeiBean;
    }

    @Override
    public FenLeiRecyclerOutHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.reycler_out_item_layout,null);
        FenLeiRecyclerOutHolder fenLeiRecyclerOutHolder = new FenLeiRecyclerOutHolder(view);

        return fenLeiRecyclerOutHolder;
    }

    @Override
    public void onBindViewHolder(FenLeiRecyclerOutHolder holder, int position) {

        final ChildFenLeiBean.DataBean dataBean = childFenLeiBean.getData().get(position);
        //赋值...设置适配器
        holder.recycler_out_text.setText(dataBean.getName());

        //设置布局管理器
        holder.recycler_innner.setLayoutManager(new GridLayoutManager(context,3));
        //给里面的recyclerView设置适配器
        FenRecyclerInnerAdapter fenRecyclerInnerAdapter = new FenRecyclerInnerAdapter(context, dataBean);
        holder.recycler_innner.setAdapter(fenRecyclerInnerAdapter);

        //设置条目的点击事件
        fenRecyclerInnerAdapter.setOnItemListner(new OnItemListner() {
            @Override
            public void onItemClick(int i) {
                //Toast.makeText(context,dataBean.getList().get(i).getName(),Toast.LENGTH_SHORT).show();
                //做跳转传值的操作....跳转的是商品列表页面...商品列表页面调用的而是搜索关键字的接口
                Intent intent = new Intent(context, ProductListActivity.class);

                //传值...关键词
                intent.putExtra("keywords",dataBean.getList().get(i).getName());

                context.startActivity(intent);

            }

            @Override
            public void onItemLongClick(int i) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return childFenLeiBean.getData().size();
    }
}
