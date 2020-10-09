package org.lineageos.setupwizard;

import android.content.Intent;
import static org.lineageos.setupwizard.SetupWizardApp.ACTION_RESTORE_FROM_BACKUP;
import static org.lineageos.setupwizard.SetupWizardApp.REQUEST_CODE_RESTORE;

public class RestoreIntroActivity extends SubBaseActivity {

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (LOGV) {
            Log.d(TAG, "onCreate savedInstanceState=" + savedInstanceState);
        }
        super.onCreate(savedInstanceState);
        setNextText(R.string.skip);

        if (savedInstanceState == null) {
            onStartSubactivity();
        }
        //getIntent().getStringExtra("id");

        extras = getIntent().getExtras();
        if (extras != null) {
            Log.v(TAG, "Restore activity =" + );

            for (String key: extras.keySet()) {
                Log.e("RestoreActivity: ", key + " => " + extras.get(key) );
            }
        }
    }

    @Override
    protected void onStartSubactivity() {
        setNextAllowed(true);

        findViewById(R.id.intro_restore_button).setOnClickListener(v -> launchRestore());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.intro_restore_activity;
    }

    @Override
    protected int getTitleResId() {
        return R.string.intro_restore_title;
    }

    @Override
    protected int getIconResId() {
        return R.drawable.ic_restore;
    }

    @Override
    protected int getSubactivityNextTransition() {
        return TRANSITION_ID_SLIDE;
    }

    @Override
    public void onNavigateNext() {
        nextAction(ResultCodes.RESULT_SKIP);

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    private void launchRestore() {
        Intent intent = new Intent(ACTION_RESTORE_FROM_BACKUP);
        startSubactivity(intent, REQUEST_CODE_RESTORE);
    }

}
