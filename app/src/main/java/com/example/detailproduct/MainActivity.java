package com.example.detailproduct;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView SizeChoose;
    TableLayout TableSizeProduct;
    Button BtnSizeS;
    Button BtnSizeM;
    Button BtnSizeL;
    TextView PriceProduct;
    Button BtnDes;
    Button Buy;
    RadioButton Color1,Color2,Color3;
    Locale locale=new Locale("vi","VN");
    NumberFormat format=NumberFormat.getCurrencyInstance(locale);
    boolean checkDes=false;
    boolean CheckClickSize=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXadulieu();
        PriceProduct.setText(format.format(123456789));
        SuKienClick();


    }
    void AnhXadulieu(){
        SizeChoose=findViewById(R.id.SizeChoose);
        TableSizeProduct=findViewById(R.id.DesProduct);
        BtnSizeL=findViewById(R.id.BtnSizeL);
        BtnSizeM=findViewById(R.id.BtnSizeM);
        BtnSizeS=findViewById(R.id.BtnSizeS);
        Buy=findViewById(R.id.BuyProduct);
        BtnDes=findViewById(R.id.btnDes);
        Color1=findViewById(R.id.Color1);
        Color2=findViewById(R.id.Color2);
        Color3=findViewById(R.id.Color3);
        PriceProduct=findViewById(R.id.PriceProduct);
    }
    void SuKienClick(){
        BtnSizeS.setOnClickListener(BtnSclick());
        BtnSizeM.setOnClickListener(BtnMclick());
        BtnSizeL.setOnClickListener(BtnLclick());
        BtnDes.setOnClickListener(view -> BtnDesClick());
    }

    private void BtnDesClick() {
        if(!checkDes){
            Animation animSlideup= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
            TableSizeProduct.startAnimation(animSlideup);
            TableSizeProduct.setVisibility(View.VISIBLE);
            checkDes=true;
        }
        else {
            TableSizeProduct.setVisibility(View.INVISIBLE);
            checkDes=false;
        }
    }

    @NonNull
    private View.OnClickListener BtnLclick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnClick(BtnSizeL);
                makeBtnDefault(BtnSizeM,BtnSizeS);
            }
        };
    }

    @NonNull
    private View.OnClickListener BtnMclick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnClick(BtnSizeM);
                makeBtnDefault(BtnSizeL,BtnSizeS);
            }
        };
    }
    boolean ClickAbleBuy(){
        if(CheckClickSize&&(Color1.isChecked()||Color2.isChecked()||Color3.isChecked()))
        {
            return true;
        }
        return false;
    }

    @NonNull
    private View.OnClickListener BtnSclick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnClick(BtnSizeS);
                makeBtnDefault(BtnSizeL,BtnSizeM);
            }
        };
    }
    void BtnClick(Button Click){
        CheckClickSize=true;
        String Text = Click.getText().toString();
        SizeChoose.setText(Text);
        Click.setTextColor(Color.parseColor("#F5F5F5"));
        Click.setBackgroundResource(R.drawable.btn_size);

    }
    void makeBtnDefault(Button Click1,Button Click2){
        Click1.setBackgroundResource(R.drawable.btn_size_default);
        Click2.setBackgroundResource(R.drawable.btn_size_default);
        Click1.setTextColor(Color.parseColor("#000000"));
        Click2.setTextColor(Color.parseColor("#000000"));
        if(ClickAbleBuy())
        {
            Buy.setClickable(true);
        }
    }
}