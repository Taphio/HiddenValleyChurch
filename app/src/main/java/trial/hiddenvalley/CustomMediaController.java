package trial.hiddenvalley;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;

/**
 * Created by Grant on 2/1/2016.
 */
public class CustomMediaController extends MediaController {

    private Context thisContext;
    public CustomMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMediaController(Context context, boolean useFastForward) {
        super(context, useFastForward);
    }

    public CustomMediaController(Context context) {
        this(context, true);
    }


    /*@Override
    public void show(int timeout)
    {
        //Do nothing
    }

    @Override
    public void hide()
    {
        //Do nothing
    }*/


}
