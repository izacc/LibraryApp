package com.example.libraryapplicationproject;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
        final CustomAdapter adapter = new CustomAdapter(getFragmentManager());
        vp.setAdapter(adapter);
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
                case 0: return viewPagerInformation.newInstance("sd","place holder text",R.drawable.placeholder);
                case 1: return viewPagerInformation.newInstance("placeHolder","place holder text",R.drawable.placeholder);
                case 2: return viewPagerInformation.newInstance("asdasd","place holder text",R.drawable.placeholder);
                case 3: return viewPagerInformation.newInstance("asdasdas","place holder text",R.drawable.placeholder);
                case 4: return viewPagerInformation.newInstance("placaeHolder","place holder text",R.drawable.placeholder);
                case 5: return viewPagerInformation.newInstance("asdasdasd","place holder text",R.drawable.placeholder);
                default: return viewPagerInformation.newInstance("you cant see me","place holder text",R.drawable.placeholder);
            }
        }

        @Override
        public int getCount() {
            return 6;
        }

    }
}
