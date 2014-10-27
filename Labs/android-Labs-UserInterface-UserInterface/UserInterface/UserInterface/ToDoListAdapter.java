package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import course.labs.todomanager.ToDoItem.Status;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private LayoutInflater mLayoutInflater;
	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mLayoutInflater = LayoutInflater.from(context);
	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();
	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);
	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder viewHolder = null;
		if (convertView == null) {

			convertView = (RelativeLayout) mLayoutInflater.inflate(
					R.layout.todo_item, null);

			viewHolder = new ViewHolder();

			viewHolder.dateView = (TextView) convertView
					.findViewById(R.id.dateView);
			viewHolder.titleView = (TextView) convertView
					.findViewById(R.id.titleView);
			viewHolder.statusView = (CheckBox) convertView
					.findViewById(R.id.statusCheckBox);
			viewHolder.priorityView = (TextView) convertView
					.findViewById(R.id.priorityView);

			convertView.setTag(viewHolder);
		} else {

			viewHolder = (ViewHolder) convertView.getTag();
		}

		final ToDoItem toDoItem = mItems.get(position);

		viewHolder.titleView.setText(toDoItem.getTitle());
		viewHolder.statusView.setChecked(toDoItem.getStatus() == Status.DONE);
		viewHolder.dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));
		viewHolder.statusView.setTag(Integer.valueOf(position));
		viewHolder.statusView
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						Log.i(TAG, "Entered onCheckedChanged()");

						Integer pos = (Integer) buttonView.getTag();
						ToDoItem doItem = mItems.get(pos);
						doItem.setStatus(isChecked ? ToDoItem.Status.DONE
								: ToDoItem.Status.NOTDONE);
					}
				});
		viewHolder.priorityView.setText(toDoItem.getPriority().toString());

		return convertView;
	}

	static class ViewHolder {

		TextView titleView = null;
		CheckBox statusView = null;
		TextView priorityView = null;
		TextView dateView = null;
	}
}