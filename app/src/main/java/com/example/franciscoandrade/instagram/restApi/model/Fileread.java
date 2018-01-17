package com.example.franciscoandrade.instagram.restApi.model;

import android.content.Context;

/**
 * Created by franciscoandrade on 1/17/18.
 */

public final class Fileread {

static  Context context;

    public Fileread(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public Fileread() {
    }

//    public static String getFile(){
//        String data="";
//        StringBuffer stringBuffer= new StringBuffer();
//        InputStream is= context.getResources().openRawResource(R.raw.keyignore);
//        BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(is));
//        if (is!=null){
//            try{
//                while ((data= bufferedReader.readLine())!=null){
//                    stringBuffer.append(data);
//                }
//                Log.d("TEXT", "onCreateView: "+stringBuffer);
//                is.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        return stringBuffer.toString();
//    }


}
