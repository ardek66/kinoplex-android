package net.idf.kinoplexandroid;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class KinoActivity extends AppCompatActivity {
    private WsClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kino);

        client = KinoClient.getClient();

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabAdapter adapter = new TabAdapter(this);
        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(adapter);

        Resources res = getResources();
        String[] tab_texts = res.getStringArray(R.array.tab_texts);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tab_texts[position])
        ).attach();
    }

    @Override
    public void onBackPressed() {
        KinoClient.stop();
        super.onBackPressed();
    }
}