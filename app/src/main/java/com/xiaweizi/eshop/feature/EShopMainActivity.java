package com.xiaweizi.eshop.feature;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.xiaweizi.eshop.R;
import com.xiaweizi.eshop.feature.cart.CartFragment;
import com.xiaweizi.eshop.feature.category.CategoryFragment;
import com.xiaweizi.eshop.feature.home.HomeFragment;
import com.xiaweizi.eshop.feature.mine.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EShopMainActivity extends AppCompatActivity implements OnTabSelectListener {

    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private CartFragment mCartFragment;
    private MineFragment mMineFragment;

    //当前正在显示的Fragment
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eshop_main);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        //可以看一下Fragmentmanager 里面是不是已经有了这些Fragment
        retrieveFragment();
        // 设置导航选择的监听事件
        bottomBar.setOnTabSelectListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mCurrentFragment != mHomeFragment){
            bottomBar.selectTabWithId(R.id.tab_home);
            return;
        }
        moveTaskToBack(true);
    }

    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                if (mHomeFragment == null) mHomeFragment = new HomeFragment();
                switchFragment(mHomeFragment);
                break;
            case R.id.tab_category:
                if (mCategoryFragment == null) mCategoryFragment = new CategoryFragment();
                switchFragment(mCategoryFragment);
                break;
            case R.id.tab_cart:
                if (mCartFragment == null) mCartFragment = new CartFragment();
                switchFragment(mCartFragment);
                break;
            case R.id.tab_mine:
                if (mMineFragment == null) mMineFragment = new MineFragment();
                switchFragment(mMineFragment);
                break;
            default:
                break;
        }
    }

    private void switchFragment(Fragment target){
        // 如果此时的Fragment就是要替换的Fragment，那么直接退出
        if (mCurrentFragment == target) return;

        // 获得Fragment事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // 判断当前Fragment不为空，则隐藏当前的Fragment
        if (mCurrentFragment != null){
            transaction.hide(mCurrentFragment);
        }

        // 如果要显示的Fragment 如果已经添加了，那么直接show
        if (target.isAdded()){
            transaction.show(target);
        } else {
            // 如果要显示的Fragment 没有被添加，就添加进去
            transaction.add(R.id.layout_container, target, target.getClass().getName());
        }

        //事务进行提交
        transaction.commit();

        //并将要显示的Fragment 设为当前的Fragment
        mCurrentFragment = target;
    }

    // 恢复因为系统重启造成的Fragmentmanager里面恢复的Fragment
    private void retrieveFragment() {
        FragmentManager manager = getSupportFragmentManager();
        mHomeFragment = (HomeFragment) manager.findFragmentByTag("HomeFragment");
        mCategoryFragment = (CategoryFragment) manager.findFragmentByTag("CategoryFragment");
        mCartFragment = (CartFragment) manager.findFragmentByTag("CartFragment");
        mMineFragment = (MineFragment) manager.findFragmentByTag("MineFragment");
    }

}
