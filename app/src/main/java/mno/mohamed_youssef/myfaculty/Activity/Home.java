package mno.mohamed_youssef.myfaculty.Activity;

import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.dialog.AddDoctor;
import mno.mohamed_youssef.myfaculty.dialog.AddInfo;
import mno.mohamed_youssef.myfaculty.dialog.AddLectures;
import mno.mohamed_youssef.myfaculty.dialog.AddNews;
import mno.mohamed_youssef.myfaculty.dialog.AddPlaces;
import mno.mohamed_youssef.myfaculty.dialog.AddSchedule;
import mno.mohamed_youssef.myfaculty.dialog.AddSection;
import mno.mohamed_youssef.myfaculty.dialog.AddSections;
import mno.mohamed_youssef.myfaculty.dialog.AddSubject;
import mno.mohamed_youssef.myfaculty.dialog.AddTeamInfo;
import mno.mohamed_youssef.myfaculty.dialog.ChooseDate;
import mno.mohamed_youssef.myfaculty.dialog.DeleteSection;
import mno.mohamed_youssef.myfaculty.fragment.DoctorsFragment;
import mno.mohamed_youssef.myfaculty.fragment.HomeFragment;
import mno.mohamed_youssef.myfaculty.fragment.InfoFragment;
import mno.mohamed_youssef.myfaculty.fragment.LocationsFragment;
import mno.mohamed_youssef.myfaculty.fragment.ManageFragment;
import mno.mohamed_youssef.myfaculty.fragment.News_Fragment;
import mno.mohamed_youssef.myfaculty.fragment.StudentSignUp;
import mno.mohamed_youssef.myfaculty.fragment.SubjectFragment;
import mno.mohamed_youssef.myfaculty.fragment.TeamInfoFragment;
import mno.mohamed_youssef.myfaculty.model.Database;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private AddNews addNews;
    private AddDoctor addDoctor;
    private AddSchedule addSchedule;
    private AddInfo addInfo;
    private AddTeamInfo addTeamInfo;
    private AddSubject  addSubject;
    private AddPlaces addPlaces;
    private Database database;
    private AddSections addSections;
    private AddLectures addLectures;
    private DeleteSection deleteSection;


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // if(FirebaseApp.getApps(this).isEmpty())
       // database = new Database(this);

        getFragmentManager().beginTransaction().replace(R.id.Home, new HomeFragment(),"Home").addToBackStack("Home").commit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
         drawer.setDrawerListener(toggle);
         toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



       drawer.openDrawer(GravityCompat.START);
        ///init_menu_nav(navigationView);



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentManager fm = getFragmentManager();

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
        } else {
                super.onBackPressed();
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.clear();
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.me){
           startActivity(new Intent(this,AboutMeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

   @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            getFragmentManager().beginTransaction().replace(R.id.Home, new News_Fragment(),"News").addToBackStack("News").commit();

        } else if (id == R.id.nav_schedule) {
            getFragmentManager().beginTransaction().replace(R.id.Home, new StudentSignUp(),"SingUp").commit();

        }else if (id == R.id.nav_instructor){
            getFragmentManager().beginTransaction().replace(R.id.Home, new DoctorsFragment(),"Doctors").addToBackStack("Doctors").commit();
        }else if (id == R.id.nav_subject){
            getFragmentManager().beginTransaction().replace(R.id.Home, new SubjectFragment(),"Subject").addToBackStack("Subject").commit();
        }else if (id == R.id.nav_locations){
            getFragmentManager().beginTransaction().replace(R.id.Home, new LocationsFragment(),"Location").addToBackStack("Location").commit();
        }else if (id == R.id.nav_info){
            getFragmentManager().beginTransaction().replace(R.id.Home, new InfoFragment(),"Info").addToBackStack("Info").commit();
        }
        else if (id == R.id.nav_team){
            getFragmentManager().beginTransaction().replace(R.id.Home, new TeamInfoFragment(),"TeamInfo").addToBackStack("TeamInfo").commit();
        }
        else {
            try {
                if (id == R.id.faculty_site) {
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Database.fsite)));

                } else if (id == R.id.natega) {
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Database.rsite)));
                } else if (id == R.id.faculty_Group) {
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Database.fGsite)));

                } else if (id == R.id.team_Group) {
                    startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Database.tsite)));

                } else if (id == R.id.faculty_sittings) {
                    getFragmentManager().beginTransaction().replace(R.id.Home, new ManageFragment(),"settings").addToBackStack("settings").commit();


                }
            }catch (Exception e){

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




    public boolean addNews(MenuItem item){


        addNews = new AddNews(this);
        addNews.show();

        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && requestCode==1){


            try {
                Uri imageUri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                addNews.setImage(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }



        else if(requestCode==2 && requestCode==2){


            try {
                Uri imageUri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                addTeamInfo.setImage(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        else if(requestCode==3 && requestCode==3){


            try {
                Uri imageUri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                addLectures.setImage(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        else if(requestCode==4 && requestCode==4){


            try {
                Uri imageUri = data.getData();

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                addSections.setImage(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }




    public boolean  chDay(MenuItem item){




        ChooseDate chooseDate = new ChooseDate(this);
        chooseDate.show();
        chooseDate.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                onStart();
            }
        });


        return true;

   }



    public boolean  addSection(MenuItem item){




        AddSection addSection = new AddSection(this);
        addSection.show();


        return true;

    }



    public boolean addDoctor(MenuItem item){


        addDoctor = new AddDoctor(this);
        addDoctor.show();

        return true;
    }




    public boolean addSchedule(MenuItem item){


        addSchedule = new AddSchedule(this,getFragmentManager());
        addSchedule.show();

        return true;
    }





    public boolean addInfo(MenuItem item){


        addInfo = new AddInfo(this);
        addInfo.show();

        return true;
    }



    public boolean addInfoTeam(MenuItem item){


        addTeamInfo = new AddTeamInfo(this);
        addTeamInfo.show();

        return true;
    }


    public boolean addSubject(MenuItem item){


        addSubject = new AddSubject(this);
        addSubject.show();

        return true;
    }
    public boolean addPlaces(MenuItem item){


        addPlaces = new AddPlaces(this);
        addPlaces.show();

        return true;
    }



    public boolean addSections(MenuItem item){


        addSections = new AddSections(this);
        addSections.show();

        return true;
    }



    public boolean addLectures(MenuItem item){


        addLectures = new AddLectures(this);
        addLectures.show();

        return true;
    }




    public boolean deleteSection(MenuItem item){


        deleteSection = new DeleteSection(this);
        deleteSection.show();

        return true;
    }

}



