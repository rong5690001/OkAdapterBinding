package com.okbind;

import android.os.Bundle;

import com.okbind.library.OkBindingAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        List data = new ArrayList();
//        data.add("String");
//        data.add(1);
//        data.add(0.1f);
//        data.add("String");
//        data.add(1);
//        data.add(0.1f);
//        data.add("String");
//        data.add(1);
//        data.add(0.1f);
        data.add(new TestViewModel("TestViewModel"));
        data.add(new TestViewModel("TestViewModel"));
        data.add(new TestViewModel2("TestViewModel2"));
        OkBindingAdapter okAdapter = new OkBindingAdapter(data);

        okAdapter.register(TestViewModel2.class, new TestItembind2());
        okAdapter.register(TestViewModel.class, new TestItembind());

        recyclerView.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL
                , false));
        recyclerView.setAdapter(okAdapter);
    }
}
