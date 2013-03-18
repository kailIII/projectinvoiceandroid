/**
 * Copyright 2012 Viettel Telecom. All rights reserved.
 * HAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hector.invoice.views;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.hector.invoice.R;
import com.hector.invoice.common.BaseFragmentActivity;
import com.hector.invoice.common.ImageUtil;
import com.hector.invoice.common.PagerAdapter;
import com.hector.invoice.common.TabFactory;
import com.hector.invoice.common.TabInfo;
import com.hector.invoice.constant.Constants;
import com.hector.invoice.constant.IntentConstants;

/**
 * Mo ta muc dich cua lop (interface)
 * 
 * @author: HaiTC3
 * @version: 1.0
 * @since: 1.1
 */
public class TabExportInvoiceOrder extends BaseFragmentActivity implements
		OnPageChangeListener, OnTabChangeListener, OnClickListener {
	// tab host
	private TabHost mTabHost;
	// view page
	private ViewPager mViewPager;
	private HashMap<String, TabInfo> mapTabInfo = new HashMap<String, TabInfo>();
	private PagerAdapter mPagerAdapter;
	// current screen index
	public int currentScreenIndex = -1;
	Button btBack;
	Button btEmail;
	RelativeLayout rllMainTopMenu;
	TextView tvDescriptionTopic;
	TextView tvTopicTitle;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jobaid.jobaids.BaseFragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// Inflate the layout
		setContentView(R.layout.layout_export_invoice_order_view);

		this.initControlView();
		this.initialiseTabHost(savedInstanceState);
		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState
					.getString(IntentConstants.INTENT_CURRENT_TAB_SELECTED));
			this.currentScreenIndex = mTabHost.getCurrentTab();
		} else {
			this.currentScreenIndex = 0;
		}
		// Intialise ViewPager
		this.intialiseViewPager();
	}

	/**
	 * 
	 * init control view
	 * 
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Oct 24, 2012
	 */
	public void initControlView() {
		btBack = (Button) super.findViewById(R.id.btBack);
		btBack.setOnClickListener(this);
		btEmail = (Button) super.findViewById(R.id.btEmail);
		btEmail.setOnClickListener(this);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
	 */
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString(IntentConstants.INTENT_CURRENT_TAB_SELECTED,
				mTabHost.getCurrentTabTag()); // save the tab
		// selected
		super.onSaveInstanceState(outState);
	}

	/**
	 * 
	 * init page
	 * 
	 * @author: HaiTC3
	 * @return: void
	 * @throws:
	 * @since: Mar 18, 2013
	 */
	private void intialiseViewPager() {

		List<Fragment> fragments = new Vector<Fragment>();
		RechnungExportView view1 = RechnungExportView
				.newInstance("bundle from parent");
		LieferscheinExportView view2 = LieferscheinExportView
				.newInstance("bundle from parent");
		AngebotExportView view3 = AngebotExportView
				.newInstance("bundle from parent");

		fragments.add(view1);
		fragments.add(view2);
		fragments.add(view3);
		this.mPagerAdapter = new PagerAdapter(
				super.getSupportFragmentManager(), fragments);
		this.mViewPager = (ViewPager) super.findViewById(R.id.viewpager);
		this.mViewPager.setAdapter(this.mPagerAdapter);
		this.mViewPager.setOnPageChangeListener(this);
		mTabHost.getTabWidget().getChildAt(Constants.TAB_EXPORT_RECHNUNG)
				.setBackgroundColor(ImageUtil.getColor(R.color.BLUE));
	}

	/**
	 * 
	 * Initialise the Tab Host
	 * 
	 * @author: HaiTC3
	 * @param args
	 * @return: void
	 * @throws:
	 * @since: Mar 18, 2013
	 */
	private void initialiseTabHost(Bundle args) {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		TabInfo tabInfo = null;
		AddTab(this, this.mTabHost, "screen 1", Constants.TAB_EXPORT_RECHNUNG);
		tabInfo = new TabInfo("screen 1", RechnungExportView.class, args);
		this.mapTabInfo.put(tabInfo.tag, tabInfo);

		AddTab(this, this.mTabHost, "screen 2",
				Constants.TAB_EXPORT_LIEFERSCHEIN);
		tabInfo = new TabInfo("screen 2", LieferscheinExportView.class, args);
		this.mapTabInfo.put(tabInfo.tag, tabInfo);

		AddTab(this, this.mTabHost, "screen 3", Constants.TAB_EXPORT_ANGEBOT);
		tabInfo = new TabInfo("screen 3", AngebotExportView.class, args);
		this.mapTabInfo.put(tabInfo.tag, tabInfo);

		mTabHost.setOnTabChangedListener(this);
	}

	/**
	 * Add Tab content to the Tabhost
	 * 
	 * @param activity
	 * @param tabHost
	 * @param tabSpec
	 * @param clss
	 * @param args
	 */
	private void AddTab(BaseFragmentActivity activity, TabHost tabHost,
			String tabName, int type) {
		View tabview = createTabView(tabHost.getContext(), tabName, type);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.weight = 1;
		lp.gravity = Gravity.CENTER;
		lp.setMargins(0, 0, 0, 0);
		tabview.setLayoutParams(lp);

		TabSpec setContent = tabHost.newTabSpec(tabName).setIndicator(tabview)
				.setContent(new TabFactory(activity));
		tabHost.addTab(setContent);
	}

	/**
	 * Mo ta chuc nang cua ham
	 * 
	 * @author: TamPQ
	 * @param context
	 * @param tabName
	 * @param type
	 * @return
	 * @return: View
	 * @throws:
	 */
	private View createTabView(Context context, String tabName, int type) {
		TextView tvTitleTab = (TextView) LayoutInflater.from(context).inflate(
				R.layout.tab_item_top, null);
		// ImageView ivNumPage = (ImageView) view.findViewById(R.id.ivNumPage);
		switch (type) {
		case Constants.TAB_EXPORT_RECHNUNG:
			tvTitleTab.setText("Rechnung");
			if (!tvTitleTab.isSelected()) {
				tvTitleTab.setBackgroundColor(ImageUtil
						.getColor(R.color.COLOR_GRAY_DONE));

			} else {
				tvTitleTab.setBackgroundColor(ImageUtil.getColor(R.color.BLUE));
			}
			break;
		case Constants.TAB_EXPORT_LIEFERSCHEIN:
			tvTitleTab.setText("LIEFERSCHEIN");
			if (!tvTitleTab.isSelected()) {
				tvTitleTab.setBackgroundColor(ImageUtil
						.getColor(R.color.COLOR_GRAY_DONE));

			} else {
				tvTitleTab.setBackgroundColor(ImageUtil.getColor(R.color.BLUE));
			}
			break;
		case Constants.TAB_EXPORT_ANGEBOT:
			tvTitleTab.setText("ANGEBOT");
			if (!tvTitleTab.isSelected()) {
				tvTitleTab.setBackgroundColor(ImageUtil
						.getColor(R.color.COLOR_GRAY_DONE));

			} else {
				tvTitleTab.setBackgroundColor(ImageUtil.getColor(R.color.BLUE));
			}
			break;
		default:
			break;
		}

		return tvTitleTab;
	}

	/**
	 * 
	 * update display icon when changed tab selected
	 * 
	 * @param index
	 * @return: void
	 * @throws:
	 * @author: HaiTC3
	 * @date: Oct 26, 2012
	 */
	public void updateIconWhenChangedTab(int index) {
		if (index == Constants.TAB_EXPORT_RECHNUNG) {
			mTabHost.getTabWidget().getChildAt(Constants.TAB_EXPORT_RECHNUNG)
					.setBackgroundColor(ImageUtil.getColor(R.color.BLUE));
			mTabHost.getTabWidget()
					.getChildAt(Constants.TAB_EXPORT_LIEFERSCHEIN)
					.setBackgroundColor(
							ImageUtil.getColor(R.color.COLOR_GRAY_DONE));
			mTabHost.getTabWidget()
					.getChildAt(Constants.TAB_EXPORT_ANGEBOT)
					.setBackgroundColor(
							ImageUtil.getColor(R.color.COLOR_GRAY_DONE));
		} else if (index == Constants.TAB_EXPORT_LIEFERSCHEIN) {
			mTabHost.getTabWidget()
					.getChildAt(Constants.TAB_EXPORT_LIEFERSCHEIN)
					.setBackgroundColor(ImageUtil.getColor(R.color.BLUE));
			mTabHost.getTabWidget()
					.getChildAt(Constants.TAB_EXPORT_RECHNUNG)
					.setBackgroundColor(
							ImageUtil.getColor(R.color.COLOR_GRAY_DONE));
			mTabHost.getTabWidget()
					.getChildAt(Constants.TAB_EXPORT_ANGEBOT)
					.setBackgroundColor(
							ImageUtil.getColor(R.color.COLOR_GRAY_DONE));
		} else if (index == Constants.TAB_EXPORT_ANGEBOT) {
			mTabHost.getTabWidget()
					.getChildAt(Constants.TAB_EXPORT_LIEFERSCHEIN)
					.setBackgroundColor(
							ImageUtil.getColor(R.color.COLOR_GRAY_DONE));
			mTabHost.getTabWidget()
					.getChildAt(Constants.TAB_EXPORT_RECHNUNG)
					.setBackgroundColor(
							ImageUtil.getColor(R.color.COLOR_GRAY_DONE));
			mTabHost.getTabWidget().getChildAt(Constants.TAB_EXPORT_ANGEBOT)
					.setBackgroundColor(ImageUtil.getColor(R.color.BLUE));
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(java.lang.String)
	 */
	public void onTabChanged(String tag) {
		// TabInfo newTab = this.mapTabInfo.get(tag);
		try {
			int pos = this.mTabHost.getCurrentTab();
			this.mViewPager.setCurrentItem(pos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int index = mTabHost.getCurrentTab();
			currentScreenIndex = index;
			this.updateIconWhenChangedTab(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageScrolled
	 * (int, float, int)
	 */
	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
	 * (int)
	 */
	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		this.mTabHost.setCurrentTab(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.ViewPager.OnPageChangeListener#
	 * onPageScrollStateChanged(int)
	 */
	@Override
	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btBack) {
			// return to main menu view
			this.onBackPressed();
		}
	}

}
