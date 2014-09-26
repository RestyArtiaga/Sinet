package com.example.sampedrawer2;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MyHomeFragment extends Fragment {

	public interface OnImageSelected{
		public void onImageClicked(int index);
	}
	private static final int SWIPE_MIN_DISTANCE = 100;
	private static final int SWIPE_THRESHOLD_VELOCITY = 200;
	private ViewFlipper mViewFlipper;	
	private AnimationListener mAnimationListener;
	private Context mContext;
	
	private Activity activity;
	private OnImageSelected listen;
	
	private ImageView siisIMG, confIMG, indIMG,prodIMG,bestPrIMG,confTab,indTab,prodTab,bestPrTab;
	
	@SuppressWarnings("deprecation")
	private final GestureDetector detector = new GestureDetector(new SwipeGestureDetector());
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
		if (activity instanceof OnImageSelected) {
	        listen = (OnImageSelected) activity;
	      } else {
	        throw new ClassCastException(activity.toString()
	            + " must implemenet HomeFragment.OnImageSelected");
	      }
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 Log.d("Motherfucker", "Motherfucker");
		 View view = inflater.inflate(R.layout.home_fragment,
			        container, false);		
		 mContext = getActivity();
		
		 mViewFlipper = (ViewFlipper) view.findViewById(R.id.view_flipper);
		 
		 confTab = (ImageView) view.findViewById(R.id.conferencesIMGTab);
		 indTab = (ImageView) view.findViewById(R.id.industryNewsIMGTab);
		 prodTab = (ImageView) view.findViewById(R.id.productsIMGTab);
		 bestPrTab = (ImageView) view.findViewById(R.id.bestPracticesIMGTab);
			
			mViewFlipper.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(final View view, final MotionEvent event) {																				
					detector.onTouchEvent(event);										
					return true;
				}
			});
												
			mViewFlipper.setAutoStart(true);
			mViewFlipper.setFlipInterval(8000);			
			mViewFlipper.startFlipping();
															
			//animation listener
			mAnimationListener = new Animation.AnimationListener() {
				public void onAnimationStart(Animation animation) {
					//animation started event
				}

				public void onAnimationRepeat(Animation animation) {
				}

				public void onAnimationEnd(Animation animation) {
					//TODO animation stopped event
				}
			};
			
			confTab.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listen.onImageClicked(1);	
					Toast.makeText(mContext, "CONFERENCE",Toast.LENGTH_SHORT).show();
				}
			});
			indTab.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								listen.onImageClicked(2);
								Toast.makeText(mContext, "INDUSTRY",Toast.LENGTH_SHORT).show();
							}
						});
			prodTab.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listen.onImageClicked(3);		
					Toast.makeText(mContext, "PRODUCTS",Toast.LENGTH_SHORT).show();
				}
			});
			bestPrTab.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listen.onImageClicked(4);
					Toast.makeText(mContext, "BEST PRACTICES",Toast.LENGTH_SHORT).show();
				}
			});
			
		return view;
	}
	
	class SwipeGestureDetector extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			try {
				// right to left swipe
				if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.left_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showNext();
					return true;
				} else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
					mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.right_in));
					mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext,R.anim.right_out));
					// controlling animation
					mViewFlipper.getInAnimation().setAnimationListener(mAnimationListener);
					mViewFlipper.showPrevious();
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
		
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			String temp = mViewFlipper.getCurrentView().getTag().toString();
			Toast.makeText(mContext, temp, Toast.LENGTH_SHORT).show();
			//listen.onImageClicked(Integer.parseInt(temp));
			return super.onSingleTapConfirmed(e);
		}
				
	}
}
