package com.bamboo.sample.ui.aty;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bamboo.common.base.BaseActivity;
import com.bamboo.common.view.PileAvertView;
import com.bamboo.sample.R;
import com.bamboo.sample.adapter.PileAdapter;
import com.bamboo.sample.bean.HeadBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
@Route(path = "/test/pv")
public class PileImageActivity extends BaseActivity {
    @BindView(R.id.pileAvertView)
   PileAvertView mPileAvertView;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    PileAdapter mPileAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.bbl_pile_image_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        List<String> urls=new ArrayList<>();
        urls.clear();
        urls.add("http://tupian.qqw21.com/article/UploadPic/2020-4/2020462134735274.jpg");
        urls.add("http://f.hiphotos.baidu.com/zhidao/pic/item/ac345982b2b7d0a2a14af2cbcbef76094a369ae1.jpg");
        urls.add("http://cdn.duitang.com/uploads/item/201505/17/20150517130829_BsaMJ.jpeg");
        urls.add("http://b-ssl.duitang.com/uploads/item/201706/17/20170617202755_vasTA.thumb.700_0.jpeg");
        urls.add("http://image.biaobaiju.com/uploads/20180801/21/1533131259-UXyVrsukOo.jpg");
//设置数据源
        mPileAvertView.setAvertImages(urls);

        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        linearLayoutManager.setStackFromEnd(false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mPileAdapter=new PileAdapter(getData());
        mRecyclerView.setAdapter(mPileAdapter);
    }

    private List<HeadBean> getData() {
        List<HeadBean> items=new ArrayList<>();
        items.add(new HeadBean("http://tupian.qqw21.com/article/UploadPic/2020-4/2020462134735274.jpg"));
        items.add(new HeadBean("http://f.hiphotos.baidu.com/zhidao/pic/item/ac345982b2b7d0a2a14af2cbcbef76094a369ae1.jpg"));
        items.add(new HeadBean("http://cdn.duitang.com/uploads/item/201505/17/20150517130829_BsaMJ.jpeg"));
        items.add(new HeadBean("http://b-ssl.duitang.com/uploads/item/201706/17/20170617202755_vasTA.thumb.700_0.jpeg"));
        items.add(new HeadBean("http://image.biaobaiju.com/uploads/20180801/21/1533131259-UXyVrsukOo.jpg")); items.add(new HeadBean("http://tupian.qqw21.com/article/UploadPic/2020-4/2020462134735274.jpg"));
        items.add(new HeadBean("http://f.hiphotos.baidu.com/zhidao/pic/item/ac345982b2b7d0a2a14af2cbcbef76094a369ae1.jpg"));
        items.add(new HeadBean("http://cdn.duitang.com/uploads/item/201505/17/20150517130829_BsaMJ.jpeg"));
        items.add(new HeadBean("http://b-ssl.duitang.com/uploads/item/201706/17/20170617202755_vasTA.thumb.700_0.jpeg"));
        items.add(new HeadBean("http://image.biaobaiju.com/uploads/20180801/21/1533131259-UXyVrsukOo.jpg")); items.add(new HeadBean("http://tupian.qqw21.com/article/UploadPic/2020-4/2020462134735274.jpg"));
        items.add(new HeadBean("http://f.hiphotos.baidu.com/zhidao/pic/item/ac345982b2b7d0a2a14af2cbcbef76094a369ae1.jpg"));
        items.add(new HeadBean("http://cdn.duitang.com/uploads/item/201505/17/20150517130829_BsaMJ.jpeg"));
        items.add(new HeadBean("http://b-ssl.duitang.com/uploads/item/201706/17/20170617202755_vasTA.thumb.700_0.jpeg"));
        items.add(new HeadBean("http://image.biaobaiju.com/uploads/20180801/21/1533131259-UXyVrsukOo.jpg")); items.add(new HeadBean("http://tupian.qqw21.com/article/UploadPic/2020-4/2020462134735274.jpg"));
        items.add(new HeadBean("http://f.hiphotos.baidu.com/zhidao/pic/item/ac345982b2b7d0a2a14af2cbcbef76094a369ae1.jpg"));
        items.add(new HeadBean("http://cdn.duitang.com/uploads/item/201505/17/20150517130829_BsaMJ.jpeg"));
        items.add(new HeadBean("http://b-ssl.duitang.com/uploads/item/201706/17/20170617202755_vasTA.thumb.700_0.jpeg"));
        items.add(new HeadBean("http://image.biaobaiju.com/uploads/20180801/21/1533131259-UXyVrsukOo.jpg")); items.add(new HeadBean("http://tupian.qqw21.com/article/UploadPic/2020-4/2020462134735274.jpg"));
        items.add(new HeadBean("http://f.hiphotos.baidu.com/zhidao/pic/item/ac345982b2b7d0a2a14af2cbcbef76094a369ae1.jpg"));
        items.add(new HeadBean("http://cdn.duitang.com/uploads/item/201505/17/20150517130829_BsaMJ.jpeg"));
        items.add(new HeadBean("http://b-ssl.duitang.com/uploads/item/201706/17/20170617202755_vasTA.thumb.700_0.jpeg"));
        items.add(new HeadBean("http://image.biaobaiju.com/uploads/20180801/21/1533131259-UXyVrsukOo.jpg")); items.add(new HeadBean("http://tupian.qqw21.com/article/UploadPic/2020-4/2020462134735274.jpg"));
        items.add(new HeadBean("http://f.hiphotos.baidu.com/zhidao/pic/item/ac345982b2b7d0a2a14af2cbcbef76094a369ae1.jpg"));
        items.add(new HeadBean("http://cdn.duitang.com/uploads/item/201505/17/20150517130829_BsaMJ.jpeg"));
        items.add(new HeadBean("http://b-ssl.duitang.com/uploads/item/201706/17/20170617202755_vasTA.thumb.700_0.jpeg"));
        items.add(new HeadBean("http://image.biaobaiju.com/uploads/20180801/21/1533131259-UXyVrsukOo.jpg"));

        return items;
    }
}
