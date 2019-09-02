package top.linxixiangxin.passcet;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import card.CardFragmentPagerAdapter;
import card.CardPagerAdapter;
import card.Cardmain;
import card.ShadowTransformer;

public class FristFragment extends Fragment{

    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;
    private Cardmain card;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_frist,null,false);
        mViewPager = (ViewPager) getActivity().findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapter();

        card = new Cardmain(mViewPager,mCardAdapter);
        return view;

    }
    }
