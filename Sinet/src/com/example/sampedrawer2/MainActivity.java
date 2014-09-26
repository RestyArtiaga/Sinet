package com.example.sampedrawer2;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

public class MainActivity extends BaseActivity implements MyHomeFragment.OnImageSelected {
	private android.app.FragmentTransaction ft;
	private Fragment homeFragment;
	BestPracticesFragment bestprFragment;
	ProductFragment prodFragment;
	IndustryFragment indFragment;
	ConferenceFragment confFragment;
	SIISFragment siisFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(savedInstanceState==null){
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.content, new MyHomeFragment());		
		ft.commit();
		}
	}

	
		
		@Override
		public void onImageClicked(int index) {							
			
			if(index==0){				
				setFragment(new SIISFragment(),"SIIS");
			}else if (index==1){				
				setFragment(new ConferenceFragment(),"Conference & Events");
			}else if (index==2){
				setFragment(new IndustryFragment(),"Industry News");				
			}else if (index==3){
				setFragment(new ProductFragment(),"Products");
			}else if (index==4){
				setFragment(new BestPracticesFragment(),"Best Practices");
			}						
		}


}
