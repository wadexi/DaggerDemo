package com.example.a073105.daggerdemo.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.a073105.daggerdemo.R;
import com.example.a073105.daggerdemo.adapters.ContactAdapter;
import com.example.a073105.daggerdemo.beans.ContactData;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConstantFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConstantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConstantFragment extends Fragment {


    Context applicationContext;
    RecyclerView recyclerView;
    List<ContactData> mDatas = new ArrayList<>();

    private static final String TAG = "ConstantFragment";

    // The column index for the _ID column
    private static final int CONTACT_ID_INDEX = 0;
    // The column index for the CONTACT_KEY column
    private static final int CONTACT_KEY_INDEX = 1;

    private OnFragmentInteractionListener mListener;

    public ConstantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConstantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConstantFragment newInstance() {
        ConstantFragment fragment = new ConstantFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        applicationContext = getActivity().getApplicationContext();
        initView();


        Cursor descCursor = getActivity().getContentResolver().
                query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        new String[]{
                                ContactsContract.Contacts._ID,
                                ContactsContract.Contacts.LOOKUP_KEY,
                                ContactsContract.Contacts.PHOTO_URI,
                                ContactsContract.Contacts.DISPLAY_NAME,
                                ContactsContract.CommonDataKinds.Phone.NUMBER},
                        null,
                        null,
                        "desc");

        if (descCursor != null) {
            while (descCursor.moveToNext()) {
                String photoUri = descCursor.getString(descCursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                String name = descCursor.getString(descCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String photoNum = descCursor.getString(descCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.d(TAG, "onCreate: photoUri:" + photoUri + "  name:" + name + "  photoNum:" + photoNum);
            }
        }

    }

    /**
     * 初始化控件
     */
    private void initView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(applicationContext,LinearLayout.VERTICAL,false));
        recyclerView.setAdapter(new ContactAdapter(applicationContext,mDatas));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_constant, container, false);
        recyclerView = rootView.findViewById(R.id.contacts_recyclerview);
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void dataFromConstant(String uri);
    }
}
