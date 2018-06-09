package aksenchyk.doit.adapters;

import android.support.annotation.NonNull;

/**
 * Created by ixvar on 6/8/2018.
 */

public class UserId {

    public String userId;

    public <T extends UserId> T withId(@NonNull final String id) {

        this.userId = id;
        return (T) this;
    }

}
