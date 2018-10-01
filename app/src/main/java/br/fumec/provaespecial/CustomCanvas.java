package br.fumec.provaespecial;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CustomCanvas extends View {

    private int posCorEscolhida;
    private float radius = 50;
    private boolean limpar;
    private List<float[]> coords = new ArrayList<>();

    public CustomCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            float posCorEscolhidaFloat = (float) posCorEscolhida;
            float[] item = {x, y, posCorEscolhidaFloat, radius};
            coords.add(item);
            limpar = false;
            invalidate();
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        if (!limpar) {
            for (int i = 0; i < coords.size(); i++){
                int caso = ((int)coords.get(i)[2]);
                switch (caso){
                    case 0:
                        paint.setARGB(255, 255, 0,0);
                        break;
                    case 1:
                        paint.setARGB(255, 0, 255,0);
                        break;
                    case 2:
                        paint.setARGB(255, 0, 0,255);
                        break;
                }
                canvas.drawCircle(coords.get(i)[0], coords.get(i)[1], coords.get(i)[3], paint);
            }
        }
    }
    public void setPosCorEscolhida(int posCorEscolhida) {
        this.posCorEscolhida = posCorEscolhida;
    }
    public void setPequeno(boolean pequeno) {
        if (pequeno){
            radius = 50;
        }else {
            radius = 100;
        }
    }

    public List<float[]> getCoords() {
        return coords;
    }

    public void setCoords(List<float[]> coords) {
        this.coords = coords;
    }

    public void setLimpar(boolean limpar) {
        this.limpar = limpar;
    }
}
