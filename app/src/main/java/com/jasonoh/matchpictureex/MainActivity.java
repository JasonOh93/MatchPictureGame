package com.jasonoh.matchpictureex;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ImageView iv_start, iv_how_to, iv_board;
    ImageView[] iv_animals = new ImageView[5];

    int count_random = 0;

//    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //해당하는 id를 찾아오기
        iv_start = findViewById( R.id.iv_start );
        iv_how_to = findViewById( R.id.iv_how_to );
        iv_board = findViewById( R.id.iv06);

        for(int i = 0; i < iv_animals.length; i++) {
            iv_animals[i] = findViewById( R.id.iv01 + i );
        }

//        adView = findViewById(R.id.adv);

        //광고 요청 객체 생성
//        adView.loadAd( new AdRequest.Builder().build() );

        //start 눌렀을 때 반응
        iv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initial();
                iv_start.setEnabled( false ); //시작 누르면 안눌리게 하기 위해서

            }//onClick method
        });//start OnClickListener

        // how to 눌렀을 때의 반응
        iv_how_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //AlertDialog를 만들어 그안의 내용을 확인하기
                AlertDialog.Builder alert_builder = new AlertDialog.Builder( MainActivity.this );
                alert_builder.setTitle(R.string.alert_title);
                alert_builder.setMessage(R.string.alert_message);
                alert_builder.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });// PositiveButton

                AlertDialog alertDialog = alert_builder.create();
                alertDialog.setCanceledOnTouchOutside( false );
                alertDialog.show();


            }//onClick method
        }); //how to OnClickListener

    }//onCreate method

    //각각 동물 그림과 아래의 보드의 이름과 같은 것을 찾기 위한 클릭 객체 만들기
    public void clickFind(View view) {

        //클릭 되는 view는 ImageView의 자료형과 맞는지 확인한 후 내부의 테그를 이용하여 비교하기
        if( view instanceof ImageView ) {
            if( view.getTag() == iv_board.getTag() ) {
                AlertDialog.Builder contact_builder = new AlertDialog.Builder(this);

                contact_builder.setTitle( R.string.contact_title ); //타이틀 정하기

                contact_builder.setMessage( R.string.contact_message ); //메세지 정하기

                contact_builder.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initial(); // 확인 버튼을 누르면 재시작
                    }
                }); //setPositiveButton method

                contact_builder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i = 0; i < iv_animals.length; i++) iv_animals[i].setVisibility( View.INVISIBLE );
                        iv_board.setVisibility( View.INVISIBLE );
                        iv_start.setEnabled( true );
                    }
                });//setNegativeButton method

                AlertDialog alertDialog_contact = contact_builder.create();
                alertDialog_contact.setCanceledOnTouchOutside( false ); // 화면 외부의 공간을 터치 했을때 눌리는지 여부
                alertDialog_contact.show();

            }//if문
            else {
                Toast.makeText(this, R.string.clickFind, Toast.LENGTH_SHORT).show();
            }//else문
        }//view가 ImageView의 자료형이 같은지 외부 if문

    }//clickStart method

    //초기설정 메소드
    void initial() {

        ArrayList<Integer> arrayList = new ArrayList<>();

        for(int i = 0; i < iv_animals.length; i++) arrayList.add( i );

        Collections.shuffle( arrayList );

        for(int i = 0; i < iv_animals.length; i++) {
            iv_animals[i].setImageResource( R.drawable.a_ele + arrayList.get( i ) );
            iv_animals[i].setVisibility( View.VISIBLE ); //초기에 그림이 INVISIBLE 되어 있기 때문에

            iv_animals[i].setTag( arrayList.get(i) );
        }//동물 이미지용 for문

        Collections.shuffle( arrayList ); //보드판의 내용을 다시 랜덤하게

        iv_board.setImageResource( R.drawable.b_ele + ( arrayList.get( iv_animals.length - 1 ) ) ); //보드판 이미지
        iv_board.setVisibility( View.VISIBLE ); //초기에 그림이 INVISIBLE 되어 있기 때문에
        iv_board.setTag( arrayList.get( iv_animals.length - 1 ) );

    }//initial() method

}//MainActivity class
