package com.smallville7123.crafter;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.smallville7123.crafter.egl.EGLSurfaceView;
import com.smallville7123.crafter.egl.EGLTextureView;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    EGLSurfaceView eglSurfaceView;
    EGLSurfaceView eglSurfaceView2;
    EGLTextureView eglTextureView;
    EGLTextureView eglTextureView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eglSurfaceView = new MagnumEGLSurfaceView(this);
        eglSurfaceView2 = new MagnumEGLSurfaceView(this);
        eglTextureView = new MagnumEGLTextureView(this);
        eglTextureView2 = new MagnumEGLTextureView(this);

        LinearLayout a = new LinearLayout(this);
        a.setOrientation(LinearLayout.VERTICAL);
        LinearLayout b = new LinearLayout(this);
        b.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.HORIZONTAL);

        b.addView(eglTextureView, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT) { {weight = 1f;} });
        b.addView(eglTextureView2, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT) { {weight = 1f;} });
        a.addView(b, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT) { {weight = 1f;} });

        c.addView(eglSurfaceView, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT) { {weight = 1f;} });
        c.addView(eglSurfaceView2, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT) { {weight = 1f;} });
        a.addView(c, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT) { {weight = 1f;} });

        setContentView(a);
    }

    @Override
    protected void onResume() {
        super.onResume();
        eglTextureView.onResume();
        eglTextureView2.onResume();
        eglSurfaceView.onResume();
        eglSurfaceView2.onResume();
    }

    @Override
    protected void onPause() {
        eglTextureView.onPause();
        eglTextureView2.onPause();
        eglSurfaceView.onPause();
        eglSurfaceView2.onPause();
        super.onPause();
    }
}