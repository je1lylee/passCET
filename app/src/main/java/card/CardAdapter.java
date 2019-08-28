package card;

/**
 * Created by dell on 2019/8/28.
 */

import android.support.v7.widget.CardView;

import top.linxixiangxin.passcet.R;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
