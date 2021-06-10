package ru.startandroid.develop.cyberplayjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentBoard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBoard extends Fragment {

    public FragmentBoard() {
        // Required empty public constructor
    }

    public static FragmentBoard newInstance() {

        return new FragmentBoard();
    }

    View selectedView;

    private ImageView boardM;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_board, container, false);

        boardM = root.findViewById(R.id.boardMap);
        FrameLayout flcontainer = (FrameLayout) root.findViewById(R.id.boardContainer);
        FloatingActionButton fab = root.findViewById(R.id.fabAdd);
        Button pubutton = root.findViewById(R.id.popUpMenuBoardBtn);

        final boolean[] isRotate = {false};

        FloatingActionButton fabSmoke = root.findViewById(R.id.fabSmoke);
        ViewAniimation.init(fabSmoke);
        FloatingActionButton fabMolotov = root.findViewById(R.id.fabMoly);
        ViewAniimation.init(fabMolotov);
        FloatingActionButton fabFlash = root.findViewById(R.id.fabFlash);
        ViewAniimation.init(fabFlash);
        FloatingActionButton fabPlayer = root.findViewById(R.id.fabPlayer);
        ViewAniimation.init(fabPlayer);
        FloatingActionButton fabClear = root.findViewById(R.id.fabClear);
        ViewAniimation.init(fabClear);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRotate[0])
                {
                    ViewAniimation.showIn(fabSmoke);
                    ViewAniimation.showIn(fabMolotov);
                    ViewAniimation.showIn(fabFlash);
                    ViewAniimation.showIn(fabPlayer);
                    ViewAniimation.showIn(fabClear);
                }
                else
                {
                    ViewAniimation.showOut(fabSmoke);
                    ViewAniimation.showOut(fabMolotov);
                    ViewAniimation.showOut(fabFlash);
                    ViewAniimation.showOut(fabPlayer);
                    ViewAniimation.showOut(fabClear);
                }
                isRotate[0] = ViewAniimation.rotateFab(v,!isRotate[0]);

            }
        });

        pubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(root.getContext(),pubutton);
                popup.inflate(R.menu.popup_menu);

                Menu menu = popup.getMenu();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        pubutton.setText(item.getTitle());
                        return menuItemClicked(item,flcontainer);
                    }
                });
                popup.show();
            }
        });

        fabSmoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createImageView(root,flcontainer,R.drawable.smoke);
            }
        });
        fabMolotov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createImageView(root,flcontainer,R.drawable.moly);
            }
        });
        fabFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createImageView(root,flcontainer,R.drawable.flash);
            }
        });
        fabPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createImageView(root,flcontainer,R.drawable.player);
            }
        });
        fabClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flcontainer.removeView(selectedView);
            }
        });

        return root;
    }


    private void createImageView(View root, FrameLayout frameLayout,int imgRes)
    {
        //Bitmap bmp = BitmapFactory.decodeFile(@);
        ImageView imageView = new ImageView(root.getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(88,88);
        params.leftMargin = 0;
        params.rightMargin =0;
        imageView.setLayoutParams(params);
        imageView.setImageResource(imgRes);
        imageView.setOnTouchListener(touchListener);
        frameLayout.addView(imageView);
    }

    private int xDelta, yDelta;
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override public boolean onTouch(View view, MotionEvent event) {
            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) view.getLayoutParams();

                    xDelta = x - lParams.leftMargin;
                    yDelta = y - lParams.topMargin;
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    break;
                }
                case MotionEvent.ACTION_MOVE: {

                        FrameLayout.LayoutParams layoutParams =
                                (FrameLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                    break;
                }
            }
            selectedView = view;
            return true;
        }
    };

    private boolean menuItemClicked(MenuItem item,FrameLayout frameLayout)
    {
        switch (item.getItemId())
        {
            case R.id.pumDust2:
                boardM.setImageResource(R.drawable.dust2_radar);
                break;
            case R.id.pumInferno:
                boardM.setImageResource(R.drawable.inferno_radar);
                break;
            case R.id.pumMirage:
                boardM.setImageResource(R.drawable.mirage_radar);
                break;
            case R.id.pumAncient:
                boardM.setImageResource(R.drawable.ancient_radar);
                break;
            case R.id.pumOverpass:
                boardM.setImageResource(R.drawable.overpass_radar);
                break;
            case R.id.pumVertigo:
                boardM.setImageResource(R.drawable.vertigo_radar);
                break;
            case R.id.pumNuke:
                boardM.setImageResource(R.drawable.nuke_radar);
                break;
            default:
                break;

        }
        frameLayout.removeAllViews();

        return true;
    }
}