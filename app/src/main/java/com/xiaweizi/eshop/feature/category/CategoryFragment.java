package com.xiaweizi.eshop.feature.category;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaweizi.eshop.R;
import com.xiaweizi.eshop.network.EShopClient;
import com.xiaweizi.eshop.network.core.UICallback;
import com.xiaweizi.eshop.network.entity.CategoryPrimary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 分类页面.
 */
public class CategoryFragment extends Fragment {

    private static final String TAG = "CategoryFragment-->";

    @BindView(R.id.standard_toolbar_title)
    TextView mToolbarTitle;
    @BindView(R.id.standard_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.list_category)
    ListView mListCategory;
    @BindView(R.id.list_children)
    ListView mListChildren;

    private List<CategoryPrimary> mPrimaryList;

    private CategoryAdapter mPrimaryAdapter;
    private ChildrenAdapter mChildrenAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        ButterKnife.bind(this, view);

        mPrimaryList = new ArrayList<>();
        mPrimaryAdapter = new CategoryAdapter();
        mChildrenAdapter = new ChildrenAdapter();

        mListCategory.setAdapter(mPrimaryAdapter);
        mListChildren.setAdapter(mChildrenAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 初始化视图的操作
        initView();
    }

    private void initView() {

        initToolbar();
        //listview展示

        //拿到数据
        if (mPrimaryList != null){
            // 更新UI

        } else {
            // 通过网络请求获取数据
            Call categoryCall = EShopClient.getInstance().getCategory();
            categoryCall.enqueue(new UICallback() {
                @Override
                public void onUiFailure(Call call, IOException e) {
                    Log.e(TAG, "onUiFailure: " + e.getMessage());
                }

                @Override
                public void onUiResponse(Call call, Response response) {
                    try {
                        Log.i(TAG, "onUiResponse: " + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.i(TAG, "onUiResponse: " + e.getMessage());
                    }
                }
            });
        }

    }

    private void initToolbar(){
        //Fragment显示选项菜单
        setHasOptionsMenu(true);

        // 处理toolbar
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        // 处理actionbar不展示默认的标题
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbarTitle.setText(R.string.category_title);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_category, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                // 返回箭头
                getActivity().onBackPressed();
                break;
            case R.id.menu_search:
                // 搜索按钮
                // TODO: 2017/2/24 搜索功能的实现
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    //    public static CategoryFragment newInstance() {
//        return new CategoryFragment();
//    }
//
//    @BindView(R.id.list_category) ListView categoryListView;
//    @BindView(R.id.list_children) ListView childrenListView;
//
//    private CategoryAdapter mCategoryAdapter;
//    private ChildrenAdapter mChildrenAdapter;
//
//    private List<CategoryPrimary> mData;
//
//    @Override protected int getContentViewLayout() {
//        return R.layout.fragment_category;
//    }
//
//    @Override protected void initView() {
//
//        new ToolbarWrapper(this)
//                .setShowBack(false)
//                .setShowTitle(false)
//                .setCustomTitle(R.string.category_title);
//
//        mCategoryAdapter = new CategoryAdapter();
//        categoryListView.setAdapter(mCategoryAdapter);
//
//        mChildrenAdapter = new ChildrenAdapter();
//        childrenListView.setAdapter(mChildrenAdapter);
//
//        if (mData != null) {
//            updateCategory();
//        } else {
//            enqueue(new ApiCategory());
//        }
//    }
//
//    @Override
//    protected void onBusinessResponse(String apiPath, boolean success, ResponseEntity rsp) {
//        if (!ApiPath.CATEGORY.equals(apiPath)) {
//            throw new UnsupportedOperationException(apiPath);
//        }
//
//        if (success) {
//            ApiCategory.Rsp categoryRsp = (ApiCategory.Rsp) rsp;
//            mData = categoryRsp.getData();
//            updateCategory();
//        }
//    }
//
//    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.fragment_category, menu);
//    }
//
//    @Override public boolean onOptionsItemSelected(MenuItem item) {
//        int itemId = item.getItemId();
//        if (itemId == R.id.menu_search) {
//            int position = categoryListView.getCheckedItemPosition();
//            int categoryId = mCategoryAdapter.getItem(position).getId();
//            navigateToSearch(categoryId);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//
//        // 请求失败的简化处理方案: 界面切换时触发重新请求.
//        if (!hidden && mData == null) {
//            enqueue(new ApiCategory());
//        }
//    }
//
//
//    @OnItemClick(R.id.list_category) void onItemClick(int position) {
//        chooseCategory(position);
//    }
//
//    @OnItemClick(R.id.list_children) void onChildrenItemClick(int position) {
//        int categoryId = mChildrenAdapter.getItem(position).getId();
//        navigateToSearch(categoryId);
//    }
//
//    private void updateCategory() {
//        mCategoryAdapter.reset(mData);
//        chooseCategory(0);
//    }
//
//    private void chooseCategory(int position) {
//        // http://stackoverflow.com/questions/27335355/setselected-works-buggy-with-listview
//        categoryListView.setItemChecked(position, true);
//
//        mChildrenAdapter.reset(mCategoryAdapter.getItem(position).getChildren());
//    }
//
//    private void navigateToSearch(int categoryId) {
//        Filter filter = new Filter();
//        filter.setCategoryId(categoryId);
//        Intent intent = SearchGoodsActivity.getStartIntent(getContext(), filter);
//        getActivity().startActivity(intent);
//    }
}
