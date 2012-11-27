package com.batontouch.createbaton;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.batontouch.main.R;

public class BatonCreate_PostATask2 extends Activity {
	private int hour;
	private int minute;
	private int year;
	private int month;
	private int day;
	static String pmAm = "오전 ";
	static final int TIME_DIALOG_ID2 = 1000;
	static final int TIME_DIALOG_ID = 999;
	static final int DATE_DIALOG_ID1 = 1002;
	static final int DATE_DIALOG_ID2 = 1001;
	private Button dateToAnswer, timeToAnswer, dateTobeDone, timeTobeDone;

	private EditText expectedtime;
	String fontPath = "fonts/NanumPen.ttf";
	private String spendtime, answerdate, donedate;
	private Bundle extras;

	// DateFormat formatDateTime = DateFormat.getDateTimeInstance();
	// Calendar dateTime = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Bundle 전달 받은 extras 저장
		Intent in = getIntent();
		extras = new Bundle();
		extras = in.getExtras();
		// ====

		setContentView(R.layout.batoncreate_postatask2);

		TextView expectTime = (TextView) findViewById(R.id.expectedTime1);
		TextView confirmTime = (TextView) findViewById(R.id.confirmTime);
		TextView finishDate = (TextView) findViewById(R.id.finishdate);
		Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
		expectTime.setTypeface(tf);
		confirmTime.setTypeface(tf);
		finishDate.setTypeface(tf);
		
		
		expectedtime = (EditText) findViewById(R.id.expectedtime);

		dateToAnswer = (Button) findViewById(R.id.dateToAnswer);
		timeToAnswer = (Button) findViewById(R.id.timeToAnswer);
		dateTobeDone = (Button) findViewById(R.id.dateTobeDone);
		timeTobeDone = (Button) findViewById(R.id.timeTobeDone);
		setCurrentTimeOnView();
		expectedtime.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					new AlertDialog.Builder(BatonCreate_PostATask2.this)
							.setItems(R.array.expecttime,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											String[] distance = getResources()
													.getStringArray(
															R.array.expecttime);

											expectedtime
													.setText(distance[which]
															+ "");
										}
									}).setNegativeButton("취소", null).show();
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(expectedtime.getWindowToken(),
							0);
					break;
				}
				return true;
			}
		});

	}

	// /////////////////////////////// DatePicker Dialog
	public void setCurrentTimeOnView() {

		// final Calendar c = Calendar.getInstance();
		// hour = c.get(Calendar.HOUR_OF_DAY);
		// minute = c.get(Calendar.MINUTE);
		// timePicker1.setCurrentHour(hour);
		// timePicker1.setCurrentMinute(minute);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		// dateToAnswer.setText(new StringBuilder().append(month + 1 + "월 ")
		// .append(day + "일").append(" "));
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener, hour, minute,
					false);
		case TIME_DIALOG_ID2:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener2, hour,
					minute, false);
		case DATE_DIALOG_ID1:
			// set time picker as current time
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		case DATE_DIALOG_ID2:
			// set time picker as current time
			return new DatePickerDialog(this, datePickerListener2, year, month,
					day);
		}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;

			convertToPm(hour);

			timeToAnswer.setText(new StringBuilder().append(pmAm)
					.append(pad(hour)).append("시").append(pad(minute))
					.append("분"));
			// set current time into timepicker
			// timePicker1.setCurrentHour(hour);
			// timePicker1.setCurrentMinute(minute);

		}
	};
	private TimePickerDialog.OnTimeSetListener timePickerListener2 = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;
			convertToPm(hour);
			timeTobeDone.setText(new StringBuilder().append(pmAm)
					.append(pad(hour)).append("시").append(pad(minute))
					.append("분"));
			// set current time into timepicker
			// timePicker1.setCurrentHour(hour);
			// timePicker1.setCurrentMinute(minute);

		}
	};

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			dateToAnswer.setText(new StringBuilder().append(month + 1 + "월")
					.append(day + "일").append(" "));

			// set selected date into datepicker also
			// dpResult.init(year, month, day, null);

		}
	};

	private DatePickerDialog.OnDateSetListener datePickerListener2 = new DatePickerDialog.OnDateSetListener() {
		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			dateTobeDone.setText(new StringBuilder().append(month + 1 + "월")
					.append(day + "일").append(" "));

			// set selected date into datepicker also
			// dpResult.init(year, month, day, null);

		}
	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

	public void convertToPm(int mhour) {
		if (mhour > 12) {
			hour = mhour - 12;
			pmAm = "오후 ";
		}
	}

	public void dateToAnswer(View v) {
		showDialog(DATE_DIALOG_ID1);
	}

	public void timeToAnswer(View v) {
		showDialog(TIME_DIALOG_ID);
	}

	public void dateTobeDone(View v) {
		showDialog(DATE_DIALOG_ID2);
	}

	public void timeTobeDone(View v) {
		showDialog(TIME_DIALOG_ID2);
	}

	public void nextBtnClick(View v) {
		spendtime = expectedtime.getText().toString();
		answerdate = dateToAnswer.getText().toString() + " "
				+ timeToAnswer.getText().toString();
		donedate = dateTobeDone.getText().toString() + " "
				+ timeTobeDone.getText().toString();

		Intent intent = new Intent(getApplicationContext(),
				BatonCreate_PostATask3.class);

		extras.putString("spendtime", spendtime);
		extras.putString("calldate", answerdate);
		extras.putString("enddate", donedate);
		intent.putExtras(extras);

		startActivity(intent);
	}
}
