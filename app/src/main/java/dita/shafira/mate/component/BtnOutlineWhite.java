package dita.shafira.mate.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatButton;

import dita.shafira.mate.R;

public class BtnOutlineWhite extends AppCompatButton {
    private Drawable enabledBackground;

    public BtnOutlineWhite(Context context) {
        super(context);
    }

    public BtnOutlineWhite(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BtnOutlineWhite(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(enabledBackground );
        setGravity(Gravity.CENTER);
    }

    private void init(){
        enabledBackground = getResources().getDrawable(R.drawable.btn_outline);

    }
}
