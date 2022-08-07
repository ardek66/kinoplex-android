package net.idf.kinoplexandroid;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TabContent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabContent extends Fragment {

    public static final String ARG_TYP = "TAB_TYPE";
    public static final String ARG_POS = "TAB_POS";

    private String tabType;
    private int pos;

    ViewPager2 viewPager;
    TabAdapter adapter;

    public TabContent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tabType Parameter 1.
     * @param tabPos  Parameter2.
     * @return A new instance of fragment TabContent.
     */
    // TODO: Rename and change types and number of parameters
    public static TabContent newInstance(String tabType, int tabPos) {
        TabContent fragment = new TabContent();
        Bundle args = new Bundle();
        args.putString(ARG_TYP, tabType);
        args.putInt(ARG_POS, tabPos);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            tabType = args.getString(ARG_TYP);
            pos = args.getInt(ARG_POS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (ViewGroup) inflater.inflate(R.layout.fragment_tab_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        Resources res = getResources();
        String[] slide_texts = res.getStringArray(R.array.slide_texts);
        ((TextView) view.findViewById(R.id.textView)).setText(slide_texts[pos]);
    }
}