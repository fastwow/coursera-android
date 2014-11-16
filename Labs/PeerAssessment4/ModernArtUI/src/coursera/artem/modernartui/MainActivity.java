package coursera.artem.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * 
 * @author Artem
 * 
 */
public class MainActivity extends Activity {

	public static final String MOMA_URL = "http://www.moma.org";

	private LinearLayout mYellowRecView;
	private LinearLayout mBlueRecView;
	private LinearLayout mCyanRecView;
	private SeekBar mBgSeekBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().setIcon(android.R.color.transparent);

		mYellowRecView = (LinearLayout) findViewById(R.id.yellow_rect);
		mYellowRecView.setBackgroundColor(Color.YELLOW);

		mBlueRecView = (LinearLayout) findViewById(R.id.blue_rect);
		mBlueRecView.setBackgroundColor(Color.BLUE);

		mCyanRecView = (LinearLayout) findViewById(R.id.cyan_rect);
		mCyanRecView.setBackgroundColor(Color.CYAN);

		mBgSeekBar = (SeekBar) findViewById(R.id.bg_seek_bar);
		mBgSeekBar
				.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {

						// Change rec color
						mYellowRecView.setBackgroundColor(Color.YELLOW
								+ progress);
						mBlueRecView.setBackgroundColor(Color.BLUE - progress);
						mCyanRecView.setBackgroundColor(Color.CYAN - progress);
					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {

					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {

					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.more_information:

			new AlertDialog.Builder(this)
					.setTitle(R.string.moma_tl)
					.setMessage(R.string.moma_mes)
					.setPositiveButton(R.string.visit_moma,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									Intent intent = new Intent(
											Intent.ACTION_VIEW, Uri
													.parse(MOMA_URL));
									startActivity(intent);
								}
							})
					.setNegativeButton(R.string.not_now,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									dialog.dismiss();
								}
							}).create().show();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
