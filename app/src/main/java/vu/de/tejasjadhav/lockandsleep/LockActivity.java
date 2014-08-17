package vu.de.tejasjadhav.lockandsleep;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class LockActivity extends Activity {
	private DevicePolicyManager mDevicePolicyManager;
	private ComponentName mComponentName;

	public static final int INTENT_REQUEST_ADMIN = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	    mDevicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
	    mComponentName = new ComponentName(this, AdminAccessReceiver.class);
	    boolean isAdminAccessGiven = mDevicePolicyManager.isAdminActive(mComponentName);

	    if (isAdminAccessGiven) {
		    mDevicePolicyManager.lockNow();
		    finish();
	    } else {
		    Intent requestAdminAccessIntent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
		    requestAdminAccessIntent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
		    requestAdminAccessIntent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Lock the phone");
		    startActivityForResult(requestAdminAccessIntent, INTENT_REQUEST_ADMIN);
	    }
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == INTENT_REQUEST_ADMIN && resultCode == RESULT_OK) {
			mDevicePolicyManager.lockNow();
			finish();
		}
	}
}
