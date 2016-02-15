package trial.hiddenvalley;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Grant on 2/7/2016.
 */
public class MessageListAdapter extends BaseAdapter {

    Context context;
    private static LayoutInflater inflater = null;

    public MessageListAdapter(Context context)
    {
        this.context = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        int count = MessageManager.getCount();
        return count;
    }

    @Override
    public Object getItem(int position) {
        return MessageManager.getMessage(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Message mess = MessageManager.getMessage(position);

        View vi = convertView;

        if(vi == null)
            vi =  inflater.inflate(R.layout.row_message, null);

        TextView titleText = (TextView) vi.findViewById(R.id.messageTitle);
        TextView dateText = (TextView) vi.findViewById(R.id.messageDate);
        final ImageButton audioButton = (ImageButton) vi.findViewById(R.id.messageAudioButton);

        titleText.setText(mess.title);
        dateText.setText("" + mess.month + "/" + mess.day + "/" + mess.year);

        audioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(mess.audioURL), "audio/mp3");
                    context.startActivity(intent);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });

        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // = "http://hiddenv.com/filerequest/2413.mp4";
                Intent myIntent = new Intent(context, VideoActivity.class);
                myIntent.putExtra("url", mess.videoURL); //Optional parameters
                context.startActivity(myIntent);

            }
        });

        return vi;

    }
}
