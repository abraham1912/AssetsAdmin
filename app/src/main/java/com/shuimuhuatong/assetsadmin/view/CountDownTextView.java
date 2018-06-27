package com.shuimuhuatong.assetsadmin.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;

import com.shuimuhuatong.assetsadmin.R;


public class CountDownTextView extends android.support.v7.widget.AppCompatTextView {
	private int mTimeTotal=60, mTimeInterval=1;
	private CountDownTimer mMyCountDownTimer; 
	private String mHint, mText; 
	
	public CountDownTextView(Context context) {
		this(context, null);
	}
	
	public CountDownTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public CountDownTextView(final Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CountDownTextView, defStyle, 0);
		int n = a.getIndexCount();
		for (int i = 0; i < n; i++)
		{
			int attr = a.getIndex(i);
			switch (attr)
			{
			case R.styleable.CountDownTextView_timeTotal:
				mTimeTotal = a.getInt(attr, 60);
				break;
			case R.styleable.CountDownTextView_timeInterval:
				mTimeInterval = a.getInt(attr, 1);
				break;
				
			case R.styleable.CountDownTextView_hint:
				mHint = a.getString(attr);
				break;
			}

		}
		a.recycle();
		mText = (String) getText();
		mMyCountDownTimer = new CountDownTimer(mTimeTotal*1000, mTimeInterval*1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				if (TextUtils.isEmpty(mHint))
					setText(String.format(context.getString(R.string.smscode_hint_format), millisUntilFinished/1000));
				else
					setText(String.format(context.getString(R.string.smscode_hint_format_more), millisUntilFinished/1000, mHint));
				    //setBackgroundResource(R.drawable.send_smc_shape_grayroundcorner);
			}

			@Override
			public void onFinish() {
				mText = "重新发送" ;
				setText(mText);
				setClickable(true);
				setEnabled(true);
				setTextColor(getResources().getColor(R.color.white));
			}
		};
		
	}

	public void timeStart(){
		mMyCountDownTimer.start();
		setClickable(false);
		setEnabled(false);
		setGravity(Gravity.CENTER);
		setTextColor(getResources().getColor(R.color.white));
	}
	
	public void reset() {
		mMyCountDownTimer.cancel();
		setText(mText);
		setClickable(true);
		setEnabled(true);
		setTextColor(getResources().getColor(R.color.white));
	}

	@Override
	public boolean performClick() {
		timeStart();
        return super.performClick();
    }
	
}
