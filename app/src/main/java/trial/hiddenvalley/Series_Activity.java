package trial.hiddenvalley;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

public class Series_Activity extends AppCompatActivity {

    private VideoView myVideoView;
    private int position = 0;
    private ProgressDialog progressDialog;
    private MediaController mediaControls;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        String appId = getResources().getString(R.string.facebook_app_id);

        AccessToken.setCurrentAccessToken(new AccessToken("727071327430501|4AW7t4T6WaJGdSaNkDB-zzyZ3Lw",appId,"727071327430501|4AW7t4T6WaJGdSaNkDB-zzyZ3Lw",null,null,null,null,null));

        setContentView(R.layout.activity_series);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ListView messageList = (ListView) findViewById(R.id.messageList);
        setSupportActionBar(toolbar);

        MessageListAdapter listAdapter = new MessageListAdapter(this);


        /* make the API call */
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "237644354159?fields=cover&format=json",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {

                        try {
                            String source = (String) response.getJSONObject().getJSONObject("cover").get("source");
                        }
                        catch (Exception e)
                        {

                        }
                    }
                }
        ).executeAsync();

        MessageManager.init();

        //Drawable background = Drawable.createFromPath("pic.jpg");

        messageList.setAdapter((ListAdapter) listAdapter);


        toolbar.setSubtitle("About");

        toolbar.setCollapsible(true);

        setListViewHeightBasedOnChildren(messageList);

        //tbl.setContentScrim(this.getResources().getDrawable(R.drawable.ic_fb_banner));

        //tbl.setBackground(background);

        //toolbar.setLogo(R.drawable.ic_fb_banner);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                String value = MessageManager.getMessage(0).videoURL;

                // = "http://hiddenv.com/filerequest/2413.mp4";
                Intent myIntent = new Intent(Series_Activity.this, VideoActivity.class);
                myIntent.putExtra("url", value); //Optional parameters
                Series_Activity.this.startActivity(myIntent);

            }
        });*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_series, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        if(id == R.id.action_donate){

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=K4VS6E7QW3S6Y&submit.x=62&submit.y=15"));
            startActivity(browserIntent);

        }


        return super.onOptionsItemSelected(item);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = (ListAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
           /* View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0,0);


*/
            totalHeight += context.getResources().getDimension(R.dimen.message_row_height);//listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight; //+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

        //((BaseAdapter)listAdapter).notifyDataSetChanged();

    }

}
