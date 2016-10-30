package mno.mohamed_youssef.myfaculty.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import mno.mohamed_youssef.myfaculty.Activity.AboutMeActivity;
import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.dialog.GetUrl;
import mno.mohamed_youssef.myfaculty.model.Database;

public class ManageFragment extends Fragment {


    private RadioButton admin;
    private RadioButton editor;
    private Button addEditor;
    private Button removeEditor;
    private Button rsite;
    private Button tsite;
    private Button fsite;
    private Button fGsite;
    private SharedPreferences sharedPreferenceUser;
    private String password = "";
    private AutoCompleteTextView passView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //setSupportActionBar(toolbar);
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        admin = (RadioButton) view.findViewById(R.id.radioButtonadmin);
        editor = (RadioButton) view.findViewById(R.id.radioButtoneditor);

        addEditor = (Button) view.findViewById(R.id.buttonaddEditor);
        rsite = (Button) view.findViewById(R.id.editTextchangeResultSite);
        tsite = (Button) view.findViewById(R.id.editTextChangeSiteTam);
        fsite = (Button) view.findViewById(R.id.editTextchangeSiteF);
        fGsite = (Button) view.findViewById(R.id.editTextchangegroupF);


        admin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !sharedPreferenceUser.getString("admin","").equals(Database.adminPass)) {
                    editor.setChecked(false);
                    new DialogFragment() {
                        @Override
                        public Dialog onCreateDialog(Bundle savedInstanceState) {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                            // Get the layout inflater
                            final LayoutInflater inflater = getActivity().getLayoutInflater();

                            builder.setView(inflater.inflate(R.layout.get_password, null))
                                    // Add action buttons
                                    .setPositiveButton("دخول", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {

                                            passView = (AutoCompleteTextView) ((Dialog) dialog).findViewById(R.id.inputpassword);
                                            password = passView.getText().toString();
                                            if (Database.adminPass.equals(password)) {
                                                sharedPreferenceUser.edit().putString("admin", password).commit();
                                                Toast.makeText(getActivity(), "تم التسجيل بنجاح", Toast.LENGTH_LONG).show();


                                            } else {
                                                Toast.makeText(getActivity(), "لم يتم التسجيل", Toast.LENGTH_LONG).show();
                                                admin.setChecked(false);
                                            }
                                        }
                                    })
                                    .setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            getDialog().cancel();

                                        }
                                    });
                            return builder.create();
                        }

                    }.show(getFragmentManager(), "getPassword");

                }

            }
        });


        editor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !sharedPreferenceUser.getString("admin","").equals(Database.editorPass)) {

                    admin.setChecked(false);
                    new DialogFragment() {
                        @Override
                        public Dialog onCreateDialog(Bundle savedInstanceState) {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                            // Get the layout inflater
                            final LayoutInflater inflater = getActivity().getLayoutInflater();

                            builder.setView(inflater.inflate(R.layout.get_password, null))
                                    // Add action buttons
                                    .setPositiveButton("دخول", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {

                                            passView = (AutoCompleteTextView) ((Dialog) dialog).findViewById(R.id.inputpassword);
                                            password = passView.getText().toString();
                                            sharedPreferenceUser.edit().putString("admin", password).commit();
                                            if (Database.editorPass.equals(password)) {
                                                Toast.makeText(getActivity(), "تم التسجيل بنجاح", Toast.LENGTH_LONG).show();

                                            } else {
                                                Toast.makeText(getActivity(), "لم يتم التسجيل", Toast.LENGTH_LONG).show();
                                                editor.setChecked(false);

                                            }
                                        }
                                    })
                                    .setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            getDialog().cancel();
                                        }
                                    });
                            return builder.create();
                        }

                    }.show(getFragmentManager(), "getPassword");

                }
            }
        });


        if (sharedPreferenceUser.getString("admin", "").equals(Database.adminPass)) {
            admin.setChecked(true);
            addEditor.setClickable(true);
            addEditor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new DialogFragment() {
                        @Override
                        public Dialog onCreateDialog(Bundle savedInstanceState) {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                            // Get the layout inflater
                            final LayoutInflater inflater = getActivity().getLayoutInflater();

                            builder.setView(inflater.inflate(R.layout.get_password, null))
                                    // Add action buttons
                                    .setPositiveButton("تعديل", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int id) {

                                            passView = (AutoCompleteTextView) ((Dialog) dialog).findViewById(R.id.inputpassword);
                                            password = passView.getText().toString();
                                            Database.myRef.child("EditorPassword").setValue(password);
                                            Toast.makeText(getActivity(), "تم التعديل بنجاح", Toast.LENGTH_LONG).show();

                                        }
                                    })
                                    .setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            getDialog().cancel();
                                        }
                                    });
                            return builder.create();
                        }

                    }.show(getFragmentManager(), "getPassword");

                }
            });
        } else {
            addEditor.setClickable(false);

        }

        if (sharedPreferenceUser.getString("admin", "").equals(Database.editorPass)) {
            editor.setChecked(true);
        }

        if (sharedPreferenceUser.getString("admin","").equals(Database.adminPass)||
                sharedPreferenceUser.getString("admin","").equals(Database.editorPass)) {
            rsite.setClickable(true);
            tsite.setClickable(true);
            fsite.setClickable(true);
            fGsite.setClickable(true);


            rsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GetUrl getUrl = new GetUrl(getActivity(), "Rsite");
                    getUrl.show(getFragmentManager(), "rsite");

                }
            });

            tsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GetUrl getUrl = new GetUrl(getActivity(), "Tsite");
                    getUrl.show(getFragmentManager(), "tsite");
                }
            });

            fsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetUrl getUrl = new GetUrl(getActivity(), "Fsite");
                    getUrl.show(getFragmentManager(), "fsite");
                }
            });

            fGsite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GetUrl getUrl = new GetUrl(getActivity(), "FGsite");
                    getUrl.show(getFragmentManager(), "fGsite");
                }
            });

        } else {
            rsite.setClickable(false);
            tsite.setClickable(false);
            fsite.setClickable(false);
            fGsite.setClickable(false);
            addEditor.setClickable(false);

        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("الأعدادت");

    }

      @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.home, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
    if (id == R.id.me){
           startActivity(new Intent(getActivity(),AboutMeActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }


}
