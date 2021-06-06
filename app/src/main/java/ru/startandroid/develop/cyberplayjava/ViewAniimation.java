package ru.startandroid.develop.cyberplayjava;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class ViewAniimation {
    public static boolean rotateFab(final View v, boolean rotate)
    {
        v.animate().setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                }).rotation(rotate ? 135f : 0f);
        return rotate;
    }
}
