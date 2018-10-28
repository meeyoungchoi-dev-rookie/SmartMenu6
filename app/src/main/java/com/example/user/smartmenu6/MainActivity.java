package com.example.user.smartmenu6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.smartmenu6.dto.ToToken;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    FireBaseModel fireBaseModel;
    String clientToken;
    private Button mCustomer = null;
    private Button mManager = null;
    private Button mKitchen = null;


    public final static int CHOOSE_BUTTON_REQUEST = 0;


    public final static String BUTTONS = "buttons";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineFirBase();
        fireBaseModel.refreshClientTokenList();

       clientToken= FirebaseInstanceId.getInstance().getToken();

        if(clientToken!=null){
            Log.d("test", clientToken.toString());
        }





        mCustomer = (Button) findViewById(R.id.customer);
        mManager = (Button) findViewById(R.id.manager);
        mKitchen = (Button) findViewById(R.id.kitchen);


        mCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToToken toToken=new ToToken();
                toToken.setRole("4");
                toToken.setToken(clientToken);
                addOrUpdateToken(toToken);

                /*//클라이언트토큰을 던져주면 밑에 이프문이 수행된다.
                ToToken areadyToken=getAreadyToken(clientToken);
                //이미 저장되어있는 토큰을 가지고 온다.
                //클라이언트토큰은 내 디바이스에서 생성한 토큰
                //이거를 겟얼레디 토큰에 보낸다.
                //디바이스에서 가지고있는 토큰과 파이어베이스에서 가지고 있는 토큰이 일차하는것을 찾는다.
                //그래서 키가 포함된 파이어베이스 토큰을 겟얼리디 토큰을 호출한 것으로 반환한다.

                if(areadyToken==null){
                    fireBaseModel.addToToken(toToken);
                }else{
                    fireBaseModel.updateToToken(areadyToken.getKey(),toToken);
                }
*/

                Intent I = new Intent(MainActivity.this, CustomerSecondPage.class);
                startActivityForResult(I, CHOOSE_BUTTON_REQUEST);

            }
        });
        mManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, ManagerSecondPage.class);

                startActivityForResult(I, CHOOSE_BUTTON_REQUEST);
            }
        });

        mKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToToken toToken=new ToToken();
                toToken.setRole("2");
                toToken.setToken(clientToken);
                addOrUpdateToken(toToken);
                Intent I = new Intent(MainActivity.this, KitchenMenuCheck.class);

                startActivityForResult(I, CHOOSE_BUTTON_REQUEST);
            }
        });


    }

    private void addOrUpdateToken(ToToken toToken) {
        ToToken areadyToken=getAreadyToken(toToken.getToken());
        if(areadyToken==null){
            fireBaseModel.addToToken(toToken);
        }else{
            fireBaseModel.updateToToken(areadyToken.getKey(),toToken);
        }

    }

    //boolean이 이미 있는 토큰을 가져온다
    public ToToken getAreadyToken(String clientToken) {

                //블린의 변수명 알티인블린이 펄스이다.
                ToToken rtnToToken=null;
                //인트 아이가 0  부터 파이어베이스모델에서 가져온 클라이언트토큰 리스트의 크기보다 작을때 까지
                //포문을 돌리면서 아이를 증가시킨다.
                if(fireBaseModel.getClientTokenList()!=null){

                    for(int i=0; i<fireBaseModel.getClientTokenList().size(); i++){
                        //투토큰 변수에 파이어베이스모델에서 가져온 클라이언트 토큰 리스트의 아이번쨰를 가져와서 넣는다.
                        // Log.d("token: ",fireBaseModel.getClientTokenList().get(i).)
                        ToToken toToken=fireBaseModel.getClientTokenList().get(i);
                        //만약에 투토큰에서 가져온 토큰이 클라이언트 토큰과 같다면
                        if(toToken.getToken() !=null && toToken.getToken().equals(clientToken)) {
                            //알티텐불린은 트루이다.
                            rtnToToken=toToken;
                }

                    }
        }
        //알티엔 블린을 반환한다.
        return rtnToToken;
    }
    protected  void defineFirBase(){

        fireBaseModel = new FireBaseModel(this);
        fireBaseModel .firebaseAuthWithGoogle();
        fireBaseModel.firebaseNoneAuth();

    }





}
