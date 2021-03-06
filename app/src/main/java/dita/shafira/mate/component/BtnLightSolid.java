package dita.shafira.mate.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;

import dita.shafira.mate.R;

public class BtnLightSolid extends AppCompatButton {
    private Drawable enabledBackground, disabledBackground;

    public BtnLightSolid(Context context) {
        super(context);
        init();
    }

    public BtnLightSolid(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BtnLightSolid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(isEnabled() ? enabledBackground : disabledBackground);
        setGravity(Gravity.CENTER);
    }

    private void init(){
        enabledBackground = getResources().getDrawable(R.drawable.btn_solid_2);
        disabledBackground = getResources().getDrawable(R.drawable.btn_solid_disable_2);
    }
}
