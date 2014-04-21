package edu.purdue.cs.dodgeball;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class DrawTest extends View {
	
    Paint paint = new Paint();
	int height = 480;
	int width = 400;
	
    public DrawTest(Context context) {
        super(context);            

    	//WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	//Display display = wm.getDefaultDisplay();
    	//Point size = new Point();
    	//display.getSize(size);
    	//height = size.y;
    	//width = size.x;
    }
	
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(width-5,height-5,width+5,height+5, paint);
    }
}
