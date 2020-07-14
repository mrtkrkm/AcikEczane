package muratk.mkeczane.MenuClasses;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import muratk.mkeczane.Fragments.AcilNumaralar;
import muratk.mkeczane.Fragments.NobetciEczane;
import muratk.eczanem.R;
import me.drakeet.materialdialog.MaterialDialog;


public class MenuActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private ArrayList<Fragment> fragments;
    private MaterialDialog mMaterialDialog;
    InterstitialAd mInterstitialAd;
    private int positionControl = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8034656764343756/4290933624");
        requestNewInterstitial();

        fragments = new ArrayList<Fragment>();

        fragments.add(new NobetciEczane());
        fragments.add(new AcilNumaralar());

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mToolbar.setTitle("Nöbetçi Eczaneler");
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        mNavigationDrawerFragment.setUserData("muratk Açık Eczanem", "Hangi eczaneler açık diye düşünme", BitmapFactory.decodeResource(getResources(), R.drawable.eczane_icon));
        mNavigationDrawerFragment.closeDrawer();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (position == 0){
            if (positionControl != 0){
                mToolbar.setTitle("Nöbetçi Eczaneler");
            }
            positionControl = 1;
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new NobetciEczane()).commit();
        }
        else if(position == 2){
            final String appPackageName = getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }
        else {
            mToolbar.setTitle("Acil Numaralar");
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragments.get(position)).commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else{
            mMaterialDialog = new MaterialDialog(new ContextThemeWrapper(MenuActivity.this, R.style.MyAlertDialog))
                    .setTitle("Uyarı")
                    .setMessage("Programdan çıkmak istiyor musunuz?")
                    .setPositiveButton("Evet", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                requestNewInterstitial();
                            }
                            mMaterialDialog.dismiss();
                            finish();
                        }
                    })
                    .setNegativeButton("Hayır", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });
            mMaterialDialog.show();
        }
    }
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch(keycode) {
            case KeyEvent.KEYCODE_MENU:
                if (mNavigationDrawerFragment.isDrawerOpen()){
                    mNavigationDrawerFragment.closeDrawer();
                }
                else {
                    mNavigationDrawerFragment.openDrawer();
                }
                return true;
        }

        return super.onKeyDown(keycode, e);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.menu, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
