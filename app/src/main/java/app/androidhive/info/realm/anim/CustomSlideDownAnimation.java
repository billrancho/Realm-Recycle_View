package app.androidhive.info.realm.anim;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CustomSlideDownAnimation extends Animation {

    public final static int COLLAPSE = 1;
    public final static int EXPAND = 0;

    private View mView;
    private int mEndHeight;
    private int mType;
    private LayoutParams mLayoutParams;

    public CustomSlideDownAnimation(View view, int duration, int type) {

        setDuration(duration);
        mView = view;
        mEndHeight = mView.getHeight();
        mLayoutParams = view.getLayoutParams();
        mType = type;
        if(mType == EXPAND) {
            mLayoutParams.height = 0;
        } else {
            mLayoutParams.height = LayoutParams.WRAP_CONTENT;
        }
        view.setVisibility(View.VISIBLE);
    }

    public int getHeight(){
        return mView.getHeight();
    }

    public void setHeight(int height){
        mEndHeight = height;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        super.applyTransformation(interpolatedTime, t);
        if (interpolatedTime < 1.0f) {
            if(mType == EXPAND) {
                mLayoutParams.height =  (int)(mEndHeight * interpolatedTime);
            } else {
                mLayoutParams.height = (int) (mEndHeight * (1 - interpolatedTime));
            }
            mView.requestLayout();
        } else {
            if(mType == EXPAND) {
                mLayoutParams.height = LayoutParams.WRAP_CONTENT;
                mView.requestLayout();
            }else{
//                mView.setVisibility(View.GONE);
            }
        }
    }
}