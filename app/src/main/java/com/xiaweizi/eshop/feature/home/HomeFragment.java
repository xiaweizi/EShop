package com.xiaweizi.eshop.feature.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaweizi.eshop.R;
import com.xiaweizi.eshop.base.utils.LogUtils;

/**
 * 首页面.
 */
public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        LogUtils.i("onCreateView --- HomeFragment");
        return view;
    }
    //
//    private static final int[] PROMOTE_COLORS = {
//            R.color.purple,
//            R.color.orange,
//            R.color.pink,
//            R.color.colorPrimary
//    };
//
//    private static final int[] PROMOTE_PLACE_HOLDER = {
//            R.drawable.mask_round_purple,
//            R.drawable.mask_round_orange,
//            R.drawable.mask_round_pink,
//            R.drawable.mask_round_yellow
//    };
//
//    public static HomeFragment newInstance() {
//        return new HomeFragment();
//    }
//
//    @BindView(R.id.list_home_goods) ListView goodsListView;
//
//    private HomeGoodsAdapter mGoodsAdapter; // 首页商品列表适配器.
//    private BannerAdapter<Banner> mBannerAdapter; //轮播图适配器.
//    private PtrWrapper mPtrWrapper;
//
//    private boolean mBannerRefreshed = false;
//    private boolean mCategoryRefreshed = false;
//
//    private ImageView[] mIvPromotes = new ImageView[4];
//    private TextView mTvPromoteGoods;
//
//    @Override protected int getContentViewLayout() {
//        return R.layout.fragment_home;
//    }
//
//    @Override protected void initView() {
//        new ToolbarWrapper(this).setCustomTitle(R.string.home_title);
//
//        mGoodsAdapter = new HomeGoodsAdapter();
//        goodsListView.setAdapter(mGoodsAdapter);
//
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        View view = inflater.inflate(R.layout.partial_home_header, goodsListView, false);
//
//        BannerLayout bannerLayout = ButterKnife.findById(view, R.id.layout_banner);
//        mBannerAdapter = new BannerAdapter<Banner>() {
//            @Override protected void bind(ViewHolder holder, Banner data) {
//                GlideUtils.loadBanner(data.getPicture(), holder.ivBannerItem);
//            }
//        };
//        bannerLayout.setBannerAdapter(mBannerAdapter);
//
//        mIvPromotes[0] = ButterKnife.findById(view, R.id.image_promote_one);
//        mIvPromotes[1] = ButterKnife.findById(view, R.id.image_promote_two);
//        mIvPromotes[2] = ButterKnife.findById(view, R.id.image_promote_three);
//        mIvPromotes[3] = ButterKnife.findById(view, R.id.image_promote_four);
//
//        mTvPromoteGoods = ButterKnife.findById(view, R.id.text_promote_goods);
//
//        goodsListView.addHeaderView(view);
//
//        mPtrWrapper = new PtrWrapper(this) {
//            @Override public void onRefresh() {
//                mBannerRefreshed = false;
//                mCategoryRefreshed = false;
//
//                // 获取轮播图和促销商品数据.
//                enqueue(new ApiHomeBanner());
//                // 获取首页商品分类数据.
//                enqueue(new ApiHomeCategory());
//            }
//        };
//        mPtrWrapper.postRefresh(50);
//    }
//
//    @Override
//    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
//        switch (apiPath) {
//            case ApiPath.HOME_DATA:
//                mBannerRefreshed = true;
//                if (success) {
//                    ApiHomeBanner.Rsp bannerRsp = (ApiHomeBanner.Rsp) rsp;
//                    mBannerAdapter.reset(bannerRsp.getData().getBanners());
//                    setPromoteGoods(bannerRsp.getData().getGoodsList());
//                }
//                break;
//            case ApiPath.HOME_CATEGORY:
//                mCategoryRefreshed = true;
//                if (success) {
//                    ApiHomeCategory.Rsp categoryRsp = (ApiHomeCategory.Rsp) rsp;
//                    mGoodsAdapter.reset(categoryRsp.getData());
//                }
//                break;
//            default:
//                throw new UnsupportedOperationException(apiPath);
//        }
//
//        if (mBannerRefreshed && mCategoryRefreshed) {
//            // 两个接口的数据都返回了, 才停止下拉刷新.
//            mPtrWrapper.stopRefresh();
//        }
//    }
//
//
//    // 设置显示促销商品.
//    private void setPromoteGoods(final List<SimpleGoods> simpleGoodsList) {
//
//        mTvPromoteGoods.setVisibility(View.VISIBLE);
//
//        for (int i = 0; i < mIvPromotes.length; i++) {
//            mIvPromotes[i].setVisibility(View.VISIBLE);
//            final SimpleGoods simpleGoods = simpleGoodsList.get(i);
//
//            GlideUtils.loadPromote(simpleGoods.getImg(),
//                    mIvPromotes[i],
//                    PROMOTE_PLACE_HOLDER[i],
//                    PROMOTE_COLORS[i]);
//
//            mIvPromotes[i].setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    Intent intent = GoodsActivity.getStartIntent(
//                            getContext(), simpleGoods.getId());
//                    getActivity().startActivity(intent);
//                }
//            });
//        }
//    }
}
