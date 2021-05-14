package ru.startandroid.develop.cyberplayjava;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentItemNades extends Fragment{

    ArrayList<StateNade> states = new ArrayList();
    RecyclerView nadesList;
    private Context mContext;



    public FragmentItemNades() {

    }

    public static FragmentItemNades newInstance() { return new FragmentItemNades(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_item_nade, container, false);
        setInitialData();

        nadesList = (RecyclerView) root.findViewById(R.id.nadeList);
        StateNadeAdapter stateAdapter = new StateNadeAdapter(root.getContext(),states);
        nadesList.setLayoutManager(new GridLayoutManager(root.getContext(), 2));
        nadesList.setAdapter(stateAdapter);

        VideoView videoView = (VideoView) root.findViewById(R.id.videoPlayer);


        nadesList.addOnItemTouchListener(
                new RecyclerItemClickListener(root.getContext(), nadesList ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        StateNade sn = (StateNade) states.get(position);
                        videoView.setVideoURI(Uri.parse(sn.getVideoLink()));
                        Toast.makeText(getActivity(),"Был выбран пункт " + sn.getName() ,Toast.LENGTH_SHORT).show();
                        videoView.setMediaController( new MediaController(root.getContext()));

                        videoView.start();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        return root;
    }




    private void setInitialData(){
        states.add(new StateNade("ss",  R.drawable.de_dust2, "https://r11---sn-n8v7knez.googlevideo.com/videoplayback?expire=1620067983&ei=L_KPYN-RJdiCyQW-1oHgAg&ip=95.53.53.151&id=o-AC6iljnvFWpu1miMB85qJFTwrGaAjd0QgOGc77tASGtZ&itag=18&source=youtube&requiressl=yes&gcr=ru&vprv=1&mime=video%2Fmp4&ns=9WpT6zjUX3npe6QQQuPS0loF&gir=yes&clen=2576552&ratebypass=yes&dur=141.548&lmt=1607557709800247&fvip=1&fexp=24001373,24007246&c=WEB&txp=5530432&n=DSEs_CtmmvInsq73yKX&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cgcr%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRQIgNKamNFLMJYyVHpn_NzRjZLJTe11cVkoEa9k9cRAef_ICIQD7Rh4h40yayKHHypzgWVnwVlz-d6_shiEhWRzPcWlitA%3D%3D&rm=sn-gvnuxaxjvh-5gie7e,sn-gvnuxaxjvh-n8vk7e,sn-5gols7e&req_id=8f7951ac53e1a3ee&redirect_counter=3&cms_redirect=yes&ipbypass=yes&mh=Jd&mip=178.76.225.61&mm=30&mn=sn-n8v7knez&ms=nxu&mt=1620046100&mv=m&mvi=11&pl=19&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRQIgAelTxT1MZv150tZiHhRdOsgFZIGtzpp1SHVpVwdtfucCIQDf4eVjTjKrO8PFasU6wXVpV8B7pvtAv1cTMaPoL90XJA%3D%3D"));
        states.add(new StateNade("Inferno", R.drawable.de_inferno, "https://r4---sn-ug5onuxaxjvh-gv8e.googlevideo.com/videoplayback?expire=1620060909&ei=jdaPYNrRHouAW_v8koAM&ip=5.180.221.89&id=o-ACMC1I4n0_ZbdW1BX6OEnEL4R8E1p4G6jZKDVjBjjbQx&itag=18&source=youtube&requiressl=yes&vprv=1&mime=video%2Fmp4&ns=IBiGDLS4oQVpju7gAfzINJAF&gir=yes&clen=17031081&ratebypass=yes&dur=246.340&lmt=1550146165759722&fvip=4&fexp=24001373,24007246&c=WEB&txp=5531432&n=VtsXAu0fm5Z4cDyPa6M&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRgIhANBK2Ws4bxwzifh4tETNxGpyWQEPVVWxn8z0hdFXEUpOAiEAv4bdF1Ch7vtVkmFiXDiQ25YI3SrFC1uKPrdbVmxcrWo%3D&redirect_counter=1&rm=sn-aigez67e&req_id=5b3b2e83b36fa3ee&cms_redirect=yes&ipbypass=yes&mh=Mt&mip=178.76.225.61&mm=31&mn=sn-ug5onuxaxjvh-gv8e&ms=au&mt=1620046363&mv=m&mvi=4&pl=19&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRAIgCJxNVbNSK4nqF094voRHL5Yx8tbwvxbTjy3qKGtvHr0CIGrqdMnxWyuz36sjrQ6fzmlMDvy2I3b-4E5gA39puc2j"));
        states.add(new StateNade("Mirage", R.drawable.de_inferno, "link"));
        states.add(new StateNade("Train", R.drawable.de_inferno, "link"));
        states.add(new StateNade("Overpass", R.drawable.de_vertigo, "link"));
        states.add(new StateNade("Vertigo", R.drawable.de_vertigo, "link"));
        states.add(new StateNade("Nuke", R.drawable.de_nuke, "link"));
    }
}
