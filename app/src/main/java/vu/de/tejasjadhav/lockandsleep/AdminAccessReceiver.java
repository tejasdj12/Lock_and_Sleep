package vu.de.tejasjadhav.lockandsleep;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

public class AdminAccessReceiver extends DeviceAdminReceiver {
    public AdminAccessReceiver() {
    }

	@Override
	public void onEnabled(Context context, Intent intent) {

	}
}
