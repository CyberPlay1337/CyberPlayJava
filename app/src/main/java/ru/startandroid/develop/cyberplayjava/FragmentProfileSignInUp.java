package ru.startandroid.develop.cyberplayjava;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentProfileSignInUp extends Fragment {
    public FragmentProfileSignInUp() {
        // Required empty public constructor
    }


    public static FragmentProfileSignInUp newInstance() {
        return new FragmentProfileSignInUp();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.profile_sign_in_up, container, false);

        SharedPreferences myPreferences
                = PreferenceManager.getDefaultSharedPreferences(root.getContext());

        final boolean[] isSignIn = {true};



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://94.228.115.44:80/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TextView tvLogin = root.findViewById(R.id.loginTextView);
        TextView tvLoginMessage = root.findViewById(R.id.loginMessageTextView);

        Button btnSignIn = root.findViewById(R.id.btnSignIn);
        Button btnSignUp = root.findViewById(R.id.btnSignUp);

        EditText etLogin = root.findViewById(R.id.loginEditText);
        EditText etPassword = root.findViewById(R.id.passwordEditText);
        EditText etConfirmPassword = root.findViewById(R.id.passwordConfirmEditText);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSignIn[0])
                {
                    tvLogin.setText(getResources().getString(R.string.profile_sign_up));

                    btnSignIn.setText(getResources().getString(R.string.profile_sign_up));
                    btnSignUp.setText(getResources().getString(R.string.profile_sign_in));

                    etConfirmPassword.setVisibility(View.VISIBLE);

                    isSignIn[0] = false;
                }
                else {
                    tvLogin.setText(getResources().getString(R.string.profile_sign_in));

                    btnSignIn.setText(getResources().getString(R.string.profile_sign_in));
                    btnSignUp.setText(getResources().getString(R.string.profile_sign_up));

                    etConfirmPassword.setVisibility(View.INVISIBLE);

                    isSignIn[0] = true;

                }
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSignIn[0])
                {
                    // Log.d("ret", "LoginResponse : isSuccess");
                    MessagesApi messagesApi = retrofit.create(MessagesApi.class);
                    Call<LoginResponse> call = messagesApi.loginUser(new User(etLogin.getText().toString(),etPassword.getText().toString()));
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if(response.isSuccessful()){
                                // Log.d("ret", "LoginResponse : isSuccess");
                                if (response.body().getMessage().equals("Logined."))
                                {
                                    // Log.d("ret", "Status : logined");

                                    SharedPreferences.Editor myEditor = myPreferences.edit();
                                    myEditor.putString("jwt", response.body().getJwt());
                                    myEditor.putString("login",etLogin.getText().toString());
                                    myEditor.commit();


                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.fl_content, FragmentProfile.newInstance());
                                    fragmentTransaction.commit();

                                }
                                else
                                {
                                    Toast.makeText(getActivity(),"Wrong login or password!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                // Log.d("ret", "LoginResponse : isFalse");
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });
                }
                else {
                    if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString()) && etLogin.length() >= 4 && etPassword.length() >= 8) {
                        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
                        Call<ValidateResponse> call = messagesApi.createUser(new User(etLogin.getText().toString(), etPassword.getText().toString()));
                        call.enqueue(new Callback<ValidateResponse>() {
                            @Override
                            public void onResponse(Call<ValidateResponse> call, Response<ValidateResponse> response) {
                                if (response.isSuccessful()) {
                                    // Log.d("ret", "-----isSuccess----");
                                    if (response.body().getMessage().equals("User was created.")) {
                                        // Log.d("ret", "-----user created----");

                                        MessagesApi messagesApi = retrofit.create(MessagesApi.class);
                                        Call<LoginResponse> call2 = messagesApi.loginUser(new User(etLogin.getText().toString(), etPassword.getText().toString()));
                                        call2.enqueue(new Callback<LoginResponse>() {
                                            @Override
                                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                                if (response.isSuccessful()) {
                                                    // Log.d("ret", "-----isSuccess----");
                                                    if (response.body().getMessage().equals("Logined.")) {
                                                        // Log.d("ret", "-----logined----");

                                                        SharedPreferences.Editor myEditor = myPreferences.edit();
                                                        myEditor.putString("jwt", response.body().getJwt());
                                                        myEditor.putString("login", etLogin.getText().toString());
                                                        myEditor.commit();

                                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                                        fragmentTransaction.replace(R.id.fl_content, FragmentProfile.newInstance());
                                                        fragmentTransaction.commit();

                                                    } else {
                                                        // Log.d("ret", "not logined----");
                                                    }
                                                } else {
                                                    // Log.d("ret", "-----isFalse-----");
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<LoginResponse> call, Throwable t) {

                                            }
                                        });

                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.replace(R.id.fl_content, FragmentProfile.newInstance());
                                        fragmentTransaction.commit();

                                    } else {
                                        // Log.d("ret", "user not created----");
                                        tvLoginMessage.setText(response.body().getMessage());
                                    }
                                } else {
                                    // Log.d("ret", "-----isFalse-----");
                                }
                            }

                            @Override
                            public void onFailure(Call<ValidateResponse> call, Throwable t) {

                            }
                        });
                    }
                    else {
                        if(etLogin.length() < 4){
                            tvLoginMessage.setText("Login is short.");

                        }
                        else {
                            if(etPassword.length() < 8)
                                tvLoginMessage.setText("Password is short.");
                            else
                                tvLoginMessage.setText("Password mismatch.");
                        }

                    }
                }
                // Log.d("ret",myPreferences.getString("jwt", "unknown"));
            }
        });

        return root;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
