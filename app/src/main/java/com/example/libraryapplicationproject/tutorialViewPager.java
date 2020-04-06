package com.example.libraryapplicationproject;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.relex.circleindicator.CircleIndicator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class tutorialViewPager extends Fragment {


    public tutorialViewPager() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_tutorial_view_pager, container, false);
        final ViewPager vp = view.findViewById(R.id.vpTutorial);
        final CustomAdapter adapter = new CustomAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(vp);
        vp.setPageTransformer(true, new ZoomOutPageTransformer());
        final Button vpButton = view.findViewById(R.id.takeHome);
        vpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });
         return view;
    }
    public class CustomAdapter extends FragmentPagerAdapter {

        public CustomAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return viewPagerInformation.newInstance("Welcome","Welcome to Book Locker. This app lets you get a hold of books you've always wanted to read. you can find books by Category or by searching for a specific book",R.drawable.logo);
                case 1: return viewPagerInformation.newInstance("Home","In the Home section of the app, you can Can find books by Category. each category has 40 books in them and it will refresh after every fresh launch of the app",R.drawable.img3);
                case 2: return viewPagerInformation.newInstance("Look Up a Book","Within the search section, you can search specific books by tapping on the magnifying glass which brings up the keyboard. Than you will get a list of books related to that search via by title",R.drawable.img1);
                case 3: return viewPagerInformation.newInstance("View Detail","Once you tap on a book, you will be taken to the detail section of the book. Here you can read the description of the book, see the average rating, read a preview or even buy it if you would like.",R.drawable.img2);
                case 4: return viewPagerInformation.newInstance("The Locker","Within the locker section, you can see books you have added to your locker. Here, you can rate the book by taping the stars or delete it from your locker by tapping the X marker.",R.drawable.img4);
                default: return viewPagerInformation.newInstance("you cant see me","place holder text",R.drawable.placeholder);
            }
        }

        @Override
        public int getCount() {
            return 5;
        }

    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0f);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0f);
            }
        }
    }
}
