package com.smallville7123.crafter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.smallville7123.crafter.egl.EGLTextureView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MagnumEGLTextureView extends EGLTextureView {
    MagnumRenderer renderer;

    public MagnumEGLTextureView(Context context) {
        super(context);
    }

    public MagnumEGLTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void init() {
        super.init();
        setEGLContextClientVersion(3);
        setRenderer(renderer = new MagnumRenderer());
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    MotionEventSerializer jniMotionEvent = new MotionEventSerializer(10);

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return renderer.onTouchEvent(renderer.nativeInstance, jniMotionEvent.process(event));
    }

    private class MagnumRenderer implements Renderer {
        native long createNativeInstance();
        native void onEglSetup(long instance, Object classInstance, String name, String signature);
        native void surfaceChanged(long instance, int w, int h);
        native boolean onTouchEvent(long instance, float[] motionEvent);
        native void onDraw(long instance);
        native void onEglTearDown(long instance);
        long nativeInstance;

        MagnumRenderer() {
            System.loadLibrary("App");
            nativeInstance = createNativeInstance();
        }

        @Override
        public void onEglSetup() {
            onEglSetup(nativeInstance, MagnumEGLTextureView.this,
                    getJavaNameForJNI(METHOD.SWAP_BUFFERS),
                    getJavaSignatureForJNI(METHOD.SWAP_BUFFERS)
            );
        }

        @Override
        public void onEglTearDown() {
            onEglTearDown(nativeInstance);
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            // do nothing
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            surfaceChanged(nativeInstance, width, height);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            onDraw(nativeInstance);
        }
    }
}
