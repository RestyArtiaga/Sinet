package com.example.sampedrawer2;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BaseActivity extends ActionBarActivity {

	private String[] mMenuTitles;
	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mTitle;
	BestPracticesFragment bestprFragment;
	ProductFragment prodFragment;
	IndustryFragment indFragment;
	ConferenceFragment confFragment;
	SIISFragment siisFragment;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.base_activity_layout);
		initDrawer();

	}

	protected void initDrawer() {
		Log.d("CALLED", "CALLED");
		mTitle = getTitle();
		
		mMenuTitles = getResources().getStringArray(R.array.menu_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

		View header = getLayoutInflater().inflate(
				R.layout.header_listview_menu, null);
		mDrawerList.addHeaderView(header);

		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, mMenuTitles));

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				mDrawerLayout
						.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				mDrawerLayout
						.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
				supportInvalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
			mDrawerToggle.setDrawerIndicatorEnabled(true);
		} else {
			mDrawerToggle.setDrawerIndicatorEnabled(false);
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		
		
			if (item.getItemId() == android.R.id.home) {
				
			}
				boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
				if (drawerOpen) {
					mDrawerLayout.closeDrawer(mDrawerList);
				} else {
					mDrawerLayout.openDrawer(mDrawerList);
				}
			
			return true;
		
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			selectItem(position);
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
			setTitle("Sinet");

			mDrawerToggle.setDrawerIndicatorEnabled(true);
		}

	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		switch (position) {

		case 1:
			
			setFragment(new MyHomeFragment(),"Sinet");
			// setFragment(new DefaultFragment());
			break;
		case 2:
			
			setFragment(new SIISFragment(),"SIIS");
			// setFragment(new DefaultFragment());
			break;
		case 3:
			
			setFragment(new ProductFragment(),"Products");
			// setFragment(new DefaultFragment());
			break;
		case 4:
			
			setFragment(new ConferenceFragment(),"Conferences & Events");
			// setFragment(new DefaultFragment());
			break;
		case 5:
			
			setFragment(new IndustryFragment(),"Industry News");
			// setFragment(new DefaultFragment());
		case 6:
			setTitle("Survey");

			// setFragment(new DefaultFragment());
			break;

		}

		if (position > 0)
			mDrawerLayout.closeDrawer(mDrawerList);

		mDrawerToggle.setDrawerIndicatorEnabled(true);

		mDrawerList.setItemChecked(position, true);

	}

	public void setFragment(android.support.v4.app.Fragment fragment,String title) {
		setTitle(title);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.content, fragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.addToBackStack(null);
		ft.commit();
	}
	// private void setFragment(Fragment fragment)
	// {
	// FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
	// //ft.setCustomAnimations(android.R.anim.slide_in_left,
	// android.R.anim.slide_out_right);
	// ft.replace(R.id.content, fragment);
	// ft.addToBackStack(null);
	// // Commit the transaction
	// ft.commit();
	// }

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
